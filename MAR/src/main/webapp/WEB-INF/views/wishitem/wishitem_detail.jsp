<%@page import="com.sist.mar.member.domain.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<!-- JS -->
<script src="${hContext}/resources/js/jquery.min.js"></script>
<script src="${hContext}/resources/js/eclass.js" charset="utf-8"></script>
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" >
<title>WISHITEM_DETAIL</title>

</head>	
<body>
	<!-- center -->
	<form action="" id="wishPopupFrm" name="wishPopupFrm" >
		<input type="hidden" name="memberId" id="memberId" value="<%=vo.getMemberId()%>"/>
		<input type="hidden" name="itemNo"   id="itemNo" value="${outVO.itemNo}"  />
	</form>
	
	<div class="pop_section">
		<div class="pop_cont_wrap">
			<div class="pop_lst_wrap">
				<dl class="cont_dfnt_lst">
					<dt>
						<input type="text" name="item_name" id="item_name" value="${outVO.item_name}">
					</dt>
					<dd>
						<c:choose>
							<c:when test="${outVO.item_price eq outVO.item_final_price}">
								<input type="text" name="item_price" id="item_price" value="${outVO.item_price}원">
								<input type="hidden" name="item_final_price" id="item_final_price" value="${outVO.item_final_price}원">
							</c:when>
							<c:otherwise>
								<div class='in-block'>
									<div class="discount_outer">
										<div class="discount">
											<span class="item_price" >${outVO.item_price}원</span>
										</div>
									</div>
									<input type="text" name="item_final_price" id="item_final_price" value="${outVO.item_final_price}원" >
								</div>
							</c:otherwise>
						</c:choose>
					</dd>
				</dl>
				<div class="cont_wrap">
					<ul class="in-block">
						<li>
							<input type="button" value="-" id="minus" >
						</li>
						<li>
							<input type="text" name="quantity" id="quantity" value="1" >
						</li>
						<li>
							<input type="button" value="+" id="plus" >
						</li>
					</ul>
				</div>
			</div>
			<div class="pop_bottom_wrap">
				<div class="pop_bottom_inner_wrap">
					<div class="pop_tot_cont">
						<input type="text" value="합계">
					</div>
					<div class="pop_tot_price">
						<input type="text" name="total_price" id="total_price" value="${outVO.item_final_price}">
						<span>원</span>
					</div>
				</div>
				<ul class="btn_lst in-block">
					<li>
						<button class="border_color" onClick="self.close();">취소</button>
					</li>
					<li>
						<button class="bg_color" id="addToCartList">장바구니 담기</button>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- center -->
	
	<script type="text/javascript">
		// 페이지시작 -------------------------------------------
		$(document).ready(function() {});

		// 플러스버튼 ------------------------------------------
		$("#plus").on("click", function(e){
			console.log("plus");
			e.preventDefault();
			
			if(parseInt($("#quantity").val()) >= 99){
				alert("수량을 더이상 늘릴 수 없습니다.");
				return;
			}
			
			let num = parseInt($("#quantity").val()) + 1;
			let price = num * parseInt($("#item_final_price").val());
			$("#quantity").val(num);
			$("#total_price").val(price);
		});
		
		// 마이너스버튼 ------------------------------------------
		$("#minus").on("click", function(e){
			console.log("minus");
			e.preventDefault();
			if(parseInt($("#quantity").val()) == 1){
				alert("수량을 더이상 줄일 수 없습니다.");
				return;
			}
			let num = parseInt($("#quantity").val()) - 1;
			let price = num * parseInt($("#item_final_price").val());
			$("#quantity").val(num);
			$("#total_price").val(price);
		});
		
		// 엔터버튼 ------------------------------------------
		$("#quantity").on("keypress", function(e) {
			if(window.event.keyCode == 13){
				if(parseInt($("#quantity").val()) >= 100){
					alert("수량은 99개 이상은 주문할 수 없습니다.");
					return;
				}
				let num = $("#quantity").val();
				let price = num * parseInt($("#item_final_price").val());
				$("#quantity").val(num);
				$("#total_price").val(price);
			}
		});
		
		// 장바구니담기(팝업 닫기) -----------------------------------
		$("#addToCartList").on("click", function(e){
			e.preventDefault();
			
			if(parseInt($("#quantity").val()) >= 100){
				alert("수량은 99개 이상은 주문할 수 없습니다.");
				return;
			}

			let url       = "${hContext}/cart/do_insert.do";
			let paramters = {
								"memberId" : $("#memberId").val(),
								"itemNo"   : $("#itemNo").val(),
								"quantity" : $("#quantity").val()
							};
			let method    = "POST";
			let async     = "true";
			EClass.callAjax(url, paramters, method, async, function(data) {
				if(data.msgId == 0) {
					alert("data.msgContents");
				}else{
					if(confirm(data.msgContents+"\늘 사는 것으로 이동하시겠습니까?")==true){
						javascript:opener.window.location.href ="${hContext}/cart/cart_list.do";
					}
				}
				self.close();
				
			});		
		});
	</script>


</body>
</html>