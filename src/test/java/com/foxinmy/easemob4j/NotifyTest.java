package com.foxinmy.easemob4j;

import org.junit.Before;
import org.junit.Test;

import com.foxinmy.easemob4j.api.NotifyApi;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.message.Notify;
import com.foxinmy.easemob4j.message.NotifyMessage;
import com.foxinmy.easemob4j.message.Text;
import com.foxinmy.easemob4j.type.TargetType;

/**
 * 消息相关测试
 * 
 * @className NotifyTest
 * @author jy
 * @date 2015年03月18日
 * @since JDK 1.7
 * @see
 */
public class NotifyTest extends TokenTest {
	private NotifyApi notifyApi;

	@Before
	public void init() {
		notifyApi = new NotifyApi(tokenHolder);
	}

	@Test
	public void sendNotify() throws EasemobException {
		Notify notify = new Text("test...");
		NotifyMessage message = new NotifyMessage(TargetType.users, notify, "1");
		message.setFrom("2");
		System.err.println(notifyApi.sendNotify(message));
	}
}
