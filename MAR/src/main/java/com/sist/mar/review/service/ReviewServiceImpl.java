package com.sist.mar.review.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.cmn.DTO;
import com.sist.mar.review.dao.ReviewDaoImpl;

@Service
public class ReviewServiceImpl{

	@Autowired
	ReviewDaoImpl reviewDaoImpl;
	
	public ReviewServiceImpl(){}

	

	public int doReadCnt(DTO dto) throws SQLException {
		
		return reviewDaoImpl.doReadCnt(dto);
	}
	

	public int doDelete(DTO dto) throws SQLException {
		
		return reviewDaoImpl.doDelete(dto);
	}


	public int doInsert(DTO dto) throws SQLException {
		
		return reviewDaoImpl.doInsert(dto);
	}


	public DTO doSelectOne(DTO dto) throws SQLException {
		
		return reviewDaoImpl.doSelectOne(dto);
	}


	public int doUpdate(DTO dto) throws SQLException {
		
		return reviewDaoImpl.doUpdate(dto);
	}


	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		return reviewDaoImpl.doRetrieve(dto);
	}
	
	
//	public List<?> doRetrieveSelf(DTO dto) throws SQLException {
//		
//		return reviewDaoImpl.doRetrieveSelf(dto);
//	}
	
}
