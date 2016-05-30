package com.foxinmy.easemob4j.api;

import java.util.Iterator;
import java.util.List;

/**
 * 数组结果
 *
 * @className ArrayResult
 * @author jinyu(foxinmy@gmail.com)
 * @date 2016年2月22日
 * @since JDK 1.7
 * @see
 */
public class ArrayResult<T> extends ApiResult implements Iterable<T> {
	private static final long serialVersionUID = -846002363369319803L;
	private String cursor;
	private int count;
	private List<T> content;

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArrayResult [cursor=" + cursor + ", count=" + count
				+ ", content=" + content + ", " + super.toString() + "]";
	}

	@Override
	public Iterator<T> iterator() {
		return content.iterator();
	}
}
