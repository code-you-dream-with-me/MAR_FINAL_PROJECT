package com.sist.mar.member.controller;

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

	@RequestMapping(value = "member/sign_in_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String viewLogin(Model model) throws Exception {
		return "sign/sign_in";
	}
	
	@RequestMapping(value = "member/sign_up_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String viewSignUp(Model model) throws Exception {
		return "sign/sign_up";
	}
	
	@RequestMapping(value = "member/mypage_view.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	public String viewMypage(Model model, HttpSession session, MemberVO member) throws Exception {
		
		//세션 받아서 회원정보 검색
		if(null != session.getAttribute("member")) {
			MemberVO inVO= (MemberVO) session.getAttribute("member");
			member.setMemberId(inVO.getMemberId());
		}
		
		MemberVO outVO = memberService.doSelectOne(member);
		
		//화면으로 전송
		model.addAttribute("vo", outVO);
		
		//화면
		return "member/mypage";
	}
	
	
	@RequestMapping(value = "member/do_register.do", method = RequestMethod.POST
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
	
	@RequestMapping(value = "member/do_login_check.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doLoginCheck(MemberVO memberVO, HttpSession session) throws Exception {
		
		LOG.debug("doLoginCheck");
		Message message = new Message();
		message.setMsgId(Integer.toString(memberService.doLoginCheck(memberVO)));
		
		if(message.getMsgId().equals("1")) {
			message.setMsgContents("로그인이 완료되었습니다.");
			MemberVO member = memberService.doSelectOne(memberVO);
			session.setAttribute("member", member);
		}
		else {
			message.setMsgContents("아이디와 비밀번호를 확인해 주세요.");
		}
		
		Gson gson = new Gson();
		LOG.debug("메세지: "+gson.toJson(message));
		
		return gson.toJson(message);
		
	}
	
	@RequestMapping(value = "member/do_logoff.do", method = RequestMethod.GET)
	public String doLogOff(HttpSession session) {
		String returnUrl = "main/main";
		
		if(null != session.getAttribute("member")) {
			session.removeAttribute("member");
			session.invalidate();
		}
		
		return returnUrl;
	}
	
	@RequestMapping(value = "member/do_check_duplicated_id.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doCheckDuplicatedId(MemberVO memberVO) throws Exception {
		
		LOG.debug("doCheckDuplicatedId");
		Message message = new Message();
		message.setMsgId(Integer.toString(memberService.doCheckDuplicatedId(memberVO)));
		
		if(message.getMsgId().equals("1")) message.setMsgContents("이미 존재하는 이메일입니다.");
		else message.setMsgContents("사용가능한 이메일입니다.");
		
		Gson gson = new Gson();
		LOG.debug("메세지: "+gson.toJson(message));
		
		return gson.toJson(message);
		
	}
	
	@RequestMapping(value = "member/do_update.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(MemberVO member) throws Exception {
		LOG.debug("doUpdate");
		
		Message message = new Message();
		message.setMsgId(Integer.toString(memberService.doUpdate(member)));
		
		if(message.getMsgId().equals("1")) message.setMsgContents("수정되었습니다.");
		else message.setMsgContents("수정되지 않았습니다.");
		
		Gson gson = new Gson();
		LOG.debug("메세지: "+gson.toJson(message));
		
		return gson.toJson(message);
	}
	
	
}
