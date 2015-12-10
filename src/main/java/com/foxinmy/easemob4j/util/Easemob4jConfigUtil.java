package com.foxinmy.easemob4j.util;

import java.io.File;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import com.foxinmy.easemob4j.model.EMAccount;

/**
 * 环信配置
 * 
 * @className Easemob4jConfigUtil
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.6
 * @see
 */
public class Easemob4jConfigUtil {
	private final static String CLASSPATH_PREFIX = "classpath:";
	private final static String CLASSPATH_VALUE;
	private final static ResourceBundle easemobBundle;
	static {
		easemobBundle = ResourceBundle.getBundle("easemob4j");
		File file = null;
		CLASSPATH_VALUE = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		for (String key : easemobBundle.keySet()) {
			if (!key.endsWith(".path")) {
				continue;
			}
			file = new File(getValue(key).replaceFirst(CLASSPATH_PREFIX,
					CLASSPATH_VALUE));
			if (!file.exists() && !file.mkdirs()) {
				System.err.append(String.format("%s create fail.%n",
						file.getAbsolutePath()));
			}
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
	 * 获取easemob.properties文件中的key值
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String wrapKey = wrapKeyName(key);
		return System.getProperty(wrapKey, easemobBundle.getString(wrapKey));
	}

	/**
	 * 判断属性是否存在[classpath:]如果存在则拼接项目路径后返回 一般用于文件的绝对路径获取
	 * 
	 * @param key
	 * @return
	 */
	public static String getClassPathValue(String key) {
		return new File(getValue(key).replaceFirst(CLASSPATH_PREFIX,
				CLASSPATH_VALUE)).getPath();
	}

	/**
	 * 获取账号信息
	 * 
	 * @return
	 */
	public static EMAccount getAccount() {
		String text = getValue("account");
		return JSON.parseObject(text, EMAccount.class);
	}
}
