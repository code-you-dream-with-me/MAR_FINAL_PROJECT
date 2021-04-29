package com.sist.mar.item.domain;

import com.sist.mar.cmn.DTO;

public class Item extends DTO {
	
	private int 	itemNo;
	private String 	name;
	private int 	price;
	private String 	production;
	private String 	weight;
	private String 	expired;
	private String 	detail;
	private int		discount;
	private int 	finalPrice;
	private int 	sales;
	private int 	categoryNo;
	private String 	regId;
	private String	regDt;
	private String	modDt;
	
	private int mainImage;
	private String path;
	private String saveName;    

	
	public Item () {}


	public Item(int itemNo, String name, int price, String production, String weight, String expired, String detail,
			int discount, int finalPrice, int sales, int categoryNo, String regId, String regDt, String modDt,
			int mainImage, String path, String saveName) {
		super();
		this.itemNo = itemNo;
		this.name = name;
		this.price = price;
		this.production = production;
		this.weight = weight;
		this.expired = expired;
		this.detail = detail;
		this.discount = discount;
		this.finalPrice = finalPrice;
		this.sales = sales;
		this.categoryNo = categoryNo;
		this.regId = regId;
		this.regDt = regDt;
		this.modDt = modDt;
		this.mainImage = mainImage;
		this.path = path;
		this.saveName = saveName;
	}


	public int getItemNo() {
		return itemNo;
	}


	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getProduction() {
		return production;
	}


	public void setProduction(String production) {
		this.production = production;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getExpired() {
		return expired;
	}


	public void setExpired(String expired) {
		this.expired = expired;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
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


	public int getCategoryNo() {
		return categoryNo;
	}


	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}


	public String getRegId() {
		return regId;
	}


	public void setRegId(String regId) {
		this.regId = regId;
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


	public int getMainImage() {
		return mainImage;
	}


	public void setMainImage(int mainImage) {
		this.mainImage = mainImage;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getSaveName() {
		return saveName;
	}


	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}


	@Override
	public String toString() {
		return "Item [itemNo=" + itemNo + ", name=" + name + ", price=" + price + ", production=" + production
				+ ", weight=" + weight + ", expired=" + expired + ", detail=" + detail + ", discount=" + discount
				+ ", finalPrice=" + finalPrice + ", sales=" + sales + ", categoryNo=" + categoryNo + ", regId=" + regId
				+ ", regDt=" + regDt + ", modDt=" + modDt + ", mainImage=" + mainImage + ", path=" + path
				+ ", saveName=" + saveName + ", toString()=" + super.toString() + "]";
	}






	
}
