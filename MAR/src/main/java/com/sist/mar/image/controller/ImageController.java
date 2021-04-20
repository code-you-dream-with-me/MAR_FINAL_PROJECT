package com.sist.mar.image.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sist.mar.cmn.Message;
import com.sist.mar.image.domain.ImageVO;
import com.sist.mar.image.service.ImageServiceImpl;

@Controller
public class ImageController {

//	▼ 변수 ===============================================================
	
	final Logger LOG = LoggerFactory.getLogger(ImageController.class);
	
	@SuppressWarnings("unused")
	private String viewName = "";
	
	@Autowired
	ImageServiceImpl imageService;
	
	
//	▼ 생성자 ==============================================================	
	
	public ImageController() {}
	
	
//	▼ 메소드 ===============================================================
	
	
	@RequestMapping(value = "image/do_innsert.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(@RequestParam(value = "imageList", required = false)String jsonStr, 
				           @RequestParam(value = "fromTb", required = false)String fromTb 
			) throws Exception {
		
		Gson gson = new Gson();
		List<ImageVO> imageList = gson.fromJson(jsonStr, new TypeToken<List<ImageVO>>() {}.getType());
		
		Message message = new Message();
		message.setMsgId(Integer.toString(imageService.upRegisterImages(imageList, fromTb)));
		
		if(message.getMsgId().equals("1")) message.setMsgContents("이미지 등록이 완료되었습니다.");
		else message.setMsgContents("이미지 등록에 실패하였습니다.");
		
		LOG.debug("메세지: "+gson.toJson(message));
		
		return gson.toJson(message);
		
	}
	
	@RequestMapping(value = "image/do_delete.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(@RequestParam(value = "imageList", required = false)String jsonStr) throws Exception {
		
		Gson gson = new Gson();
		List<ImageVO> imageList = gson.fromJson(jsonStr, new TypeToken<List<ImageVO>>() {}.getType());
		
		Message message = new Message();
		message.setMsgId(Integer.toString(imageService.upDeleteImages(imageList)));
		
		if(message.getMsgId().equals("1")) message.setMsgContents("이미지 삭제가 완료되었습니다.");
		else message.setMsgContents("이미지 삭제에 실패하였습니다.");
		
		LOG.debug("메세지: "+gson.toJson(message));
		
		return gson.toJson(message);
		
	}
	
	@RequestMapping(value = "image/do_update.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(
			@RequestParam(value = "fromTb", required = false)String fromTb,
			@RequestParam(value = "fromNo", required = false)String fromNo,
			@RequestParam(value = "imageListDel", required = false)String jsonStrDel,
			@RequestParam(value = "imageListNew", required = false)String jsonStrNew
			) throws Exception {
		
		Gson gson = new Gson();
		List<ImageVO> imageListDel = gson.fromJson(jsonStrDel, new TypeToken<List<ImageVO>>() {}.getType());
		List<ImageVO> imageListNew = gson.fromJson(jsonStrNew, new TypeToken<List<ImageVO>>() {}.getType());
		
		Message message = new Message();
		message.setMsgId(Integer.toString(imageService.upUpdateImages(Integer.parseInt(fromTb), Integer.parseInt(fromNo), imageListDel, imageListNew)));
		
		if(message.getMsgId().equals("1")) message.setMsgContents("이미지 수정이 완료되었습니다.");
		else message.setMsgContents("이미지 수정에 실패하였습니다.");
		
		LOG.debug("메세지: "+gson.toJson(message));
		
		return gson.toJson(message);
		
	}
	
	
	@RequestMapping(value = "image/do_retrieve.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(ImageVO image) throws Exception {
		List<ImageVO> imageList = imageService.doRetrieveImages(image);
		Gson gson = new Gson();
		return gson.toJson(imageList.toArray());
	}
	
	
	
	
}
