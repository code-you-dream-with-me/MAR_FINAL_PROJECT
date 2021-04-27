package com.sist.mar.recipe.domain;

import com.sist.mar.cmn.DTO;

public class SimpleItemVO extends DTO {

	private int    SimpleItemNo;
	private String SimpleItemName;
	private int    SimpleItemPrice;
	private int    SimpleItemImageNo;
	private String SimpleItemImagePath;
	private String SimpleItemImageName;
	
	public SimpleItemVO() {}

	public SimpleItemVO(int simpleItemNo, String simpleItemName, int simpleItemPrice, int simpleItemImageNo,
			String simpleItemImagePath, String simpleItemImageName) {
		super();
		SimpleItemNo = simpleItemNo;
		SimpleItemName = simpleItemName;
		SimpleItemPrice = simpleItemPrice;
		SimpleItemImageNo = simpleItemImageNo;
		SimpleItemImagePath = simpleItemImagePath;
		SimpleItemImageName = simpleItemImageName;
	}

	public int getSimpleItemNo() {
		return SimpleItemNo;
	}

	public void setSimpleItemNo(int simpleItemNo) {
		SimpleItemNo = simpleItemNo;
	}

	public String getSimpleItemName() {
		return SimpleItemName;
	}

	public void setSimpleItemName(String simpleItemName) {
		SimpleItemName = simpleItemName;
	}

	public int getSimpleItemPrice() {
		return SimpleItemPrice;
	}

	public void setSimpleItemPrice(int simpleItemPrice) {
		SimpleItemPrice = simpleItemPrice;
	}

	public int getSimpleItemImageNo() {
		return SimpleItemImageNo;
	}

	public void setSimpleItemImageNo(int simpleItemImageNo) {
		SimpleItemImageNo = simpleItemImageNo;
	}

	public String getSimpleItemImagePath() {
		return SimpleItemImagePath;
	}

	public void setSimpleItemImagePath(String simpleItemImagePath) {
		SimpleItemImagePath = simpleItemImagePath;
	}

	public String getSimpleItemImageName() {
		return SimpleItemImageName;
	}

	public void setSimpleItemImageName(String simpleItemImageName) {
		SimpleItemImageName = simpleItemImageName;
	}

	@Override
	public String toString() {
		return "SimpleItemVO [SimpleItemNo=" + SimpleItemNo + ", SimpleItemName=" + SimpleItemName
				+ ", SimpleItemPrice=" + SimpleItemPrice + ", SimpleItemImageNo=" + SimpleItemImageNo
				+ ", SimpleItemImagePath=" + SimpleItemImagePath + ", SimpleItemImageName=" + SimpleItemImageName
				+ ", toString()=" + super.toString() + "]";
	}
	
}
