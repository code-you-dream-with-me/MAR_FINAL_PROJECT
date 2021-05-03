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
    <title>후기 상세조회</title>
    
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
			<h2>후기 열람</h2>
		</div>
		<!--// 제목 -->
	
		<!--  form -->
		<form action = "" class = "form-horizontal">

		
			<div class = "col-md-10 col-lg-10 text-right">
				<input type = "button" class = "btn btn-primary btn-sm" value = "수정" id = "doUpdateBtn" /> 
				<input type = "button" class = "btn btn-primary btn-sm" value = "삭제" id = "doDeleteBtn" /> 
			</div>
			
			
			<div class = "form-horizontal col-md-12 col-lg-12">
				
				<h4>후기 정보</h4> <br/>
				<div class = "form-group">
				
					<label class="col-md-2 col-lg-2 control-label"> 후기 번호</label>
					<div class = "col-md-2 col-lg-2">
						<input type = "hidden" readonly="readonly" value= "${checkMemberId }" id = "checkMemberId" name = "checkMemberId"/>
						<input type = "text"   readonly="readonly" value= "${reviewNo }" class = "form-control" id = "reviewNo" name = "reviewNo" />  
					</div>
					
					<label class="col-md-2 col-lg-2 control-label">등록일</label>
					<div class = "col-md-3 col-lg-3">
						<input type = "text" readonly="readonly" class = "form-control" id = "regDt" name = "regDt" /> 
					</div>
					
				</div>
				
				<h4>고객 정보</h4> <br/>
				<div class = "form-group">
				
					<label class="col-md-2 col-lg-2 control-label">주문상품번호</label>
					<div class = "col-md-2 col-lg-2">
						<input type = "text" readonly="readonly" class = "form-control" value= "${orderitemNo }" id = "orderitemNo" name = "orderitemNo" /> 
					</div>
					
					<label class="col-md-2 col-lg-2 control-label">이메일(ID)</label>
					<div class = "col-md-3 col-lg-3">
						<input type = "text" readonly="readonly" class = "form-control" value= "${memberId }" id = "memberId" name = "memberId" />  
					</div>

				</div>
				
			</div>		
			
			<div class = "form-horizontal">
				<div class = "col-md-10 col-lg-10">
					<h4>제목</h4>
					<input type = "text" readonly="readonly" class = "form-control" id = "title" name = "title" /> <br/>
					
					<h4>내용</h4>
					<textarea class="form-control" readonly="readonly" id = "contents" name = "contents" rows = "15" ></textarea> <br/>
				</div>
			</div>
			
			<div class = "col-md-10 col-lg-10">
				<input type = "button" class = "btn btn-primary btn-sm" value = "사진추가" id = "thumbBtn" /> 
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
			
				
				doSelectOne();
				
				doSelectMyOne();

			
		});//--document ready
		
		
		// 주문상품목록을 통한 접근
		function doSelectMyOne(){
			
			console.log("doSelectMyOne");
			
			$.ajax({
			  		type: "GET",
			  		url : "${hContext}/review/do_selectMyOne.do",
			  		asyn: "true",
			  		dataType : "html",
			  		data:{
			  			orderitemNo : $("#orderitemNo").val()
			  		},
			  		success:function(data){	//통신 성공
			  			var parseData = JSON.parse(data);
			  			console.log("orderitemNo:" + orderitemNo);
			  		
			  			var reviewNo = parseData.reviewNo;
			  		    var memberId = parseData.memberId;
			  		    var orderitemNo = parseData.orderitemNo;
			  		    var title = parseData.title;
			  		    var contents = parseData.contents;
			  		    var regDt = parseData.regDt;
			  		    
			  		    $("#reviewNo").val(reviewNo);
			  		    $("#memberId").val(memberId);
			  		    $("#memberId").val(memberId);
			  		    $("#title").val(title);
			  		    $("#contents").val(contents);
			  		    $("#regDt").val(regDt);

			  		    
			      	},
			      	error:function(data){//실패시 처리
			      		console.log("error:"+data);
			      	}
			      	
			  	});
		}  		
		
		
		// 일반 후기 게시판(상품이던 마이페이지건)을 통한 접근
		function doSelectOne(){
			
			console.log("doSelectOne");
			
			$.ajax({
			  		type: "GET",
			  		url : "${hContext}/review/do_selectOne.do",
			  		asyn: "true",
			  		dataType : "html",
			  		data:{
			  			reviewNo : $("#reviewNo").val()
			  		},
			  		success:function(data){	//통신 성공
			  			var parseData = JSON.parse(data);
			  			console.log("reviewNo:" + reviewNo);
			  		
			  			var reviewNo = parseData.reviewNo;
			  		    var memberId = parseData.memberId;
			  		    var orderitemNo = parseData.orderitemNo;
			  		    var title = parseData.title;
			  		    var contents = parseData.contents;
			  		    var regDt = parseData.regDt;
			  		    
			  		    $("#memberId").val(memberId);
			  		    $("#orderitemNo").val(orderitemNo);
			  		    $("#title").val(title);
			  		    $("#contents").val(contents);
			  		    $("#regDt").val(regDt);

			  		    
			      	},
			      	error:function(data){//실패시 처리
			      		console.log("error:"+data);
			      	}
			      	
			  	});
		}  		
		
		
		// doDeleteBtn 클릭시 게시물 삭제
		$("#doDeleteBtn").on("click", function(e) {
			
			console.log("doDeleteBtn");
			e.preventDefault();
			
			if($("#checkMemberId").val() == null )  {
				alert("삭제를 누르기 전에 로그인을 먼저 해주세요.");
				return;
			}
			
			if($("#checkMemberId").val() != $("#memberId").val() ) {
				alert("후기를 작성한 작성자 본인이 아니면 삭제하실 수 없습니다.");
				return;
			}
			
			let url = "${hContext}/review/do_delete.do";
			let parameter = {"reviewNo" : $("#reviewNo").val(),
							 "memberId" : $("#memberId").val(),
							 "orderitemNo" : $("#orderitemNo").val()};
			let method	= "GET";
			let async	= false;
			
			console.log("parameter : " + $("#reviewNo").val());
			
			if(confirm("삭제 하시겠습니까?") == false) return;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
				console.log("data : " + data);
				console.log("data.msgContents : " + data.msgContents);
				// "msgId":"1","msgContents"
				
				alert(data.msgContents);
				
				if("1" == data.msgId){	// 삭제 성공
					
					// 삭제 후 후기 게시판으로(마이페이지 searchDiv = 20)으로 이동
					var searchDiv20 = "20";
					window.location.href = "${hContext}/review/review_view.do?searchDiv20=" + searchDiv20;

				}else{	// 삭제 실패
					alert(data.msgId + " \n " +data.msgContents);
				}
			})
			
		});
		
		
		// doUpdateBtn click 시 수정 화면인 review_mng_view로 이동
 		$("#doUpdateBtn").on("click", function(e){
 			
 			console.log("doUpdateBtn");
			e.preventDefault();
			
			
			if($("#checkMemberId").val() == null )  {
				alert("수정을 누르기 전에 로그인을 먼저 해주세요.");
				return;
			}
			
			if($("#checkMemberId").val() != $("#memberId").val() ) {
				alert("후기를 작성한 작성자 본인이 아니면 수정하실 수 없습니다.");
				return;
			}
			
			var reviewNo = $("#reviewNo").val();
			var memberId = $("#memberId").val();
			
			console.log("reviewNo : " + reviewNo);
			console.log("memberId : " + memberId);
			
			window.location.href = "${hContext}/review/review_mng_view.do?reviewNo=" + reviewNo + "&memberId" + memberId;
				
		}); 
		
    </script>
    <!--// javascript -->    

</body>
</html>
