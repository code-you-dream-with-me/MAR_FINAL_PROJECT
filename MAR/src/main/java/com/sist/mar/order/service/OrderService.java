package com.sist.mar.order.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mar.cmn.DTO;
import com.sist.mar.order.dao.OrderDao;


@Repository
public class OrderService {

	final Logger LOG = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	public OrderDao orderDao;
	
	public OrderService() {}
	
	// 주문 --------------------------------------------------------
	public int doInsertOrdering(DTO dto) throws SQLException {
		return this.orderDao.doInsertOrdering(dto);
	}
	public int doInsertOrderitem(DTO dto) throws SQLException {
		return this.orderDao.doInsertOrderitem(dto);
	}
	public int doSelectOneOrderNo(String param) throws SQLException {
		return this.orderDao.doSelectOneOrderNo(param);
	}
	public int doDeleteCart(String param) throws SQLException {
		return this.orderDao.doDeleteCart(param);
	}
	//-----------------------------------------------------------
}
