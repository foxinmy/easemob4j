package com.foxinmy.easemob4j.api;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.foxinmy.easemob4j.http.HttpRequest;

/**
 * 
 * @className BaseApi
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class BaseApi {
	protected final HttpRequest request = new HttpRequest();
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
}
