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
      #idCheckBtn {
      	width: 70px;
      	float: right;
      	font-size: small;
      	padding: 2px;
      }
      
      .form-signin h3 {
      	margin:-50px;
      }
      
    </style>


    <!-- 사용자 정의 스타일 입력 스타일시트 -->
    <link href="${hContext}/resources/assets/sign/signin.css" rel="stylesheet">

  </head>

  <body style="background-color: white;"><!--  class="text-center" -->
  
  <br/><br/><br/>
	  <main class="form-signin">
	    <a href="javascript:headerToMain();"><img class="mb-4" src="${hContext}/resources/image_source/markit_logo.png" alt="" width="300" ></a>
		<div class="py-5 text-center">
		  <h3>회원가입</h3>
		</div>

        <form id="signUpFrm" name="signUpFrm">
        <input type="hidden" name="idCheckFlag" id="idCheckFlag" value="">
	  	<input type="hidden" name="checkedId" id="checkedId" value="">
	  	
          <div class="mb-3">
            <label for="memberId" class="form-label">Email address</label><input type="button" class="btn btn-primary btn-sm" value="중복체크" id="idCheckBtn" />
            <input type="email" class="form-control" id="memberId" aria-describedby="emailHelp" placeholder="입력 후 중복체크를 해주세요">
          </div>
          <div class="mb-3">
            <label for="pw" class="form-label">Password</label>
            <input type="password" class="form-control" id="pw">
          </div>
          <div class="mb-3">
            <label for="pw_check" class="form-label">Password check</label>
            <input type="password" class="form-control" id="pw_check" placeholder="비밀번호를 한 번 더 입력해 주세요">
          </div>
          <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name">
          </div>
          <div class="mb-3">
            <label for="phone" class="form-label">Phone number</label>
            <input type="text" class="form-control" id="phone"  placeholder="'-'를 빼고 입력해주세요">
          </div>
          <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" id="address">
          </div>
        </form><br/>
          <button type="submit" id="signUpBtn" class="btn btn-primary">회원등록</button><br/><br/>
		  
	    
	  </main>	
    <br/><br/>
	
	<script src="${hContext}/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${hContext}/resources/assets/recipe/form-validation.js"></script>  
	
	<script type="text/javascript">
	
		function headerToMain() {
			window.location.href = "${hContext}/main/main_view.do";
		}
		
		$("#idCheckBtn").on("click", function(e){
	    	console.log("idCheckBtn");
	    	if(!verifyEmail()) { $("#memberId").val(""); return; }
	    	
	    	$.ajax({
		  		type: "POST",
		  		url:"${hContext}/member/do_check_duplicated_id.do",
		  		asyn:"true",
		  		dataType:"html",
		  		data:{
		  			memberId: $("#memberId").val()
		  		},
		  		success:function(data){//통신 성공
		  			
		  			var parseData = JSON.parse(data);
		  			
		  			if("1"==parseData.msgId){
		    			alert(parseData.msgContents);
		    			$("#memberId").val("");
		    			document.getElementById("memberId").focus();
		    			$("#idCheckFlag").val("");
		    			$("#checkedId").val("");
		    		}else{
		    			alert(parseData.msgContents);
		    			document.getElementById("pw").focus();
		    			$("#idCheckFlag").val("checked");
		    			$("#checkedId").val($("#memberId").val());
		    			
		    		}
		  			
		  		},
		  		error:function(data){//실패시 처리
		  			console.log("error:"+data);
		  		}
		  	});
	    	
	    });
		
		function verifyEmail() { 
			var emailVal = $("#memberId").val(); 
			var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; 
			if (emailVal.match(regExp) != null) { console.log('Good!'); return true; } 
			else { alert('올바른 이메일을 입력해 주십시오.'); return false; } 
		}

		function checkPassword(){

			var id = $("#memberId").val();
			var password = $("#pw").val();
			
	    	if(!/^[a-zA-Z0-9]{7,15}$/.test(password)) { alert('비밀번호는 숫자와 영문자 조합으로 7~15자리를 사용해야 합니다.'); return false; }

	    	var checkNumber = password.search(/[0-9]/g);
	    	var checkEnglish = password.search(/[a-z]/ig);

	    	if(checkNumber <0 || checkEnglish <0) { alert("비밀번호는 숫자와 영문자를 혼용하여야 합니다."); return false; }
	    	if(/(\w)\1\1\1/.test(password)) { alert('비밀번호에 444같은 문자를 4번 이상 사용하실 수 없습니다.'); return false; }
	    	if(password.search(id) > -1){ alert("비밀번호에 아이디가 포함되었습니다."); return false; }

	    	return true;

	    }
		
	
		$("#signUpBtn").on("click", function(e){
			//console.log("signUpBtn");
			
			var memberId = $("#memberId").val();
			var pw = $("#pw").val();
			var pw_check = $("#pw_check").val();
			var name = $("#name").val();
			var phone = $("#phone").val();
			var address = $("#address").val();
			
			var idCheckFlag = $("#idCheckFlag").val();
			var checkedId = $("#checkedId").val();
			
			if(null == memberId || memberId.trim().length == 0) { document.getElementById("memberId").focus(); alert("E-MAIL을 입력 하세요."); return; }
			if(null == pw || pw.trim().length == 0) { document.getElementById("pw").focus(); alert("비밀번호를 입력 하세요."); return; }
			if(null == pw_check || pw_check.trim().length == 0) { document.getElementById("pw_check").focus(); alert("비밀번호 확인을 입력 하세요."); return; }
			if(null == name || name.trim().length == 0) { document.getElementById("name").focus(); alert("이름을 입력 하세요."); return; }
			if(null == phone || phone.trim().length == 0) { document.getElementById("phone").focus(); alert("전화번호를 입력 하세요."); return; }
			if(null == address || address.trim().length == 0) { document.getElementById("address").focus(); alert("주소를 입력 하세요."); return; }
			
			if(idCheckFlag != "checked") { alert("id 중복체크를 하세요."); return; }
			if(checkedId != memberId) { alert("id 중복체크를 하세요."); return; }
			if(!checkPassword()) {return;}
			
			$.ajax({
		  		type: "POST",
		  		url:"${hContext}/member/do_register.do",
		  		asyn:"true",
		  		dataType:"html",
		  		data:{
		  			memberId: memberId,
		  			pw: pw,
		  			name: name,
		  			phone: phone,
		  			address: address
		  		},
		  		success:function(data){//통신 성공
		  			
		  			var parseData = JSON.parse(data);
		  			alert(parseData.msgContents);
		  			window.location.href = "${hContext}/member/sign_in_view.do";
		  			
		  		},
		  		error:function(data){//실패시 처리
		  			console.log("error:"+data);
		  		}
		  	});
			
		});

	
	</script>
  </body>

</html>