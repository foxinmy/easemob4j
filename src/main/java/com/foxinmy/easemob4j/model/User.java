package com.foxinmy.easemob4j.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @className User
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.6
 * @see
 */
public class User implements Serializable {

	private static final long serialVersionUID = -4696349242637444865L;

	@JSONField(name = "username")
	private String uname;
	private String password;

	public User() {
	}

	public User(String uname, String password) {
		this.uname = uname;
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [uname=" + uname + ", password=" + password + "]";
	}

}
