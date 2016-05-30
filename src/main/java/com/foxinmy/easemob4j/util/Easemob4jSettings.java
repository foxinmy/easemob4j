package com.foxinmy.easemob4j.util;

import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.easemob4j.token.EasemobToken;
import com.foxinmy.weixin4j.cache.CacheStorager;
import com.foxinmy.weixin4j.cache.FileCacheStorager;
import com.foxinmy.weixin4j.http.HttpParams;
import com.foxinmy.weixin4j.util.StringUtil;

/**
 * 环信配置
 *
 * @className Easemob4jSettings
 * @author jinyu(foxinmy@gmail.com)
 * @date 2016年5月24日
 * @since JDK 1.7
 * @see
 */
public class Easemob4jSettings {
	/**
	 * 账号信息
	 */
	private EMAccount account;
	/**
	 * Http参数
	 */
	private HttpParams httpParams;
	/**
	 * 系统临时目录
	 */
	private String tmpdir;
	/**
	 * Token的存储方式 默认为FileCacheStorager
	 */
	private CacheStorager<EasemobToken> cacheStorager;

	/**
	 * 默认使用easemob4j.properties配置的信息
	 */
	public Easemob4jSettings() {
		this(Easemob4jConfigUtil.getAccount());
	}

	/**
	 * 账号信息
	 *
	 * @param account
	 */
	private Easemob4jSettings(EMAccount account) {
		this.account = account;
	}

	public HttpParams getHttpParams() {
		return httpParams;
	}

	public void setHttpParams(HttpParams httpParams) {
		this.httpParams = httpParams;
	}

	public String getTmpdir() {
		return tmpdir;
	}

	public void setTmpdir(String tmpdir) {
		this.tmpdir = tmpdir;
	}

	public EMAccount getAccount() {
		return account;
	}

	public void setCacheStorager(CacheStorager<EasemobToken> cacheStorager) {
		this.cacheStorager = cacheStorager;
	}

	public String getTmpdir0() {
		if (StringUtil.isBlank(tmpdir)) {
			return Easemob4jConfigUtil.getValue("easemob4j.tmpdir",
					System.getProperty("java.io.tmpdir"));
		}
		return tmpdir;
	}

	public CacheStorager<EasemobToken> getCacheStorager() {
		return cacheStorager;
	}

	public CacheStorager<EasemobToken> getCacheStorager0() {
		if (cacheStorager == null) {
			return new FileCacheStorager<EasemobToken>(getTmpdir0());
		}
		return cacheStorager;
	}

	@Override
	public String toString() {
		return "Easemob4jSettings [account=" + account + ", httpParams="
				+ httpParams + ", tmpdir=" + tmpdir + ", cacheStorager="
				+ cacheStorager + "]";
	}
}
