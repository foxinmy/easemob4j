package com.foxinmy.easemob4j.token;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.easemob4j.util.Easemob4jConfigUtil;
import com.foxinmy.weixin4j.xml.XmlStream;

/**
 * 用FILE保存TOKEN
 * 
 * @className FileTokenHolder
 * @author jy
 * @date 2015年1月9日
 * @since JDK 1.6
 */
public class FileTokenHolder extends TokenHolder {
	private final String tokenPath;

	public FileTokenHolder() {
		this(Easemob4jConfigUtil.getAccount());
	}

	public FileTokenHolder(EMAccount account) {
		super(account);
		this.tokenPath = Easemob4jConfigUtil.getValue("token.path");
	}

	@Override
	public Token getToken() throws EasemobException {
		File token_file = new File(String.format("%s/%s.xml", tokenPath,
				getCacheKey()));
		Token token = null;
		Calendar ca = Calendar.getInstance();
		long now_time = ca.getTimeInMillis();
		try {
			if (token_file.exists()) {
				token = XmlStream.fromXML(new FileInputStream(token_file),
						Token.class);
				long expire_time = token.getTime()
						+ (token.getExpiresIn() * 1000) - 2;
				if (expire_time > now_time) {
					return token;
				}
			}
			token = createToken();
			XmlStream.toXML(token, new FileOutputStream(token_file));
		} catch (IOException e) {
			throw new EasemobException(e.getMessage());
		}
		return token;
	}
}
