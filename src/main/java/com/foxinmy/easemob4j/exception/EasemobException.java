package com.foxinmy.easemob4j.exception;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.util.StringUtil;

/**
 * 调用接口抛出的异常
 *
 * @className EasemodException
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月28日
 * @since JDK 1.6
 * @see
 */
public class EasemobException extends WeixinException {

	private static final long serialVersionUID = -2886846452743448673L;

	public EasemobException(String code, String desc) {
		super(code, desc);
	}

	public EasemobException(String message, Throwable cause) {
		super(message, cause);
	}

	public EasemobException(String desc) {
		super(desc);
	}

	public EasemobException(Throwable e) {
		super(e);
	}

	@Override
	public String getMessage() {
		if (StringUtil.isNotBlank(getErrorCode())) {
			return new StringBuilder().append(getErrorCode()).append(" >> ")
					.append(getErrorDesc()).toString();
		} else {
			return super.getMessage();
		}
	}
}
