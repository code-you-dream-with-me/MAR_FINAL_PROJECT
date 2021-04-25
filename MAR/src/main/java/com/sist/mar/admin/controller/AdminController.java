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
import com.sist.mar.cmn.Message;
import com.sist.mar.cmn.Search;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.item.domain.Item;
import com.sist.mar.order.domain.Ordering;
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
	
	@RequestMapping(value = "admin/do_retrieve_item.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieveItem(Search search) throws Exception {
		
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), "regDt"));
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), "desc"));
		
		List<Item> itemList = adminService.doRetrieveItem(search);
		Gson gson = new Gson();
		return gson.toJson(itemList.toArray());
		
	}
	
	
	@RequestMapping(value = "admin/do_dicount_item.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDiscountItem(Item item) throws Exception {
		
		LOG.debug("doDiscountItem");
		LOG.debug("item: "+item);
		
		Message message = new Message();
		Gson gson = new Gson();
		
		int flag = adminService.doDiscountItem(item);
		
		message.setMsgId(Integer.toString(flag));
		
		if(message.getMsgId().equals("1")) message.setMsgContents("할인률이 수정되었습니다.");
		else message.setMsgContents("할인률이 수정이 실패하였습니다.");
		
		return gson.toJson(message);
		
	}
	
	@RequestMapping(value = "admin/do_retrieve_ordering.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieveOrdering(Search search) throws Exception {
		
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), "nothing"));
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), "0"));
		
		List<Ordering> orderingList = adminService.doRetrieveOrdering(search);
		Gson gson = new Gson();
		return gson.toJson(orderingList.toArray());
		
	}
	
	
	
	
}
