package com.sist.mar.cmn;
/**
 * 모든 VO의 Parent
 * @author sist
 *
 */
public class DTO {
	
	//item 테이블
	private String item_name;        //wishitem & cart 출력용
	private int item_price;          //wishitem & cart 출력용
	private int item_discount;       //wishitem & cart 출력용
	private int item_final_price;    //wishitem & cart 출력용
	//image 테이블
	private String image_save_name;  //wishitem & cart 출력용
	private String image_path;       //wishitem & cart 출력용
	//공통
	private int num;                 //번호
	private int orderItemCnt;        //해당주문의 총상품 종류번호
	private int totalCnt;            //총글수
	//주문상품조회
	private String itemName;			 //상품명(주문번호를 단서로 otherItem 쿼리 뽑고, 그곳의 상품번호를 단서로 상품명을 뽑는건 3중 조인으로 가능한데, 
									 //		문제는 otheritem vo에 이를 받을 관련변수 없어서 추가)
	
	
	public DTO() {}

	public DTO(String item_name, int item_price, int item_discount, int item_final_price, String image_save_name,
			String image_path, int num, int totalCnt) {
		super();
		this.item_name = item_name;
		this.item_price = item_price;
		this.item_discount = item_discount;
		this.item_final_price = item_final_price;
		this.image_save_name = image_save_name;
		this.image_path = image_path;
		this.num = num;
		this.orderItemCnt = orderItemCnt;
		this.totalCnt = totalCnt;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public int getItem_discount() {
		return item_discount;
	}

	public void setItem_discount(int item_discount) {
		this.item_discount = item_discount;
	}

	public int getItem_final_price() {
		return item_final_price;
	}

	public void setItem_final_price(int item_final_price) {
		this.item_final_price = item_final_price;
	}

	public String getImage_save_name() {
		return image_save_name;
	}

	public void setImage_save_name(String image_save_name) {
		this.image_save_name = image_save_name;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	
	public int getOrderItemCnt() {
		return orderItemCnt;
	}

	public void setOrderItemCnt(int orderItemCnt) {
		this.orderItemCnt = orderItemCnt;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	@Override
	public String toString() {
		return "DTO [item_name=" + item_name + ", item_price=" + item_price + ", item_discount=" + item_discount
				+ ", item_final_price=" + item_final_price + ", image_save_name=" + image_save_name + ", image_path="
				+ image_path + ", num=" + num + ", orderItemCnt=" + orderItemCnt + ", totalCnt=" + totalCnt + "]";
	}

	
}
