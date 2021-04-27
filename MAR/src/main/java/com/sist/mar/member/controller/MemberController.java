package com.sist.mar.member.controller;

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
import com.sist.mar.member.domain.MemberVO;
import com.sist.mar.member.service.MemberServiceImpl;

@Controller
public class MemberController {

//	▼ 변수 ===============================================================

	final Logger LOG = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberServiceImpl memberService;
	
//	▼ 생성자 ==============================================================	

	public MemberController() {}
	
//	▼ 메소드 ===============================================================

	@RequestMapping(value = "member/login_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String viewLogin(Model model) throws Exception {
		return "sign/sign_in";
	}
	
	@RequestMapping(value = "member/sign_up_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String viewSignUp(Model model) throws Exception {
		return "sign/sign_up";
	}
	
	
	@RequestMapping(value = "recipe/do_register.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRegister(MemberVO memberVO) throws Exception {
		
		LOG.debug("doRegister");
		Message message = new Message();
		message.setMsgId(Integer.toString(memberService.doRegister(memberVO)));
		
		if(message.getMsgId().equals("1")) message.setMsgContents("회원 등록이 완료되었습니다.");
		else message.setMsgContents("회원 등록에 실패하였습니다.");
		
		Gson gson = new Gson();
		LOG.debug("메세지: "+gson.toJson(message));
		
		return gson.toJson(message);
		
	}
	
}
