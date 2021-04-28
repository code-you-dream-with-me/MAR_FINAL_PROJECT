package com.sist.mar.wishitem.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.mar.cmn.DTO;

public interface WishitemService {

	int doDelete(String param) throws SQLException;
	
	int doInsert(DTO dto) throws SQLException;
	
	DTO doSelectOne(String param) throws SQLException;
	
	List<?> doRetrieve(DTO dto) throws SQLException;
	
	int wishitemCheck(DTO dto) throws SQLException;
	
}
