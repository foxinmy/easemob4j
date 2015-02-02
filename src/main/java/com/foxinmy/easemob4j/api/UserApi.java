package com.foxinmy.easemob4j.api;

import com.alibaba.fastjson.JSON;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.http.ApiResult;
import com.foxinmy.easemob4j.http.Response;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.easemob4j.model.User;
import com.foxinmy.easemob4j.token.Token;
import com.foxinmy.easemob4j.token.TokenHolder;

/**
 * 用户相关
 * 
 * @className UserApi
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class UserApi extends BaseApi {
	private final TokenHolder tokenHolder;
	private final EMAccount account;

	public UserApi(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
		this.account = tokenHolder.getAccount();
	}

	/**
	 * 创建用户
	 * 
	 * @param user
	 * @return
	 * @throws EasemobException
	 */
	public ApiResult createUser(User... user) throws EasemobException {
		String create_user_url = String.format(
				getRequestUri("create_user_url"), account.getOrgName(),
				account.getAppName());
		Token token = tokenHolder.getToken();
		Response response = request.post(create_user_url,
				token.getAccessToken(), JSON.toJSONString(user));
		return response.getAsResult();
	}

	/**
	 * 添加好友
	 * 
	 * @param ownerName
	 * @param friendName
	 * @return
	 * @throws EasemobException
	 */
	public ApiResult contactFriend(String ownerName, String friendName)
			throws EasemobException {
		String contact_friend_url = String.format(
				getRequestUri("contact_friend_url"), account.getOrgName(),
				account.getAppName(), ownerName, friendName);
		Token token = tokenHolder.getToken();
		Response response = request.post(contact_friend_url,
				token.getAccessToken(), null);
		return response.getAsResult();
	}
}
