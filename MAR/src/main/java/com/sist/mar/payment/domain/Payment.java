package com.sist.mar.payment.domain;

import com.sist.mar.cmn.DTO;

public class Payment extends DTO {

	private String payNo;    //결제번호(아임포트 사이트에서 받기)
	private int orderNo;     //주문번호
	private String state;    //결제상태 (결제완료시1, 결제취소시0)
	private String datetime; //결제일시 (SYSDATE)
	
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
