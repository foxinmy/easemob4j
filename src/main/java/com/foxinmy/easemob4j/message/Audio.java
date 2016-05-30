package com.foxinmy.easemob4j.message;

import com.foxinmy.easemob4j.type.MessageType;

/**
 * 语音消息:需要先上传语音文件，然后再发送此消息。（url中的uuid和secret可以从上传后的response获取）
 * 
 * @className Audio
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年3月18日
 * @since JDK 1.6
 * @see
 */
public class Audio extends Notify {

	private static final long serialVersionUID = 7515160819198857752L;

	private String url; // 上传语音远程地址，在上传语音后会返回uuid
	private String filename; // 语音名称
	private int length; // 语音时间（单位秒）
	private String secret;// secret在上传文件后会返回

	public Audio() {
		super(MessageType.audio);
	}

	public Audio(String url, String filename, int length, String secret) {
		super(MessageType.audio);
		this.url = url;
		this.filename = filename;
		this.length = length;
		this.secret = secret;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public String toString() {
		return "Audio [url=" + url + ", filename=" + filename + ", length="
				+ length + ", secret=" + secret + "]";
	}

}
