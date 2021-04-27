<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일         수정자      수정내용
    -----       -----  -------------------------------------------
    2021. 4. 21.             최초작성 
    
    author woogieReal
    since 2020.11.23
    Copyright (C) by woogieReal All right reserved.
*/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- core태그 -->
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 국제화 태그 -->
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../cmn/common.jsp" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.82.0">
    <title>레시피 조회</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/checkout/">

	<script src="${hContext}/resources/js/jquery.min.js"></script>
	<script src="${hContext}/resources/js/bootstrap.min.js"></script>

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

    </style>


    <!-- 사용자 정의 스타일 입력 스타일시트 -->
    <link href="${hContext}/resources/assets/recipe/woogie.css" rel="stylesheet">

  </head>

  <body>
  <br/><br/><br/>
	<div class="container">
	  <main>
	  
		<div class="py-5 text-center">
		  <h2>회원가입</h2>
		</div>
		
	    <div class="row g-5" style="border: 1px solid black;">	
		  

        <div class="bd-example">
        <form id="signUpFrm" name="signUpFrm">
          <div class="mb-3">
            <label for="memberId" class="form-label">Email address</label>
            <input type="email" class="form-control" id="memberId" aria-describedby="emailHelp">
          </div>
          <div class="mb-3">
            <label for="pw" class="form-label">Password</label>
            <input type="password" class="form-control" id="pw">
          </div>
          <div class="mb-3">
            <label for="pw_check" class="form-label">Password check</label>
            <input type="password" class="form-control" id="pw_check">
          </div>
          <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name">
          </div>
          <div class="mb-3">
            <label for="phone" class="form-label">Phone number</label>
            <input type="text" class="form-control" id="phone">
          </div>
          <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" id="address">
          </div>
        </form><br/><br/>
          <button type="submit" id="signUpBtn" class="btn btn-primary">회원등록</button>
        </div>
		  
	    </div>
	    
	  </main>	
	</div>
    <br/><br/><br/><br/>
	
	<script src="${hContext}/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${hContext}/resources/assets/recipe/form-validation.js"></script>  
	
	<script type="text/javascript">
		
		$("#signUpBtn").on("click", function(e){
			console.log("signUpBtn");
			
			console.log($("#memberId").val());
			console.log($("#pw").val());
			console.log($("#name").val());
			console.log($("#phone").val());
			console.log($("#address").val());
			
			/* $.ajax({
		  		type: "POST",
		  		url:"${hContext}/member/do_sign_up.do",
		  		asyn:"true",
		  		dataType:"html",
		  		data:{
		  			memberId: $("#memberId").val(),
		  			pw: $("#pw").val(),
		  			name: $("#name").val(),
		  			phone: $("#phone").val(),
		  			address: $("#address").val()
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
		  	}); */
			
		});

	
	</script>
  </body>

</html>