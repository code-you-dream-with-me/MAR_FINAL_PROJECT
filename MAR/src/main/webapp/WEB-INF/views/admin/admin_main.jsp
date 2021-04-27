<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- core태그 -->
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 국제화 태그 -->
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.82.0">
    <title>관리자 페이지</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/checkout/">

	<script src="${hContext}/resources/js/jquery.min.js"></script>
	<script src="${hContext}/resources/js/bootstrap.min.js"></script>
	<script src="${hContext}/resources/js/eclass.js"></script>

    <!-- Bootstrap core CSS -->
	<link href="${hContext}/resources/assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      tbody > tr:nth-child(odd) {background-color: #e1f5fe;}
      .orderBtn {
      	background-color: white;
      	width: 22px;
      	font-size: small;
      	padding: 0px 0px 2px 0px;
      	margin: 0px;
      	text-align: center;
      	border-radius: 5px;
      }
      .wordBtn {
      	background-color: white;
      	width: 40px;
      	text-align: center;
      	margin: 0px 1px 0px 1px;
      	padding: 0px;
      	border-radius: 5px;
      	font-size: small;
      }
      .menuBtn {
      	background-color: white;
      	width: 70px;
      	height: 30px;
      	text-align: center;
      	margin: 0px 0px 0px 0px;
      	padding: 0px;
      	border-radius: 5px;
      	font-size: medium;
      	float: right;
      	font-weight: bolder;
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="${hContext}/resources/assets/admin/cheatsheet.css" rel="stylesheet">

  </head>
  <body class="bg-light">
  
  	<!-- header -->
	<header class="bd-header bg-dark py-3 d-flex align-items-stretch border-bottom border-dark">
	  <div class="container-fluid d-flex align-items-center">
	    <h1 class="d-flex align-items-center fs-4 text-white mb-0">
	      <img src="${hContext}/resources/assets/brand/bootstrap-logo-white.svg" width="38" height="30" class="me-3" alt="Bootstrap">
	      Cheatsheet
	    </h1>
	    <a href="../examples/cheatsheet-rtl/" class="ms-auto link-light" hreflang="ar">RTL cheatsheet</a>
	  </div>
	</header>
	<!--// header -->
	
	<!-- 오른쪽 네비게이션 -->
	<aside class="bd-aside sticky-xl-top text-muted align-self-start mb-3 mb-xl-5 px-2">
	  <h2 class="h6 pt-4 pb-3 mb-4 border-bottom">On this page</h2>
	  <nav class="small" id="toc">
	    <ul class="list-unstyled">
	    
	      <li class="my-2">
	        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#member-collapse" aria-controls="contents-collapse">회원</button>
	        <ul class="list-unstyled ps-3 collapse" id="member-collapse">
	          <li><a class="d-inline-flex align-items-center rounded" href="#member_list">회원 목록</a></li>
	        </ul>
	      </li>
	      <li class="my-2">
	        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#product-collapse" aria-controls="forms-collapse">상품</button>
	        <ul class="list-unstyled ps-3 collapse" id="product-collapse">
	          <li><a class="d-inline-flex align-items-center rounded" href="#product_list">상품 목록</a></li>
	        </ul>
	      </li>
	      <li class="my-2">
	        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#order-collapse" aria-controls="forms-collapse">주문</button>
	        <ul class="list-unstyled ps-3 collapse" id="order-collapse">
	          <li><a class="d-inline-flex align-items-center rounded" href="#order_list">주문 목록</a></li>
	        </ul>
	      </li>
	      <li class="my-2">
	        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#recipe-collapse" aria-controls="components-collapse">레시피</button>
	        <ul class="list-unstyled ps-3 collapse" id="recipe-collapse">
	          <li><a class="d-inline-flex align-items-center rounded" href="#recipe_list">레시피 목록</a></li>
	        </ul>
	      </li>
	      
	    </ul>
	  </nav>
	</aside>
	<!--// 오른쪽 네비게이션 -->
	
	<div class="bd-cheatsheet container-fluid bg-body">
	  <section id="content">
	    <h2 class="sticky-xl-top fw-bold pt-3 pt-xl-5 pb-2 pb-xl-3">관리자</h2>
	    
	    <!-- 회원 목록 -->
	    <article class="my-3" id="member_list">
	    
	      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
	        <h3>회원 목록</h3>
	      </div>
	      
	      <div class="bd-example">
		    <table class="table table-hover">
	          <thead>
	          	<tr>
	              <th scope="col" width="25%">이메일</th>
	              <th scope="col" width="15%">이름</th>
	              <th scope="col" width="45%">주소</th>
	              <th scope="col" width="15%">전화번호</th>
	          	</tr>
	          </thead>
	          <tbody>
	          	<tr>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	          	</tr>
	          
	          
	          </tbody>
	        </table>
	      </div>
	      
	    </article>
	    
	    
	    <!-- 상품 목록 -->
	    <article class="my-3" id="product_list">
	    
	      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
	        <h3>상품 목록</h3>
	      </div>
	      
		  	
		  	
	      <div class="bd-example">
	        <table class="table table-hover" name="itemTable" id="itemTable">
	          <thead>
	          	<tr>
	              <th scope="col" width="10%">상품번호</th>
	              <th scope="col" width="25%">상품명</th>
	              <th scope="col" width="10%">종류</th>
	              <th scope="col" width="10%">가격
	                <input type="button" class="orderBtn" name="ItemPrice" id="ItemPrice" value="○" />
	              </th>
	              <th scope="col" width="8%">할인률</th>
	              <th scope="col" width="5%"> </th>
	              <th scope="col" width="10%">최종가격</th>
	              <th scope="col" width="12%">판매량
	                <input type="button" class="orderBtn" name="ItemSales" id="ItemSales" value="○" />
	              </th>
	              <th scope="col" width="10%">등록일
	                <input type="button" class="orderBtn" name="ItemRedDtBtn" id="ItemRedDtBtn" value="▼" />
	              </th>
	          	</tr>
	          </thead>
	          <tbody>
	          </tbody>
	        </table>
	      </div>
	    </article>
	    
	    <!-- 주문 목록 -->
	    <article class="my-3" id="order_list">
	    
	      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
	        <h3>주문 목록</h3>
	      </div>
		  	
	      <div class="bd-example">
	        <table class="table table-hover" name="orderingTable" id="orderingTable">
	          <thead>
	          	<tr>
	              <th scope="col" width="10%">주문번호</th>
	              <th scope="col" width="15%">주문자</th>
	              <th scope="col" width="33%">배송지</th>
	              <th scope="col" width="10%">금액</th>
	              <th scope="col" width="10%">상태</th>
	              <th scope="col" width="12%">
	              	<input type="button" class="wordBtn" name="orderStateBtn" id="orderStateBtn" value="전체" style="width: 60px;" />
	              </th>
	              <th scope="col" width="10%">결제일</th>
	          	</tr>
	          </thead>
	          <tbody>
	          </tbody>
	        </table>
	      </div>
	    </article>
	    
	    
	    <!-- 레시피 목록 -->
	    <article class="my-3" id="recipe_list">
	    
	      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
	        <h3>레시피 목록</h3>
	      </div>
		  
	      <div class="bd-example">
	      	<input type="button" class="menuBtn" name="addRecipeBtn" id="addRecipeBtn" value="추가" />
	        <table class="table table-hover" name="recipeTable" id="recipeTable">
	          <thead>
	          	<tr>
	              <th scope="col" width="8%">번호</th>
	              <th scope="col" width="40%">레시피 제목</th>
	              <th scope="col" width="28%">작성자</th>
	              <th scope="col" width="12%">
	              	조회수
	              	<input type="button" class="orderBtn" name="readCntBtn" id="readCntBtn" value="○" />
	              </th>
	              <th scope="col" width="12%">
	              	등록일
	              	<input type="button" class="orderBtn" name="redDtBtn" id="redDtBtn" value="▼" />
	              </th>
	          	</tr>
	          </thead>
	          <tbody>
	          </tbody>
	        </table>

	      </div>
	    </article>
	    
	  </section>  
	</div>    
    
	
	<script src="${hContext}/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
  	<script src="${hContext}/resources/assets/admin/cheatsheet.js"></script>
  </body>

  <script type="text/javascript">
  		
  $(document).ready(function() {
      console.log("1.document:최초수행!");
      doRetrieveReicpe();
      doRetrieveItem();
      doRetrieveOrdering();
    });
  
  $("#addRecipeBtn").on("click", function(e){
	  console.log("addRecipeBtn");
	  window.location.href = "${hContext}/recipe/recipe_view.do";
  });
  
  
  /* Recipe */
  $("#readCntBtn").on("click", function(e){
	$("#redDtBtn").val("○");
	switch($("#readCntBtn").val()){
		case "▼": $("#readCntBtn").val("▲"); doRetrieveReicpe("readCnt", "asc"); break;
		case "▲": $("#readCntBtn").val("▼"); doRetrieveReicpe("readCnt", "desc"); break;
		case "○": $("#readCntBtn").val("▼"); doRetrieveReicpe("readCnt", "desc"); break;
	}
  });
  
  /* Recipe */
  $("#redDtBtn").on("click", function(e){
	$("#readCntBtn").val("○");
	switch($("#redDtBtn").val()){
		case "▼": $("#redDtBtn").val("▲"); doRetrieveReicpe("regDt", "asc"); break;
		case "▲": $("#redDtBtn").val("▼"); doRetrieveReicpe("regDt", "desc"); break;
		case "○": $("#redDtBtn").val("▼"); doRetrieveReicpe("regDt", "desc"); break;
	}
  });
  
  
  function doRetrieveReicpe(orderDiv, orderWord) {
  	
  	$.ajax({
  		type: "GET",
  		url:"${hContext}/admin/do_retrieve_recipe.do",
  		asyn:"true",
  		dataType:"html",
  		data:{
  			searchDiv: orderDiv,
  			searchWord: orderWord
  		},
  		success:function(data){//통신 성공
  			var parseData = JSON.parse(data);
  			$("#recipeTable > tbody").empty();
  			var html = "";
  			
  			if(parseData.length > 0){ 
  				
  				$.each(parseData, function(i, value) {
  					html += "   <tr>                              ";
  					html += "     <td>"+ value.recipeNo +"</td>   ";
  					html += "     <td>"+ value.title +"</td>      ";
  					html += "     <td>"+ value.regId +"</td>      ";
  					html += "     <td>"+ value.readCnt +"</td>    ";
  					html += "     <td>"+ value.regDt +"</td>      ";
  					html += "   </tr>                             ";
  				});
  				
  			}else { 
  				html += " <tr> ";
  				html += "   <td class='text-center' colspan='99'>등록된 게시글이 없습니다.</td> ";
  				html += " </tr> ";
  			}
  			$("#recipeTable > tbody").append(html);
  		},
  		error:function(data){//실패시 처리
  			console.log("error:"+data);
  		}
  	});     	
  	
  }
  
  //table click
  $("#recipeTable > tbody").on("click","tr",function(e){
    	e.preventDefault();
    	let tds = $(this).children();
    	var recipeNo = tds.eq(0).text();
    	console.log("recipeNo:"+recipeNo);
        window.location.href = "${hContext}/recipe/recipe_view2.do?recipeNo="+recipeNo;
  });

  /* Item */
  $("#ItemPrice").on("click", function(e){
		$("#ItemRedDtBtn").val("○");
		$("#ItemSales").val("○");
		switch($("#ItemPrice").val()){
			case "▼": $("#ItemPrice").val("▲"); doRetrieveItem("price", "asc"); break;
			case "▲": $("#ItemPrice").val("▼"); doRetrieveItem("price", "desc"); break;
			case "○": $("#ItemPrice").val("▼"); doRetrieveItem("price", "desc"); break;
		}
  });  
  
  /* Item */
  $("#ItemSales").on("click", function(e){
		$("#ItemRedDtBtn").val("○");
		$("#ItemPrice").val("○");
		switch($("#ItemSales").val()){
			case "▼": $("#ItemSales").val("▲"); doRetrieveItem("sales", "asc"); break;
			case "▲": $("#ItemSales").val("▼"); doRetrieveItem("sales", "desc"); break;
			case "○": $("#ItemSales").val("▼"); doRetrieveItem("sales", "desc"); break;
		}		
  });

  /* Item */
  $("#ItemRedDtBtn").on("click", function(e){
		$("#ItemSales").val("○");
		$("#ItemPrice").val("○");
		switch($("#ItemRedDtBtn").val()){
			case "▼": $("#ItemRedDtBtn").val("▲"); doRetrieveItem("regDt", "asc"); break;
			case "▲": $("#ItemRedDtBtn").val("▼"); doRetrieveItem("regDt", "desc"); break;
			case "○": $("#ItemRedDtBtn").val("▼"); doRetrieveItem("regDt", "desc"); break;
		}		
  });
  
  function doRetrieveItem(orderDiv, orderWord) {
 
	  	$.ajax({
	  		type: "GET",
	  		url:"${hContext}/admin/do_retrieve_item.do",
	  		asyn:"true",
	  		dataType:"html",
	  		data:{
	  			searchDiv: orderDiv,
	  			searchWord: orderWord
	  		},
	  		success:function(data){//통신 성공
	  			var parseData = JSON.parse(data);
	  			$("#itemTable > tbody").empty();
	  			var html = "";
	  			
	  			if(parseData.length > 0){ 
	  				
	  				$.each(parseData, function(i, value) {
	  					html += "   <tr>                              ";
	  					html += "     <td>"+ value.itemNo +"</td>   ";
	  					html += "     <td>"+ value.name +"</td>      ";
	  					html += "     <td>"+ value.categoryNo +"</td>      ";
	  					html += "     <td>"+ value.price +"</td>    ";
	  					html += "     <td><input type='text' id='discountNum"+value.itemNo+"' value='"+ value.discount +"' style='width: 40px;'/></td>    ";
	  					html += "     <td><input type='button' class='wordBtn' value='수정' onclick='discount("+value.itemNo+");' /></td>    ";
	  					html += "     <td>"+ value.price*(100-value.discount)/100 +"</td>    ";
	  					html += "     <td>"+ value.sales +"</td>    ";
	  					html += "     <td>"+ value.regDt +"</td>      ";
	  					html += "   </tr>                             ";
	  				});
	  				
	  			}else { 
	  				html += " <tr> ";
	  				html += "   <td class='text-center' colspan='99'>등록된 게시글이 없습니다.</td> ";
	  				html += " </tr> ";
	  			}
	  			$("#itemTable > tbody").append(html);
	  		},
	  		error:function(data){//실패시 처리
	  			console.log("error:"+data);
	  		}
	  	});     	
	  	
	  }
  
  function discount(itemNo) {
	  
	  var discountNum = $("#discountNum"+itemNo).val();
	  console.log(discountNum);
	  console.log(isNaN(discountNum));
	  
	  //isNaN(param) -> true: "문자열", undifined, NaN / false: 숫자, boolean, null, ""
	  if(isNaN(discountNum)) {
		  alert("숫자를 입력하십시오"); 
		  $("#discountNum"+itemNo).val("");
		  $("#discountNum"+itemNo).focus();
		  return;
	  }
	  
	  $.ajax({
	  		type: "GET",
	  		url:"${hContext}/admin/do_dicount_item.do",
	  		asyn:"true",
	  		dataType:"html",
	  		data:{
	  			itemNo: itemNo,
	  			discount: discountNum
	  		},
	  		success:function(data){//통신 성공
	  			
	  			var parseData = JSON.parse(data);
	  			console.log(parseData.msgContents);
	  			
	  			if      ($("#ItemRedDtBtn").val()  == "▼") doRetrieveItem("regDt", "desc");
	  			else if ($("#ItemRedDtBtn").val()  == "▲") doRetrieveItem("regDt", "asc");
	  			else if ($("#ItemPrice").val() == "▼") doRetrieveItem("price", "desc");
	  			else if ($("#ItemPrice").val() == "▲") doRetrieveItem("price", "asc");
	  			else if ($("#ItemSales").val() == "▼") doRetrieveItem("sales", "desc");
	  			else if ($("#ItemSales").val() == "▲") doRetrieveItem("sales", "asc");
	  			
	  		},
	  		error:function(data){//실패시 처리
	  			console.log("error:"+data);
	  		}
	  	});
  }

  /* ordering */
  $("#orderStateBtn").on("click", function(e){
		switch($("#orderStateBtn").val()){
			case "전체": $("#orderStateBtn").val("주문확정"); doRetrieveOrdering("orderState", "1"); break;
			case "주문확정": $("#orderStateBtn").val("취소요청"); doRetrieveOrdering("orderState", "2"); break;
			case "취소요청": $("#orderStateBtn").val("취소확정"); doRetrieveOrdering("orderState", "3"); break;
			case "취소확정": $("#orderStateBtn").val("전체"); doRetrieveOrdering("nothing", "0"); break;
		}	
  });  
  
  function doRetrieveOrdering(orderDiv, orderWord) {
	  	
	  	$.ajax({
	  		type: "GET",
	  		url:"${hContext}/admin/do_retrieve_ordering.do",
	  		asyn:"true",
	  		dataType:"html",
	  		data:{
	  			searchDiv: orderDiv,
	  			searchWord: orderWord
	  		},
	  		success:function(data){//통신 성공
	  			var parseData = JSON.parse(data);
	  			$("#orderingTable > tbody").empty();
	  			var html = "";
	  			
	  			if(parseData.length > 0){ 
	  				
	  				$.each(parseData, function(i, value) {
	  					var orderStateStr = "";
	  					switch(value.orderState) {
	  						case "1" : orderStateStr = "주문확정"; html += "   <tr>"; break;
	  						case "2" : orderStateStr = "취소요청"; html += "   <tr class='table-danger'>"; break;
	  						case "3" : orderStateStr = "취소확정"; html += "   <tr class='table-success'>"; break;
	  					}
	  					
	  					html += "     <td>"+ value.orderNo +"</td>   ";
	  					html += "     <td>"+ value.memberId +"</td>      ";
	  					html += "     <td>"+ value.address +"</td>      ";
	  					html += "     <td>"+ value.price +"</td>    ";
  						html += "     <td>"+ orderStateStr +"</td>    "; 
	  					
	  					switch(value.orderState) {
  						case "2" : html += "<td><input type='button' class='wordBtn' value='승인' onclick='approve("+value.orderNo +","+ orderWord+");'  /><input type='button' class='wordBtn' value='거부' onclick='reject("+value.orderNo +","+ orderWord+");'  /></td>"; break;
  						case "3" : html += "<td><input type='button' class='wordBtn' value='승인취소' onclick='returnn("+value.orderNo +","+ orderWord+");' style='width: 82px;' /></td>"; break;
  						default  : html += "<td>"+ "" +"</td>    ";  break;
	  					}
	  					
	  					html += "     <td>"+ value.orderDate +"</td>      ";
	  					html += "   </tr>                             ";
	  				});
	  				
	  			}else { 
	  				html += " <tr> ";
	  				html += "   <td class='text-center' colspan='99'>등록된 주문이 없습니다.</td> ";
	  				html += " </tr> ";
	  			}
	  			$("#orderingTable > tbody").append(html);
	  		},
	  		error:function(data){//실패시 처리
	  			console.log("error:"+data);
	  		}
	  	});     	
	  	
  }  
  
  function reject(orderNo, orderWord) {
	  controlOrderState(orderNo, "reject", orderWord);
  }
  
  function returnn(orderNo, orderWord) {
	  controlOrderState(orderNo, "return", orderWord);
  }
  
  function approve(orderNo, orderWord) {
	  controlOrderState(orderNo, "approve", orderWord);
  }
  
  function controlOrderState(orderNo, methodName, orderWord) {
	  var orderDiv = "";
	  if(orderWord == undefined || orderWord == "0") orderDiv = "nothing";
	  else orderDiv = "orderState";
	  
	  $.ajax({
	  		type: "GET",
	  		url:"${hContext}/admin/do_"+methodName+"_cancel.do",
	  		asyn:"true",
	  		dataType:"html",
	  		data:{
	  			orderNo: orderNo
	  		},
	  		success:function(data){//통신 성공
	  			var parseData = JSON.parse(data);
	  			console.log(orderNo+"번 주문에 대한 "+parseData.msgContents);
	  			doRetrieveOrdering(orderDiv, orderWord);
	  		},
	  		error:function(data){//실패시 처리
	  			console.log("error:"+data);
	  		},
	  		complete:function(data){//성공/실패와 관계없이 수행!
	  			//console.log("complete:"+data);
	  		}
	  	});
  }
  
  
  </script>

</html>
