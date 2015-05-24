package com.foxinmy.easemob4j.token;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.model.EMAccount;
import com.foxinmy.easemob4j.util.EMConfigUtil;

/**
 * 用REDIS保存TOKEN
 * 
 * @className RedisTokenHolder
 * @author jy
 * @date 2015年1月9日
 * @since JDK 1.7
 */
public class RedisTokenHolder extends TokenHolder {

	private JedisPool jedisPool;

	public final static int MAX_TOTAL = 50;
	public final static int MAX_IDLE = 5;
	public final static int MAX_WAIT_MILLIS = 2000;
	public final static boolean TEST_ON_BORROW = false;
	public final static boolean TEST_ON_RETURN = true;

	public RedisTokenHolder() {
		this("localhost", 6379, EMConfigUtil.getAccount());
	}

	public RedisTokenHolder(JedisPool jedisPool) {
		this(jedisPool, EMConfigUtil.getAccount());
	}

	public RedisTokenHolder(String host, int port, EMAccount account) {
		super(account);
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(MAX_TOTAL);
		jedisPoolConfig.setMaxIdle(MAX_IDLE);
		jedisPoolConfig.setMaxWaitMillis(MAX_WAIT_MILLIS);
		jedisPoolConfig.setTestOnBorrow(TEST_ON_BORROW);
		jedisPoolConfig.setTestOnReturn(TEST_ON_RETURN);
		this.jedisPool = new JedisPool(jedisPoolConfig, host, port);
	}

	public RedisTokenHolder(JedisPool jedisPool, EMAccount account) {
		super(account);
		this.jedisPool = jedisPool;
	}

	@Override
	public Token getToken() throws EasemobException {
		Token token = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String cacheKey = getCacheKey();
			String accessToken = jedis.get(cacheKey);
			if (accessToken == null || accessToken.trim().isEmpty()) {
				token = createToken();
				jedis.setex(cacheKey, (int) token.getExpiresIn(),
						token.getAccessToken());
			} else {
				token = new Token(accessToken);
			}
		} catch (JedisException e) {
			jedisPool.returnBrokenResource(jedis);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return token;
	}
}
