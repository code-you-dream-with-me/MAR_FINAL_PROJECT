package com.sist.mar.payment.service;

import java.sql.SQLException;

import com.sist.mar.cmn.DTO;

public interface PaymentService {

	int doInsert(DTO dto) throws SQLException;
	
}
