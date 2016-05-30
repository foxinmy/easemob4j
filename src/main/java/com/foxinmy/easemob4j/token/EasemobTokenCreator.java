package com.foxinmy.easemob4j.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.weixin4j.cache.CacheCreator;
import com.foxinmy.weixin4j.http.ContentType;
import com.foxinmy.weixin4j.http.HttpClient;
import com.foxinmy.weixin4j.http.HttpClientException;
import com.foxinmy.weixin4j.http.HttpMethod;
import com.foxinmy.weixin4j.http.HttpRequest;
import com.foxinmy.weixin4j.http.HttpResponse;
import com.foxinmy.weixin4j.http.HttpStatus;
import com.foxinmy.weixin4j.http.entity.StringEntity;
import com.foxinmy.weixin4j.http.factory.HttpClientFactory;
import com.foxinmy.weixin4j.logging.InternalLogger;
import com.foxinmy.weixin4j.logging.InternalLoggerFactory;
import com.foxinmy.weixin4j.model.Consts;

/**
 * 环信token创建
 *
 * @className EasemobTokenCreator
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年12月10日
 * @since JDK 1.7
 * @see
 */
public class EasemobTokenCreator implements CacheCreator<EasemobToken> {

	private final InternalLogger logger = InternalLoggerFactory
			.getInstance(getClass());
	private static final String ASSESS_TOKEN_URL = "https://a1.easemob.com/%s/%s/token";

	private final EMAccount account;
	private final HttpClient httpClient;

	public EasemobTokenCreator(EMAccount account) {
		this.account = account;
		this.httpClient = HttpClientFactory.getInstance();
	}

	@Override
	public EasemobToken create() throws EasemobException {
		JSONObject body = new JSONObject();
		body.put("grant_type", "client_credentials");
		body.put("client_id", account.getClientId());
		body.put("client_secret", account.getClientSecret());
		String url = String.format(ASSESS_TOKEN_URL, account.getOrgName(),
				account.getAppName());
		HttpRequest request = new HttpRequest(HttpMethod.POST, url);
		request.setEntity(new StringEntity(body.toJSONString(),
				ContentType.APPLICATION_JSON));
		try {
			logger.info("weixin request >> " + request.getMethod() + " "
					+ request.getURI().toString());
			HttpResponse response = httpClient.execute(request);
			byte[] bytes = response.getContent();
			JSONObject result = JSON.parseObject(bytes, 0, bytes.length,
					Consts.UTF_8.newDecoder(), JSONObject.class);
			logger.info("weixin response << " + response.getProtocol()
					+ response.getStatus() + ":" + result);
			if (response.getStatus().getStatusCode() != HttpStatus.SC_OK) {
				throw new EasemobException(result.getString("error"),
						result.getString("error_description"));
			}
			return new EasemobToken(result.getString("access_token"),
					result.getLongValue("expires_in"),
					result.getString("application"));
		} catch (HttpClientException e) {
			throw new EasemobException(e);
		}
	}

	@Override
	public String key() {
		return String.format("easemob4j_token_%s_%s", account.getOrgName(),
				account.getAppName());
	}

	public EMAccount getAccount() {
		return account;
	}
}
