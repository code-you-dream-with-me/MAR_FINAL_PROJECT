package com.sist.mar.main.dao;

import java.sql.SQLException;
import java.util.List;

import com.sist.mar.cmn.DTO;




public interface MainDao {
	
	//doRetrieve 전체조회
	//1. 등록된 순서(최근등록된게 앞으로)  -> doRetrieve(DTO dto) SQLException
	//2. 베스트 순서(판매량이 높은 순서)  -> doBestRetrieve(DTO dto) SQLException
	//3. 알뜰쇼핑 (할인율이 적용된 item) -> doSaleRetrieve(DTO dto) SQLException
	
	
	/**
	 * 등록된 순서로 목록조회
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	List<?> doRetrieve() throws SQLException;
	

	
}
