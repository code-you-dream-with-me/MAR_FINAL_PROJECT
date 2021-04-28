package com.sist.mar.payment.dao;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mar.cmn.DTO;
import com.sist.mar.payment.domain.Payment;

@Repository
public class PaymentDao {
	
	final static Logger LOG = LoggerFactory.getLogger(PaymentDao.class);

	final String NAMESPACE = "com.sist.mar.payment";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public PaymentDao() {}
	
	//---------------------------------------------------------
	/**
	 * 결제정보 등록
	 * param = Payment 전부(datetime빼고)
	 * return 1(성공)/0(실패)
	 */
	public int doInsert(DTO dto) throws SQLException {
		Payment pay = (Payment) dto;
		String statement = this.NAMESPACE + ".doInsert";
		return this.sqlSessionTemplate.insert(statement, pay);
	}
	
}
