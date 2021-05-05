package com.sist.mar.ordering.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.cmn.DTO;
import com.sist.mar.order.domain.Ordering;
import com.sist.mar.order.domain.Orderitem;
import com.sist.mar.ordering.dao.OrderingDaoImpl;

@Service
public class OrderingServiceImpl {
	
	public OrderingServiceImpl(){};

	@Autowired
	OrderingDaoImpl orderingDaoImpl;
	
	
	public List<?> doSelectItemList(DTO dto) throws SQLException {
		
		return orderingDaoImpl.doSelectItemList(dto);
	}
	
	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		return orderingDaoImpl.doRetrieve(dto);
	}

	public int doUndoRequest(DTO dto) {
	
		return orderingDaoImpl.doUndoRequest(dto);
	}

	public DTO doSelectOne(DTO dto) throws SQLException {
		
		return orderingDaoImpl.doSelectOne(dto);
	}
	
	//item_sales 업데이트 ==========================================================================
	/* 1. 회원에 따른 주문테이블 조회 */
	public List<?> doRetrieveOrdering(DTO dto) throws SQLException {
		return orderingDaoImpl.doRetrieveOrdering(dto);
	}
	/* 3. 주문번호에 따른 수량 조회 */
	public List<?> doRetrieveOrderitem(DTO dto) throws SQLException {
		return orderingDaoImpl.doRetrieveOrderitem(dto);
	}
	/* 2. 1일 이상이 지난 주문건에 대해서 주문상태 4로 업데이트 */
	public int doUpdateOrdering(DTO dto) throws SQLException {
		return orderingDaoImpl.doUpdateOrdering(dto);
	}
	/* 4. 조회된 수량으로 sales 업데이트 */
	public int doUpdateItem(DTO dto) throws SQLException {
		return orderingDaoImpl.doUpdateItem(dto);
	}
	//===========================================================================================
	
}
