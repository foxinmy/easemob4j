package com.foxinmy.easemob4j;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.easemob4j.api.ApiResult;
import com.foxinmy.easemob4j.api.ArrayResult;
import com.foxinmy.easemob4j.api.NotifyApi;
import com.foxinmy.easemob4j.api.UserApi;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.message.ChatMessage;
import com.foxinmy.easemob4j.message.NotifyMessage;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.easemob4j.model.MultSearcher;
import com.foxinmy.easemob4j.model.User;
import com.foxinmy.easemob4j.token.EasemobTokenCreator;
import com.foxinmy.easemob4j.token.EasemobTokenManager;
import com.foxinmy.easemob4j.util.Easemob4jSettings;

/**
 * 环信API实现
 *
 * @className EasemobProxy
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月29日
 * @since JDK 1.6
 * @see <a href="http://www.easemob.com/docs/rest/">环信API</a>
 */
public class EasemobProxy {

	private final UserApi userApi;
	private final NotifyApi notifyApi;

	private final EasemobTokenManager tokenManager;
	private Easemob4jSettings settings;

	public EasemobProxy() {
		this(new Easemob4jSettings());
	}

	public EasemobProxy(Easemob4jSettings settings) {
		this(new EasemobTokenManager(new EasemobTokenCreator(
				settings.getAccount()), settings.getCacheStorager0()));
		this.settings = settings;
	}

	private EasemobProxy(EasemobTokenManager tokenManager) {
		this.tokenManager = tokenManager;
		userApi = new UserApi(tokenManager);
		notifyApi = new NotifyApi(tokenManager);
	}

	public EasemobTokenManager getTokenManager() {
		return this.tokenManager;
	}

	public EMAccount getAccount() {
		return this.settings.getAccount();
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
	 * @see <a href="http://www.easemob.com/docs/rest/sendmessage/">发送消息</a>
	 * @throws EasemobException
	 */
	public JSONObject sendNotify(NotifyMessage message) throws EasemobException {
		return notifyApi.sendNotify(message);
	}

	/**
	 * 获取聊天记录
	 *
	 * @param searcher
	 *            搜索条件
	 * @return 聊天记录
	 * @see com.foxinmy.easemob4j.api.NotifyApi
	 * @see com.foxinmy.easemob4j.model.MultSearcher
	 * @see com.foxinmy.easemob4j.message.ChatMessage
	 * @see <a
	 *      href="http://docs.easemob.com/start/100serverintegration/30chatlog/">获取聊天记录</a>
	 * @throws EasemobException
	 */
	public ArrayResult<ChatMessage> getMessages(MultSearcher searcher)
			throws EasemobException {
		return notifyApi.getMessages(searcher);
	}

	public static final String VERSION = "1.1.3";
}
