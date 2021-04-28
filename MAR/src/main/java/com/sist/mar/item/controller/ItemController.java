package com.sist.mar.item.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import com.sist.mar.cmn.Message;
import com.sist.mar.item.domain.Item;
import com.sist.mar.item.service.ItemService;

@Controller
public class ItemController {
	
	final Logger LOG = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	ItemService itemService;
	
	public ItemController ()  {}
	
	@RequestMapping(value = "item/item_view.do")
	public String view01(Model model) {
		LOG.debug("=================");
		LOG.debug("=item view=");
		LOG.debug("=================");
		return "item/item_reg";
	}
	
	@RequestMapping(value = "item/item_deview.do")
	public String view02(Model model) {

		return "item/item_detail";
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
			resultMsg = item.getName()+"\n수정 성공";
		}else {
			resultMsg = item.getName()+"\n수정 실패";
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
			resultMsg = item.getItemNo()+"\n삭제 성공";
		}else {
			resultMsg = "삭제 실패";
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
			resultMsg = item.getName()+"\n등록 성공";
		}else {
			resultMsg = "등록 실패";
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
