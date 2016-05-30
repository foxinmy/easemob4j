package com.foxinmy.easemob4j.model;

import java.io.Serializable;

/**
 * 多条记录搜索条件
 *
 * @className MultSearcher
 * @author jinyu(foxinmy@gmail.com)
 * @date 2016年2月22日
 * @since JDK 1.6
 * @see
 */
public class MultSearcher implements Serializable {
	private static final long serialVersionUID = -2866626011266285378L;

	/**
	 * 时间戳
	 */
	private final long timestamp;
	/**
	 * 条件符号
	 */
	private final WhereOp whereOp;
	/**
	 * 限制行数
	 */
	private final int limit;
	/**
	 * 游标
	 */
	private final String cursor;

	MultSearcher(long timestamp, WhereOp whereOp, int limit, String cursor) {
		this.timestamp = timestamp;
		this.whereOp = whereOp;
		this.limit = limit;
		this.cursor = cursor;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public WhereOp getWhereOp() {
		return whereOp;
	}

	public int getLimit() {
		return limit;
	}

	public String getCursor() {
		return cursor;
	}

	public static MultSearcher.Builder custom() {
		return new Builder();
	}

	public String toQL() {
		return String.format("select * where timestamp%s%s", whereOp.getOp(),
				timestamp);
	}

	@Override
	public String toString() {
		return "MultSearcher [timestamp=" + timestamp + ", whereOp=" + whereOp
				+ ", limit=" + limit + ", cursor=" + cursor + "]";
	}

	public static class Builder {
		/**
		 * 时间戳
		 */
		private long timestamp;
		/**
		 * 条件符号
		 */
		private WhereOp whereOp;
		/**
		 * 限制行数
		 */
		private int limit;
		/**
		 * 游标
		 */
		private String cursor;

		public Builder() {
			this.timestamp = System.currentTimeMillis();
			this.whereOp = WhereOp.lt;
			this.limit = 10;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public Builder setTimestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public WhereOp getWhereOp() {
			return whereOp;
		}

		public Builder setWhereOp(WhereOp whereOp) {
			this.whereOp = whereOp;
			return this;
		}

		public int getLimit() {
			return limit;
		}

		public Builder setLimit(int limit) {
			this.limit = limit;
			return this;
		}

		public String getCursor() {
			return cursor;
		}

		public Builder setCursor(String cursor) {
			this.cursor = cursor;
			return this;
		}

		public MultSearcher build() {
			return new MultSearcher(timestamp, whereOp, limit, cursor);
		}
	}

	public enum WhereOp {
		eq(" = "), // 等于
		lt(" < "), // 小于
		gt(" > "); // 大于
		private String op;

		WhereOp(String op) {
			this.op = op;
		}

		public String getOp() {
			return op;
		}
	}
}
