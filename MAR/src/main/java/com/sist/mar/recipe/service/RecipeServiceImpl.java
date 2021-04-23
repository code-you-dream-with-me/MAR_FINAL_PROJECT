package com.sist.mar.recipe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.recipe.dao.RecipeDaoImpl;
import com.sist.mar.recipe.domain.RecipeVO;

@Service
public class RecipeServiceImpl {

//	▼ 변수 ===============================================================
	
	final Logger LOG = LoggerFactory.getLogger(RecipeServiceImpl.class);
	
	@Autowired
	private RecipeDaoImpl recipeDao;
	
	
//	▼ 생성자 ==============================================================	
	
	public RecipeServiceImpl() {}

	
//	▼ 메소드 ===============================================================	

	public int upRegisterRecipe(RecipeVO recipeVO) throws Exception {
		return recipeDao.doInsert(recipeVO);
	}

	public int upDeleteRecipe(RecipeVO recipeVO) throws Exception {
		return recipeDao.doDelete(recipeVO);
	}
	
	public int upUpdateRecipe(RecipeVO recipeVO) throws Exception {
		return recipeDao.doUpdate(recipeVO);
	}
	
	public RecipeVO doSelectRecipe(RecipeVO recipeVO) throws Exception {
		recipeDao.doCountReadCnt(recipeVO);
		return recipeDao.doSelectOne(recipeVO);
	}
	
	
	
	
}
