package com.foxinmy.easemob4j.message;

import com.foxinmy.easemob4j.type.MessageType;

/**
 * 透传消息：不会在客户端提示（铃声，震动，通知栏等），但可以在客户端监听到的消息推送，具体功能可以根据自身自定义
 * 
 * @className Command
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年3月18日
 * @since JDK 1.6
 * @see
 */
public class Command extends Notify {

	private static final long serialVersionUID = -7338226343067960714L;

	private String action;

	public Command() {
		super(MessageType.cmd);
	}

	public Command(String action) {
		super(MessageType.cmd);
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "Command [action=" + action + "]";
	}
}
