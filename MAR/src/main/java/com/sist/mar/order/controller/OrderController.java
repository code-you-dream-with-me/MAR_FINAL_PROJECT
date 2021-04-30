package com.sist.mar.order.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.mar.cart.domain.Cart;
import com.sist.mar.cart.service.CartService;
import com.sist.mar.cmn.Message;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.member.domain.MemberVO;
import com.sist.mar.order.domain.Ordering;
import com.sist.mar.order.service.OrderService;
import com.sist.mar.payment.domain.Payment;
import com.sist.mar.payment.service.PaymentService;

@Controller
public class OrderController {

	final Logger LOG = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	PaymentService paymentService;

	// 주문서 페이지 -------------------------------------------------------------------------------------
	@RequestMapping(value = "order/order_form.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String order_form(String memberId, Model model) throws SQLException {
		LOG.debug("================");
		LOG.debug("= order_form() =");
		LOG.debug("================");
		
		//HttpSession session
		//세션으로 memberId
		//if(null != session.getAttribute("member")) {
		//	MemberVO member = (MemberVO) session.getAttribute("member");
		//	memberId = member.getuId();
		//}
		
		//장바구니 정보
		List<Cart> list = (List<Cart>) cartService.doRetrieve(memberId);
		//회원정보
		MemberVO outVO = (MemberVO) cartService.doOrder(memberId);
		LOG.debug("outVO : " + outVO);
		
		model.addAttribute("list", list);
		model.addAttribute("outVO", outVO);
		
		return "order/order_form";
	}

	// 결제 및 주문 등록 --------------------------------------------------------------------------------
	@RequestMapping(value = "order/do_order.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doOrder(Ordering ordering, Payment payment) throws SQLException {
		LOG.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		LOG.debug("doOrder");
		LOG.debug("ordering : " + ordering);
		LOG.debug("payment : " + payment);
		LOG.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		
		int flag = 0;
		String resultMsg = "";
		
		//HttpSession session
		//세션으로 memberId
		//if(null != session.getAttribute("member")) {
		//	MemberVO member = (MemberVO) session.getAttribute("member");
		//	ordering.setmemberId(member.getuId());
		//}
		
		//nvl 처리
		ordering.setOrderState(StringUtil.nvl(ordering.getOrderState(), "1"));
		payment.setState(StringUtil.nvl(payment.getState(), "1"));
		
		flag = orderService.doInsertOrdering(ordering);
		
		
		//주문테이블 등록 및 주문번호 조회
		int orderNo = orderService.doSelectOneOrderNo(ordering.getMemberId());
		//결제테이블 등록
		payment.setOrderNo(orderNo);
		paymentService.doInsert(payment);
		
		if (flag != 0) {
			//장바구니 내역에서 주문상품으로 이동 및 장바구니 삭제
			//flag = 2(등록,삭제 성공)/1(등록 성공, 삭제 실패)/0(실패)
			flag += orderService.doInsertOrderitem(ordering.getMemberId(), orderNo);
			
			if (flag == 3) {
				resultMsg = "결제 및 주문을 완료했습니다.";
			} else {
				resultMsg = "상품 등록을 실패했습니다.";
			}
		} else {
			resultMsg = "주문 등록을 실패했습니다.";
		}

		Message message = new Message();
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("jsonStr : " + jsonStr);
		return jsonStr;
	}


}
