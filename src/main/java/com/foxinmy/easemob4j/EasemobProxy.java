package com.foxinmy.easemob4j;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.easemob4j.api.ApiResult;
import com.foxinmy.easemob4j.api.NotifyApi;
import com.foxinmy.easemob4j.api.UserApi;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.message.NotifyMessage;
import com.foxinmy.easemob4j.model.User;
import com.foxinmy.easemob4j.token.FileTokenHolder;
import com.foxinmy.easemob4j.token.TokenHolder;

/**
 * 环信API实现
 * 
 * @className EasemobProxy
 * @author jy
 * @date 2015年1月29日
 * @since JDK 1.7
 * @see <a href="http://www.easemob.com/docs/rest/">环信API</a>
 */
public class EasemobProxy {

	private final UserApi userApi;
	private final NotifyApi notifyApi;

	public EasemobProxy() {
		this(new FileTokenHolder());
	}

	public EasemobProxy(TokenHolder tokenHolder) {
		userApi = new UserApi(tokenHolder);
		notifyApi = new NotifyApi(tokenHolder);
	}

	/**
	 * 创建用户
	 * 
	 * @param user
	 *            单个或者多个用户
	 * @return 操作结果
	 * @see com.foxinmy.easemob4j.api.UserApi
	 * @see <a
	 *      href="http://www.easemob.com/docs/rest/userapi/#im">创建单个或者多个用户</a>
	 * @throws EasemobException
	 */
	public ApiResult createUser(User... user) throws EasemobException {
		return userApi.createUser(user);
	}

	/**
	 * 添加好友
	 * 
	 * @param ownerName
	 *            我的昵称
	 * @param friendName
	 *            好友昵称
	 * @return 操作结果
	 * @see com.foxinmy.easemob4j.api.UserApi
	 * @see <a
	 *      href="http://www.easemob.com/docs/rest/userapi/#contactsfriend">添加好友</a>
	 * @throws EasemobException
	 */
	public ApiResult contactFriend(String ownerName, String friendName)
			throws EasemobException {
		return userApi.contactFriend(ownerName, friendName);
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 *            消息对象
	 * @return 操作结果 { "stliu1": "success", "jma3": "success", "stliu":
	 *         "success", "jma4": "success" }
	 * @see com.foxinmy.easemob4j.api.NotifyApi
	 * @see com.foxinmy.easemob4j.message.NotifyMessage
	 * @see com.foxinmy.easemob4j.message.Text
	 * @see <a href="http://www.easemob.com/docs/rest/sendmessage/">发送消息</a>
	 * @throws EasemobException
	 */
	public JSONObject sendNotify(NotifyMessage message) throws EasemobException {
		return notifyApi.sendNotify(message);
	}
}
