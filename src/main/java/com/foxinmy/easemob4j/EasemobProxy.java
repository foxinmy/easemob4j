package com.foxinmy.easemob4j;

import com.foxinmy.easemob4j.api.UserApi;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.http.ApiResult;
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
 * @see
 */
public class EasemobProxy {
	
	private final UserApi userApi;

	public EasemobProxy() {
		this(new FileTokenHolder());
	}

	public EasemobProxy(TokenHolder tokenHolder) {
		userApi = new UserApi(tokenHolder);
	}

	/**
	 * 创建用户
	 * 
	 * @param user
	 * @return
	 * @throws EasemobException
	 */
	public ApiResult createUser(User... user) throws EasemobException {
		return userApi.createUser(user);
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
		return userApi.contactFriend(ownerName, friendName);
	}
}
