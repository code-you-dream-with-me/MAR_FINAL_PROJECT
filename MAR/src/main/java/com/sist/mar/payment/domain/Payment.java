package com.sist.mar.payment.domain;

import com.sist.mar.cmn.DTO;

public class Payment extends DTO {

	private String payNo;
	private int orderNo;
	private String state;
	private String datetime;
	
	public Payment() {}

	public Payment(String payNo, int orderNo, String state) {
		super();
		this.payNo = payNo;
		this.orderNo = orderNo;
		this.state = state;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "Payment [payNo=" + payNo + ", orderNo=" + orderNo + ", state=" + state + ", datetime=" + datetime
				+ ", toString()=" + super.toString() + "]";
	}

}
