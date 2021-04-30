package com.sist.mar.main.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.cmn.DTO;
import com.sist.mar.main.dao.MainDaoImpl;

@Service
public class MainServiceImpl implements MainService {
	
	final Logger LOG = LoggerFactory.getLogger(MainServiceImpl.class);
	
	@Autowired
	private MainDaoImpl mainDao;

	public MainServiceImpl() {}
	
	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		LOG.debug(" ٩( ᐛ )و Service를 지나서~ ");
		LOG.debug("================================");
		return this.mainDao.doRetrieve(dto);
	}

	@Override
	public List<?> doRecipeRetrieve(DTO dto) throws SQLException {
		LOG.debug(" ٩( ᐛ )و Service를 지나서~ ");
		LOG.debug("================================");
		return this.mainDao.doRecipeRetrieve(dto);
	}


}
