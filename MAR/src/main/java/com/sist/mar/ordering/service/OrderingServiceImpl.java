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
	
}
