package com.foxinmy.easemob4j.http;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 调用接口返回结果
 * 
 * @className ApiResult
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class ApiResult implements Serializable {

	private static final long serialVersionUID = -6185313616955051150L;

	private String action;
	private String application;
	private JSONObject params;
	private String path;
	private String uri;
	private long timestamp;
	private int duration;
	private String organization;
	private String applicationName;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public JSONObject getParams() {
		return params;
	}

	public void setParams(JSONObject params) {
		this.params = params;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	@Override
	public String toString() {
		return "ApiResult [action=" + action + ", application=" + application
				+ ", params=" + params + ", path=" + path + ", uri=" + uri
				+ ", timestamp=" + timestamp + ", duration=" + duration
				+ ", organization=" + organization + ", applicationName="
				+ applicationName + "]";
	}
}
