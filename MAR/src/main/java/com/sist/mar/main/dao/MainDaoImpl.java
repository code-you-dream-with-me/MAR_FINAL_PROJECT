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
import com.sist.mar.main.domain.CateSearchVO;
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
	
	/**
	 * 최신등록순 상품목록
	 * @param CateSearchVO dto
	 * @return MainVO List<?>
	 * @throws SQLException
	 */
	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		LOG.debug(" ٩( ᐛ )و Dao시작! >>>>>>>>>>>> 최신등록순 상품목록");
		
		CateSearchVO param = (CateSearchVO) dto;
		String statement = this.NAMESPACE+".doRetrieve";
		LOG.debug("=================================================");
		LOG.debug("=param= "+param);
		LOG.debug("=statement= "+statement);
		LOG.debug("=================================================");

		List<MainVO> list = sqlSessionTemplate.selectList(statement, param);
		
		for(MainVO vo:list) {
			LOG.debug("=vo="+vo);
		}
		
		return list;
	}

	/**
	 * 판매량순 상품목록
	 * @param CateSearchVO dto
	 * @return MainVO List<?>
	 * @throws SQLException
	 */
	public List<?> doBestRetrieve(DTO dto) throws SQLException {
		LOG.debug(" ٩( ᐛ )و Dao시작! >>>>>>>>>>>> 판매량순 상품목록");
		
		CateSearchVO param = (CateSearchVO) dto;
		String statement = this.NAMESPACE+".doBestRetrieve";
		LOG.debug("=================================================");
		LOG.debug("=param= "+param);
		LOG.debug("=statement= "+statement);
		LOG.debug("=================================================");

		List<MainVO> list = sqlSessionTemplate.selectList(statement, param);
		
		for(MainVO vo:list) {
			LOG.debug("=vo="+vo);
		}
		
		return list;
	}

	/**
	 * 할인적용된 상품목록
	 * @param CateSearchVO dto
	 * @return MainVO List<?>
	 * @throws SQLException
	 */
	public List<?> doSaleRetrieve(DTO dto) throws SQLException {
		LOG.debug(" ٩( ᐛ )و Dao시작! >>>>>>>>>>>> 할인적용된 상품목록");
		
		CateSearchVO param = (CateSearchVO) dto;
		String statement = this.NAMESPACE+".doSaleRetrieve";
		LOG.debug("=================================================");
		LOG.debug("=param= "+param);
		LOG.debug("=statement= "+statement);
		LOG.debug("=================================================");

		List<MainVO> list = sqlSessionTemplate.selectList(statement, param);
		
		for(MainVO vo:list) {
			LOG.debug("=vo="+vo);
		}
		
		return list;
	}

}
