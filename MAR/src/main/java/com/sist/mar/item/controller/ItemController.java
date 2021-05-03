package com.sist.mar.item.controller;

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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import com.sist.mar.cmn.Message;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.code.domain.Code;
import com.sist.mar.code.service.CodeService;
import com.sist.mar.item.domain.Item;
import com.sist.mar.item.service.ItemService;
import com.sist.mar.member.domain.MemberVO;
import com.sist.mar.review.service.ReviewServiceImpl;

@Controller
public class ItemController {
	
	final Logger LOG = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	ReviewServiceImpl reviewServiceImpl;
	
	@Autowired
	CodeService codeService;
	
	public ItemController ()  {}
	
	@RequestMapping(value = "item/item_view.do")
	public String view01(Model model) {
		LOG.debug("=================");
		LOG.debug("=item view=");
		LOG.debug("=================");
		return "item/item_reg";
	}
	
//	@RequestMapping(value = "item/item_deview.do")
//	public String view02(Model model) {
//
//		return "item/item_detail_review";
//	}
	@RequestMapping(value = "item/item_deview.do", method = RequestMethod.GET)
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
		
		return "item/item_detail_review";
		//return "item/item_detail";
		
	}
	
	
	public List<?> getCodePageRetrieve(List<String> codeList) throws SQLException{
		
		Map<String, Object> codeMap = new HashMap<String, Object>();
		codeMap.put("codeList", codeList);
		
		return codeService.getCodeRetrieve(codeMap);
		
	}
	
	@RequestMapping(value = "item/item_mod.do")
	public String view03(Model model) {

		return "item/item_mod";
	}
	
	
	
	
	/**
	 * 상품 전체 목록 조회
	 * @param dto
	 * @return JSON(Item)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */     
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "item/get_all.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody		
	public String getAllList() throws SQLException {
		List<Item> list = (List<Item>) itemService.getAllList();
		for(Item vo:list) {
			LOG.debug(vo.toString());
		}
		
		//list to json
		Gson  gson=new Gson();
		String jsonList = gson.toJson(list);
		
		LOG.debug("jsonList:"+jsonList);
		
		return jsonList;

	}
	
	/**
	 * 관련 상품 목록 조회
	 * @param dto
	 * @return JSON(Item)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */     
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "item/get_relatedlist.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody		
	public String getRelatedList(Item item) throws SQLException {
		LOG.debug("param_init:"+item);
		

		List<Item> list = (List<Item>) itemService.getRelatedList(item);
		for(Item vo:list) {
			LOG.debug(vo.toString());
		}
		
		//list to json
		Gson  gson=new Gson();
		String jsonList = gson.toJson(list);
		
		LOG.debug("jsonList:"+jsonList);
		
		return jsonList;

	}
	
	
	
	/**
	 * 상품 단건 조회
	 * @param dto
	 * @return JSON(Item)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */     
	@RequestMapping(value = "item/do_selectone.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String doSelectOne(Item item) throws SQLException {
		LOG.debug("param:"+item);
		
		Item outVO = (Item) itemService.doSelectOne(item);
		
		LOG.debug("outVO:"+outVO);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(outVO);
		LOG.debug("jsonStr:"+jsonStr);
		
		return jsonStr;
	}
	
	/**
	 * 상품 수정
	 * @param dto
	 * @return JSON(1:성공,0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */     
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "item/do_update.do",method = RequestMethod.POST
			,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody       
	public String doUpdate(Item item) throws SQLException {
		LOG.debug("param:"+item);
		
		int flag = itemService.doUpdate(item);
		String resultMsg = "";
		if(1 == flag) {
			resultMsg = item.getName()+"\n 상품이 수정되었습니다.";
		}else {
			resultMsg = item.getName()+"\n수정에 실패하였습니다.";
		}
		
		Message message=new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson  gson=new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("jsonStr:"+jsonStr);
		
		return jsonStr;
	}
	
	
	/**
	 * 상품 삭제
	 * @param user
	 * @return JSON(1:성공. 0:실패) 
	 * @throws SQLException
	 */
	@RequestMapping(value = "item/do_delete.do", method = RequestMethod.POST
				,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Item item) throws SQLException{
		LOG.debug("param:"+item);
		
		int flag = itemService.doDelete(item);
		String resultMsg = "";
		
		if(1==flag) {
			resultMsg = item.getItemNo()+"\n삭제되었습니다.";
		}else {
			resultMsg = "삭제가 실패되었습니다.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("jsonStr:"+jsonStr);
		
		return jsonStr;
	}
	
	
	
	/**
	 * 상품 등록
	 * @param dto
	 * @return JSON(1:성공. 0:실패) 
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "item/do_insert.do", method = RequestMethod.GET
					,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Item item) throws SQLException{
		LOG.debug("param:"+item);
		
		int flag = itemService.doInsert(item);
		String resultMsg = "";
		
		if(1==flag) {
			resultMsg = item.getName()+"\n등록되었습니다.";
		}else {
			resultMsg = "등록을 실패하였습니다.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("jsonStr:"+jsonStr);
		
		return jsonStr;
	}
	
}
