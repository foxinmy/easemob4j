package com.foxinmy.easemob4j.exception;

import com.foxinmy.weixin4j.exception.WeixinException;

/**
 * 调用接口抛出的异常
 * 
 * @className EasemodException
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.6
 * @see
 */
public class EasemobException extends WeixinException {

	private static final long serialVersionUID = 1L;

	public EasemobException(String error, String desc) {
		super(error, desc);
	}

	public EasemobException(String desc) {
		super(desc);
	}
}
