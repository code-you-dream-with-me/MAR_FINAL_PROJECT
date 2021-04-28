package com.sist.mar.wishitem.controller;

import java.sql.SQLException;
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
import com.sist.mar.cmn.Message;
import com.sist.mar.cmn.Search;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.wishitem.domain.Wishitem;
import com.sist.mar.wishitem.service.WishitemService;

@Controller
public class WishitemController {

	final Logger LOG = LoggerFactory.getLogger(WishitemController.class);
	
	@Autowired
	WishitemService wishService;
	
	// 늘사는것 페이지 -------------------------------------------------------------------------------------
	@RequestMapping(value = "wishitem/wishitem_list.do")
	public String wishitem_list() throws SQLException {
		LOG.debug("===================");
		LOG.debug("= wishitem_list() =");
		LOG.debug("===================");
		
		//처음 조회 속도 향상을 위해 전체 검색 1회 실행
		Search search = new Search("", "", 10, 1);
		doRetrieve(search);
		
		return "wishitem/wishitem_list";
	}
		
	// 늘사는것 삭제 -----------------------------------------------------------------------------------
	@RequestMapping(value = "wishitem/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(String param) throws SQLException {
		LOG.debug("= doDelete() =");
		LOG.debug("param : " + param);
		
		int flag = wishService.doDelete(param);
		String resultMsg = "";
		if(flag == 1) {
			resultMsg = "삭제를 성공하였습니다.";
		} else {
			resultMsg = "삭제를 실패하였습니다.";
		}
		
		Message message = new Message();
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("jsonStr : " + jsonStr);
		return jsonStr;
	}
	
	// 늘사는것 등록 -----------------------------------------------------------------------------------
	@RequestMapping(value = "wishitem/do_insert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Wishitem wishitem) throws SQLException {
		LOG.debug("= doInsert() =");
		LOG.debug("wishitem : " + wishitem);
		
		int checkFlag = wishService.wishitemCheck(wishitem);
		String resultMsg = "";
		if (checkFlag == 0) {
			//동일상품이 장바구니에 없다면 등록
			int flag = wishService.doInsert(wishitem);
			if(1 == flag) {
				resultMsg = "늘사는것에 상품을 담았습니다.";
			} else {
				resultMsg = "상품을 담지 못했습니다.";
			}
		} else {
			//동일상품이 장바구니에 있다면 등록x
			resultMsg = "늘사는것에 존재하는 상품입니다.";
		}

		Message message = new Message();
		message.setMsgContents(resultMsg);

		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("jsonStr : " + jsonStr);
		return jsonStr;
	}
	
	// 늘사는것 단건조회 (팝업창) -----------------------------------------------------------------------------------
	@RequestMapping(value = "wishitem/wishitem_detail.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String doSelcetone(String param, Model model) throws SQLException {
		LOG.debug("=====================");
		LOG.debug("= wishitem_detail() =");
		LOG.debug("=====================");

		Wishitem outVO = (Wishitem) wishService.doSelectOne(param);
		LOG.debug("outVO : " + outVO);
		model.addAttribute("outVO", outVO);
		
		return "wishitem/wishitem_detail";	
	}
	
	// 늘사는것 조회 -----------------------------------------------------------------------------------
	@RequestMapping(value = "wishitem/do_retrieve.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(Search search) throws SQLException{
		LOG.debug("= doRetrieve() =");
		LOG.debug("search : " + search);
		
		//NVL 처리 
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
		if(search.getPageSize() == 0) {
			search.setPageSize(10);
		}
		if(search.getPageNum() == 0) {
			search.setPageNum(1);
		}
		LOG.debug("param_init : " + search);
		
		List<Wishitem> list = (List<Wishitem>) wishService.doRetrieve(search);
		for(Wishitem vo : list) {
			LOG.debug(vo.toString());
		}

		//List to Json
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		LOG.debug("jsonList : " + jsonList);
		return jsonList;
	}

}
