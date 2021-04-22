package com.sist.mar.wishitem.domain;

import com.sist.mar.cmn.DTO;

public class Wishitem extends DTO {
	
	private int wishNo;       //늘사는것 번호 (시퀀스)
	private String memberId;  //회원 이메일
	private int itemNo;       //상품번호
	private String wishDate;  //늘사는것 날짜 (SYSDATE)
	
	public Wishitem() {}

	//wishDate는 자동기입이므로 제외
	public Wishitem(int wishNo, String memberId, int itemNo) {
		super();
		this.wishNo = wishNo;
		this.memberId = memberId;
		this.itemNo = itemNo;
	}

	public int getWishNo() {
		return wishNo;
	}

	public void setWishNo(int wishNo) {
		this.wishNo = wishNo;
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

	public String getWishDate() {
		return wishDate;
	}

	public void setWishDate(String wishDate) {
		this.wishDate = wishDate;
	}

	@Override
	public String toString() {
		return "Wishitem [wishNo=" + wishNo + ", memberId=" + memberId + ", itemNo=" + itemNo + ", wishDate=" + wishDate
				+ ", toString()=" + super.toString() + "]";
	}

}
