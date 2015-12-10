package com.foxinmy.easemob4j.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.easemob4j.type.MessageType;

/**
 * 文本消息:给一个或者多个用户, 或者一个或者多个群组发送消息, 并且通过可选的 from 字段让接收方看到发送方是不同的人,同时, 支持扩展字段, 通过
 * ext 属性, app可以发送自己专属的消息结构.
 * 
 * @className Text
 * @author jy
 * @date 2015年3月18日
 * @since JDK 1.6
 * @see
 */
public class Text extends Notify {

	private static final long serialVersionUID = -6210351891828222350L;

	@JSONField(name = "msg")
	private String content;

	public Text() {
		super(MessageType.txt);
	}

	public Text(String content) {
		super(MessageType.txt);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Text [content=" + content + "]";
	}
}
