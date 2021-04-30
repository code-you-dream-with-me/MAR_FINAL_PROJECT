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
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.sist.mar.cmn.Message;
import com.sist.mar.order.controller.OrderController;
import com.sist.mar.payment.service.PaymentService;

@Controller
public class PaymentController {
	final Logger LOG = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	PaymentService paymentService;
	
	private IamportClient api;
	
	private String api_key = "실행시 입력";
	private String api_secret = "실행시 입력";
	
	public PaymentController() {
    	// REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
		this.api = new IamportClient(api_key, api_secret);
	}
	
	// 결제 검증 --------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/verifyIamport/{imp_uid}.do", method = {RequestMethod.GET, RequestMethod.POST})
	public IamportResponse<Payment> paymentByImpUid( Model model, Locale locale, HttpSession session, 
	@PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException {	
			return api.paymentByImpUid(imp_uid);
	}
	
	// 결제 완료 페이지 --------------------------------------------------------------------------------
	@RequestMapping(value = "payment/do_payment.do", method = RequestMethod.GET)
	public String doPayment(String memberName, int price, String payNo, Model model) throws SQLException {
		LOG.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		LOG.debug("doPayment");
		LOG.debug("memberName : " + memberName);
		LOG.debug("price : " + price);
		LOG.debug("payNo : " + payNo);
		LOG.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		
		model.addAttribute("memberName", memberName);
		model.addAttribute("price", price);
		model.addAttribute("payNo", payNo);
		
		return "payment/payment_detail";
	}
	
	// 결제 취소 -----------------------------------------------------------------------------------
	@RequestMapping("payment/do_payment_cancel.do")
	@ResponseBody
	public Message dopaymentCancel(Model model, String payNo, HttpSession session) throws IamportResponseException, IOException{
		//취소를 위한 클라이언트 생성
		IamportClient client;
		client = new IamportClient(api_key, api_secret);
		
		//취소 접근 권한 얻기
		IamportResponse<AccessToken> auth_response = client.getAuth();
		
		//취소 진행
		boolean cancelCheck=false;
		String resultMsg = "";
		if(auth_response.getResponse()!=null){
			CancelData cancel_data = new CancelData(payNo, true);
			IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);
			if(payment_response.getResponse()!=null){
				cancelCheck=true;
				resultMsg = "결제가 취소되었습니다.";
				
				//=====================================
				//재욱님 dao 추가부분
				//=====================================

			}else{
				resultMsg = "취소되지 않았습니다.";			
			}			  				
		}
		
		Message msg = new Message();
		msg.setMsgContents(resultMsg);
		return msg;
	}
}
