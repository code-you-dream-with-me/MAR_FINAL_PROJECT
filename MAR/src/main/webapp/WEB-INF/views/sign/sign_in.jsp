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
    <title>로그인</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">

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

    
    <!-- Custom styles for this template -->
    <link href="${hContext}/resources/assets/sign/signin.css" rel="stylesheet">
  </head>
  <body class="text-center" style="background-color: white;">
    
<main class="form-signin">
  <form>
    <a href="javascript:headerToMain();"><img class="mb-4" src="${hContext}/resources/image_source/markit_logo.png" alt="" width="300" ></a>
    

    <div class="form-floating">
      <input type="email" class="form-control" id="memberId" placeholder="name@example.com">
      <label for="memberId">Email address</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="pw" placeholder="Password">
      <label for="pw">Password</label>
    </div>

  </form>
  
    <div>
      <button id="signInBtn" class="w-100 btn btn-lg btn-primary" type="submit" style="margin-bottom: 15px;">로그인</button>
      <button id="signUpBtn" class="w-100 btn btn-lg btn-primary" type="submit">회원가입</button>
    </div>
    <p class="mt-5 mb-3 text-muted">&copy; markit</p>

</main>

	<script src="${hContext}/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${hContext}/resources/assets/recipe/form-validation.js"></script>


	<script type="text/javascript">
	
		function headerToMain() {
			window.location.href = "${hContext}/main/main_view.do";
		}
		
		$("#signUpBtn").on("click", function(){
			console.log("signUpBtn");
			window.location.href = "${hContext}/member/sign_up_view.do";
		});
		
		$("#signInBtn").on("click", function(){
			console.log("signInBtn");
			
			$.ajax({
		  		type: "POST",
		  		url:"${hContext}/member/do_login_check.do",
		  		asyn:"true",
		  		dataType:"html",
		  		data:{
		  			memberId: $("#memberId").val(),
		  			pw: $("#pw").val()
		  		},
		  		success:function(data){//통신 성공
		  			
		  			var parseData = JSON.parse(data);
		  			if(parseData.msgId == 1){
			  			window.location.href = "${hContext}/main/main_view.do ";
		  			} else {
			  			alert(parseData.msgContents);
		  			}
		  		
		  			//window.location.href = "${hContext}/admin/admin_view.do ";
		  			
		  		},
		  		error:function(data){//실패시 처리
		  			console.log("error:"+data);
		  		}
		  	});
		});
	
	</script>
  </body>
</html>
