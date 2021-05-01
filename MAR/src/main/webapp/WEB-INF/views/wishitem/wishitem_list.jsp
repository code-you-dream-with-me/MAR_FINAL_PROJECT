<%@page import="com.sist.mar.member.domain.MemberVO"%>
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
<title>WISHITEM_LIST</title>
</head>	
<body>	
	<!-- center -->
	<div class="container02">
		<form action="" id="wishFrm" name="wishFrm">
			<input type="hidden" id="member_id" name="member_id" value="<%=vo.getMemberId()%>" />
			<input type="hidden" id="param" name="param" />
			<input type="hidden" id="itemNo" name="itemNo" />
		</form>

		<div class="mid_section">
			<div class="comn_ttl_wrap align_ttl_wrap">
				<dl>
					<dt>
						<h1>늘 사는 것</h1>
					</dt>
					<dd>늘 사는 것으로 등록하신 상품 목록입니다</dd>
				</dl>
			</div>
			<div class="comn_tb_wrap">
				<table id="wishTable">
					<thead>
						<tr>
							<th class="info_gs">
								<p>상품정보</p>
							</th>
							<th class="selc_wrap">
								<p>선택</p>
							</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
		<div class="text-center">
			<div id="page-selection" class="page"></div>
		</div>
	</div>
	<!-- center -->

	
	<script type="text/javascript"> //상품이름 클릭시 이동 이벤트 추가하기
		//document ------------------------------------------------------------
		$(document).ready(function() {
			doRetrieve(1);
		});

		// 페이징 --------------------------------------------------------------
		//pageTotal(총페이지수), page(현재페이지), maxVisible(이동할페이지개수)
		function renderingPage(pageTotal, page) {
			//이전에 연결된 event 핸들러를 요소에서 제거
			$("#page-selection").unbind('page');
			
			$('#page-selection').bootpag({
			    total: pageTotal,      //총글수
			    page: page,            //시작
			    maxVisible: 10,        //바닥에 보일 페이지수
			    leaps: true,
			    firstLastUse: true,
			    first: '←',
			    last: '→',
			    wrapClass: 'pagination',
			    activeClass: 'active',
			    disabledClass: 'disabled',
			    nextClass: 'next',
			    prevClass: 'prev',
			    lastClass: 'last',
			    firstClass: 'first'
			}).on("page", function(event, num){
				doRetrieve(num); //서버호출
			}); 
		}
		
		//테이블 내 삭제버튼 클릭 ----------------------------------------------------
		$("#wishTable>tbody").on("click",".delete_wish", function(e){
			e.preventDefault();
			//경고메세지
			if(confirm("삭제하시겠습니까?") == false) return;
			//post 형식으로 보내기
			let url       = "${hContext}/wishitem/do_delete.do";
			let paramters = {"param":$(this).closest('tr').find('td:eq(0)').text()};
			let method    = "POST";
			let async     = "true";
			EClass.callAjax(url, paramters, method, async, function(data) {
				alert(data.msgContents);
				doRetrieve(1);
			});	
		});
		
		//테이블 내 장바구니담기버튼  클릭 ----------------------------------------------
		$("#wishTable>tbody").on("click",".selectOne_wish", function(e){
			e.preventDefault();
			//필요한 param값(param값 넘길 form, 이동할 url, 임의의 이름, 팝업창 옵션)
			var frm = document.wishFrm;
			var url = "${hContext}/wishitem/wishitem_detail.do";
			var name = 'wish_detail_popup';
			var option = "width = 500, height = 500, top = 150, left = 700," //가로크기, 세로크기, 창의 위치값*2
					   + "location = no, resizable = no, menubar = no, " //주소창비활성화, 창사이즈변경불가, 메뉴바없음
					   + "titlebar = no, toolbar  = no, scrollbars  = no"; //타이틀바, 툴바, 스크롤바 전부 x
			//팝업창 열기
			window.open(url, name,option);
			//post 형식으로 보내기
 			frm.target = name;
			frm.action = url;
			frm.method = "POST";
			frm.param.value = $(this).closest('tr').find('td:eq(0)').text();
			console.log("frm.param.value:"+frm.param.value);
			frm.submit();  
		});
		
		//테이블 내 상품명 클릭 ----------------------------------------------
		$("#wishTable>tbody").on("click",".table_item_name", function(e){
			e.preventDefault();
 			//post 형식으로 보내기 //변경해야함
 			var frm = document.wishFrm;
 			frm.action = "${hContext}/item/item_deview.do";
 			frm.itemNo.value = $(this).closest('tr').find('td:eq(1)').text();
 			frm.method = "GET";
 			frm.submit();
		});
		
		//목록 조회(동적) -----------------------------------------------------
		function doRetrieve(page) {
	      	$.ajax({
	    		type: "POST",
	    		url:"${hContext}/wishitem/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			searchWord: $("#member_id").val(),
	    			pageSize: 10,
	    			pageNum: page	
	    		},
	    		success:function(data){
	    			var parseDate = JSON.parse(data);
	    			//기존 데이터 삭제
	    			$("#wishTable > tbody").empty();
	    			
	    			//페이징 변수 선언
	    			let totalCount = 0;
	    			let pageTotal = 1;
	    			
	    			//이미지 변수 
	    			let imgroot = "";
	    			
	    			//동적으로 html
	    			var html = "";
	    			
 	    			if(parseDate.length > 0) { //data가 있는 경우
 	    				//페이징변수
		    			totalCount = parseDate[0].totalCnt;
		    			pageTotal = totalCount/10;
		    			pageTotal = Math.ceil(pageTotal);

	    				$.each(parseDate, function(i, value) {
	    					imgFullPath = "${hContext}" + value.image_path + value.image_save_name;
	    					
	    					html += "<tr>";
	    					html += "   <td style='display: none;'>" + value.wishNo + "</td>";
	    					html += "   <td style='display: none;'>" + value.itemNo + "</td>";
	    					html += "	<td class='info_gs'> ";
	    					html += "		<div class='in-block'>";
	    					html += "			<div class='img_wrap'>";
	    					html += "				<img src='" + imgFullPath + "'  alt='이미지'>";
	    					html += "			</div>";
	    					html += "			<div class='info_goods_wrap'>";
	    					html += "				<dl>";
	    					html += "					<dt>";
	    					html += "						<p class='table_item_name'>" + value.item_name + "</p>";
	    					html += "					</dt>";
	    					html += "					<dd>" + value.item_price + "원</dd>";
	    					html += "				</dl>";
	    					html += "			</div>";
	    					html += "		</div>";
	    					html += "	</td>";
	    					html += "	<td class='selc_wrap'>";
	    					html += "		<ul>";
	    					html += "			<li>";
	    					html += "				<button class='bg_color selectOne_wish'>장바구니 담기</button>";
	    					html += "			</li>";
	    					html += "			<li>";
	    					html += "				<button class='border_color delete_wish'>삭제</button>";
	    					html += "			</li>";
	    					html += "		</ul>";
	    					html += "	</td>";
	    					html += "</tr>";
	    				});
	    			} else { //data가 없는 경우
	    				html += "<tr>";
	    				html += "	<td colspan='99'>자주사는상품은 늘사는것에 담아 간편하게!</td>";
	    				html += "</tr>"; 
	    			} 
	    			//tbody에 추가
	    			$("#wishTable > tbody").append(html);
	    			//페이징처리
	    			console.log(pageTotal + " || " + page);
	    			renderingPage(pageTotal,page);
	        	},
	        	error:function(data){
	        		//실패시 처리
	        	},
	        	complete:function(data){
	        		//성공/실패와 관계없이 수행!
	        	}
	    	});  	
		}
	</script>
</body>
</html>