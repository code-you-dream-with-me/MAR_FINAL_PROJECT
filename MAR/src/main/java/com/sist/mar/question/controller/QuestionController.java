package com.sist.mar.question.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.mar.cmn.Message;
import com.sist.mar.cmn.Search;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.question.domain.QuestionVO;
import com.sist.mar.question.service.QuestionServiceImpl;

@Controller
public class QuestionController {
	
	final Logger LOG = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	QuestionServiceImpl questionServiceImpl;
	
	public QuestionController() {
		
	}
	
	
	@RequestMapping(value = "/question/do_delete.do", method =  RequestMethod.GET
			, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(QuestionVO question) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("param : " + question);
		LOG.debug("=======================");
		
		int flag = this.questionServiceImpl.doDelete(question);
		
		String resultMsg = "";
		
		if(1 == flag) {
			resultMsg = question.getqUser() + "님\n삭제성공.";
		}else {
			resultMsg = "삭제 실패.";
		}
		
		Message message = new Message();
		message.setMsgId(flag + "");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		
		LOG.debug("=======================");
		LOG.debug("jsonStr : " + jsonStr);
		LOG.debug("=======================");
		
		return jsonStr;
		
	}
	

	@RequestMapping(value = "/question/do_insert.do", method =  RequestMethod.GET
					, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(QuestionVO question) throws SQLException{
		
		LOG.debug("=======================");
		LOG.debug("param : " + question);
		LOG.debug("=======================");
		
		if(null == question.getTitle() || question.getTitle().equals("")) {
			
			LOG.debug("question.getTitle : " + question.getTitle());
			throw new NullPointerException("질의 제목을 입력하세요.");

		}
		
		if(null == question.getContents() || question.getContents().equals("")) {
			
			LOG.debug("question.getContents : " + question.getContents());
			throw new NullPointerException("질의 내용을 입력하세요.");
		}
		
		int flag = this.questionServiceImpl.doInsert(question);
		
		String resultMsg = "";
		if(1 == flag) {
			resultMsg = question.getqUser() + "님\n 질의 등록에 성공했습니다. 곧 응답하겠습니다";
		}else {
			resultMsg = "질의 등록에 실패했습니다.";
		}
		
		Message message = new Message();
		message.setMsgId(flag + "");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		
		LOG.debug("=======================");
		LOG.debug("jsonStr : " + jsonStr);
		LOG.debug("=======================");
		
		return jsonStr;
		
	}
	
	
	@RequestMapping(value = "/question/do_update.do", method = RequestMethod.GET ,
 			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(QuestionVO question) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("param : " + question);
		LOG.debug("=======================");
		
		int flag = this.questionServiceImpl.doUpdate(question);
		
		String resultMsg = "";
		
		if(1 == flag) {
			resultMsg = question.getqUser()  + "님께서\n질의 수정에 성공하셨습니다";
		}else {
			resultMsg = question.getqUser()  + "님께서\n질의 수정에 실패하셨습니다";
		}
		
		Message message = new Message();
		message.setMsgId(flag + "");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		
		LOG.debug("=======================");
		LOG.debug("jsonStr : " + jsonStr);
		LOG.debug("=======================");
		
		return jsonStr;
		
	}
	
	
	@RequestMapping(value = "/question/do_selectOne.do", method = RequestMethod.GET ,
 			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(QuestionVO question) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("param : " + question);
		LOG.debug("=======================");
		
		QuestionVO outVO = (QuestionVO) this.questionServiceImpl.doSelectOne(question);
		
		LOG.debug("=======================");
		LOG.debug("outVO : " + outVO);
		LOG.debug("=======================");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(outVO);
		
		LOG.debug("=======================");
		LOG.debug("jsonStr : " + jsonStr);
		LOG.debug("=======================");
		
		return jsonStr;
		
		
	}
	

	@RequestMapping(value = "/question/do_retrieve.do", method = RequestMethod.GET ,
			 			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(Search search) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("search : " + search);
		LOG.debug("=======================");
		
		// NVL처리
		
		// 검색구분
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), ""));
		
		// 검색어
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
		
		// 페이지 넘버
		if(search.getPageNum() == 0) {
			search.setPageNum(1);
		}
		
		// 페이지 사이즈
		if(search.getPageSize() == 0) {
			search.setPageSize(10);
		}
		
		LOG.debug("=======================");
		LOG.debug("param_init : " + search);
		LOG.debug("=======================");
		
		List<QuestionVO> list = (List<QuestionVO>) questionServiceImpl.doRetrieve(search);
		for(QuestionVO vo : list) {
			LOG.debug(vo.toString());
		}
		
		// List to Json
		Gson gson = new Gson();
		
		String jsonList = gson.toJson(list);
		
		LOG.debug("=======================");
		LOG.debug("jsonList : " + jsonList);
		LOG.debug("=======================");
		
		return jsonList;
	}

}
