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

		return "wishitem/wishitem_list";
	}
		
	// 늘사는것 삭제 -----------------------------------------------------------------------------------
	@RequestMapping(value = "wishitem/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody //페이지 이동 없이 실행 
	public String doDelete(String param) throws SQLException {
		LOG.debug("= doDelete() =");
		LOG.debug("param : " + param);
		
		//삭제 실행
		int flag = wishService.doDelete(param);
		String resultMsg = "";
		//삭제 성공 또는 실패 시 메세지 추가 (1-성공, 0-실패)
		if(flag == 1) {
			resultMsg = "상품을 삭제하였습니다.";
		} else {
			resultMsg = "삭제를 실패하였습니다.";
		}
		
		//메세지 객체에 메세지 담기
		Message message = new Message();
		message.setMsgContents(resultMsg);
		
		//json으로 화면에 전송
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("jsonStr : " + jsonStr);
		return jsonStr;
	}
	
	// 늘사는것 등록 -----------------------------------------------------------------------------------
	@RequestMapping(value = "wishitem/do_insert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody //페이지 이동없이 실행
	public String doInsert(Wishitem wishitem) throws SQLException {
		LOG.debug("= doInsert() =");
		LOG.debug("wishitem : " + wishitem);
		
		//이미 존재하는 상품인지 체크
		int checkFlag = wishService.wishitemCheck(wishitem);
		String resultMsg = "";
		//상품의 존재 유무 및 등록 성공 실패에 따른 메세지 추가
		//(flag 1-등록성공,flag 0-등록실패,checkFlag 1-이미 존재하는 상품,checkFlag 0-존재하지 않았던 상품)
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

		//메세지 객체에 메세지 담기
		Message message = new Message();
		message.setMsgContents(resultMsg);

		//json으로 화면에 전송
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

		//param(wishNo)에 맞는 단건 조회
		Wishitem outVO = (Wishitem) wishService.doSelectOne(param);
		LOG.debug("outVO : " + outVO);
		//outVO라는 이름으로 화면으로 전송
		model.addAttribute("outVO", outVO);
		
		//전송할 화면
		return "wishitem/wishitem_detail";	
	}
	
	// 늘사는것 조회 -----------------------------------------------------------------------------------
	@RequestMapping(value = "wishitem/do_retrieve.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody //페이지 이동없이 실행
	public String doRetrieve(Search search) throws SQLException{ 
		LOG.debug("= doRetrieve() =");
		LOG.debug("search : " + search);
		
		//NVL 처리 (null값이 들어오면 안되는 변수들에 값을 대신 넣어줌)
		if(search.getPageSize() == 0) {
			search.setPageSize(10);
		}
		if(search.getPageNum() == 0) {
			search.setPageNum(1);
		}
		LOG.debug("param_init : " + search);
		
		//searchWord(memberId)에 맞는 늘사는것 리스트 출력 
		List<Wishitem> list = (List<Wishitem>) wishService.doRetrieve(search);
		for(Wishitem vo : list) {
			LOG.debug(vo.toString());
		}

		//json으로 화면에 전송
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		LOG.debug("jsonList : " + jsonList);
		return jsonList;
	}

}
