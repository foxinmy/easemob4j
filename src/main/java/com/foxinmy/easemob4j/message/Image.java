package com.foxinmy.easemob4j.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.easemob4j.type.MessageType;

/**
 * 图片消息:给一个或者多个用户, 或者一个或者多个群组发送消息, 并且通过可选的 from 字段让接收方看到发送方是不同的人,同时, 支持扩展字段, 通过
 * ext 属性, app可以发送自己专属的消息结构.
 * 
 * @className Image
 * @author jy
 * @date 2015年3月18日
 * @since JDK 1.7
 * @see
 */
public class Image extends Notify {

	private static final long serialVersionUID = 7628089074103900471L;

	private String url; // 上传图片消息地址,在上传图片成功后会返回uuid
	private String filename; // 图片名称
	private String secret;// secret在上传图片后会返回，只有含有secret才能够下载此图片
	@JSONField(serialize = false)
	private String thumb; // 上传缩略图地址
	@JSONField(serialize = false, name = "thumb_secret")
	private String thumbSecret; // thumb_secret在上传缩略图后会返回

	public Image() {
		super(MessageType.img);
	}

	public Image(String url, String filename, String secret) {
		super(MessageType.img);
		this.url = url;
		this.filename = filename;
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

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getThumbSecret() {
		return thumbSecret;
	}

	public void setThumbSecret(String thumbSecret) {
		this.thumbSecret = thumbSecret;
	}

	@Override
	public String toString() {
		return "Image [url=" + url + ", filename=" + filename + ", secret="
				+ secret + ", thumb=" + thumb + ", thumbSecret=" + thumbSecret
				+ "]";
	}
}
