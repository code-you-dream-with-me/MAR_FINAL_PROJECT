package com.sist.mar.payment.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.sist.mar.order.controller.OrderController;
import com.sist.mar.payment.service.PaymentService;

@Controller
public class PaymentController {

	final Logger LOG = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	PaymentService paymentService;
	
	private IamportClient api;
	
	public PaymentController() {
    	// REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
		this.api = new IamportClient("실행시입력","실행시입력");
	}
	
	// 결제 검증 --------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/verifyIamport/{imp_uid}.do", method = {RequestMethod.GET, RequestMethod.POST})
	public IamportResponse<Payment> paymentByImpUid( Model model, Locale locale, HttpSession session, 
	@PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException {	
			return api.paymentByImpUid(imp_uid);
	}
	
	// 결제 완료 페이지 --------------------------------------------------------------------------------
	@RequestMapping(value = "order/do_payment.do", method = RequestMethod.GET)
	public String doPayment(String memberName, int price, Model model) throws SQLException {
		LOG.debug("doPayment");
		LOG.debug("memberId : " + memberName);
		LOG.debug("price : " + price);
		
		model.addAttribute("memberName", memberName);
		model.addAttribute("price", price);
		
		return "payment/payment_detail";
	}
	

	
}
