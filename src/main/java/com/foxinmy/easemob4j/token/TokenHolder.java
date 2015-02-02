package com.foxinmy.easemob4j.token;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.http.HttpRequest;
import com.foxinmy.easemob4j.http.Response;
import com.foxinmy.easemob4j.model.Consts;
import com.foxinmy.easemob4j.model.EMAccount;

/**
 * token持有者
 * 
 * @className TokenHolder
 * @author jy.hu
 * @date 2014年9月27日
 * @since JDK 1.7
 * @see com.foxinmy.weixin4j.model.Token
 * @see com.foxinmy.easemob4j.token.FileTokenHolder
 * @see com.foxinmy.easemob4j.token.RedisTokenHolder
 */
public abstract class TokenHolder {
	public abstract Token getToken() throws EasemobException;

	private final HttpRequest request;
	private final EMAccount account;

	public TokenHolder(EMAccount account) {
		this.account = account;
		this.request = new HttpRequest();
	}

	public EMAccount getAccount() {
		return account;
	}

	protected String getCacheKey() {
		return String.format("t:em:%s:%s", account.getOrgName(),
				account.getAppName());
	}

	protected Token createToken() throws EasemobException {
		JSONObject body = new JSONObject();
		body.put("grant_type", "client_credentials");
		body.put("client_id", account.getClientId());
		body.put("client_secret", account.getClientSecret());
		String url = String.format(Consts.ASSESS_TOKEN_URL,
				account.getOrgName(), account.getAppName());
		Response response = request.post(url, null, body.toJSONString());
		Token token = response.getAsObject(Token.class);
		token.setTime(System.currentTimeMillis());
		return token;
	}
}
