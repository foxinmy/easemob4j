package com.foxinmy.easemob4j.token;

import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.token.TokenCreator;
import com.foxinmy.weixin4j.token.TokenHolder;
import com.foxinmy.weixin4j.token.TokenStorager;

/**
 * 扩展TokenHolder
 * 
 * @className EasemobTokenHolder
 * @author jy
 * @date 2015年12月10日
 * @since JDK 1.7
 * @see
 */
public class EasemobTokenHolder extends TokenHolder {

	private final EMAccount account;

	public EasemobTokenHolder(TokenCreator tokenCreator,
			TokenStorager tokenStorager, EMAccount account) {
		super(tokenCreator, tokenStorager);
		this.account = account;
	}

	@Override
	public Token getToken() throws EasemobException {
		try {
			return super.getToken();
		} catch (WeixinException e) {
			throw new EasemobException(e.getErrorCode(), e.getErrorMsg());
		}
	}

	@Override
	public String getAccessToken() throws EasemobException {
		try {
			return super.getAccessToken();
		} catch (WeixinException e) {
			throw new EasemobException(e.getErrorCode(), e.getErrorMsg());
		}
	}

	@Override
	public Token refreshToken() throws EasemobException {
		try {
			return super.refreshToken();
		} catch (WeixinException e) {
			throw new EasemobException(e.getErrorCode(), e.getErrorMsg());
		}
	}

	public EMAccount getAccount() {
		return account;
	}
}
