package com.foxinmy.easemob4j;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.token.EasemobTokenCreator;
import com.foxinmy.easemob4j.token.EasemobTokenManager;
import com.foxinmy.easemob4j.util.Easemob4jSettings;

/**
 *
 * @className TokenTest
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class TokenTest {

	protected EasemobTokenManager tokenHolder;

	@Before
	public void setUp() {
		Easemob4jSettings settings = new Easemob4jSettings();
		tokenHolder = new EasemobTokenManager(new EasemobTokenCreator(
				settings.getAccount()), settings.getCacheStorager0());
	}

	@Test
	public void test() throws EasemobException {
		Assert.assertNotNull(tokenHolder.getCache());
	}
}
