<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->

<!-- 부트스트랩 -->
<link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="${hContext}/resources/css/bootstrap-theme.min.css">

<!-- layout css -->
<link rel="stylesheet" href="${hContext}/resources/css/layout.css">

<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
<!-- icon -->
<link href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined" rel="stylesheet">

<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${hContext}/resources/js/jquery.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${hContext}/resources/js/bootstrap.min.js"></script>
<!-- js간략화 -->
<script src="${hContext}/resources/js/eclass.js"></script><!-- ajax js -->
<script src="${hContext}/resources/js/eutil.js"></script><!-- 빈칸인식js -->
<title>MY_PAGE</title>
</head>
<body>
	<!-- center -->
	<div class="container02">
		<h2 class="control-label">회원 정보 수정<span style="font-size:16px;">&nbsp &nbsp (*)은 필수 입력입니다.</span></h2>
		<form class="form-horizontal">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="memberId" value="${vo.memberId}" readonly>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">이름</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="name" value="${vo.name}" readonly>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">현재 비밀번호(*)</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="currPw">
		      <input type="hidden" class="form-control" id="pw" value="${vo.pw}"> 
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">새 비밀번호</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="newPw" >
		    </div>
		  </div>
	
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">새 비밀번호 확인</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="newPwCheck">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">전화번호(*)</label>
		    <div class="col-sm-10">
		      <input type="tel" class="form-control" id="phone" value="${vo.phone}" maxlength="11">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">주소(*)</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="address" value="${vo.address}" maxlength="100">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" id="doUpdate" class="btn btn-default">수정하기</button>
		    </div>
		  </div>
		</form>
	</div>
	<!-- center -->
	
	<script type="text/javascript">
	//document ------------------------------------------------------------
	$(document).ready(function() {
		console.log("document");

	});
	
	//수정하기 버튼 ------------------------------------------------------------
	$("#doUpdate").on("click", function(e){
		e.preventDefault();
		console.log("doUpdate");
		
		//비밀번호 확인 
		if($("#pw").val() != $("#currPw").val()) { alert("비밀번호가 일치하지 않습니다."); return; }

		//비밀번호 변경 시 확인
		if("" != $("#newPw").val()) {
			if($("#newPw").val() != $("#newPwCheck").val()) { alert("새 비밀번호와 새 비밀번호 체크가 일치하지 않습니다."); return; }
			//비밀번호 형태 확인
	    	var checkNumber = $("#newPw").val().search(/[0-9]/g);
	    	var checkEnglish = $("#newPw").val().search(/[a-z]/ig);
			if(!/^[a-zA-Z0-9]{7,15}$/.test($("#newPw").val())) { alert('비밀번호는 숫자와 영문자 조합으로 7~15자리를 사용해야 합니다.'); return;}
	    	if(checkNumber <0 || checkEnglish <0) { alert("비밀번호는 숫자와 영문자를 혼용하여야 합니다."); return; }
	    	if(/(\w)\1\1\1/.test($("#newPw").val())) { alert('비밀번호에 444같은 문자를 4번 이상 사용하실 수 없습니다.'); return; }
	    	if($("#newPw").val().search($("#memberId").val()) > -1){ alert("비밀번호에 아이디가 포함되었습니다."); return; }
		}

		//전화번호 데이터 없을 시 return
		let  regExp = /^[0-9]*$/;
		if("" == $("#phone").val()) {
			alert("전화번호가 입력되지 않았습니다.");
			return; 
		}
		
		//전화번호는 숫자만 입력 가능
		if(regExp.test($("#phone").val()) == false){
			alert("전화번호는 숫자만 입력이 가능합니다.")
			return;
		}
		
		//주소 데이터 없을 시 return
		if("" == $("#address").val()) {
			alert("주소가 입력되지 않았습니다.");
			return; 
		}
		
		//경고메세지
		if(confirm("수정하시겠습니까?") == false) {
			return;
		} else {
			$("#newPw").val($("#pw").val());
			$("#newPwCheck").val($("#pw").val());
		}

		//post 형식으로 보내기
		let url       = "${hContext}/member/do_update.do";
		let paramters = {
							memberId : $("#memberId").val(),
							pw       : $("#newPw").val(),
							phone    : $("#phone").val(),
							address  : $("#address").val()
						};
		let method    = "POST";
		let async     = "true";
		EClass.callAjax(url, paramters, method, async, function(data) {
			alert(data.msgContents);
			
			$("#currPw").val("");
			$("#newPw").val("");
			$("#newPwCheck").val("");
		});	
		
	});
	
	</script>
	
</body>
</html>