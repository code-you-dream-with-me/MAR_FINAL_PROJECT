package com.sist.mar.admin.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mar.cmn.DTO;
import com.sist.mar.item.domain.Item;
import com.sist.mar.order.domain.Ordering;
import com.sist.mar.recipe.domain.RecipeVO;

@Repository
public class AdminDaoImpl {

//	▼ 변수 ===============================================================
	final static Logger LOG = LoggerFactory.getLogger(AdminDaoImpl.class);
	final String NAMESPACE = "com.sist.mar.admin";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
//	▼ 생성자 ==============================================================
	
	public AdminDaoImpl() {}
	
	
//	▼ 메소드 ===============================================================
	
	public List<RecipeVO> doRetrieveReicpe(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.selectList(this.NAMESPACE + ".doRetrieveReicpe", dto);
	}
	
	public List<Item> doRetrieveItem(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.selectList(this.NAMESPACE + ".doRetrieveItem", dto);
	}
	
	public int doDiscountItem(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.update(this.NAMESPACE + ".doDiscountItem", dto);
	}
	
	public List<Ordering> doRetrieveOrdering(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.selectList(this.NAMESPACE + ".doRetrieveOrdering", dto);
	}
	
	
}
