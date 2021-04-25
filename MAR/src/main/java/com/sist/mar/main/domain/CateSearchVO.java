package com.sist.mar.main.domain;

import com.sist.mar.cmn.DTO;

public class CateSearchVO extends DTO {

	private int	   categoryNo; //카테고리번호
	private String listDiv;    //목록분류 (카테고리별목록10, 신상품목록20, 베스트목록30, 알뜰목록40)
	private String searchWord; //검색어(상품명만 검색)
	private int    pageSize;   //페이지 사이즈
	private int    pageNum;    //페이지 num
	
	
	public CateSearchVO() {}


	public CateSearchVO(int categoryNo, String listDiv, String searchWord, int pageSize, int pageNum) {
		super();
		this.categoryNo = categoryNo;
		this.listDiv = listDiv;
		this.searchWord = searchWord;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}


	public int getCategoryNo() {
		return categoryNo;
	}


	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}


	public String getListDiv() {
		return listDiv;
	}


	public void setListDiv(String listDiv) {
		this.listDiv = listDiv;
	}


	public String getSearchWord() {
		return searchWord;
	}


	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	@Override
	public String toString() {
		return "CateSearchVO [categoryNo=" + categoryNo + ", listDiv=" + listDiv + ", searchWord=" + searchWord
				+ ", pageSize=" + pageSize + ", pageNum=" + pageNum + ", toString()=" + super.toString() + "]";
	}


	
	
	
}
