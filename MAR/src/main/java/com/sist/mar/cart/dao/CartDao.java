package com.sist.mar.cart.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mar.cart.domain.Cart;
import com.sist.mar.cmn.DTO;

@Repository
public class CartDao  {

	final static Logger LOG = LoggerFactory.getLogger(CartDao.class);

	final String NAMESPACE = "com.sist.mar.cart";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public CartDao() {}
//=============================================================================
	/**
	 * 늘사는것 팝업창 -> 장바구니 (늘사는것목록페이지 장바구니담기버튼) 
	 * param = cart_no
	 * return flag(성공=1/실패=0)
	 */
	public int doDelete(String param) throws SQLException {
		String statemet = NAMESPACE + ".doDelete";
		return this.sqlSessionTemplate.delete(statemet, param);
	}

//=============================================================================
	/**
	 * 1. 늘사는것 팝업창 -> 장바구니 (늘사는것목록페이지 장바구니담기버튼) // 2. 상품상세페이지 -> 장바구니 
	 * param = cart_no, member_id, item_no, quantity
	 * return 1(성공)/0(실패)
	 */
	public int doInsert(DTO dto) throws SQLException {
		Cart cart = (Cart) dto;
		String statement = this.NAMESPACE + ".doInsert";
		return this.sqlSessionTemplate.insert(statement, cart);
	}
	
//=============================================================================
	/**
	 * 장바구니 수량 변경 (장바구니에 담긴 상품 수량 변경)
	 * param = quantity(변경될 수량값) , cart_no(해당 장바구니번호)
	 * return 1(성공)/0(실패)
	 */
	public int doUpdate(DTO dto) throws SQLException {
		Cart cart = (Cart) dto;
		String statemet = NAMESPACE + ".doUpdate";
		return this.sqlSessionTemplate.update(statemet, cart);
	}
	
//=============================================================================
	/**
	 * 장바구니 목록 (장바구니페이지)
	 * param = member_id
	 * return List<?>
	 */
	public List<?> doRetrieve(String param) throws SQLException {
		String statement = this.NAMESPACE + ".doRetrieve";
		return this.sqlSessionTemplate.selectList(statement, param);
	}
	
//=============================================================================
	/**
	 * 주문페이지 회원정보
	 * param = member_id(String으로 받음)
	 * return DTO(=member)
	 */
	public DTO doOrder(String param) throws SQLException {
		String statement = this.NAMESPACE + ".doOrder";
		return this.sqlSessionTemplate.selectOne(statement, param);
	}
	
//=============================================================================
	/**
	 * cart에 이미 들어있는 상품 체크
	 * param = cart(item_no, member_id)
	 * return int
	 * */
	public int cartCheck(DTO dto) throws SQLException {
		Cart cart = (Cart) dto;
		String statement = this.NAMESPACE + ".cartCheck";
		return this.sqlSessionTemplate.selectOne(statement, cart);
	}
	
//=============================================================================



}
