package com.foxinmy.easemob4j.model;

import java.nio.charset.Charset;

/**
 * 常量类
 * 
 * @className Consts
 * @author jy
 * @date 2014年12月3日
 * @since JDK 1.6
 * @see
 */
public final class Consts {
	public static final Charset UTF_8 = Charset.forName("UTF-8");
	/**
	 * 使用FileTokenStorager时token的存放路径
	 */
	public static final String DEFAULT_TOKEN_PATH = "/tmp/weixin4j/token";
	/**
	 * token获取uri
	 */
	public static final String ASSESS_TOKEN_URL = "https://a1.easemob.com/%s/%s/token";
}
