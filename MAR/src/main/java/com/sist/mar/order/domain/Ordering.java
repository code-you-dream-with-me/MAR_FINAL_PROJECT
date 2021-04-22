package com.sist.mar.order.domain;

import com.sist.mar.cmn.DTO;

public class Ordering extends DTO {

	private int orderNo;       //주문 번호 (시퀀스)
	private String memberId;   //주문자 (=회원 이메일)
	private int price;          //주문 금액
	private String name;        //수령자명
	private String phone;       //수령자 번호
	private String address;     //배송지
	private String request;     //요청사항
	private String orderState; //주문상태 
	private String orderDate;  //결제일 (SYSDATE)
	
	public Ordering() {}

	// orderNo, orderDate는 자동기입이므로 제외
	public Ordering(String memberId, int price, String name, String phone, String address, String request,
			String orderState) {
		super();
		this.memberId = memberId;
		this.price = price;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.request = request;
		this.orderState = orderState;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Ordering [orderNo=" + orderNo + ", memberId=" + memberId + ", price=" + price + ", name=" + name
				+ ", phone=" + phone + ", address=" + address + ", request=" + request + ", orderState=" + orderState
				+ ", orderDate=" + orderDate + ", toString()=" + super.toString() + "]";
	}

}
