package com.foxinmy.easemob4j;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.Consts;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.easemob4j.token.EasemobTokenCreator;
import com.foxinmy.easemob4j.token.EasemobTokenHolder;
import com.foxinmy.easemob4j.util.Easemob4jConfigUtil;
import com.foxinmy.weixin4j.token.FileTokenStorager;

/**
 * 
 * @className TokenTest
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class TokenTest {

	protected EasemobTokenHolder tokenHolder;

	@Before
	public void setUp() {
		EMAccount account = Easemob4jConfigUtil.getAccount();
		String tokenPath = Easemob4jConfigUtil.getValue("easemob4j.token.path",
				Consts.DEFAULT_TOKEN_PATH);
		tokenHolder = new EasemobTokenHolder(new EasemobTokenCreator(account),
				new FileTokenStorager(tokenPath), account);
	}

	@Test
	public void test() throws EasemobException {
		Assert.assertNotNull(tokenHolder.getToken());
	}
}
