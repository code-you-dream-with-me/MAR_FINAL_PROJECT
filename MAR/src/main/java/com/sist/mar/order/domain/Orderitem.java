package com.sist.mar.order.domain;

import com.sist.mar.cmn.DTO;

public class Orderitem extends DTO {

	private int orderitemNo;    //주문상품 번호 (시퀀스)
	private int orderNo;        //주문 번호
	private int itemNo;         //상품 번호
	private int quantity;       //수량
	private String reviewState; //상태값(리뷰 작성 가능 여부)
	
	public Orderitem() {}

	//orderitemNo는 자동기입이므로 제외 //cartNO 삭제예정
	public Orderitem(int orderNo, int itemNo, int quantity) {
		super();
		this.orderNo = orderNo;
		this.itemNo = itemNo;
		this.quantity = quantity;
	}

	public int getOrderitemNo() {
		return orderitemNo;
	}

	public void setOrderitemNo(int orderitemNo) {
		this.orderitemNo = orderitemNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getReviewState() {
		return reviewState;
	}

	public void setReviewState(String reviewState) {
		this.reviewState = reviewState;
	}

	@Override
	public String toString() {
		return "Orderitem [orderitemNo=" + orderitemNo + ", orderNo=" + orderNo + ", itemNo=" + itemNo + ", quantity="
				+ quantity + ", reviewState=" + reviewState + ", toString()=" + super.toString() + "]";
	}


}
