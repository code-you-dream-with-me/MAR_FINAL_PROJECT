package com.sist.mar.item.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;


import com.sist.mar.cmn.DTO;
import com.sist.mar.item.domain.Item;

@Repository
public class ItemDaoImpl  {
	final static Logger LOG = LoggerFactory.getLogger(ItemDaoImpl.class);
	
	final String NAMESPACE = "com.sist.mar.item";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public ItemDaoImpl() {}


	/**
	 * 상품 전체 목록 조회
	 * @return
	 * @throws SQLException
	 */
	public List<?> getAllList() throws SQLException {
		String statement = this.NAMESPACE+".getAllList";
		
		List<Item> list = sqlSessionTemplate.selectList(statement);
		
		for(Item vo : list) {
			LOG.debug("vo :"+vo);
		}
		
		return list;
	}


	/**
	 * 관련 상품 목록 조회
	 * @param dto
	 * @return List
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	public List<?> getRelatedList(DTO dto) throws SQLException {
		Item inVO = (Item) dto;
		Item outVO = null;
		
		String statement = this.NAMESPACE+".getRelatedList";	
		
		List<Item> list = sqlSessionTemplate.selectList(statement, inVO);
		
		for(Item vo :list) {
			LOG.debug("vo"+vo);
		}
		
		return list;
	}


	/**
	 * 상품 단건 조회
	 * @param dto
	 * @return DTO
	 * @throws SQLException
	 */
	public DTO doSelectOne(DTO dto) throws SQLException {
		Item inVO = (Item) dto;
		Item outVO = null;
		
		String statement = NAMESPACE+".doSelectOne";
		LOG.debug("=inVO="+inVO);
		LOG.debug("=statement="+statement);
		
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);

		LOG.debug("=outVO="+outVO);
		
		if(null == outVO) {
			LOG.debug("=============================");
			LOG.debug("=null outVO ="+outVO);
			LOG.debug("=============================");			
			throw new EmptyResultDataAccessException("여기 EmptyResultDataAccessException",1);
		}
		
		return outVO;


	}

	/**
	 * 상품 수정
	 * @param dto
	 * @return int (성공:1, 실패:0)
	 * @throws SQLException
	 */
	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Item item = (Item) dto;
		
		String statement = NAMESPACE+".doUpdate";

        flag = sqlSessionTemplate.update(statement, item);
		return flag;
	}

	/**
	 * 상품 삭제
	 * @param dto
	 * @return int (성공:1, 실패:0)
	 * @throws SQLException
	 */
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Item item = (Item) dto;

		String statement = NAMESPACE+".doDelete";
		
		LOG.debug("=item="+item);
		LOG.debug("=statement="+statement);
		
		flag = this.sqlSessionTemplate.delete(statement, item);
		
		return flag;
	}
	

	/**
	 * 상품 등록
	 * @param dto
	 * @return int (성공:1, 실패:0)
	 * @throws SQLException
	 */
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Item item = (Item) dto;
		
		String statement = NAMESPACE+".doInsert";
		LOG.debug("=item="+item);
		LOG.debug("=statement="+statement);

		flag = this.sqlSessionTemplate.insert(statement, item);
		return flag;
	}


}
