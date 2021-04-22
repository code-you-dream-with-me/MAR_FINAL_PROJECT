package com.sist.mar.answer.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.answer.dao.AnswerDaoImpl;
import com.sist.mar.cmn.DTO;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerDaoImpl answerDaoImpl;
	
	@Override
	public List<?> getAllList() throws SQLException {
		return answerDaoImpl.getAllList();
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		return answerDaoImpl.doSelectOne(dto);
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {
		return answerDaoImpl.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		return answerDaoImpl.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		return answerDaoImpl.doInsert(dto);
	}

}
