package com.sist.mar.image.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sist.mar.cmn.Message;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.image.domain.ImageVO;
import com.sist.mar.image.service.ImageServiceImpl;

@Controller
public class ImageController {

//	▼ 변수 ===============================================================
	
	final Logger LOG = LoggerFactory.getLogger(ImageController.class);
	
	private String VIEW_NAME = "image/file_upload_popup";
	
	@Autowired
	ImageServiceImpl imageService;
	
	
//	▼ 생성자 ==============================================================	
	
	public ImageController() {}
	
	
//	▼ 메소드 ===============================================================
	
	@RequestMapping(value = "image/image_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String view(
			@RequestParam(value = "fromTb", required = false)String fromTb,
			Model model) throws Exception {
		LOG.debug("fromTb: "+fromTb);
		model.addAttribute("fromTb", fromTb);
		return VIEW_NAME;
	}
	
	@RequestMapping(value = "image/do_upload.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpload(MultipartHttpServletRequest request) throws Exception {
		
		List<MultipartFile> fileList = request.getFiles("file_list");
		
		Gson gson = new Gson();
		
		//파일이 업로드 될 경로
		String path = "C:\\Users\\123wo\\git\\MAR_FINAL_PROJECT\\MAR\\src\\main\\webapp\\resources\\upload";
		String simplePath = "/resources/upload/";
		
		
		//위에서 설정한 경로의 폴더가 없을 경우 생성
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		
		List<ImageVO> imageList = new ArrayList<ImageVO>();
		
		for(MultipartFile file : fileList){
			if(!file.isEmpty()) {
				String orgName = file.getOriginalFilename();
				String saveName = StringUtil.getUUID() + orgName;
				int fileSize = (int) file.getSize();
				String fileExt = orgName.substring(orgName.lastIndexOf("."));
				
				ImageVO image = new ImageVO();
				image.setOrgName(orgName);
				image.setSaveName(saveName);
				image.setPath(simplePath);
				image.setFileSize(fileSize);
				image.setFileExt(fileExt);
				
				imageList.add(image);
				
				file.transferTo(new File(path, saveName));
			}
		}
		
		
		String jsonStr = gson.toJson(imageList.toArray());
		
		LOG.debug(jsonStr);
		
		return jsonStr;
		
	}
	
	
	@RequestMapping(value = "image/do_insert.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(@RequestParam(value = "imageList", required = false)String jsonStr, 
				           @RequestParam(value = "fromTb", required = false)String fromTb,
				           @RequestParam(value = "MainImage", required = false)String mainImage
			) throws Exception {
		
		int mainImageNum = Integer.parseInt(StringUtil.nvl(mainImage, "0"));
		LOG.debug("mainImage"+ mainImageNum);
		
		Gson gson = new Gson();
		List<ImageVO> imageList = gson.fromJson(jsonStr, new TypeToken<List<ImageVO>>() {}.getType());
		
		Message message = new Message();
		message.setMsgId(Integer.toString(imageService.upRegisterImages(imageList, fromTb, mainImageNum)));
		
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
