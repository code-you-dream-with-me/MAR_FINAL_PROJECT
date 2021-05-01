<%@page import="com.sist.mar.member.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<% MemberVO vo = (MemberVO) session.getAttribute("member"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/normalize.css">
<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/y_base.css">
<!-- 결제 아임포트 -->
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js" type="text/javascript"></script>
<title>ORDER_FORM</title>
</head>
<body>
	<!-- center -->
	<div class="container01">
		<form action="" id="" name="">
			<input type="hidden" id="memberId" name="memberId" value="<%=vo.getMemberId()%>">
			<input type="hidden" id="payNo" name="payNo" value="">
		</form>
		<div class="mid_section">
			<div class="comn_tb_wrap">
				<div class="comn_tb_ttl">
					<h1>주문상품</h1>
				</div>
				<table class="goods_info">
					<thead>
						<tr>
							<th class="cart_lst_wrap" colspan="3">
								<p>상품</p>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="row_sum_price" value="0" />
						<c:set var="row_sum_sale" value="0" />
						<c:forEach var="vo" items="${list}">
							<tr>
								<td class="cartNo" style="display: none;">${vo.cartNo}</td>
								<td class="info_gs">
									<div class="in-block">
										<div class="img_wrap">
											<img src="${vo.image_path} ${vo.image_save_name}" alt="이미지">
										</div>
										<div class="info_goods_wrap">
											<dl>
												<dt>
													<p>${vo.item_name}</p>
												</dt>
											</dl>
										</div>
									</div>
								</td>
								<td class="cont_wrap">
									<ul class="in-block">
										<li><input type="text" value="${vo.quantity}개"></li>
									</ul>
								</td>
								<td class="cout_pay_wrap"><c:choose>
										<c:when test="${vo.item_price eq vo.item_final_price}">
											<input type="text" name="item_price" id="item_price"
												value="${vo.item_price}원">
											<input type="hidden" name="item_final_price"
												class="item_final_price" value="${vo.item_final_price}원">
										</c:when>
										<c:otherwise>
											<div class='in-block'>
												<div class="discount_outer">
													<div class="discount">
														<span>${vo.item_price}원</span>
													</div>
												</div>
												<input type="text" name="item_final_price"
													class="item_final_price" value="${vo.item_final_price}원">
											</div>
										</c:otherwise>
									</c:choose> <c:set var="row_sum_price"
										value="${row_sum_price + (vo.item_price * vo.quantity)}" /> <c:set
										var="row_sum_sale"
										value="${row_sum_sale + (vo.item_final_price * vo.quantity)}" />
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="comn_tb_wrap ">
				<div class="comn_tb_ttl">
					<h1>주문자 정보</h1>
				</div>
				<table class="tb_order_info">
					<tr>
						<th>
							<p>수령자명(결제자명)</p>
						</th>
						<td><input type="text" id="member_name" name="member_name"
							value="${outVO.name}" maxlength="30"></td>
					</tr>
					<tr>
						<th>
							<p>휴대폰</p>
						</th>
						<td><input type="text" id="member_phone" name="member_phone"
							value="${outVO.phone}" maxlength="11"></td>
					</tr>
					<tr>
						<th>
							<p>배송지</p>
						</th>
						<td><input type="text" id="member_addr" name="member_addr"
							value="${outVO.address}" maxlength="100"></td>
					</tr>
					<tr>
						<th>
							<p>요청사항</p>
						</th>
						<td><input type="text" id="request" name="request"></td>
					</tr>
				</table>
			</div>
			<div class="row no-gutters">
				<div class="col-md-9">
					<div class="comn_tb_wrap ">
						<div class="comn_tb_ttl">
							<h1>결제수단</h1>
						</div>
						<table class="tb_order_info">
							<tr>
								<th>
									<p>카드결제</p>
								</th>
								<td class="tb_select_wrap"><select id="selectbox">
										<option value="">선택</option>
										<option value="html5_inicis">일반결제</option>
								</select></td>
							</tr>
	
						</table>
						<div class="pay_agree_outer">
							<div class="pay_agree_inner">
								<p>결제 정보 동의(필수)</p>
								<input type="checkbox" id="privacy">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="comn_tb_ttl">
						<h1>결제금액</h1>
					</div>
					<div class="comn_color_box">
						<dl class="in-block">
							<dt>상품금액</dt>
							<dd>
								<input type="text" name="tota_origin_price"
									id="tota_origin_price">
							</dd>
						</dl>
						<dl class="in-block">
							<dt>상품 할인 금액</dt>
							<dd>
								<input type="text" name="total_sale_price" id="total_sale_price">
							</dd>
						</dl>
						<dl class="in-block">
							<dt>최종 금액</dt>
							<dd>
								<input type="text" name="total_final_price"
									id="total_final_price">
							</dd>
						</dl>
					</div>
					<div class="fin_payment">
						<button id="payment_button" name="payment_button">결제하기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- center -->
	

	<script type="text/javascript">
		// 입력값에 맞는 가격 변경 --------------------------------------------------
		$(document).ready(function() {
			console.log("document");
			$("#tota_origin_price").val(${row_sum_price});
			$("#total_sale_price").val(${row_sum_price - row_sum_sale});
			$("#total_final_price").val(${row_sum_sale});
		});
		
		// 결제 및 주문 등록 --------------------------------------------------
		function doInsertOrder() {
 			let url       = "${hContext}/order/do_order.do";
			let paramters = {
							  "payNo" : $('#payNo').val(),
							  "memberId" : $('#memberId').val(),
							  "price" : $('#total_final_price').val(),
							  "name" : $('#member_name').val(),
							  "phone" : $('#member_phone').val(),
							  "address" : $('#member_addr').val(),
							  "request" : $('#request').val()	
							};
			let method    = "POST";
			let async     = "true";
			EClass.callAjax(url, paramters, method, async, function(data) {
				alert(data.msgContents);
				window.location.href = "${hContext}/order/do_payment.do?memberName="
						             + $('#member_name').val() + "&price=" + $('#total_final_price').val();
			});	 
		}
		
		//결제하기 버튼 -----------------------------------------------------------------
		$("#payment_button").on("click", function(){
			
			let  regExp = /^[0-9]*$/;
			if(regExp.test($("#member_phone").val()) == false){
				alert("전화번호는 숫자만 입력이 가능합니다.")
				return;
			}
			
			if( $("#member_name").val().length == 0 
			 || $("#member_phone").val().length == 0
			 || $("#member_addr").val().length == 0) {
				alert("회원정보가 부족합니다."); 
				return;
			}
			
			if("" == $("#selectbox option:selected").val()) {
				alert("결제방식을 선택해주세요.");
				return;
			}
			if(false == $("#privacy").is(":checked")) {
				alert("결제 진행 필수 동의를 하지 않았습니다.");
				return;
			}
			
			var IMP = window.IMP; 
			//=================================================================
		    IMP.init('실행시에만 입력'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		    //=================================================================
		    var msg;
		        
		    IMP.request_pay({
			    pg : $("#selectbox option:selected").val(),
			    pay_method : 'card',
			    merchant_uid : 'merchant_' + new Date().getTime(),
			    name : 'markit',
			    amount : $("#total_final_price").val(),
			    buyer_email : $("#memberId").val(),
			    buyer_name : $("#member_name").val(),
			    buyer_tel : $("#member_phone").val(),
			    buyer_addr : $("#member_addr").val() 
		    }, function(rsp) {
				//imp_uid 추출하여 payNO값으로 저장
				$("#payNo").val(rsp.imp_uid);
				// 결제검증
				$.ajax({
		        	type : "POST",
		        	url : "${hContext}/verifyIamport/" + rsp.imp_uid + ".do"
		        }).done(function(data) { 
		        	// 위의 rsp.paid_amount 와 data.response.amount를 비교한후 로직 실행 (import 서버검증)
		        	if(rsp.paid_amount == data.response.amount){
		        		//결제 검증 성공시 주문정보 및 결제정보 등록
		        		doInsertOrder();
		        	} else {
		        		//결제 검증 실패시 payNO값 초기화 및 return
		        		$("#payNo").val("");
		        		alert("결제 실패");
		        		return;
		        	}
		        });
			});
	  	});
		
	</script>
</body>
</html>