package com.sist.mar.payment.service;

import java.sql.SQLException;

import com.sist.mar.cmn.DTO;

public interface PaymentService {
	//다형성과 AOP, 트랜잭션 처리를 위해 인터페이스 생성
	
	int doInsert(DTO dto) throws SQLException;
	
}
