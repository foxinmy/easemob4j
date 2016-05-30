package com.foxinmy.easemob4j.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.User;
import com.foxinmy.easemob4j.token.EasemobToken;
import com.foxinmy.easemob4j.token.EasemobTokenManager;

/**
 * 用户API
 *
 * @className UserApi
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月28日
 * @since JDK 1.6
 * @see com.foxinmy.easemob4j.model.User
 * @see <a href="http://www.easemob.com/docs/rest/userapi/">用户体系</a>
 */
public class UserApi extends BaseApi {
	private final EasemobTokenManager tokenManager;

	public UserApi(EasemobTokenManager tokenManager) {
		super(tokenManager.getAccount());
		this.tokenManager = tokenManager;
	}

	/**
	 * 创建用户
	 *
	 * @param user
	 *            单个或者多个用户
	 * @return 操作结果
	 * @see <a
	 *      href="http://www.easemob.com/docs/rest/userapi/#im">创建单个或者多个用户</a>
	 * @throws EasemobException
	 */
	public ApiResult createUser(User... user) throws EasemobException {
		String create_user_url = getRequestUri0("create_user_url");
		EasemobToken token = tokenManager.getCache();
		JSONObject response = post(create_user_url, token.getAccessToken(),
				JSON.toJSONString(user));
		return JSON.toJavaObject(response, ApiResult.class);
	}

	/**
	 * 添加好友
	 *
	 * @param ownerName
	 *            我的昵称
	 * @param friendName
	 *            好友昵称
	 * @return 操作结果
	 * @see <a
	 *      href="http://www.easemob.com/docs/rest/userapi/#contactsfriend">添加好友</a>
	 * @throws EasemobException
	 */
	public ApiResult contactFriend(String ownerName, String friendName)
			throws EasemobException {
		String contact_friend_url = getRequestUri1("contact_friend_url",
				ownerName, friendName);
		EasemobToken token = tokenManager.getCache();
		JSONObject response = post(contact_friend_url, token.getAccessToken(),
				null);
		return JSON.toJavaObject(response, ApiResult.class);
	}
}
