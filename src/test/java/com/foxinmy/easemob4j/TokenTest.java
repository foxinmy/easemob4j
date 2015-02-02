package com.foxinmy.easemob4j;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.token.FileTokenHolder;
import com.foxinmy.easemob4j.token.TokenHolder;

/**
 * 
 * @className TokenTest
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class TokenTest {

	protected TokenHolder tokenHolder;

	@Before
	public void setUp() {
		tokenHolder = new FileTokenHolder();
	}

	@Test
	public void test() throws EasemobException {
		Assert.assertNotNull(tokenHolder.getToken());
	}
}
