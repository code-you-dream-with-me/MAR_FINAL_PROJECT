package com.sist.mar.answer.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.mar.cmn.DTO;

public interface AnswerService {

	/**
	 * 문의 답변 전체 조회
	 * @return
	 * @throws SQLException
	 */
	List<?> getAllList()throws SQLException;
	

	/**
	 * 문의 별 답변 조회
	 * @param DTO
	 * @return DTO
	 * @throws SQLException
	 */
	List<?> doSelectOne(DTO dto) throws SQLException;
	
	
	DTO doSelectAnswer(DTO dto) throws SQLException;
	
	/**
	 * 문의 답변 수정
	 * @param DTO
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int  doUpdate(DTO dto)throws SQLException;
	
	
	/**
	 * 문의 답변 삭제
	 * @param DTO
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int doDelete(DTO dto) throws  SQLException;

	/**
	 * 문의 답변 등록
	 * @param DTO
	 * @return int(1:성공,0:실패)	
	 * @throws SQLException
	 */
	int  doInsert(DTO dto) throws  SQLException;
}
