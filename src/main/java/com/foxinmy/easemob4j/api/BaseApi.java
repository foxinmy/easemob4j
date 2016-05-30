package com.foxinmy.easemob4j.api;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.weixin4j.http.ContentType;
import com.foxinmy.weixin4j.http.HttpClient;
import com.foxinmy.weixin4j.http.HttpClientException;
import com.foxinmy.weixin4j.http.HttpHeaders;
import com.foxinmy.weixin4j.http.HttpMethod;
import com.foxinmy.weixin4j.http.HttpRequest;
import com.foxinmy.weixin4j.http.HttpResponse;
import com.foxinmy.weixin4j.http.HttpStatus;
import com.foxinmy.weixin4j.http.entity.StringEntity;
import com.foxinmy.weixin4j.http.factory.HttpClientFactory;
import com.foxinmy.weixin4j.logging.InternalLogger;
import com.foxinmy.weixin4j.logging.InternalLoggerFactory;
import com.foxinmy.weixin4j.util.StringUtil;

/**
 *
 * @className BaseApi
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月28日
 * @since JDK 1.6
 * @see
 */
public class BaseApi {
	protected final InternalLogger logger = InternalLoggerFactory
			.getInstance(getClass());

	protected final HttpClient httpClient;
	protected final EMAccount account;
	private final static ResourceBundle easemobBundle;
	static {
		easemobBundle = ResourceBundle
				.getBundle("com/foxinmy/easemob4j/api/easemob");
	}

	public BaseApi(EMAccount account) {
		this.account = account;
		this.httpClient = HttpClientFactory.getInstance();
	}

	protected String getRequestUri(String key) {
		String url = easemobBundle.getString(key);
		Pattern p = Pattern.compile("(\\{[^\\}]*\\})");
		Matcher m = p.matcher(url);
		StringBuffer sb = new StringBuffer();
		String sub = null;
		while (m.find()) {
			sub = m.group();
			m.appendReplacement(sb,
					getRequestUri(sub.substring(1, sub.length() - 1)));
		}
		m.appendTail(sb);
		return sb.toString();
	}

	protected String getRequestUri0(String key) {
		return String.format(getRequestUri(key), account.getOrgName(),
				account.getAppName());
	}

	protected String getRequestUri1(String key, String... params) {
		Object[] _params = new Object[params.length + 2];
		_params[0] = account.getOrgName();
		_params[1] = account.getAppName();
		System.arraycopy(params, 0, _params, 2, params.length);
		return String.format(getRequestUri(key), _params);
	}

	protected JSONObject get(String url, String token) throws EasemobException {
		HttpRequest request = new HttpRequest(HttpMethod.GET, url);
		return execute(request, token, null);
	}

	protected JSONObject post(String url, String token, String body)
			throws EasemobException {
		HttpRequest request = new HttpRequest(HttpMethod.POST, url);
		return execute(request, token, body);
	}

	protected JSONObject execute(HttpRequest request, String token, String body)
			throws EasemobException {
		if (token != null && !token.trim().isEmpty()) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", String.format("Bearer %s", token));
			request.setHeaders(headers);
		}
		if (body != null && !body.trim().isEmpty()) {
			request.setEntity(new StringEntity(body,
					ContentType.APPLICATION_JSON));
		}
		try {
			logger.info("weixin request >> " + request.getMethod() + " "
					+ request.getURI().toString());
			HttpResponse response = httpClient.execute(request);
			JSONObject result = JSON.parseObject(StringUtil
					.newStringUtf8(response.getContent()));
			logger.info("weixin response << " + response.getProtocol()
					+ response.getStatus() + ":" + result);
			if (response.getStatus().getStatusCode() != HttpStatus.SC_OK) {
				throw new EasemobException(result.getString("error"),
						result.getString("error_description"));
			}
			return result;
		} catch (HttpClientException e) {
			throw new EasemobException(e.getMessage());
		}
	}
}
