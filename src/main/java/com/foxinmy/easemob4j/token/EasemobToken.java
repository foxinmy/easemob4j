package com.foxinmy.easemob4j.token;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.weixin4j.model.Token;

/**
 * 管理员Token
 *
 * @className EasemobToken
 * @author jinyu(foxinmy@gmail.com)
 * @date 2014年4月5日
 * @since JDK 1.6
 * @see <a href="http://docs.easemob.com/im/100serverintegration/20users">获取 APP
 *      管理员 token</a>
 */
public class EasemobToken extends Token {

	private static final long serialVersionUID = -7564855472419104084L;
	/**
	 * 当前 APP 的 UUID 值
	 */
	@JSONField(name = "application")
	private String application;

	public String getApplication() {
		return application;
	}

	@JSONCreator
	public EasemobToken(@JSONField(name = "accessToken") String accessToken,
			@JSONField(name = "expires") long expires,
			@JSONField(name = "application") String application) {
		super(accessToken, expires);
	}

	@Override
	public String toString() {
		return "EasemobToken [application=" + application + ", "
				+ super.toString() + "]";
	}
}
