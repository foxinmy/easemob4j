package com.foxinmy.easemob4j;

import org.junit.Before;
import org.junit.Test;

import com.foxinmy.easemob4j.api.UserApi;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.User;

/**
 * 用户相关测试
 *
 * @className UserTest
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class UserTest extends TokenTest {
	private UserApi userApi;

	@Before
	public void init() {
		userApi = new UserApi(tokenHolder);
	}

	@Test
	public void createUser() throws EasemobException {
		User  user = new User("test08", "123456");
		System.err.println(userApi.createUser(user));
	}

	@Test
	public void contactFriend() throws EasemobException {
		System.err.println(userApi.contactFriend("test07", "test08"));
	}
}
