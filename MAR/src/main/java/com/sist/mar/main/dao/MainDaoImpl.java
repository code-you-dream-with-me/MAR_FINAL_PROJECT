package com.sist.mar.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sist.mar.cmn.DTO;
import com.sist.mar.cmn.Search;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.main.domain.CateSearchVO;
import com.sist.mar.main.domain.MainRecipeVO;
import com.sist.mar.main.domain.MainVO;



@Repository
public class MainDaoImpl {
	
	final Logger LOG = LoggerFactory.getLogger(MainDaoImpl.class);
	
	//mybatis---------------------------------------------------------------------------
	final String NAMESPACE = "com.sist.mar.main"; //com.sist.ehr.main.doRetrieve
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;//org.mybatis.spring.SqlSessionTemplate(mapper을 실행할 클래스)
	//mybatis---------------------------------------------------------------------------
	
	//생성자
	public MainDaoImpl() {}
	
	//상품목록조회
	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		LOG.debug(" ٩( ᐛ )و Dao의 doRetrieve()시작! ");
		
		CateSearchVO param = (CateSearchVO) dto;
		String statement = this.NAMESPACE+".doRetrieve";
		LOG.debug("=================================================");
		LOG.debug("=param= "+param);
		LOG.debug("=statement= "+statement);
		LOG.debug("=================================================");
		
		List<MainVO> list = sqlSessionTemplate.selectList(statement, param);
		
//		for(MainVO vo:list) {
//			LOG.debug("=vo="+vo);
//		}
		
		return list;
	}
	
	//레시피게시물목록조회
	public List<?> doRecipeRetrieve(DTO dto) throws SQLException {
		
		LOG.debug(" ٩( ᐛ )و Dao의 doRecipeRetrieve()시작! ");
		
		CateSearchVO param = (CateSearchVO) dto;
		String statement = this.NAMESPACE+".doRecipeRetrieve";
		LOG.debug("=================================================");
		LOG.debug("=param= "+param);
		LOG.debug("=statement= "+statement);
		LOG.debug("=================================================");
		
		List<MainRecipeVO> list = sqlSessionTemplate.selectList(statement, param);
		
//		for(MainRecipeVO vo:list) {
//			LOG.debug("=vo="+vo);
//		}
		
		return list;
	}


}
