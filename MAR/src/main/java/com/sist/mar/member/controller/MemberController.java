package com.sist.mar.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

//	▼ 변수 ===============================================================

	final Logger LOG = LoggerFactory.getLogger(MemberController.class);
	
	
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
	
}
