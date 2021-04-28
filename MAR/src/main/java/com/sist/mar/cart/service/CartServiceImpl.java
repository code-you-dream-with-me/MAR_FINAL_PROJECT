package com.sist.mar.cart.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.cart.dao.CartDao;
import com.sist.mar.cmn.DTO;

@Service
public class CartServiceImpl implements CartService {

	final Logger LOG = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	public CartDao cartDao;
	
	public CartServiceImpl() {}
	
	// dao에 있는 삭제, 등록, 단건조회, 목록조회
	@Override
	public int doDelete(String param) throws SQLException {
		return this.cartDao.doDelete(param);
	}
	
	@Override
	public int doInsert(DTO dto) throws SQLException {
		return this.cartDao.doInsert(dto);
	}
	
	@Override
	public int doUpdate(DTO dto) throws SQLException {
		return this.cartDao.doUpdate(dto);
	}
	
	@Override
	public List<?> doRetrieve(String param) throws SQLException {
		return this.cartDao.doRetrieve(param);
	}
	
	@Override
	public DTO doOrder(String param) throws SQLException {
		return this.cartDao.doOrder(param);
	}
	
	@Override
	public int cartCheck(DTO dto) throws SQLException {
		return this.cartDao.cartCheck(dto);
	}
}
