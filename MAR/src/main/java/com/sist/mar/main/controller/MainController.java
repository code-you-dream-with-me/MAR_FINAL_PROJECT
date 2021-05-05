package com.sist.mar.main.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.mar.cmn.Message;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.code.domain.Code;
import com.sist.mar.code.service.CodeService;
import com.sist.mar.main.domain.CateSearchVO;
import com.sist.mar.main.domain.MainRecipeVO;
import com.sist.mar.main.domain.MainVO;
import com.sist.mar.main.service.MainService;

@Controller
public class MainController {
	
	final Logger LOG = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	MainService mainService;
	@Autowired
	CodeService codeService;
	
	public MainController() {}
	
	private String VIEW_NAME = "main/main";
	
	//<<<<<main화면 연결>>>>>
	@RequestMapping(value= "main/main_view.do")
	public String main(Model model,CateSearchVO search) throws SQLException {
		LOG.debug("******************************************************");
		LOG.debug("* 메인 화면 load *");
		LOG.debug("******************************************************");
		LOG.debug("================================");
		LOG.debug("search param: "+search);
		LOG.debug("================================");
		
		//NVL처리
		//검색어가 없을 시 ""처리
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
		//목록분류 없을 시 20(신상품조회)처리
		search.setListDiv(StringUtil.nvl(search.getListDiv(),"20"));
		if(search.getCategoryNo()==0) {
			search.setCategoryNo(0);
		}
		//페이지NUM에 0이 들어올 시 
		if(search.getPageNum()==0) {
			search.setPageNum(1);//1로 만들어줌
		}
		//페이지사이즈에 0이 들어올 시
		if(search.getPageSize()==0) {
			search.setPageSize(200);//12으로 만들어줌
		}
		
		LOG.debug("================================");
		LOG.debug("param_init(초기화): "+search);
		LOG.debug("================================");
		
		List<MainVO> list = (List<MainVO>) this.mainService.doRetrieve(search);
		
		for(MainVO vo:list) {
			LOG.debug(vo.toString());
		}
		
		//model로 list를 화면에 넘겨줌
		model.addAttribute("list",list);
		model.addAttribute("search",search);

				
		return VIEW_NAME;
	}

	//<<<<목록조회 메서드>>>>
	@RequestMapping(value="main/do_retrieve.do", method=RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String doRetrieve(Model model,CateSearchVO search) throws SQLException {
		LOG.debug(" ٩( ᐛ )و Controller의 doRetrieve()시작! ");
		LOG.debug("================================");
		LOG.debug("search param: "+search);
		LOG.debug("================================");
		
		//NVL처리
		//검색어가 없을 시 ""처리
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
		//목록분류 없을 시 20(신상품조회)처리
		search.setListDiv(StringUtil.nvl(search.getListDiv(),"20"));
		//페이지NUM에 0이 들어올 시 
		if(search.getPageNum()==0) {
			search.setPageNum(1);//1로 만들어줌
		}
		//페이지사이즈에 0이 들어올 시
		if(search.getPageSize()==0) {
			search.setPageSize(200);//12으로 만들어줌
		}
		
		LOG.debug("================================");
		LOG.debug("param_init(초기화): "+search);
		LOG.debug("================================");
		
		List<MainVO> list = (List<MainVO>) this.mainService.doRetrieve(search);
		
		for(MainVO vo:list) {
			LOG.debug(vo.toString());
		}

		//model로 list를 화면에 넘겨줌
		model.addAttribute("list",list);
		model.addAttribute("search",search);
				
		return VIEW_NAME;
		
	}
	
	//<<<<레시피목록 메서드>>>>
	@RequestMapping(value="main/do_recipe_retrieve.do", method=RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String doRecipeRetrieve(Model model,CateSearchVO search) throws SQLException {
		LOG.debug(" ٩( ᐛ )و Controller의 doRecipeRetrieve()시작! ");
		LOG.debug("================================");
		LOG.debug("search param: "+search);
		LOG.debug("================================");
		

		//NVL처리
		//검색어가 없을 시 ""처리
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
		//목록분류 없을 시 ""처리
		search.setListDiv(StringUtil.nvl(search.getListDiv(),""));
		//페이지NUM에 0이 들어올 시 
		if(search.getPageNum()==0) {
			search.setPageNum(1);//1로 만들어줌
		}
		//페이지사이즈에 0이 들어올 시
		if(search.getPageSize()==0) {
			search.setPageSize(200);//12으로 만들어줌
		}
		
		LOG.debug("================================");
		LOG.debug("param_init(초기화): "+search);
		LOG.debug("================================");
		
		List<MainRecipeVO> list = (List<MainRecipeVO>) this.mainService.doRecipeRetrieve(search);
		
		for(MainRecipeVO vo:list) {
			LOG.debug(vo.toString());
		}
		
		//model로 list를 화면에 넘겨줌
		model.addAttribute("list",list);

		return "main/main_recipe";
	}
	
	//장바구니 갈때는 로그인 세션이 필요..아 @Responsebody안해줘서 안됐던거였다.. 진짜 어이없다..
	//<<<<<장바구니화면 연결>>>>>
	@RequestMapping(value= "main/move_to_cart.do", method=RequestMethod.GET,
			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String viewCart(Model model, HttpSession session) throws SQLException {
		Message message = new Message();
		//세션========================
		if(null != session.getAttribute("member")) {
			message.setMsgId("1");
			message.setMsgContents("장바구니로 이동");
		}else {
			message.setMsgId("0");
			message.setMsgContents("로그인 후 이용하실 수 있습니다.");
		}
		//===========================

		Gson gson = new Gson();
		String messageJson = gson.toJson(message);
		LOG.debug("================================");
		LOG.debug("messageJson: "+messageJson);
		LOG.debug("================================");
		
		return messageJson;
	}
	
	private List<?> getCodePageRetrieve(List<String> codeList) throws SQLException {
		
		Map<String,Object> codeMap = new HashMap<String,Object>();
		codeMap.put("codeList",codeList);
		
		return codeService.getCodeRetrieve(codeMap);
	}




}
