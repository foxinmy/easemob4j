package com.foxinmy.easemob4j.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.Consts;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.weixin4j.http.ContentType;
import com.foxinmy.weixin4j.http.HttpClient;
import com.foxinmy.weixin4j.http.HttpClientException;
import com.foxinmy.weixin4j.http.HttpMethod;
import com.foxinmy.weixin4j.http.HttpRequest;
import com.foxinmy.weixin4j.http.HttpResponse;
import com.foxinmy.weixin4j.http.HttpStatus;
import com.foxinmy.weixin4j.http.SimpleHttpClient;
import com.foxinmy.weixin4j.http.entity.StringEntity;

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

	private final HttpClient httpClient;
	private final EMAccount account;

	public TokenHolder(EMAccount account) {
		this.account = account;
		this.httpClient = new SimpleHttpClient();
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
		HttpRequest request = new HttpRequest(HttpMethod.POST, url);
		request.setEntity(new StringEntity(body.toJSONString(),
				ContentType.APPLICATION_JSON));
		try {
			HttpResponse response = httpClient.execute(request);
			byte[] bytes = response.getContent();
			JSONObject result = JSON.parseObject(bytes, 0, bytes.length,
					Consts.UTF_8.newDecoder(), JSONObject.class);
			if (response.getStatus().getStatusCode() != HttpStatus.SC_OK) {
				throw new EasemobException(result.getString("error"),
						result.getString("error_description"));
			}
			Token token = JSON.toJavaObject(result, Token.class);
			token.setTime(System.currentTimeMillis());
			return token;
		} catch (HttpClientException e) {
			throw new EasemobException(e.getMessage());
		}
	}
}
