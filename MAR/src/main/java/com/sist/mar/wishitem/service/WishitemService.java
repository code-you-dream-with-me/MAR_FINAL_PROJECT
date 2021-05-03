package com.sist.mar.wishitem.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.mar.cmn.DTO;

public interface WishitemService {
	//다형성과 AOP, 트랜잭션 처리를 위해 인터페이스 생성

	int doDelete(String param) throws SQLException;
	
	int doInsert(DTO dto) throws SQLException;
	
	DTO doSelectOne(String param) throws SQLException;
	
	List<?> doRetrieve(DTO dto) throws SQLException;
	
	int wishitemCheck(DTO dto) throws SQLException;
	
}
