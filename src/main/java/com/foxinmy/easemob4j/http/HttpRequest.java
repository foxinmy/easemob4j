package com.foxinmy.easemob4j.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.easemob4j.exception.EasemobException;

/**
 * request
 * 
 * @className HttpRequest
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class HttpRequest {

	protected AbstractHttpClient client;

	public HttpRequest() {
		this(150, 100, 10000, 10000);
	}

	public HttpRequest(int maxConPerRoute, int maxTotal, int socketTimeout,
			int connectionTimeout) {
		PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager();
		// 指定IP并发最大数
		connectionManager.setDefaultMaxPerRoute(maxConPerRoute);
		// socket最大创建数
		connectionManager.setMaxTotal(maxTotal);

		client = new DefaultHttpClient(connectionManager);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				socketTimeout);
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
		client.getParams().setBooleanParameter(
				CoreConnectionPNames.TCP_NODELAY, false);
		client.getParams().setParameter(
				CoreConnectionPNames.SOCKET_BUFFER_SIZE, 1024 * 1024);
		client.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.IGNORE_COOKIES);
		client.getParams().setParameter(
				CoreProtocolPNames.HTTP_CONTENT_CHARSET, Consts.UTF_8);
		client.getParams().setParameter(HttpHeaders.CONTENT_ENCODING,
				Consts.UTF_8);
		client.getParams().setParameter(HttpHeaders.ACCEPT_CHARSET,
				Consts.UTF_8);

		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { xtm }, null);
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
			client.getConnectionManager().getSchemeRegistry()
					.register(new Scheme("https", 443, socketFactory));
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
			throw new RuntimeException("create tls fail");
		}
	}

	public Response post(String url, String token, String body)
			throws EasemobException {
		HttpPost method = new HttpPost(url);
		if (token != null && !token.trim().isEmpty()) {
			method.addHeader("Authorization", String.format("Bearer %s", token));
		}
		if (body != null && !body.trim().isEmpty()) {
			method.setEntity(new StringEntity(body, ContentType.create(
					ContentType.APPLICATION_JSON.getMimeType(), Consts.UTF_8)));
		}
		return doRequest(method);
	}

	protected Response doRequest(HttpRequestBase request)
			throws EasemobException {
		Response response = null;
		try {
			HttpResponse httpResponse = client.execute(request);
			StatusLine statusLine = httpResponse.getStatusLine();
			HttpEntity httpEntity = httpResponse.getEntity();
			int status = statusLine.getStatusCode();
			byte[] data = EntityUtils.toByteArray(httpEntity);
			response = new Response();
			response.setBody(data);
			response.setStatusCode(status);
			response.setStatusText(statusLine.getReasonPhrase());
			response.setStream(new ByteArrayInputStream(data));
			response.setText(new String(data, Consts.UTF_8));
			EntityUtils.consume(httpEntity);
			if (status != HttpStatus.SC_OK) {
				JSONObject errorJson = response.getAsJson();
				throw new EasemobException(errorJson.getString("error"),
						errorJson.getString("error_description"));
			}
		} catch (IOException e) {
			throw new EasemobException(e.getMessage());
		} finally {
			request.releaseConnection();
		}
		return response;
	}
}
