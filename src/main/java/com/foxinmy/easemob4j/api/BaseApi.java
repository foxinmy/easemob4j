package com.foxinmy.easemob4j.api;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.Consts;
import com.foxinmy.weixin4j.http.ContentType;
import com.foxinmy.weixin4j.http.Header;
import com.foxinmy.weixin4j.http.HttpClient;
import com.foxinmy.weixin4j.http.HttpPost;
import com.foxinmy.weixin4j.http.HttpResponse;
import com.foxinmy.weixin4j.http.HttpStatus;
import com.foxinmy.weixin4j.http.SimpleHttpClient;
import com.foxinmy.weixin4j.http.entity.StringEntity;

/**
 * 
 * @className BaseApi
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class BaseApi {
	protected final HttpClient httpClient = new SimpleHttpClient();
	private final static ResourceBundle easemobBundle;
	static {
		easemobBundle = ResourceBundle
				.getBundle("com/foxinmy/easemob4j/api/easemob");
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
		HttpPost request = new HttpPost(url);
		if (token != null && !token.trim().isEmpty()) {
			request.addHeader(new Header("Authorization", String.format(
					"Bearer %s", token)));
		}
		if (body != null && !body.trim().isEmpty()) {
			request.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
		}
		try {
			HttpResponse response = httpClient.execute(request);
			byte[] bytes = response.getContent();
			JSONObject result = JSON.parseObject(bytes, 0, bytes.length,
					Consts.UTF_8.newDecoder(), JSONObject.class);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new EasemobException(result.getString("error"),
						result.getString("error_description"));
			}
			return result;
		} catch (IOException e) {
			throw new EasemobException(e.getMessage());
		}
	}
}
