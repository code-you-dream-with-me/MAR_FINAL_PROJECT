package com.sist.mar.cart.domain;

import com.sist.mar.cmn.DTO;

public class Cart extends DTO {

	private int cartNo;      //장바구니 번호 (시퀀스)
	private String memberId; //회원 이메일 
	private int itemNo;      //상품 번호 
	private int quantity;     //수량
	
	public Cart() {}

	public Cart(int cartNo, String memberId, int itemNo, int quantity) {
		super();
		this.cartNo = cartNo;
		this.memberId = memberId;
		this.itemNo = itemNo;
		this.quantity = quantity;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", memberId=" + memberId + ", itemNo=" + itemNo + ", quantity=" + quantity
				+ ", toString()=" + super.toString() + "]";
	}

}
