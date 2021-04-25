package com.sist.mar.code.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mar.code.dao.CodeDaoImpl;

@Repository
public class CodeDaoImpl {

	final static Logger LOG = LoggerFactory.getLogger(CodeDaoImpl.class); 
	
	final String NAMESPACE = "com.sist.mar.code"; 
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;//org.mybatis.spring.SqlSessionTemplate(mapper을 실행할 클래스)
	
	public List<?> getCodeRetrieve(Map<String,Object> code) throws SQLException {
		
		String statement = NAMESPACE+""+".doRetrieve";
		Map<String,Object> codeMap = code;
		return sqlSessionTemplate.selectList(statement, codeMap);
	}
	
}
