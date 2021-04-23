package com.sist.mar.review.controller;

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
import com.sist.mar.cmn.DTO;
import com.sist.mar.cmn.Message;
import com.sist.mar.cmn.Search;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.review.domain.ReviewVO;
import com.sist.mar.review.service.ReviewServiceImpl;

@Controller
public class ReviewController {

	final Logger LOG = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	ReviewServiceImpl reviewServiceImpl;
	
	public ReviewController() {
		
	}
	
	
	@RequestMapping(value = "/review/do_delete.do", method =  RequestMethod.GET
			, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(ReviewVO review) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("param : " + review);
		LOG.debug("=======================");
		
		int flag = this.reviewServiceImpl.doDelete(review);
		
		String resultMsg = "";
		
		if(1 == flag) {
			resultMsg = review.getMemberId() + "님\n삭제성공.";
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
	

	@RequestMapping(value = "/review/do_insert.do", method =  RequestMethod.GET
					, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(ReviewVO review) throws SQLException{
		
		LOG.debug("=======================");
		LOG.debug("param : " + review);
		LOG.debug("=======================");
		
		if(null == review.getTitle() || review.getTitle().equals("")) {
			
			LOG.debug("review.getTitle : " +review.getTitle());
			throw new NullPointerException("후기의 제목을 입력하세요.");

		}
		
		if(null == review.getContents() || review.getContents().equals("")) {
			
			LOG.debug("review.getContents : " +review.getContents());
			throw new NullPointerException("후기의 내용을 입력하세요.");
		}
		
		int flag = this.reviewServiceImpl.doInsert(review);
		
		String resultMsg = "";
		if(1 == flag) {
			resultMsg = review.getMemberId() + "님\n후기 등록성공.";
		}else {
			resultMsg = "후기 등록실패.";
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
	
	
	@RequestMapping(value = "/review/do_update.do", method = RequestMethod.GET ,
 			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(ReviewVO review) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("param : " + review);
		LOG.debug("=======================");
		
		int flag = this.reviewServiceImpl.doUpdate(review);
		
		String resultMsg = "";
		
		if(1 == flag) {
			resultMsg = review.getMemberId() + "님\n후기 수정에 성공하셨습니다";
		}else {
			resultMsg = review.getMemberId() + "님\n후기 수정에 실패하셨습니다";
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
	
	
	@RequestMapping(value = "/review/do_readCnt.do", method = RequestMethod.GET ,
 			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doReadCnt(ReviewVO review) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("param : " + review);
		LOG.debug("=======================");
		
		int flag = this.reviewServiceImpl.doReadCnt(review);
		
		String resultMsg = "";
		
		if(1 == flag) {
			resultMsg = "게시물 조회 +1 에 성공하셨습니다";
		}else {
			resultMsg = "게시물 조회 +1 에 실패하셨습니다";
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
	
	
	@RequestMapping(value = "/review/do_selectOne.do", method = RequestMethod.GET ,
 			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(ReviewVO review) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("param : " + review);
		LOG.debug("=======================");
		
		ReviewVO outVO = (ReviewVO) this.reviewServiceImpl.doSelectOne(review);
		
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
	

	@RequestMapping(value = "/review/do_retrieve.do", method = RequestMethod.GET ,
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
		
		List<ReviewVO> list = (List<ReviewVO>) this.reviewServiceImpl.doRetrieve(search);
		for(ReviewVO vo : list) {
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
	
	
//	@RequestMapping(value = "/review/do_retrieveSelf.do", method = RequestMethod.GET ,
// 			produces = "application/json;charset=UTF-8")
//	@ResponseBody
//	public String doRetrieveSelf(Search search) throws SQLException {
//	
//		LOG.debug("=======================");
//		LOG.debug("search : " + search);
//		LOG.debug("=======================");
//		
//		// NVL처리
//		
//		// 검색구분
//		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), ""));
//		
//		// 검색어
//		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
//		
//		// 페이지 넘버
//		if(search.getPageNum() == 0) {
//		search.setPageNum(1);
//		}
//		
//		// 페이지 사이즈
//		if(search.getPageSize() == 0) {
//		search.setPageSize(10);
//		}
//		
//		LOG.debug("=======================");
//		LOG.debug("param_init : " + search);
//		LOG.debug("=======================");
//		
//		List<ReviewVO> list = (List<ReviewVO>) this.ReviewServiceImpl.doRetrieveSelf(search);
//		for(ReviewVO vo : list) {
//			LOG.debug(vo.toString());
//		}
//		
//		// List to Json
//		Gson gson = new Gson();
//		
//		String jsonList = gson.toJson(list);
//		
//		LOG.debug("=======================");
//		LOG.debug("jsonList : " + jsonList);
//		LOG.debug("=======================");
//		
//		return jsonList;
//	}	
}
