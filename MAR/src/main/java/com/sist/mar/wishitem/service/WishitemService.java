package com.sist.mar.wishitem.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.cmn.DTO;
import com.sist.mar.wishitem.dao.WishitemDao;

import oracle.net.aso.t;


@Service
public class WishitemService {

	final Logger LOG = LoggerFactory.getLogger(WishitemService.class);

	@Autowired
	public WishitemDao wishDao;
	
	public WishitemService() {}
	
	// dao에 있는 삭제, 등록, 단건조회, 목록조회
	public int doDelete(String param) throws SQLException {
		return this.wishDao.doDelete(param);
	}
	
	public int doInsert(DTO dto) throws SQLException {
		return this.wishDao.doInsert(dto);
	}
	
	public DTO doSelectOne(String param) throws SQLException {
		return this.wishDao.doSelectOne(param);
	}
	
	public List<?> doRetrieve(DTO dto) throws SQLException {
		return this.wishDao.doRetrieve(dto);
	}
	
	public int wishitemCheck(DTO dto) throws SQLException {
		return this.wishDao.wishitemCheck(dto);
	}
}
