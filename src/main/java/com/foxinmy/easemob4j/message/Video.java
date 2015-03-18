package com.foxinmy.easemob4j.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.easemob4j.type.MessageType;

/**
 * 视频消息:需要先上传视频文件和视频缩略图文件，然后再发送此消息。（url中的uuid和secret可以从上传后的response获取）
 * 
 * @className Video
 * @author jy
 * @date 2015年3月18日
 * @since JDK 1.7
 * @see
 */
public class Video extends Notify {

	private static final long serialVersionUID = 145374184730238600L;

	private String filename; // 视频文件名称
	private String thumb; // 成功上传视频缩略图返回的uuid
	private String secret; // 成功上传视频文件后返回的secret
	@JSONField(name = "thumb_secret")
	private String thumbSecret; // 成功上传视频缩略图后返回的secret
	private String url; // 成功上传视频文件返回的uuid
	private int length;// 视频播放长度
	@JSONField(name = "file_length")
	private int fileLength;// 视频文件大小

	public Video() {
		super(MessageType.video);
	}

	public Video(String filename, String thumb, String secret, String url) {
		super(MessageType.video);
		this.filename = filename;
		this.thumb = thumb;
		this.secret = secret;
		this.url = url;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getThumbSecret() {
		return thumbSecret;
	}

	public void setThumbSecret(String thumbSecret) {
		this.thumbSecret = thumbSecret;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getFileLength() {
		return fileLength;
	}

	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}

	@Override
	public String toString() {
		return "Video [filename=" + filename + ", thumb=" + thumb + ", secret="
				+ secret + ", thumbSecret=" + thumbSecret + ", url=" + url
				+ ", length=" + length + ", fileLength=" + fileLength + "]";
	}
}
