package com.sist.mar.member.domain;

import com.sist.mar.cmn.DTO;

public class MemberVO extends DTO {

	private String memberId;
	private String pw;
	private String name;
	private String phone;
	private String address;
	private String auth;
	private String regDt;
	private String modDt;
	
	public MemberVO() {}

	public MemberVO(String memberId, String pw, String name, String phone, String address, String auth, String regDt,
			String modDt) {
		super();
		this.memberId = memberId;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.auth = auth;
		this.regDt = regDt;
		this.modDt = modDt;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", address="
				+ address + ", auth=" + auth + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	
}
