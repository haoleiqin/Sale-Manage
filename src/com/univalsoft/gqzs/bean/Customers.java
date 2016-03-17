package com.univalsoft.gqzs.bean;
/**
 * 客户信息类
 * @author lucheng
 *
 */
public class Customers {
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String name;
	private String address;
	private String mobile;
	private String phone;
	private String eMail;
	private String whatapp;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getWhatapp() {
		return whatapp;
	}
	public void setWhatapp(String whatapp) {
		this.whatapp = whatapp;
	}
	private String opening;
	
	public String getOpening() {
		return opening;
	}
	public void setOpening(String opening) {
		this.opening = opening;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
