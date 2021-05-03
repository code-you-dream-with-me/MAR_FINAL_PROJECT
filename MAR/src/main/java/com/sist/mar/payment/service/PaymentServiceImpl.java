package com.sist.mar.payment.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.cmn.DTO;
import com.sist.mar.payment.dao.PaymentDao;

@Service
public class PaymentServiceImpl implements PaymentService {

	final Logger LOG = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	public PaymentDao paymentDao;
	
	//기본 생성자
	public PaymentServiceImpl() {}

	// dao에 있는 결제정보 등록
	@Override
	public int doInsert(DTO dto) throws SQLException {
		return this.paymentDao.doInsert(dto);
	}
}
