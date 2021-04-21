package com.sist.mar.main.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.mar.cmn.DTO;

public interface MainService {

	/**
	 * 최신상품 조회
	 * @param dto
	 * @return List<?>
	 * @throws SQLException
	 */
	List<?> doRetrieve(DTO dto) throws SQLException;
	
	/**
	 * 판매량이 높은 순서로 목록조회
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	List<?> doBestRetrieve(DTO dto) throws SQLException;
	
	/**
	 * 할인율이 적용된 상품목록 조회
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	List<?> doSaleRetrieve(DTO dto) throws SQLException;


}