package com.sist.mar.code.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.code.dao.CodeDaoImpl;

@Service
public class CodeServiceImpl implements CodeService {
	
	@Autowired
	CodeDaoImpl codeDaoImpl;
	
	@Override
	public List<?> getCodeRetrieve(Map<String, Object> code) throws SQLException {
		
		return codeDaoImpl.getCodeRetrieve(code);
	}

}
