package com.sist.mar.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sist.mar.admin.service.AdminServiceImpl;
import com.sist.mar.cmn.Search;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.recipe.domain.RecipeVO;

@Controller
public class AdminController {
	
//	▼ 변수 ===============================================================
	
	final Logger LOG = LoggerFactory.getLogger(AdminController.class);
	
	final String VIEW_NAME = "admin/admin_main";
	
	@Autowired
	AdminServiceImpl adminService;
	
	
//	▼ 생성자 ==============================================================	
	
	public AdminController() {}
	
	
//	▼ 메소드 ===============================================================
	
	@RequestMapping(value = "admin/admin_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String view(Model model) throws Exception {
		
		Gson gson = new Gson();
		List<RecipeVO> recipeList = gson.fromJson(doRetrieveReicpe(new Search()), new TypeToken<List<RecipeVO>>() {}.getType());
		
		model.addAttribute("recipeList", recipeList);
		
		return VIEW_NAME;
	}
	
	
	@RequestMapping(value = "admin/do_retrieve_recipe.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieveReicpe(Search search) throws Exception {
		
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), "regDt"));
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), "desc"));
		
		List<RecipeVO> ricpeList = adminService.doRetrieveReicpe(search);
		Gson gson = new Gson();
		return gson.toJson(ricpeList.toArray());
		
	}
	
	
	
	
	
	
	
	
	
	
}
