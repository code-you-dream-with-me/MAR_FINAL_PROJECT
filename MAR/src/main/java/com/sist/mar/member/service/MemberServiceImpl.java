package com.sist.mar.member.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.cmn.DTO;
import com.sist.mar.member.dao.MemberDaoImpl;

@Service
public class MemberServiceImpl {
	
//	▼ 변수 ===============================================================

	@Autowired
	private MemberDaoImpl memberDao;
	
//	▼ 생성자 ==============================================================	
	
	public MemberServiceImpl() {}
	
	
//	▼ 메소드 ===============================================================	
	
	public int doRegister(DTO dto) throws SQLException {
		return memberDao.doRegister(dto);
	}
	
	
}
