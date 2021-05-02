package com.sist.mar.review.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.mar.cmn.DTO;
import com.sist.mar.cmn.Message;
import com.sist.mar.cmn.Search;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.code.domain.Code;
import com.sist.mar.code.service.CodeService;
import com.sist.mar.member.domain.MemberVO;
import com.sist.mar.order.domain.Orderitem;
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
	public String view(Model model, HttpServletRequest req, HttpSession session
						, @RequestParam(value = "searchDiv20", required = false) String searchDiv20
						, @RequestParam(value = "itemNo", required = false) String itemNo) throws SQLException {
		
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
		
		// 상품상세페이지에 연결하기 전 테스트 (화면연결들어가면 //하면 됨)
		// itemNo = "2";
		
		// 마이페이지에 연결하기 전 테스트	(화면연결들어가면 //하면 됨)
		// searchDiv20 = "20";
		
		// (마이페이지에서의 접근은 searchDiv20을 "20"으로 받게 되어있다)
		// searchDiv20 못받음 = null ( = searchDiv = "10") : 상품상세조회용 DIV 
		// searchDiv20 받음   = "20" ( = searchDiv = "10") : 마이페이지용 DIV 
		String searchDiv = StringUtil.nvl(searchDiv20, "10");
		String searchWord = "";
		
		// 상품상세조회에서 받아오는 itemNo값을 searchWord라는 이름으로 받아 해당되는 상풍 리뷰 검색
		if(searchDiv.equals("10")) {
		
			searchWord = itemNo;
			
			LOG.debug("=======================");
			LOG.debug("상품상세페이지용 후기출력용 searchWord(상품번호)와 div(10)");
			LOG.debug("=======================");
		
		// 마이페이지에서 searchDiv20이라는 값을 "20"으로 받아서 이를 searchWord라는 이름으로 받아 회원이 쓴 리뷰 검색
		}else if(searchDiv.equals("20")){
			
			MemberVO member = (MemberVO) session.getAttribute("member");
			searchWord = member.getMemberId();
			
			LOG.debug("=======================");
			LOG.debug("마이페이지용 내가 쓴 후기 출력용 searchWord(유저id)와 div(20)");
			LOG.debug("=======================");
			
		}
		
		// 페이즈 사이즈 코드
		// 그렇게 모든 메서드를 거치고 난 결과를 ModelAndView로 볼 수 있게 조치
		model.addAttribute("LIST_PAGE_SIZE", listPageSizeList);
		model.addAttribute("searchDiv", searchDiv);
		model.addAttribute("searchWord", searchWord);
		
		LOG.debug("=======================");
		LOG.debug("searchDiv : " + searchDiv);
		LOG.debug("=======================");
		
		LOG.debug("=======================");
		LOG.debug("searchWord : " + searchWord);
		LOG.debug("=======================");
		
		return VIEW_NAME;
		
	}
	
	public List<?> getCodePageRetrieve(List<String> codeList) throws SQLException{
		
		Map<String, Object> codeMap = new HashMap<String, Object>();
		codeMap.put("codeList", codeList);
		
		return codeService.getCodeRetrieve(codeMap);
		
	}
	
	
	@RequestMapping(value = "/review/review_detail_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String detailView(Model model, HttpSession session
							,@RequestParam(value = "reviewNo", required = false)String reviewNo
							,@RequestParam(value = "memberId", required = false)String memberId
							,@RequestParam(value = "orderitemNo", required = false)String orderitemNo) throws Exception {
		
		
		// 로그인 세션정보와 작성자 불일치시 정보 수정 삭제 불가능하게 막는다
		if(null != session.getAttribute("member")) {
			
			MemberVO member = (MemberVO) session.getAttribute("member");
			String checkMemberId = member.getMemberId();

			model.addAttribute("checkMemberId", checkMemberId);
			
			LOG.debug("=======================");
			LOG.debug("checkMemberId : " + checkMemberId);
			LOG.debug("=======================");
			
		}
		
		model.addAttribute("reviewNo", reviewNo);
		model.addAttribute("memberId", memberId);
		model.addAttribute("orderitemNo", orderitemNo);
		
		LOG.debug("=======================");
		LOG.debug("reviewNo : " + reviewNo);
		LOG.debug("memberId : " + memberId);
		LOG.debug("orderitemNo : " + orderitemNo);
		LOG.debug("=======================");
		
		return "review/review_detail";
	}
	
	@RequestMapping(value = "/review/review_reg_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String regView(Model model, @RequestParam(value = "orderitemNo", required = false)String orderitemNo) throws Exception {
		
		
		model.addAttribute("orderitemNo", orderitemNo);
//		model.addAttribute("qUser", qUser);
		
		LOG.debug("=======================");
		LOG.debug("orderitemNo : " + orderitemNo);
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
			
			Orderitem orderitem = new Orderitem();
			orderitem.setOrderitemNo(review.getOrderitemNo());
			doReviewStateDel(orderitem); 
			
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
	
	// 리뷰 삭제시 리뷰상태 2 -> 1로 변경
	public String doReviewStateDel(Orderitem orderitem) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("param : " + orderitem);
		LOG.debug("=======================");
		
		int flag = this.reviewServiceImpl.doReviewStateDel(orderitem);
		
		String resultMsg = "";
		
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
			Orderitem orderitem = new Orderitem();
			orderitem.setOrderitemNo(review.getOrderitemNo());
			doReviewStateInsert(orderitem); 
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
	
	// 리뷰 추가시 리뷰상태 1 -> 2로 변경
	public String doReviewStateInsert(Orderitem orderitem) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("param : " + orderitem);
		LOG.debug("=======================");
		
		int flag = this.reviewServiceImpl.doReviewStateInsert(orderitem);
		
		String resultMsg = "";
		
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
	
	// 주문상품목록에서 리뷰 보기 누르면 접근
	@RequestMapping(value = "/review/do_selectMyOne.do", method = RequestMethod.GET ,
 			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectMyOne(ReviewVO review) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("ReviewVO : " + review);
		LOG.debug("=======================");
		
		ReviewVO outVO = (ReviewVO) this.reviewServiceImpl.doSelectMyOne(review);
		
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
	
	// 후기 게시판에서 접근시 조회
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
	
	
}
