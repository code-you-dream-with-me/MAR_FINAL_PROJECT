<%@page import="com.sist.mar.member.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<%  MemberVO vo = (MemberVO) session.getAttribute("member"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/normalize.css">
<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/y_base.css">
<title>CART_LIST</title>
<style type="text/css">
 a {
     font-size: 14px;
 }
 
 .search_input {
	 font-family: none;
 }
 
</style>
</head>	
<body>
	<!-- center -->
	<div class="container01">
		<form action="" id="cartFrm" name="cartFrm">
			<div>
				<input type="hidden" name="memberId" id="memberId" value="<%=vo.getMemberId()%>" /> 
				<input type="hidden" name="itemNo"   id="itemNo"/>
			</div>
		</form>
		
		<div class="mid_section">
			<div class="row no-gutters">
				<div class="col-md-9">
					<div class="comn_tb_wrap">
						<table class="tb_cart" id="cartTable">
							<thead>
								<tr>
									<th class="cart_lst_wrap" colspan="4">
										<p>상품</p>
									</th>
								</tr>
							</thead>
							<tbody> <!-- 동적 html 자리 --> </tbody>
						</table>
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
								<input type="text" name="tota_origin_price"  id="tota_origin_price"  />
							</dd>
						</dl>
						<dl class="in-block">
							<dt>상품 할인 금액</dt>
							<dd>
								<input type="text" name="total_sale_price" id="total_sale_price" />
							</dd>
						</dl>
						<dl class="in-block">
							<dt>최종 금액</dt>
							<dd>
								<input type="text" name="total_final_price"   id="total_final_price"   />
							</dd>
						</dl>
					</div>
					<div class="fin_payment">
						<button id="moveToOrder" >주문하기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- center -->
	<script type="text/javascript"> //상품이름 클릭시 이동 이벤트 추가하기
		//document ------------------------------------------------------------
		$(document).ready(function() {
			doRetrieve(1);
		});
		
		//테이블 내 X버튼 클릭 ----------------------------------------------------
		$("#cartTable>tbody").on("click",".delete_cart", function(e){
			//한번만 클릭
			e.preventDefault();
			//경고메세지
			if(confirm("삭제하시겠습니까?") == false) return;
			//post 형식으로 보내기
			let url       = "${hContext}/cart/do_delete.do";
			let paramters = {"param":$(this).closest('tr').find('td:eq(0)').text()};
			let method    = "POST";
			let async     = "true";
			EClass.callAjax(url, paramters, method, async, function(data) {
				alert(data.msgContents);
				doRetrieve(1);
			});	
		});
		
		// update(-버튼) -----------------------------------------------------
		$("#cartTable>tbody").on("click", ".minus", function(e) {
			//tr에서 클래스값이 quantity인 input검색
			var quantity = $(this).closest("tr").find(":input[class='quantity']");
			var $cartNo = $(this).closest("tr").find('.cartNo');
			//1보다 작으면 return
 			if (parseInt(quantity.val()) <= 1){
 				alert("수량을 더이상 줄일 수 없습니다.");
 				return;
 			}
			//quantity값 변경
			quantity.val(parseInt(quantity.val()) - 1);

 			let url       = "${hContext}/cart/do_update.do";
			let paramters = {"cartNo":$cartNo.html() , "quantity":quantity.val()};
			let method    = "POST";
			let async     = "true";
			EClass.callAjax(url, paramters, method, async, function(data) {
				doRetrieve(1);
			});	 
		});
		
		// update(+) 버튼 -----------------------------------------------------
		$("#cartTable>tbody").on("click", ".plus", function(e) {
			//tr에서 클래스값이 quantity인 input검색
			var quantity = $(this).closest("tr").find(":input[class='quantity']");
			var $cartNo = $(this).closest("tr").find('.cartNo');
			//99보다 크면 return
 			if (parseInt(quantity.val()) >= 99){
 				alert("수량을 더이상 늘릴 수 없습니다.");
 				return;
 			}
			//quantity값 변경
			quantity.val(parseInt(quantity.val()) + 1);

			let url       = "${hContext}/cart/do_update.do";
			let paramters = {"cartNo":$cartNo.html() , "quantity":quantity.val()};
			let method    = "POST";
			let async     = "true";
			EClass.callAjax(url, paramters, method, async, function(data) {
				doRetrieve(1);
			});	
		});

		// 주문서 페이지로 이동 ----------------------------------------------------
		$("#moveToOrder").on("click", function(e) {
			if( $("#cartTable>tbody tr td").length < 5) {
				alert("주문할 상품이 존재하지 않습니다.");
				return;
			}
			
			var frm = document.cartFrm;
			frm.action = "${hContext}/order/order_form.do";
			frm.method = "POST";
			frm.memberId = $("#memberId").val();
			frm.submit();
		});		
		
		//테이블 내 상품명 클릭 ----------------------------------------------
		$("#cartTable>tbody").on("click",".table_item_name", function(e){
			e.preventDefault();

			console.log("상품상세로 이동 : " + $(this).closest('tr').find('td:eq(1)').text() );
 			//post 형식으로 보내기
 			var frm = document.cartFrm;
 			frm.action = "${hContext}/item/item_deview.do";
 			frm.itemNo.value = $(this).closest('tr').find('td:eq(1)').text();
 			frm.method = "GET";
 			frm.submit();
		});
		
		// 목록 조회 -----------------------------------------------------
		function doRetrieve(page) {
			$.ajax({
				type : "POST",
				url : "${hContext}/cart/do_retrieve.do",
				asyn : "true",
				dataType : "html",
				data : { param : $("#memberId").val()},
				success : function(data) {//통신 성공
					//들어온 데이터 json처리
					var parseDate = JSON.parse(data);
				
					//기존 데이터 삭제
					$("#cartTable > tbody").empty();
					
					//동적으로 html
					var html = "";
					
					//가격 계산
					var orgSum = 0;
					var finalSum = 0;
					
	    			//이미지 변수 
	    			let imgroot = "";
	    			
					if (parseDate.length > 0) {
						$.each(parseDate,function(i, value) {
							imgFullPath = "${hContext}" + value.image_path + value.image_save_name;
							
							html += "<tr>";
							html += "   <td class='cartNo' style='display: none;'>"+ value.cartNo + "</td>";
							html += "   <td class='itemNo' style='display: none;'>"+ value.itemNo + "</td>";
							html += "	<td class='info_gs'>";
							html += "		<div class='in-block'>";
							html += "			<div class='img_wrap'>";
							html += "				<img src='" + imgFullPath + "' alt='이미지'>";
							html += "			</div>";
							html += "			<div class='info_goods_wrap'>";
 							html += "				<dl>";
							html += "					<dt>"; 
							html += "						<p class='table_item_name'>"+ value.item_name + "</p>";
 							html += "					</dt>";
							html += "				</dl>";
							html += "			</div>";
							html += "		</div>";
							html += "	</td>";
							html += "	<td class='cont_wrap'>";
							html += "		<ul class='in-block'>";
							html += "			<li>";
							html += "				<input type='button' class='minus' value='-' >";
							html += "			</li>";
							html += "			<li>";
							html += "				<input type='text' class='quantity' value='" + value.quantity + "' readonly>";
							html += "			</li>";
							html += "			<li>";
							html += "				<input type='button' class='plus' value='+'>";
							html += "			</li>";
							html += "		</ul>";
							html += "	</td>";
							html += "	<td class='cout_pay_wrap'>";
							//할인 부분 ----------------------------------------------------------------------------------------------
							if(value.item_price == value.item_final_price){
								html += "<input type='text' name='item_price' class='item_price' value='" + value.item_price + "원'>";
								html += "<input type='hidden' name='item_final_price' class='item_final_price' value='" + value.item_final_price + "원'>";
							} else {
								html += "<div class='in-block'>";
								html += "	<div class='discount_outer'>";
								html += "		<div class='discount'>";
								html += "			<span>" + value.item_price + "원</span>";
								html += "		</div>";
								html += "	</div>";
								html += "	<input type='text' name='item_final_price' class='item_final_price' value='" + value.item_final_price + "원' >";
								html += "</div>";
							}
							//할인 부분 ----------------------------------------------------------------------------------------------
							html += "	</td>";
							html += "	<td>";
							html += "		<button class='btn_del delete_cart'>X</button>";
							html += "	</td>";
							html += "</tr>";

							//상품 총 금액 
							orgSum += (parseInt(value.item_price) * parseInt(value.quantity));
							//최종 결제 금액
							finalSum += (parseInt(value.item_final_price) * parseInt(value.quantity));
					});
				
					} else { //data가 없는 경우
						html += "<tr>";
						html += "	<td colspan='99'>markit에서 다양한 레시피와 상품을 즐겨보세요!</td>";
						html += "</tr>";
					}
					//tbody에 추가
					$("#cartTable > tbody").append(html);
				
					//계산된 값 매핑
					$("#tota_origin_price").val(orgSum);
					$("#total_sale_price").val(orgSum - finalSum);
					$("#total_final_price").val(finalSum);
				},
				error : function(data) {//실패시 처리
					console.log("error:" + data);
				},
				complete : function(data) {//성공/실패와 관계없이 수행!
				}
			});
		}
	</script>
</body>
</html>