<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일     수정자      수정내용
    -----   -----  -------------------------------------------
    2021. 4. 19.        최초작성 
    
    author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
*/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- core -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!-- 국제화 -->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var = "hContext" value = "${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>1:1 문의</title>
    
    <!-- 부트스트랩 -->
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <%-- <script src="hContext/resources/js/jquery.min.js"></script> --%>
    
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
    <script src="${hContext}/resources/js/eutil.js"></script>
    <script src="${hContext}/resources/js/jquery.bootpag.js"></script>
</head>
<body>

	<!-- div container -->
	<div class="wrap container">
	
		<!-- header -->
		<!--//header -->
	
		<!-- 제목 -->
		<div class="page-header">
			<h2>후기 등록</h2>
		</div>
		<!--// 제목 -->
	
		<!--  form -->
		<form action = "" class = "form-horizontal">

		
			<div class = "col-md-10 col-lg-10 text-right">
				<input type = "button" class = "btn btn-primary btn-sm" value = "등록하기" id = "comInsertBtn" /> 
			</div>
			
			
			<div class = "form-horizontal col-md-12 col-lg-12">
				
				<div class = "form-group">
				
					<div class = "col-md-2 col-lg-2">
						<input type = "hidden"    class = "form-control" id = "reviewNo" name = "reviewNo" />  
					</div>
					
					<div class = "col-md-3 col-lg-3">
						<input type = "hidden"  class = "form-control" id = "regDt" name = "regDt" /> 
					</div>
					
				</div>
				
				<h4>고객 정보</h4> <br/>
				<div class = "form-group">
				
					<label class="col-md-2 col-lg-2 control-label">주문상품번호</label>
					<div class = "col-md-2 col-lg-2">
						<input type = "text"  readonly="readonly" class = "form-control" value= "${orderitemNo}" id = "orderitemNo" name = "orderitemNo" /> 
					</div>
					
					<label class="col-md-2 col-lg-2 control-label">이메일(ID)</label>
					<div class = "col-md-3 col-lg-3">
						<input type = "text"  readonly="readonly" class = "form-control" value= "${sessionScope.member.memberId}" id = "memberId" name = "memberId" />  
					</div>

				</div>
				
			</div>		
			
			<div class = "form-horizontal">
				<div class = "col-md-10 col-lg-10">
					<h4>제목</h4>
					<input type = "text"  class = "form-control" id = "title" name = "title"  maxlength="50" /> <br/>
					
					<h4>내용</h4>
					<textarea class="form-control"  id = "contents" name = "contents" rows = "15" ></textarea> <br/>
				</div>
			</div>
			
		</form>	  
		<!-- form -->
		  
		<!-- footer -->
		<!--// footer -->

	</div>
	<!-- //div container -->


    <!-- javascript -->
	<script type="text/javascript">
	
		//jquery 객체생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
	
		});//--document ready
		
		
		// comInsertBtn 클릭시 게시물 추가
		$("#comInsertBtn").on("click", function(e) {
			
			console.log("comInsertBtn");
			e.preventDefault();
			
			if(eUtil.ISEmpty($("#title").val()) == true){
				alert("제목을 입력해주세요");
				$("title").focus();
				return;
			}
			
			if(eUtil.ISEmpty($("#contents").val()) == true){
				alert("내용을 입력해주세요");
				$("#contents").focus();
				return;
			}
			
			let url = "${hContext}/review/do_insert.do";
			let parameter = {
							 "orderitemNo": $("#orderitemNo").val(),
							 "memberId"   : $("#memberId").val(),
							 "title" 	  : $("#title").val(),
							 "contents"   : $("#contents").val(),
																	};
			let method	= "GET";
			let async	= false;
			
			console.log("parameter : " + $("#reviewNo").val());
			
			if(confirm("등록 하시겠습니까?") == false) return;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
				console.log("data : " + data);
				console.log("data.msgContents : " + data.msgContents);
				// "msgId":"1","msgContents"
				
				
				var itemNo = $("#orderitemNo").val();
				
				alert(data.msgContents);
				
				if("1" == data.msgId){	// 추가 성공
					
					// 후기 추가 후 후기 게시판으로(마이페이지 searchDiv = 20)으로 이동
					var searchDiv20 = "20";
					window.location.href = "${hContext}/review/review_view.do?searchDiv20=" + searchDiv20;
					
					// 상품조회 페이지 후기 리스트 테스트
					//window.location.href = "${hContext}/review/review_view.do?itemNo=" + itemNo;
				}else{	// 삭제 실패
					alert(data.msgId + " \n " +data.msgContents);
				}
			})
			
		});
		
    </script>
    <!--// javascript -->    

</body>
</html>
