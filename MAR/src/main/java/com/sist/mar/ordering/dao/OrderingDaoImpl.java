package com.sist.mar.ordering.dao;

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
import com.sist.mar.order.domain.Ordering;
import com.sist.mar.order.domain.Orderitem;


@Repository
public class OrderingDaoImpl {

	final static Logger LOG = LoggerFactory.getLogger(OrderingDaoImpl.class);

	final String NAMESPACE = "com.sist.mar.ordering";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public OrderingDaoImpl() {}

	
	/**
	 * 전체 주문내역 목록 조회
	 * */
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
	 * 주문 취소 요청 기능 (주문상태 1 -> 2로... 관리자가 승인하면 3으로 변경)
	 */
	public int doUndoRequest(DTO dto) {
		
		int flag = 0;
		Ordering ordering = (Ordering) dto;
		
		// mybatis sql : NAMESPACE + . + id;
		String statement = this.NAMESPACE + ".doUndoRequest";
		
		LOG.debug("============================");
		LOG.debug("=ordering=" + ordering);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.update(statement, ordering);
		return flag;
		
	}
	
	/**
	 * 주문단건조회
	 */
	public DTO doSelectOne(DTO dto) throws SQLException {
		
		Ordering inVO = (Ordering) dto;

		String statement = this.NAMESPACE + ".doSelectOne";
		
		LOG.debug("============================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		Ordering outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		
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
	 * 주문상품조회
	 */
	public List<?> doSelectItemList(DTO dto) throws SQLException {
		
		List<?> list = null;
		Orderitem orderitem = (Orderitem) dto;
		
		String statement = this.NAMESPACE + ".doSelectItemList";
		LOG.debug("============================");
		LOG.debug("=orderitem=" + orderitem);
		LOG.debug("=statement=" + statement);
		LOG.debug("============================");
		
		list = this.sqlSessionTemplate.selectList(statement, orderitem);
		
		LOG.debug("============================");
		LOG.debug("=list=" + list);
		LOG.debug("============================");
		
		for(Object vo : list) {
			LOG.debug("vo : " + vo);
		}
		
		return list;
	
//		[단축]
//		User inVO = (User) dto;
//		User outVO = null;
//		
//		String statement = this.NAMESPACE + ".doSelectOne";
//		
//		return this.sqlSessionTemplate.selectOne(statement, inVO);
	}	
	
	
	//item_sales 업데이트 ==========================================================================
	/* 1. 회원에 따른 주문테이블 조회 */
	public List<?> doRetrieveOrdering(DTO dto) throws SQLException {
		Ordering ordering = (Ordering) dto;
		String statement = this.NAMESPACE + ".yebin_doRetrieve_ordering";
		return this.sqlSessionTemplate.selectList(statement, ordering);
	}
	/* 3. 주문번호에 따른 수량 조회 */
	public List<?> doRetrieveOrderitem(DTO dto) throws SQLException {
		Orderitem orderitem = (Orderitem) dto;
		String statement = this.NAMESPACE + ".yebin_doRetrieve_orderitem";
		return this.sqlSessionTemplate.selectList(statement, orderitem);
	}
	/* 2. 1일 이상이 지난 주문건에 대해서 주문상태 4로 업데이트 */
	public int doUpdateOrdering(DTO dto) throws SQLException {
		Ordering ordering = (Ordering) dto;
		String statement = this.NAMESPACE + ".yebin_doUpdate_Ordering";
		return this.sqlSessionTemplate.update(statement, ordering);
	}
	/* 4. 조회된 수량으로 sales 업데이트 */
	public int doUpdateItem(DTO dto) throws SQLException {
		Orderitem orderitem = (Orderitem) dto;
		String statement = this.NAMESPACE + ".yebin_doUpdate_item";
		return this.sqlSessionTemplate.update(statement, orderitem);
	}
	//===========================================================================================
}
