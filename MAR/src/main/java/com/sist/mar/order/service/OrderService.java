package com.sist.mar.order.service;

import java.sql.SQLException;

import com.sist.mar.cmn.DTO;
import com.sist.mar.order.domain.Ordering;

public interface OrderService {

	int doInsertOrdering(DTO dto) throws SQLException;
	
	int doInsertOrderitem(String memberId, int orderNo) throws SQLException; 
	
	int doSelectOneOrderNo(String param) throws SQLException; 
}
