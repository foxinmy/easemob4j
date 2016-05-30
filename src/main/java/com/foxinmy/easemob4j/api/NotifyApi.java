package com.foxinmy.easemob4j.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.foxinmy.easemob4j.exception.EasemobException;
import com.foxinmy.easemob4j.message.Audio;
import com.foxinmy.easemob4j.message.ChatMessage;
import com.foxinmy.easemob4j.message.Command;
import com.foxinmy.easemob4j.message.Image;
import com.foxinmy.easemob4j.message.Location;
import com.foxinmy.easemob4j.message.Notify;
import com.foxinmy.easemob4j.message.NotifyMessage;
import com.foxinmy.easemob4j.message.Text;
import com.foxinmy.easemob4j.message.Video;
import com.foxinmy.easemob4j.model.MultSearcher;
import com.foxinmy.easemob4j.token.EasemobTokenManager;
import com.foxinmy.easemob4j.type.MessageType;
import com.foxinmy.weixin4j.model.Consts;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.util.NameValue;
import com.foxinmy.weixin4j.util.URLEncodingUtil;

/**
 * 消息API
 *
 * @className NotifyApi
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年3月18日
 * @since JDK 1.6
 */
public class NotifyApi extends BaseApi {

	private final EasemobTokenManager tokenManager;

	public NotifyApi(EasemobTokenManager tokenManager) {
		super(tokenManager.getAccount());
		this.tokenManager = tokenManager;
	}

	/**
	 * 发送消息
	 *
	 * @param message
	 *            消息对象
	 * @return 操作结果 { "stliu1": "success", "jma3": "success", "stliu":
	 *         "success", "jma4": "success" }
	 * @see com.foxinmy.easemob4j.message.NotifyMessage
	 * @see com.foxinmy.easemob4j.message.Text
	 * @see com.foxinmy.easemob4j.message.Audio
	 * @see com.foxinmy.easemob4j.message.Command
	 * @see com.foxinmy.easemob4j.message.Image
	 * @see com.foxinmy.easemob4j.message.Location
	 * @see com.foxinmy.easemob4j.message.Video
	 * @see <a href="http://www.easemob.com/docs/rest/sendmessage/">发送消息</a>
	 * @throws EasemobException
	 */
	public JSONObject sendNotify(NotifyMessage message) throws EasemobException {
		String send_message_url = getRequestUri0("send_message_url");
		Token token = tokenManager.getCache();
		JSONObject response = post(send_message_url, token.getAccessToken(),
				JSON.toJSONString(message));
		return response.getJSONObject("data");
	}

	/**
	 * 获取聊天记录
	 *
	 * @param searcher
	 *            搜索条件
	 * @return 聊天记录
	 * @see com.foxinmy.easemob4j.model.MultSearcher
	 * @see com.foxinmy.easemob4j.message.ChatMessage
	 * @see <a
	 *      href="http://docs.easemob.com/start/100serverintegration/30chatlog/">获取聊天记录</a>
	 * @throws EasemobException
	 */
	@SuppressWarnings("unchecked")
	public ArrayResult<ChatMessage> getMessages(MultSearcher searcher)
			throws EasemobException {
		String chat_messages_url = getRequestUri0("chat_messages_url");
		Token token = tokenManager.getCache();
		JSONObject response = get(
				chat_messages_url
						+ "?"
						+ URLEncodingUtil.encoding(searcher.toQL(),
								Consts.UTF_8, true), token.getAccessToken());
		ArrayResult<ChatMessage> message = JSON.toJavaObject(response,
				ArrayResult.class);
		JSONArray entities = response.getJSONArray("entities");
		if (entities != null && !entities.isEmpty()) {
			List<ChatMessage> content = new ArrayList<ChatMessage>(
					entities.size());
			for (int i = 0; i < entities.size(); i++) {
				content.add(JSON.parseObject(entities.getString(i),
						ChatMessage.class, PayloadProcessor.global));
			}
			message.setContent(content);
		}
		return message;
	}

	private static class PayloadProcessor implements ExtraProcessor {
		public static final PayloadProcessor global = new PayloadProcessor();
		private static final String KEY = "payload";

		private PayloadProcessor() {
		}

		@Override
		public void processExtra(Object object, String key, Object value) {
			if (KEY.equalsIgnoreCase(key)) {
				// ext
				JSONObject extsObj = ((JSONObject) value).getJSONObject("ext");
				if (extsObj != null && !extsObj.isEmpty()) {
					List<NameValue> exts = new ArrayList<NameValue>(
							extsObj.size());
					for (Entry<String, Object> ext : extsObj.entrySet()) {
						exts.add(new NameValue(ext.getKey(), (String) ext
								.getValue()));
					}
					JSONPath.set(object, "exts", exts);
				}
				// bodies
				JSONArray recordsObj = ((JSONObject) value)
						.getJSONArray("bodies");
				if (recordsObj != null && !recordsObj.isEmpty()) {
					List<Notify> records = new ArrayList<Notify>(
							recordsObj.size());
					for (int i = 0; i < recordsObj.size(); i++) {
						JSONObject record = recordsObj.getJSONObject(i);
						MessageType type = record.getObject("type",
								MessageType.class);
						Notify notify = null;
						if (type == MessageType.txt || type == MessageType.text) {
							notify = JSON.toJavaObject(record, Text.class);
						} else if (type == MessageType.audio) {
							notify = JSON.toJavaObject(record, Audio.class);
						} else if (type == MessageType.cmd) {
							notify = JSON.toJavaObject(record, Command.class);
						} else if (type == MessageType.img) {
							notify = JSON.toJavaObject(record, Image.class);
						} else if (type == MessageType.loc) {
							notify = JSON.toJavaObject(record, Location.class);
						} else if (type == MessageType.video) {
							notify = JSON.toJavaObject(record, Video.class);
						}
						records.add(notify);
					}
					JSONPath.set(object, "records", records);
				}
			}
		}
	}
}
