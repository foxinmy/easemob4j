package com.foxinmy.easemob4j.api;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.Consts;
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

/**
 * 
 * @className BaseApi
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.6
 * @see
 */
public class BaseApi {
	protected final HttpClient httpClient;
	private final static ResourceBundle easemobBundle;
	static {
		easemobBundle = ResourceBundle
				.getBundle("com/foxinmy/easemob4j/api/easemob");
	}
	
	public BaseApi(){
		httpClient  = HttpClientFactory.getInstance();
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

	protected JSONObject post(String url, String token, String body)
			throws EasemobException {
		HttpRequest request = new HttpRequest(HttpMethod.POST, url);
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
			HttpResponse response = httpClient.execute(request);
			byte[] bytes = response.getContent();
			JSONObject result = JSON.parseObject(bytes, 0, bytes.length,
					Consts.UTF_8.newDecoder(), JSONObject.class);
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
