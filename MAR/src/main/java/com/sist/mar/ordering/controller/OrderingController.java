package com.sist.mar.ordering.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.sist.mar.cmn.Search;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.code.domain.Code;
import com.sist.mar.code.service.CodeService;
import com.sist.mar.member.domain.MemberVO;
import com.sist.mar.order.domain.Ordering;
import com.sist.mar.order.domain.Orderitem;
import com.sist.mar.ordering.service.OrderingServiceImpl;
import com.sist.mar.question.controller.QuestionController;
import com.sist.mar.question.domain.QuestionVO;

@Controller
public class OrderingController {
	
	final Logger LOG = LoggerFactory.getLogger(QuestionController.class);
	
	final String VIEW_NAME = "ordering/ordering_list";
	
	@Autowired
	OrderingServiceImpl orderingServiceImpl;
	
	@Autowired
	CodeService codeService;

//	▼ 생성자 ==============================================================	
	public OrderingController() {
		
		
	}
	
//	▼ 뷰 =================================================================		
	
	@RequestMapping(value = "ordering/ordering_view.do", method = RequestMethod.GET)
	public String orderingView(Model model, HttpSession session) throws SQLException {
		
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

		
		// 로그인 관련
		String memberId = "";
		
		// 로그인 가정한 조회 테스트
		if(null != session.getAttribute("member")) {
			
			MemberVO member = (MemberVO) session.getAttribute("member");
			memberId = member.getMemberId();
			
			//하루가 지난 상품에 대해 판매량 업데이트
			doUpdateStateAndSales(memberId);

		}
		
		
		
		// 페이즈 사이즈 코드
		// 그렇게 모든 메서드를 거치고 난 결과를 ModelAndView로 볼 수 있게 조치
		model.addAttribute("LIST_PAGE_SIZE", listPageSizeList);
		model.addAttribute("memberId", memberId);
		
		return "ordering/ordering_list";
		
	}
	
	
	public List<?> getCodePageRetrieve(List<String> codeList) throws SQLException{
		
		
		Map<String, Object> codeMap = new HashMap<String, Object>();
		codeMap.put("codeList", codeList);
		
		return codeService.getCodeRetrieve(codeMap);
		
	}
	
	
	// 연결값으로 model.addAttribute를 넘겨 주는 대신, 원하는 VO를 넣으면 JSP에서 넘겨준 값을 스프링에서 해당 값이 맞춰 처리해줌 
	@RequestMapping(value = "ordering/ordering_item_detail_view.do", method = RequestMethod.GET)
	public String orderingItemDetailView(Ordering ordering) throws SQLException {
	

		return "ordering/ordering_item_detail";	
		
	}

	
	
//	▼ 메서드 =================================================================	
	
	@RequestMapping(value = "ordering/do_undoRequest.do", method = RequestMethod.GET ,
 			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUndoRequest(Ordering ordering) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("param : " + ordering);
		LOG.debug("=======================");
		
		int flag = this.orderingServiceImpl.doUndoRequest(ordering);
		
		String resultMsg = "";
		
		if(1 == flag) {
			resultMsg = ordering.getOrderNo()  + "번 주문에서 \n취소요청이 들어왔습니다";
		}else {
			resultMsg = ordering.getOrderNo()  + "번 주문의 \n취소요청이 전달되지 못했습니다";
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
	
	
	@RequestMapping(value = "ordering/do_retrieve.do", method = RequestMethod.GET ,
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
		
		List<Ordering> list = (List<Ordering>) orderingServiceImpl.doRetrieve(search);
		for(Ordering vo : list) {
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
	
	
	@RequestMapping(value = "ordering/do_selectOne.do", method = RequestMethod.GET ,
 			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Ordering ordering) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("param : " + ordering);
		LOG.debug("=======================");
		
		Ordering outVO = (Ordering) this.orderingServiceImpl.doSelectOne(ordering);
		
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

	
	/**
	 * 주문상품조회
	 * @param user
	 * @return JSON(User)
	 * @throws SQLException
	 */
	@RequestMapping(value = "ordering/do_selectItemlist.do", method =  RequestMethod.GET
			, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectItemList(Orderitem orderitem) throws SQLException {
		
		LOG.debug("=======================");
		LOG.debug("orderitem : " + orderitem);
		LOG.debug("=======================");
		
		List<Orderitem> list = (List<Orderitem>) orderingServiceImpl.doSelectItemList(orderitem);
		for(Orderitem vo : list) {
		LOG.debug(vo.toString());
		}
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		LOG.debug("=======================");
		LOG.debug("jsonStr : " + jsonStr);
		LOG.debug("=======================");
		
		return jsonStr;
	}
	
	//주문 상태 update 및 구매 수량 update =================================================================================
	private void doUpdateStateAndSales(String memberId) throws SQLException {
		LOG.debug("=========================");
		LOG.debug("= doUpdateStateAndSales =");
		LOG.debug("= memberId = " + memberId);
		LOG.debug("=========================");
		
		//세션값을 ordering에 setting
		Ordering ordering = new Ordering();
		Orderitem orderitem = new Orderitem();
		ordering.setMemberId(memberId);
		
		//세션값으로 주문한지 하루가 지난 주문 조회
		List<Ordering> orderingList = (List<Ordering>) orderingServiceImpl.doRetrieveOrdering(ordering);
		
		for(Ordering orderingVO : orderingList) {
			
			//하루가 지나면 상태값을 4로 업데이트
			orderingServiceImpl.doUpdateOrdering(orderingVO);
			
			//orderingVO의 각 주문번호들을 orderitem에 setting
			orderitem.setOrderNo(orderingVO.getOrderNo());
			//주문번호로 주문상품들의 상품번호와 상품수량 조회
			List<Orderitem> orderitemList = (List<Orderitem>) orderingServiceImpl.doRetrieveOrderitem(orderitem);
			
			for(Orderitem orderitemVO : orderitemList) {
				
				//상품번호에 맞는 상품수량 업데이트 (sales += 수량)
				orderingServiceImpl.doUpdateItem(orderitemVO);
			}
		}
	}
	//=============================================================================================================

	
}
