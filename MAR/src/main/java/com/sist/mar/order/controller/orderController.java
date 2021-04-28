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

import com.sist.mar.cart.domain.Cart;
import com.sist.mar.cart.service.CartService;
import com.sist.mar.cmn.StringUtil;
import com.sist.mar.member.domain.MemberVO;
import com.sist.mar.order.domain.Ordering;
import com.sist.mar.order.domain.Orderitem;
import com.sist.mar.order.service.OrderService;

@Controller
public class orderController {

	final Logger LOG = LoggerFactory.getLogger(orderController.class);
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderService orderService;

	// 주문서 페이지 -------------------------------------------------------------------------------------
	@RequestMapping(value = "order/order_form.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String order_form(String memberId, Model model) throws SQLException {
		LOG.debug("================");
		LOG.debug("= order_form() =");
		LOG.debug("================");
		
		//장바구니 정보
		List<Cart> list = (List<Cart>) cartService.doRetrieve(memberId);
		//회원정보
		MemberVO outVO = (MemberVO) cartService.doOrder(memberId);
		LOG.debug("outVO : " + outVO);
		
		model.addAttribute("list", list);
		model.addAttribute("outVO", outVO);
		
		return "order/order_form";
	}
	
	// 주문서 페이지 -------------------------------------------------------------------------------------
	@RequestMapping(value = "order/do_payment.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doPayment(Ordering ordering) throws SQLException {
		LOG.debug("= doPayment() =");
		LOG.debug("ordering : " + ordering);

		//nvl 처리
		ordering.setOrderState(StringUtil.nvl(ordering.getOrderState(), "1"));
		
		//주문테이블 등록
		orderService.doInsertOrdering(ordering);

		//주문 번호 조회
		int orderNo = orderService.doSelectOneOrderNo(ordering.getMemberId());
		
		//장바구니 내역 조회
		List<Cart> list = (List<Cart>) cartService.doRetrieve(ordering.getMemberId());
			
		//등록에 필요한 값을 orderitem에 넣어주기(orderNo는 공통값)
		Orderitem orderitem = new Orderitem();
		orderitem.setOrderNo(orderNo);
		for(Cart cart : list) {
			orderitem.setItemNo(cart.getItemNo());
			orderitem.setQuantity(cart.getQuantity());
			orderService.doInsertOrderitem(orderitem);
		}
		orderService.doDeleteCart(ordering.getMemberId());

		
		return "결제성공";
	}
	
}
