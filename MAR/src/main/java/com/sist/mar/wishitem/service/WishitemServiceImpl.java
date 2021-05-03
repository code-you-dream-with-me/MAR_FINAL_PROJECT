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
public class WishitemServiceImpl implements WishitemService {

	final Logger LOG = LoggerFactory.getLogger(WishitemServiceImpl.class);

	@Autowired
	public WishitemDao wishDao;
	
	//기본생성자
	public WishitemServiceImpl() {}
	
	// dao에 있는 삭제, 등록, 단건조회, 목록조회, 아이템체크
	@Override
	public int doDelete(String param) throws SQLException {
		return this.wishDao.doDelete(param);
	}
	
	@Override
	public int doInsert(DTO dto) throws SQLException {
		return this.wishDao.doInsert(dto);
	}
	
	@Override
	public DTO doSelectOne(String param) throws SQLException {
		return this.wishDao.doSelectOne(param);
	}
	
	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		return this.wishDao.doRetrieve(dto);
	}
	
	@Override
	public int wishitemCheck(DTO dto) throws SQLException {
		return this.wishDao.wishitemCheck(dto);
	}
}
