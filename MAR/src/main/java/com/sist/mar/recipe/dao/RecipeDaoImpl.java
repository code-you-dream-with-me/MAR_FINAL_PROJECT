package com.sist.mar.recipe.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mar.cmn.DTO;
import com.sist.mar.recipe.domain.RecipeVO;
import com.sist.mar.recipe.domain.SimpleItemVO;

@Repository
public class RecipeDaoImpl {

//	▼ 변수 ===============================================================
	
	final static Logger LOG = LoggerFactory.getLogger(RecipeDaoImpl.class);
	final String NAMESPACE = "com.sist.mar.recipe";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
//	▼ 생성자 ==============================================================
	
	public RecipeDaoImpl() {}
	
	
//	▼ 메소드 ===============================================================

	public int doInsert(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.insert(this.NAMESPACE + ".doInsert", (RecipeVO) dto);
	}
	
	public int doDelete(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.delete(this.NAMESPACE + ".doDelete", (RecipeVO) dto);
	}
	
	public int doUpdate(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.update(this.NAMESPACE + ".doUpdate", (RecipeVO) dto);
	}
	
	public RecipeVO doSelectOne(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.selectOne(this.NAMESPACE + ".doSelectOne", (RecipeVO) dto);
	}
	
	public int doCountReadCnt(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.update(this.NAMESPACE + ".doCountReadCnt", (RecipeVO) dto);
	}
	
	public List<SimpleItemVO> doRetrieveRelevantItem(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.selectList(this.NAMESPACE + ".doRetrieveRelevantItem", (RecipeVO) dto);
	}
	
}
