package com.sist.mar.order.dao;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mar.cmn.DTO;
import com.sist.mar.order.domain.Ordering;
import com.sist.mar.order.domain.Orderitem;


@Repository
public class OrderDao {

	final static Logger LOG = LoggerFactory.getLogger(OrderDao.class);

	final String NAMESPACE = "com.sist.mar.order";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public OrderDao() {}
	
//=============================================================================
	// ★★★ 결제버튼시 doInsertOrdering -> doInsertOrderitem -> doDeleteCart ★★★
	
	//---------------------------------------------------------
	/**
	 * 주문내역 등록
	 * param = Ordering 전부
	 * return 1(성공)/0(실패)
	 */
	public int doInsertOrdering(DTO dto) throws SQLException {
		Ordering ordering = (Ordering) dto;
		String statement = this.NAMESPACE + ".doInsertOrdering";
		return this.sqlSessionTemplate.insert(statement, ordering);
	}
	
	//---------------------------------------------------------
	/**
	 * 주문상품입력시 필요한 orderNo 조회
	 * param = memberId
	 * return 1(성공)/0(실패)
	 */
	public int doSelectOneOrderNo(String param) throws SQLException {
		String statement = this.NAMESPACE + ".doSelectOneOrderNo";
		return this.sqlSessionTemplate.selectOne(statement, param);
	}
	
	//---------------------------------------------------------
	/**
	 * 주문상품 등록
	 * param = Orderitem 전부
	 * return 1(성공)/0(실패)
	 */
	public int doInsertOrderitem(DTO dto) throws SQLException {
		Orderitem orderitem = (Orderitem) dto;
		String statement = this.NAMESPACE + ".doInsertOrderitem";
		return this.sqlSessionTemplate.insert(statement, orderitem);

	}
	
	//---------------------------------------------------------
	/**
	 * 주문내역 등록
	 * param = member_id(아이디로 검색하여 모든 내역 삭제)
	 * return 1(성공)/0(실패)
	 */
	public int doDeleteCart(String param) throws SQLException {
		String statemet = NAMESPACE + ".doDeleteCart";
		return this.sqlSessionTemplate.delete(statemet, param);
	}
	
//=============================================================================

}
