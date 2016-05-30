package com.foxinmy.easemob4j.token;

import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.weixin4j.cache.CacheManager;
import com.foxinmy.weixin4j.cache.CacheStorager;
import com.foxinmy.weixin4j.exception.WeixinException;

/**
 * 环信Token的管理
 *
 * @className EasemobTokenManager
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年12月10日
 * @since JDK 1.6
 * @see
 */
public class EasemobTokenManager extends CacheManager<EasemobToken> {

	private final EasemobTokenCreator tokenCreator;

	/**
	 *
	 * @param tokenCreator
	 *            对token的创建
	 * @param cacheStorager
	 *            对缓存的存储
	 */
	public EasemobTokenManager(EasemobTokenCreator tokenCreator,
			CacheStorager<EasemobToken> cacheStorager) {
		super(tokenCreator, cacheStorager);
		this.tokenCreator = tokenCreator;
	}

	@Override
	public EasemobToken getCache() throws EasemobException {
		try {
			return super.getCache();
		} catch (WeixinException e) {
			throw new EasemobException(e.getErrorCode(), e.getErrorDesc());
		}
	}

	public EMAccount getAccount() {
		return tokenCreator.getAccount();
	}
}
