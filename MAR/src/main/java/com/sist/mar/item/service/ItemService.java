package com.sist.mar.item.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.mar.cmn.DTO;

public interface ItemService {

	/**
	 * 전체 상품 목록 조회
	 * @return
	 * @throws SQLException
	 */
	List<?> getAllList()throws SQLException;
	
	/**
	 * 관련 상품 목록 조회
	 * @param DTO
	 * @return List<?>
	 * @throws SQLException
	 */
	List<?> getRelatedList(DTO dto)throws SQLException;
	
	/**
	 * 상품 단건 조회
	 * @param DTO
	 * @return DTO
	 * @throws SQLException
	 */
	DTO doSelectOne(DTO dto) throws SQLException;
	
	/**
	 * 상품 수정
	 * @param DTO
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int  doUpdate(DTO dto)throws SQLException;
	
	
	/**
	 * 상품 삭제
	 * @param DTO
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int doDelete(DTO dto) throws  SQLException;

	/**
	 * 상품 등록
	 * @param DTO
	 * @return int(1:성공,0:실패)	 * 
	 * @throws SQLException
	 */
	int  doInsert(DTO dto) throws  SQLException;

}

