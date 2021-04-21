package com.sist.mar.main.domain;

import com.sist.mar.cmn.DTO;

public class CategoryVO extends DTO {
	
	private int	   categoryNo; //카테고리번호
	private String categoryNm; //카테고리명
	
	
	
	public CategoryVO() {}

	public CategoryVO(int categoryNo, String categoryNm) {
		super();
		this.categoryNo = categoryNo;
		this.categoryNm = categoryNm;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryNm() {
		return categoryNm;
	}

	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}

	@Override
	public String toString() {
		return "CategoryVO [categoryNo=" + categoryNo + ", categoryNm=" + categoryNm + ", toString()="
				+ super.toString() + "]";
	}
	
	

}
