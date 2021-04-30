package com.sist.mar.main.domain;

import com.sist.mar.cmn.DTO;

public class MainRecipeVO extends DTO{
	
	private int 	recipeNo  ;//레시피번호
	private String 	path      ;//이미지경로
	private String 	regId     ;//등록자id
	private String 	title     ;//제목
	private int 	readCnt   ;//조회수
	private String 	regDt     ;//작성일
	private String 	modDt     ;//수정일
	
	public MainRecipeVO() {}

	public MainRecipeVO(int recipeNo, String path, String regId, String title, int readCnt, String regDt,
			String modDt) {
		super();
		this.recipeNo = recipeNo;
		this.path = path;
		this.regId = regId;
		this.title = title;
		this.readCnt = readCnt;
		this.regDt = regDt;
		this.modDt = modDt;
	}

	public int getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "MainRecipeVO [recipeNo=" + recipeNo + ", path=" + path + ", regId=" + regId + ", title=" + title
				+ ", readCnt=" + readCnt + ", regDt=" + regDt + ", modDt=" + modDt + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}