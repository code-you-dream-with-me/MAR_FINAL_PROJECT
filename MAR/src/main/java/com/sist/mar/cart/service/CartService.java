package com.sist.mar.cart.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.mar.cmn.DTO;

public interface CartService {

	int doDelete(String param) throws SQLException;
	
	int doInsert(DTO dto) throws SQLException; 
	
	int doUpdate(DTO dto) throws SQLException; 
	
	List<?> doRetrieve(String param) throws SQLException; 
	
	DTO doOrder(String param) throws SQLException; 
	
	int cartCheck(DTO dto) throws SQLException;
	
}
