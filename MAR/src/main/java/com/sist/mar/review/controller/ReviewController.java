package com.sist.mar.review.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.mar.cmn.DTO;
import com.sist.mar.cmn.Message;
import com.sist.mar.cmn.Search;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.code.domain.Code;
import com.sist.mar.code.service.CodeService;
import com.sist.mar.review.domain.ReviewVO;
import com.sist.mar.review.service.ReviewServiceImpl;

@Controller
public class ReviewController {

//	▼ 변수 ===============================================================
	final Logger LOG = LoggerFactory.getLogger(ReviewController.class);
	
	final String VIEW_NAME = "review/review_list";
	
	@Autowired
	ReviewServiceImpl reviewServiceImpl;
	
	@Autowired
	CodeService codeService;

//	▼ 생성자 ==============================================================		
	public ReviewController() {
		
	}

//	▼ 뷰 =================================================================
	@RequestMapping(value = "review/review_view.do", method = RequestMethod.GET)
	public String view(Model model) throws SQLException {
		
		List codeListParam = new ArrayList<String>();
		codeListParam.add("LIST_PAGE_SIZE");		// 페이지 사이즈 
		
		// getCodePageRetrieve에 LIST에 들어간 파라미터를 추가한 codeListParam을 변수로 줘서 SQL에 반영할 수 있도록 조치하고, List로 저장
		List<Code> codeList = getCodePageRetrieve(codeListParam);
		
		List<Code> listPageSizeList = new ArrayList<Code>();
		
		for(Code vo : codeList) {
			
			if(vo.getMstCode().equals("LIST_PAGE_SIZE")){
				listPageSizeList.add(vo);
				LOG.debug(vo.toString());
			}

		}
		
		LOG.debug(listPageSizeList.toString());
		
		// 페이즈 사이즈 코드
		// 그렇게 모든 메서드를 거치고 난 결과를 ModelAndView로 볼 수 있게 조치
		model.addAttribute("LIST_PAGE_SIZE", listPageSizeList);
		
		return VIEW_NAME;
		
	}
	
	public List<?> getCodePageRetrieve(List<String> codeList) throws SQLException{
		
		Map<String, Object> codeMap = new HashMap<String, Object>();
		codeMap.put("codeList", codeList);
		
		return codeService.getCodeRetrieve(codeMap);
		
	}
	
	
	@RequestMapping(value = "/review/review_detail_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String detailView(Model model, @RequestParam(value = "reviewNo", required = false)String reviewNo) throws Exception {
		
		model.addAttribute("reviewNo", reviewNo);
		
		LOG.debug("=======================");
		LOG.debug("reviewNo : " + reviewNo);
		LOG.debug("=======================");
		
		return "review/review_detail";
	}
	
	@RequestMapping(value = "/review/review_reg_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String regView(Model model, @RequestParam(value = "reviewNo", required = false)String reviewNo) throws Exception {
		
//		model.addAttribute("reviewNo", reviewNo);
//		model.addAttribute("orderNo", orderNo);
//		model.addAttribute("qUser", qUser);
		
		LOG.debug("=======================");
		LOG.debug("reviewNo : " + reviewNo);
		LOG.debug("=======================");
		
		return "review/review_reg";
	}
	
	@RequestMapping(value = "/review/review_mng_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String mngView(Model model, @RequestParam(value = "reviewNo", required = false)String reviewNo) throws Exception {
		
		model.addAttribute("reviewNo", reviewNo);
		
		LOG.debug("=======================");
		LOG.debug("reviewNo : " + reviewNo);
		LOG.debug("=======================");
		
		return "review/review_mng";
	}
	
//	▼ 메서드 ================================================================	
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
		
		//this.doReadCnt(review);
		
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
