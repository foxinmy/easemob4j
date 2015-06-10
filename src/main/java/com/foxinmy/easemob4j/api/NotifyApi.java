package com.foxinmy.easemob4j.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.message.NotifyMessage;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.easemob4j.token.Token;
import com.foxinmy.easemob4j.token.TokenHolder;

/**
 * 消息API
 * 
 * @className NotifyApi
 * @author jy
 * @date 2015年3月18日
 * @since JDK 1.7
 * @see <a href="http://www.easemob.com/docs/rest/sendmessage/">发送消息</a>
 */
public class NotifyApi extends BaseApi {
	private final TokenHolder tokenHolder;
	private final EMAccount account;

	public NotifyApi(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
		this.account = tokenHolder.getAccount();
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 *            消息对象
	 * @return 操作结果 { "stliu1": "success", "jma3": "success", "stliu":
	 *         "success", "jma4": "success" }
	 * @see com.foxinmy.easemob4j.message.NotifyMessage
	 * @see com.foxinmy.easemob4j.message.Text
	 * @see <a href="http://www.easemob.com/docs/rest/sendmessage/">发送消息</a>
	 * @throws EasemobException
	 */
	public JSONObject sendNotify(NotifyMessage message) throws EasemobException {
		String send_message_url = String.format(
				getRequestUri("send_message_url"), account.getOrgName(),
				account.getAppName());
		Token token = tokenHolder.getToken();
		JSONObject response = post(send_message_url, token.getAccessToken(),
				JSON.toJSONString(message));
		return response.getJSONObject("data");
	}
}
