package com.sist.mar.review.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.sist.mar.cmn.DTO;
import com.sist.mar.cmn.Search;
import com.sist.mar.order.domain.Orderitem;
import com.sist.mar.review.domain.ReviewVO;

/* 
	mybatipse 오류 확인
		1. NAMESPACE + . + id 확인하기 (.이 빠지는지 꼭 주의해서 봐라)
		2. query 오류 확인
		3. resultType 확인
*/

@Repository
public class ReviewDaoImpl {

	final static Logger LOG = LoggerFactory.getLogger(ReviewDaoImpl.class);
	
	final String NAMESPACE = "com.sist.mar.review";		
	
	// xml에 존재(마이바티스를 위해 필요)
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public ReviewDaoImpl() {
		
	}
	
	/**
	 * 후기 조회수 (doSelectOne시)
	 */
	public int doReadCnt(DTO dto)  throws SQLException{
		
		int flag = 0;
		ReviewVO review = (ReviewVO) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".doReadCnt";
		
		LOG.debug("============================");
		LOG.debug("=review=" + review);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.update(statement, review);
		return flag;
		
	}
	
	
	/**
	 * 후기 작성시 orderItem의 reviewState 2 -> 1로 변환 
	 */
	public int doReviewStateDel(DTO dto) {
		
		int flag = 0;
		Orderitem orderitem = (Orderitem) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".doReviewStateDel";
		
		LOG.debug("============================");
		LOG.debug("=orderitem=" + orderitem);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.update(statement, orderitem);
		return flag;
		
	}
	
	
	/**
	 * 후기 삭제
	 */
	public int doDelete(DTO dto) throws SQLException {
		
		int flag = 0;
		ReviewVO review = (ReviewVO) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".doDelete";
		
		LOG.debug("============================");
		LOG.debug("=review=" + review);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.delete(statement, review);
		return flag;
		
	}

	
	/**
	 * 후기 작성시 orderItem의 reviewState 1 -> 2로 변환 
	 */
	public int doReviewStateInsert(DTO dto) {
		
		int flag = 0;
		Orderitem orderitem = (Orderitem) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".doReviewStateInsert";
		
		LOG.debug("============================");
		LOG.debug("=orderitem=" + orderitem);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.update(statement, orderitem);
		return flag;
		
	}
	
	/**
	 * 후기 등록
	 */
	public int doInsert(DTO dto) throws SQLException {
		
		int flag = 0;
		ReviewVO review = (ReviewVO) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".doInsert";
		
		LOG.debug("============================");
		LOG.debug("=review=" + review);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.insert(statement, review);
		return flag;
		
	}

	
	/**
	 * 후기 수정
	 */
	public DTO doSelectMyOne(DTO dto) throws SQLException {
		
		ReviewVO inVO = (ReviewVO) dto;

		String statement = this.NAMESPACE + ".doSelectMyOne";
		
		LOG.debug("============================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		ReviewVO outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		
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
	 * 후기 조회
	 */
	public DTO doSelectOne(DTO dto) throws SQLException {
		
		ReviewVO inVO = (ReviewVO) dto;

		String statement = this.NAMESPACE + ".doSelectOne";
		
		LOG.debug("============================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		ReviewVO outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		
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
	 * 후기 수정
	 */
	public int doUpdate(DTO dto) throws SQLException {
		
		int flag = 0;
		ReviewVO review = (ReviewVO) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".doUpdate";
		
		LOG.debug("============================");
		LOG.debug("=review=" + review);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.update(statement, review);
		return flag;
		
	}

	/**
	 * 상품페이지용) 전체 후기목록 조회
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
	
	/**
	 * 소비자용) 본인이 쓴 후기목록 조회
	 */
	public List<?> doRetrieveSelf(DTO dto) throws SQLException {
		
		List<?> list = null;
		
		Search search = (Search) dto;
		
		String statement = this.NAMESPACE + ".doRetrieveSelf";
		
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
