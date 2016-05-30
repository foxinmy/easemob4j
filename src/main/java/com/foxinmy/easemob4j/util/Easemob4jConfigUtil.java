package com.foxinmy.easemob4j.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.weixin4j.util.StringUtil;

/**
 * 环信配置
 * 
 * @className Easemob4jConfigUtil
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月28日
 * @since JDK 1.6
 * @see
 */
public class Easemob4jConfigUtil {
	private final static String CLASSPATH_PREFIX = "classpath:";
	private final static String CLASSPATH_VALUE;
	private static ResourceBundle easemobBundle;
	static {
		CLASSPATH_VALUE = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		try {
			easemobBundle = ResourceBundle.getBundle("easemob4j");
		} catch (MissingResourceException e) {
			;
		}
	}
	private final static String EASEMOB4J_PREFIX = "easemob4j";

	private static String wrapKeyName(String key) {
		if (!key.startsWith(EASEMOB4J_PREFIX)) {
			return String.format("%s.%s", EASEMOB4J_PREFIX, key);
		}
		return key;
	}

	/**
	 * 获取easemob4j.properties文件中的key值
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String wrapKey = wrapKeyName(key);
		return System.getProperty(wrapKey, easemobBundle.getString(wrapKey));
	}

	/**
	 * key不存在时则返回传入的默认值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getValue(String key, String defaultValue) {
		String value = defaultValue;
		try {
			value = getValue(key);
			if (StringUtil.isBlank(value)) {
				value = defaultValue;
			}
		} catch (MissingResourceException e) {
			;
		} catch (NullPointerException e) {
			;
		}
		return value;
	}

	/**
	 * 判断属性是否存在[classpath:]如果存在则拼接项目路径后返回 一般用于文件的绝对路径获取
	 * 
	 * @param key
	 * @return
	 */
	public static String getClassPathValue(String key) {
		return getValue(key).replaceFirst(CLASSPATH_PREFIX, CLASSPATH_VALUE);
	}

	/**
	 * 获取账号信息
	 * 
	 * @return
	 */
	public static EMAccount getAccount() {
		EMAccount account = null;
		try {
			account = JSON.parseObject(getValue("account"), EMAccount.class);
		} catch (NullPointerException e) {
			System.err
					.println("'easemob4j.account' key not found in easemob4j.properties.");
		} catch (MissingResourceException e) {
			System.err
					.println("'easemob4j.account' key not found in easemob4j.properties.");
		}
		return account;
	}
}
