package com.sist.mar.admin.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.admin.dao.AdminDaoImpl;
import com.sist.mar.cmn.DTO;
import com.sist.mar.recipe.domain.RecipeVO;

@Service
public class AdminServiceImpl {
	
//	▼ 변수 ===============================================================
	
	final Logger LOG = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminDaoImpl adminDao;
	
	
//	▼ 생성자 ==============================================================	

	public AdminServiceImpl() {}
	
	
//	▼ 메소드 ===============================================================	
	
	public List<RecipeVO> doRetrieveReicpe(DTO dto) throws SQLException {
		return adminDao.doRetrieveReicpe(dto);
	}
	
}
