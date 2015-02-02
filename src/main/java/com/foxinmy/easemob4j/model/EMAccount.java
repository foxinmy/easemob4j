package com.foxinmy.easemob4j.model;

import java.io.Serializable;

/**
 * 环信账号信息
 * 
 * @className EMAccount
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class EMAccount implements Serializable {

	private static final long serialVersionUID = 8859714152157524019L;
	private String orgName; // 企业的唯一标识,开发者在环信开发者管理后台注册账号时填写的企业ID
	private String appName; // 同一”企业”下”app”唯一标识,开发者在环信开发者管理后台创建应用时填写的”应用名称”
	private String orgAdmin; // 开发者在环信开发者管理后台注册时填写的”用户名”.企业管理员拥有对该企业账号下所有资源的操作权限
	private String appAdmin; // 应用管理员,具有admin权限的一个特殊IM用户，拥有对该应用下所有资源的操作权限
	private String clientId;
	private String clientSecret;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getOrgAdmin() {
		return orgAdmin;
	}

	public void setOrgAdmin(String orgAdmin) {
		this.orgAdmin = orgAdmin;
	}

	public String getAppAdmin() {
		return appAdmin;
	}

	public void setAppAdmin(String appAdmin) {
		this.appAdmin = appAdmin;
	}

	public String getAppKey() {
		return String.format("%s#%s", orgName, appName);
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	@Override
	public String toString() {
		return "EMAccount [orgName=" + orgName + ", appName=" + appName
				+ ", orgAdmin=" + orgAdmin + ", appAdmin=" + appAdmin
				+ ", appKey=" + getAppKey() + ", clientId=" + clientId
				+ ", clientSecret=" + clientSecret + "]";
	}
}
