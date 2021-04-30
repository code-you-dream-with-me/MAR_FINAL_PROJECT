package com.sist.mar.main.domain;

import com.sist.mar.cmn.DTO;

public class MainVO extends DTO{
	
	private int    itemNo	   ; //상품번호
	private String path        ; //경로
	private String name        ; //상품명
	private int    discount    ; //할인율
	private int    price       ; //가격
	private int    finalPrice  ; //최종가격
	private int    sales       ; //판매량
	
	
	
	public MainVO() {}

	public MainVO(int itemNo, String path, String name, int discount, int price, int finalPrice, int sales) {
		super();
		this.itemNo = itemNo;
		this.path = path;
		this.name = name;
		this.discount = discount;
		this.price = price;
		this.finalPrice = finalPrice;
		this.sales = sales;
	}




	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public int getFinalPrice() {
		return finalPrice;
	}


	public void setFinalPrice(int finalPrice) {
		this.finalPrice = finalPrice;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "MainVO [itemNo=" + itemNo + ", path=" + path + ", name=" + name + ", discount=" + discount + ", price="
				+ price + ", finalPrice=" + finalPrice + ", sales=" + sales + ", toString()=" + super.toString() + "]";
	}



	
}
