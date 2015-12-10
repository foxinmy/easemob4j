package com.foxinmy.easemob4j.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.easemob4j.type.MessageType;

/**
 * 地理位置消息
 * @className Location
 * @author jy
 * @date 2015年3月18日
 * @since JDK 1.6
 * @see
 */
public class Location extends Notify {
	
	private static final long serialVersionUID = 4016404089208687691L;
	
	@JSONField(name = "addr")
	private String address;// 要发送的地址
	private double lat;// 纬度
	private double lng;// 经度
	
	public Location() {
		super(MessageType.loc);
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "Location [address=" + address + ", lat=" + lat + ", lng=" + lng
				+ "]";
	}
	
}
