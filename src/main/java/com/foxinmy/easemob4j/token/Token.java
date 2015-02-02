package com.foxinmy.easemob4j.token;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 访问TOKEN
 * 
 * @className Token
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class Token implements Serializable {

	private static final long serialVersionUID = -7564855472419104084L;

	@JSONField(name = "access_token")
	private String accessToken; // token值
	@JSONField(name = "expires_in")
	private int expiresIn; // 有效时间,秒为单位, 默认是七天,在有效期内是不需要重复获取的
	private String application; // 当前app的UUID值
	private long time; // 创建时间 可能为0

	public Token() {

	}

	public Token(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Token [accessToken=" + accessToken + ", expiresIn=" + expiresIn
				+ ", application=" + application + ", time=" + time + "]";
	}
}
