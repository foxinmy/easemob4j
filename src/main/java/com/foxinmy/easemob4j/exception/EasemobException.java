package com.foxinmy.easemob4j.exception;

/**
 * 调用接口抛出的异常
 * 
 * @className EasemodException
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.6
 * @see
 */
public class EasemobException extends Exception {

	private static final long serialVersionUID = 1L;

	private String error;
	private String desc;

	public EasemobException(String error, String desc) {
		this.error = error;
		this.desc = desc;
	}

	public EasemobException(String desc) {
		this.error = "-1";
		this.desc = desc;
	}

	public String getError() {
		return error;
	}

	public String getErrorDesc() {
		return desc;
	}

	@Override
	public String getMessage() {
		return error + "," + desc;
	}
}
