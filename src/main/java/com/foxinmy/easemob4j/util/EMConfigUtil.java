package com.foxinmy.easemob4j.util;

import java.io.File;
import java.util.ResourceBundle;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.foxinmy.easemob4j.model.EMAccount;

/**
 * 环信配置
 * @className EMConfigUtil
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class EMConfigUtil {
	private final static String CLASSPATH_PREFIX = "classpath:";
	private final static String CLASSPATH_VALUE;
	private final static ResourceBundle easemobBundle;
	static {
		easemobBundle = ResourceBundle.getBundle("easemob");
		Set<String> keySet = easemobBundle.keySet();
		File file = null;
		CLASSPATH_VALUE = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		for (String key : keySet) {
			if (!key.endsWith("_path")) {
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

	/**
	 * 获取easemob.properties文件中的key值
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return easemobBundle.getString(key);
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
	 * @return
	 */
	public static EMAccount getAccount() {
		String text = getValue("account");
		return JSON.parseObject(text, EMAccount.class);
	}
}
