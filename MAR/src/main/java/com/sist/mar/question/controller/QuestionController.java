package com.sist.mar.question.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sist.mar.cmn.Message;
import com.sist.mar.cmn.Search;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.code.domain.Code;
import com.sist.mar.code.service.CodeService;
import com.sist.mar.question.domain.QuestionVO;
import com.sist.mar.question.service.QuestionServiceImpl;


@Controller
public class QuestionController {

//	▼ 변수 ===============================================================
	final Logger LOG = LoggerFactory.getLogger(QuestionController.class);
	
	final String VIEW_NAME = "question/question_list";
	
	@Autowired
	QuestionServiceImpl questionServiceImpl;
	
	@Autowired
	CodeService codeService;

//	▼ 생성자 ==============================================================	
	public QuestionController()  {
		
	}
	
//	▼ 뷰 =================================================================	
	@RequestMapping(value = "question/question_view.do", method = RequestMethod.GET)
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
	
	
	@RequestMapping(value = "/question/question_detail_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String detailView(Model model, @RequestParam(value = "questionNo", required = false)String questionNo) throws Exception {
		
		model.addAttribute("questionNo", questionNo);
		
		LOG.debug("=======================");
		LOG.debug("questionNo : " + questionNo);
		LOG.debug("=======================");
		
		return "question/question_detail";
	}
	
	@RequestMapping(value = "/question/question_reg_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String regView(Model model, @RequestParam(value = "questionNo", required = false)String questionNo) throws Exception {
		
//		model.addAttribute("questionNo", questionNo);
//		model.addAttribute("orderNo", orderNo);
//		model.addAttribute("qUser", qUser);
		
		LOG.debug("=======================");
		LOG.debug("questionNo : " + questionNo);
		LOG.debug("=======================");
		
		return "question/question_reg";
	}
	
	@RequestMapping(value = "/question/question_mng_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String mngView(Model model, @RequestParam(value = "questionNo", required = false)String questionNo) throws Exception {
		
		model.addAttribute("questionNo", questionNo);
		
		LOG.debug("=======================");
		LOG.debug("questionNo : " + questionNo);
		LOG.debug("=======================");
		
		return "question/question_mng";
	}
	
//	▼ 메서드 ================================================================		
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
	

	
	
//	@RequestMapping(value = "/question/question_detail_view.do", method = RequestMethod.GET)
//	public ModelAndView detailView(MultipartHttpServletRequest param, ModelAndView model) throws SQLException {
//		
//		int questionNo = Integer.parseInt(param.getParameter("questionNo"));
//		
//		QuestionVO question = new QuestionVO();
//		
//		question.setQuestionNo(questionNo);
//		
//		QuestionVO outVO = (QuestionVO) this.questionServiceImpl.doSelectOne(question);
//		
//		List<QuestionVO> list = new ArrayList<QuestionVO>();
//		
//		list.add(outVO);
//		model.addObject("list", list);
//		model.setViewName("question/question_detail");
//		
//		return model;
//		
//	}
	
	
//	@RequestMapping(value = "/question/question_detail_view.do", method = RequestMethod.GET)
//	public ModelAndView detailView() throws SQLException {
//		
//		ModelAndView view = new ModelAndView();
//		view.setViewName("question/question_detail");
//		
//		// 1. jsp에서 값 받기
//		// 2. 그 값을 이용 doselectone 돌리기
//		// 3. 돌린 값을 addobject(vo, "jsp값"); 놓기
//
//		return view;
//		
//	}

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
