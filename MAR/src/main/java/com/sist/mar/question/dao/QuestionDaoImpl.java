package com.sist.mar.question.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.sist.mar.answer.domain.Answer;
import com.sist.mar.cmn.DTO;
import com.sist.mar.cmn.Search;
import com.sist.mar.question.domain.QuestionVO;


/* 
	mybatipse 오류 확인
		1. NAMESPACE + . + id 확인하기 (.이 빠지는지 꼭 주의해서 봐라)
		2. query 오류 확인
		3. resultType 확인
*/

@Repository
public class QuestionDaoImpl {
	
	final static Logger LOG = LoggerFactory.getLogger(QuestionDaoImpl.class);
	
	final String NAMESPACE = "com.sist.mar.question";		
	
	// xml에 존재(마이바티스를 위해 필요)
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	
	/*
	 * 답변 체크 (있으면 1, 없으면 0(=기본값))
	 */
	public int answerCheck(DTO dto) {
		
		int flag = 0;
		
		Answer question = (Answer) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".answerCheck";
		
		LOG.debug("============================");
		LOG.debug("=question=" + question);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.update(statement, question);
		return flag;
		
	}
	
	
	/**
	 * 질의 조회수 (doSelectOne시)
	 */
	public int doReadCnt(DTO dto)  throws SQLException{
		
		int flag = 0;
		QuestionVO question = (QuestionVO) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".doReadCnt";
		
		LOG.debug("============================");
		LOG.debug("=question=" + question);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.update(statement, question);
		return flag;
		
	}
	
	/**
	 * 질의 삭제
	 */
	public int doDelete(DTO dto) throws SQLException {
		
		int flag = 0;
		QuestionVO question = (QuestionVO) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".doDelete";
		
		LOG.debug("============================");
		LOG.debug("=question=" + question);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.delete(statement, question);
		return flag;
		
	}

	/**
	 * 질의 등록
	 */
	public int doInsert(DTO dto) throws SQLException {
		
		int flag = 0;
		QuestionVO question = (QuestionVO) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".doInsert";
		
		LOG.debug("============================");
		LOG.debug("=question=" + question);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.insert(statement, question);
		return flag;
		
	}

	/**
	 * 질의 조회
	 */
	public DTO doSelectOne(DTO dto) throws SQLException {
		
		QuestionVO inVO = (QuestionVO) dto;

		String statement = this.NAMESPACE + ".doSelectOne";
		
		LOG.debug("============================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		QuestionVO outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		
		LOG.debug("============================");
		LOG.debug("=outVO=" + outVO);
		LOG.debug("============================");
		
		if(null == outVO) {
			LOG.debug("++++++++++++++++++++++++++++");
			LOG.debug("=null == outVO : " + outVO);
			LOG.debug("++++++++++++++++++++++++++++");
			throw new EmptyResultDataAccessException("여기 EmptyResultDataAccessException",1);
		}
		
		return outVO;
	}

	/**
	 * 질의 수정
	 */
	public int doUpdate(DTO dto) throws SQLException {
		
		int flag = 0;
		QuestionVO question = (QuestionVO) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".doUpdate";
		
		LOG.debug("============================");
		LOG.debug("=question=" + question);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.update(statement, question);
		return flag;
		
	}

	/**
	 * 질의목록 조회 (searchDiv : 10(소비자용), 20(관리자용))
	 */
	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		List<?> list = null;
		
		Search search = (Search) dto;
		
		String statement = this.NAMESPACE + ".doRetrieve";
		
		LOG.debug("============================");
		LOG.debug("=search=" + search);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		list = sqlSessionTemplate.selectList(statement, search);
		
		for(Object vo : list) {
			LOG.debug("vo : " + vo);
		}
		
		return list;
	}

}
