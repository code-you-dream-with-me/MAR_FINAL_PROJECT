package com.sist.mar.recipe.domain;

import com.sist.mar.cmn.DTO;

public class RecipeVO extends DTO {
	
	private int    recipeNo;
	private String regId;
	private String title;
	private String contents;
	private int    readCnt;
	private String ingredients;
	private String urlAddr;
	private String regDt;
	private String modDt;
	
	public RecipeVO() {}

	public RecipeVO(int recipeNo, String regId, String title, String contents, int readCnt, String ingredients,
			String urlAddr, String regDt, String modDt) {
		super();
		this.recipeNo = recipeNo;
		this.regId = regId;
		this.title = title;
		this.contents = contents;
		this.readCnt = readCnt;
		this.ingredients = ingredients;
		this.urlAddr = urlAddr;
		this.regDt = regDt;
		this.modDt = modDt;
	}

	public int getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getUrlAddr() {
		return urlAddr;
	}

	public void setUrlAddr(String urlAddr) {
		this.urlAddr = urlAddr;
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
		return "recipeVO [recipeNo=" + recipeNo + ", regId=" + regId + ", title=" + title + ", contents=" + contents
				+ ", readCnt=" + readCnt + ", ingredients=" + ingredients + ", urlAddr=" + urlAddr + ", regDt=" + regDt
				+ ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}
	
}
