package com.sist.mar.question.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.cmn.DTO;
import com.sist.mar.question.dao.QuestionDaoImpl;

@Service
public class QuestionServiceImpl {
	
	@Autowired
	QuestionDaoImpl questionDaoImpl;
	
	
	public QuestionServiceImpl(){}
	
	
	public int doDelete(DTO dto) throws SQLException {
		
		return questionDaoImpl.doDelete(dto);
	}


	public int doInsert(DTO dto) throws SQLException {
		
		return questionDaoImpl.doInsert(dto);
	}


	public DTO doSelectOne(DTO dto) throws SQLException {
		
		return questionDaoImpl.doSelectOne(dto);
	}


	public int doUpdate(DTO dto) throws SQLException {
		
		return questionDaoImpl.doUpdate(dto);
	}


	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		return questionDaoImpl.doRetrieve(dto);
	}
}
