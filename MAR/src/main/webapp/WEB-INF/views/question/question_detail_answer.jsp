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
<%@ page import="com.sist.mar.question.domain.QuestionVO"%>
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
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	
<%-- 	<link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet"> --%>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" >
 	
<%-- 	<link rel="stylesheet" href="${hContext}/resources/css/bootstrapcdn.css" >   --%>
     <link rel="stylesheet" href="${hContext}/resources/boot/css/bootstrap-theme.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--   	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>   -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" > </script>
 	<script src="${hContext}/resources/js/jquery.min.js"></script>
 	<script src="${hContext}/resources/js/eutil.js"></script>
	 	
    <style> 
       /*각자 작업한 화면 넣는 부분*/
       .work_container {
           width: 80%;
           height: auto;/*높이 자동변경*/
           padding: 0 0 0 20px;
       }
       .work_container h6 {
           color:rgb(77, 77, 77);
           margin: 0px 0px 0px 0px;
           font-size:25px;
       }
       .work_container hr {
           margin: 10px 0px 25px 0px;
       }
       .work_table {
           width: 100%;
       } 
       .work_table td {
           border: 2px solid #a9c0e7;
       }
       .td_title {
           width: 12%;
           text-align: center;
           background-color: #DAE3F3;
           color: #3B3838;
           font-weight: bold;
       }
       .td_body01 {
           height: 40px;
       }
       .td_body01 input {
           width: 100%;
           height: 100%;
           border: none;
       }
       .td_body02 {
           height: 400px;
       }
       .td_body02 textarea {
           width: 100%;
           height: 95%;
           resize: none;
           border: none;
       }
       .button_area {
           text-align: right;
           margin-top: 20px;
       }
       .button_area button {
           width: 100px;
           padding: 5px;
           margin-bottom: 5px;
           background-color: #2F5597;
           border-radius: 4px;
           font-size: 14px;
           color: rgb(255, 255, 255);
           border: 1px solid #2F5597
       }
       
@charset "UTF-8";

.card {
	position: relative;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-direction: column;
	flex-direction: column;
	min-width: 0;
	word-wrap: break-word;
	background-color: #fff;
	background-clip: border-box;
	border: 1px solid rgba(0, 0, 0, .125);
	border-radius: .25rem
}

.card>hr {
	margin-right: 0;
	margin-left: 0
}

.card>.list-group:first-child .list-group-item:first-child {
	border-top-left-radius: .25rem;
	border-top-right-radius: .25rem
}

.card>.list-group:last-child .list-group-item:last-child {
	border-bottom-right-radius: .25rem;
	border-bottom-left-radius: .25rem
}

.tab-content>.tab-pane {
	display: none
}

.tab-content>.active {
	display: block
}

.card-body {
	-ms-flex: 1 1 auto;
	flex: 1 1 auto;
	min-height: 1px;
	padding: 1.25rem
}

.card-title {
	margin-bottom: .75rem
}

.card-subtitle {
	margin-top: -.375rem;
	margin-bottom: 0
}

.card-text:last-child {
	margin-bottom: 0
}

.card-link:hover {
	text-decoration: none
}

.card-link+.card-link {
	margin-left: 1.25rem
}

.card-header {
	padding: .75rem 1.25rem;
	margin-bottom: 0;
	background-color: rgba(0, 0, 0, .03);
	border-bottom: 1px solid rgba(0, 0, 0, .125)
}

.card-header:first-child {
	border-radius: calc(.25rem - 1px) calc(.25rem - 1px) 0 0
}

.card-header+.list-group .list-group-item:first-child {
	border-top: 0
}

.card-footer {
	padding: .75rem 1.25rem;
	background-color: rgba(0, 0, 0, .03);
	border-top: 1px solid rgba(0, 0, 0, .125)
}

.card-footer:last-child {
	border-radius: 0 0 calc(.25rem - 1px) calc(.25rem - 1px)
}

.card-header-tabs {
	margin-right: -.625rem;
	margin-bottom: -.75rem;
	margin-left: -.625rem;
	border-bottom: 0
}

.card-header-pills {
	margin-right: -.625rem;
	margin-left: -.625rem
}

.card-img-overlay {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	padding: 1.25rem
}

.card-img, .card-img-bottom, .card-img-top {
	-ms-flex-negative: 0;
	flex-shrink: 0;
	width: 100%
}

.card-img, .card-img-top {
	border-top-left-radius: calc(.25rem - 1px);
	border-top-right-radius: calc(.25rem - 1px)
}

.card-img, .card-img-bottom {
	border-bottom-right-radius: calc(.25rem - 1px);
	border-bottom-left-radius: calc(.25rem - 1px)
}

.card-deck .card {
	margin-bottom: 15px
}

.form-group {
	margin-bottom: 1rem
}

.form-control {
	
	width: 100%;
	height: calc(1.5em + .75rem + 2px);
	padding: .375rem .75rem;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out
}

.form-control.is-valid, .was-validated .form-control:valid {
	border-color: #28a745;
	padding-right: calc(1.5em + .75rem);
	background-image:
		url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8' viewBox='0 0 8 8'%3e%3cpath fill='%2328a745' d='M2.3 6.73L.6 4.53c-.4-1.04.46-1.4 1.1-.8l1.1 1.4 3.4-3.8c.6-.63 1.6-.27 1.2.7l-4 4.6c-.43.5-.8.4-1.1.1z'/%3e%3c/svg%3e");
	background-repeat: no-repeat;
	background-position: right calc(.375em + .1875rem) center;
	background-size: calc(.75em + .375rem) calc(.75em + .375rem)
}

.form-control.is-valid:focus, .was-validated .form-control:valid:focus {
	border-color: #28a745;
	box-shadow: 0 0 0 .2rem rgba(40, 167, 69, .25)
}

.text-right {
	text-align: right !important
}
.container-fluid, .container-lg, .container-md, .container-sm, .container-xl {
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
}
.row {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    margin-right: -15px;
    margin-left: -15px;
}
.mb-2, .my-2 {
    margin-bottom: .5rem!important;
}

.mt-2, .my-2 {
    margin-top: .5rem!important;
}
p {
    margin-top: 0;
    margin-bottom: 1rem;
}
*, ::after, ::before {
    box-sizing: border-box;
}
user agent stylesheet
p {
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
}
.p-2 {
    padding: .5rem!important;
}
.m-0 {
    margin: 0!important;
}
.pl-2, .px-2 {
    padding-left: .5rem!important;
}
.flex-grow-1 {
    -ms-flex-positive: 1!important;
    flex-grow: 1!important;
}

.text-decoration-none {
    text-decoration: none!important;
}
.text-primary {
    color: #007bff!important;
}
.dropdown-item {
    display: block;
    width: 100%;
    padding: .25rem 1.5rem;
    clear: both;
    font-weight: 400;
    color: #212529;
    text-align: inherit;
    white-space: nowrap;
    background-color: transparent;
    border: 0;
}

.row {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    margin-right: -15px;
    margin-left: -15px;
}
.dropdown, .dropleft, .dropright, .dropup {
    position: relative;
}
.dropdown-menu {
    position: absolute;
    top: 100%;
    left: 0;
    z-index: 1000;
    display: none;
    float: left;
    min-width: 10rem;
    padding: .5rem 0;
    margin: .125rem 0 0;
    font-size: 1rem;
    color: #212529;
    text-align: left;
    list-style: none;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid rgba(0,0,0,.15);
    border-radius: .25rem;
}	
	
.btn {
    display: inline-block;
    font-weight: 400;
    color: #ffff;
    text-align: center;
    vertical-align: middle;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-color: transparent;
    border: 1px solid transparent;
    padding: .375rem .75rem;
    font-size: 1rem;
    line-height: 1.5;
    border-radius: .25rem;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
	

textarea.form-control {
    height: auto;
    !important
}
textarea {
    overflow: auto;
    resize: vertical;
    !important
}
button, input, optgroup, select, textarea {
    margin: 0;
    font-family: inherit;
    font-size: inherit;
    line-height: inherit;
    !important
}
.answer-contents{
	padding: 20px 0px 20px 0px;
}

textarea.form-control {
    height: auto;
    !important
}
.form-control {
    display: block;
    width: 100%;
    height: calc(1.5em + .75rem + 2px);
    padding: .375rem .75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: .25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
    !important
}
textarea {
    overflow: auto;
    resize: vertical;
    !important
}
button, input, optgroup, select, textarea {
    margin: 0;
    font-family: inherit;
    font-size: inherit;
    line-height: inherit;
    !important
}
*, ::after, ::before {
    box-sizing: border-box;
    !important
}
user agent stylesheet
textarea {
    -webkit-writing-mode: horizontal-tb !important;
    text-rendering: auto;
    color: -internal-light-dark(black, white);
    letter-spacing: normal;
    word-spacing: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    text-align: start;
    appearance: auto;
    background-color: -internal-light-dark(rgb(255, 255, 255), rgb(59, 59, 59));
    -webkit-rtl-ordering: logical;
    flex-direction: column;
    resize: auto;
    cursor: text;
    white-space: pre-wrap;
    overflow-wrap: break-word;
    column-count: initial !important;
    margin: 0em;
    font: 400 13.3333px Arial;
    border-width: 1px;
    border-style: solid;
    border-color: -internal-light-dark(rgb(118, 118, 118), rgb(133, 133, 133));
    border-image: initial;
    padding: 2px;
    !important
}

   </style>
</head>

</head>
<body>

	<!-- header -->
	<!--//header -->

	<div class="work_container"><!-- col-xs-12 col-md-10 -->

		<h6>1:1문의</h6>	 <hr>
	                    
		<table class="work_table">
	                   
	                   
			<div class = "button_space col-md-12 col-lg-12 text-right">
				<input type = "button" class = "btn btn-primary btn-sm" value = "수정" id = "doUpdateBtn" /> 
				<input type = "button" class = "btn btn-primary btn-sm" value = "삭제" id = "doDeleteBtn" /> 
				<br><br>
			</div>
			
			<tbody>
			    <tr>
			        <td class="td_title">제목</td>
			 
			        <td class="td_body01" id = "title">
			        	
			        </td>
			    </tr>
			    <tr>
			        <td class="td_title">주문번호</td>
			        
			        <td class="td_body01" >
			            <h5 id = "orderNo"> </h5>
			        </td>
			    </tr>
			    <tr>
			        <td class="td_title">내용</td>
			        <td class="td_body02">
			            <h5 id = "contents"> </h5> 
			        </td>
			    </tr>
			</tbody>
			
		</table> <br><br>
	                   
        <div>
	    <!-- answer -->
		<input type="hidden" name="questionNo" id="questionNo"  value= "${questionNo }" /> 
		<input type="hidden" name="regId" id="regId" value="${sessionScope.member.memberId}"/>
		
				<!--- Form Begins -->
                <section class="card" id="answerForm">
 
                <div class="card-header" >
				</div>
				<div class="card-body">
					<div class="tab-content" >
						<div class="form-group">
							<textarea class="form-control" id="answer" rows="7" placeholder="문의에 대한 답변을 달아주세요"></textarea>
						</div>
				</div>
				<div class="text-right">
					<button type="button" class="btn btn-primary" id="doInsertButton">답변등록</button>
				</div>
				</div>
                </section> 
                <!--- Form Ends -->

				<!--contents begins -->
				<div class="answer-contents">
					<section class="card mt-4">
						<div id="answerList" class="border p-2">
						</div>
					</section>
				</div>
				<!--contents  end -->                
		  </div>
		</div>  
		<!-- footer -->
		<!--// footer -->

	
	
	<!-- //div container -->


    <!-- javascript -->
	<script type="text/javascript">
	
		var members = { memberId: '${sessionScope.member.memberId}', 
	 		       	       auth: '${sessionScope.member.auth}' };
	
		//jquery 객체생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
			
			doSelectOne();
			doSelectOnee();
			if(members.auth != '1')  { $("#answerForm").empty(); }
	
		});//--document ready

		
		function doSelectOne(){
			
			console.log("doSelectOne");
			$.ajax({
			  		type: "GET",
			  		url : "${hContext}/question/do_selectOne.do",
			  		asyn: "false",
			  		dataType : "html",
			  		data:{
			  			questionNo : $("#questionNo").val()
			  		},
			  		success:function(data){	//통신 성공
			  			var parseData = JSON.parse(data);
			  			console.log("parseData:" + orderNo);
			  		
			  			var questionNo = parseData.questionNo;
			  		    var orderNo = parseData.orderNo;
			  		    var qUser = parseData.qUser;
			  		    var title = parseData.title;
			  		    var contents = parseData.contents;
			  		    var regDt = parseData.regDt;
			  		    var answerCheck = parseData.answerCheck;
			  		    
			  		    document.getElementById("title").innerHTML = "<h5>" + title + "</h5>";
			  		    document.getElementById("orderNo").innerText = orderNo;
			  			document.getElementById("contents").innerText = contents;
		  		    
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
			
			if($("#qUser").val() != $("#memberId").val() ) {
				alert("작성자 본인이 아니면 삭제하실 수 없습니다.");
				return;
			}
			
			if($("#answerCheck").val() == '1') {
				alert("이미 답변이 완료된 1:1문의글은 삭제하실수 없습니다.");
				return;
			}
			
			let url = "${hContext}/question/do_delete.do";
			let parameter = {"questionNo" 	: $("#questionNo").val(),
							 "qUser" 		: $("#qUser").val()			};
			let method	= "GET";
			let async	= false;
			
			console.log("parameter : " + $("#questionNo").val());
			
			if(confirm("삭제 하시겠습니까?") == false) return;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
				console.log("data : " + data);
				console.log("data.msgContents : " + data.msgContents);
				// "msgId":"1","msgContents"
				
				alert(data.msgContents);
				
				if("1" == data.msgId){	// 삭제 성공
					window.location.href = "${hContext}/question/question_view.do";
				}else{	// 삭제 실패
					alert(data.msgId + " \n " +data.msgContents);
				}
			})
			
		});
		
		
		// doUpdateBtn click 시 테이블의 데이터 박스로 전달
 		$("#doUpdateBtn").on("click", function(e){
			
 			console.log("doUpdateBtn");
			e.preventDefault();
			
			if($("#qUser").val() != $("#memberId").val() ) {
				alert("작성자 본인이 아니면 수정하실 수 없습니다.");
				return;
			}
			
			if($("#answerCheck").val() == 1) {
				alert("이미 답변이 완료된 1:1문의글은 수정하실수 없습니다.");
				return;
			}
			
			var questionNo = $("#questionNo").val();
			console.log("questionNo : " + questionNo);
			
			window.location.href = "${hContext}/question/question_mng_view.do?questionNo=" + questionNo;
				
		}); 
		
		<!--answer js-->
		
 		//수정화면으로 이동
 		function doUpdate(answerNo) {
 			console.log("doUpdate");
 			console.log(answerNo);
 			
 			if(members.auth != '1')  { alert("관리자만 수정가능합니다."); return; }
 			
 			//window.location.href = "${hContext}/answer/answer_moview.do?answerNo="+answerNo;
 			$("#answerList").empty();
 			$("#answerForm").empty();
 			
 			
 			
 			$.ajax({
 				type: "GET",
 				url:"${hContext}/answer/do_selectans.do",
 				asyn: true,
 				dataType:"html",
 				data:{	
 					"answerNo": answerNo
 				},
 				
 				success:function(data){//통신 성공
 		    		console.log("success data:"+data);
 					var parseData = JSON.parse(data);
 		
 					var answerNo = parseData.answerNo;
 					var questionNo = parseData.questionNo;
 					var aUser = parseData.aUser;
 					var contents = parseData.contents;
 					var regDt = parseData.regDt;
 					
 					var html = "";
 					
 					
 					
 					html+="		<section class='card'>";
 			        html+="            <div class='card-header'>";
 			        html+="            </div>";
 			        html+="            <div class='card-body'>";
 			        html+="                <div class='tab-content' >";
 			        html+="                        <div class='form-group'>";
 			        html+="                            <textarea  class='form-control' id='answer' rows='7'>"+contents+"</textarea>";
 			        html+="                        </div>";
 			        html+="                </div>";
 			        html+="                <div class='text-right'>";
 			        html+="                	<button type='button' class='btn btn-primary' onclick='doUpdateButton("+answerNo+");'>답변수정</button>";
 			        html+="                </div>";
 			        html+="            </div>";
 			        html+="        </section>";
 			        
 			        
 			        $("#answerList").append(html);
 			  		 
 				},
 		    	error:function(data){//실패시 처리
 		    		console.log("error:"+data);
 		    	},
 		    	complete:function(data){//성공/실패와 관계없이 수행!
 		    		console.log("complete:"+data);
 		    	}
 		
 			});
 			

 		}
 		
 		//답변 내용 수정하기
 		function doUpdateButton(answerNo){
 			console.log("doUpdateBtn");
 			console.log("answerNo"+answerNo);
 			

 			if(eUtil.ISEmpty($("#answer").val()) == true){
 				alert("내용을 입력하세요");
 				$("#answer").focus();
 				return;
 			} 
 			
 			console.log($("#answer").val());
 			
 			if(confirm("수정 하시겠습니까?")==false) return;
 			
 			let url = "${hContext}/answer/do_update.do";
 			let parameters = {
 					"answerNo"  : answerNo,
 					"contents"	: $("#answer").val()
 			};
 			
 			let method = "POST";
 			let async  = true;
 			
 			
 			EClass.callAjax(url, parameters, method, async, function(data){

 				console.log("data msgContents:"+data.msgContents);
 				
 				if("1"==data.msgId)	{//등록 성공
 					alert(data.msgContents);
 				
 					var htmL = "";
 					
 	 				htmL+="<div class='card-header' >";
 					htmL+="</div>";
 					htmL+="<div class='card-body'>";
 					htmL+="	<div class='tab-content' >";
 					htmL+="			<div class='form-group'>";
 					htmL+="				<textarea class='form-control' id='answer' rows='7' placeholder='문의에 대한 답변을 달아주세요'></textarea>";
 					htmL+="			</div>";
 					htmL+="	</div>";
 					htmL+="	<div class='text-right'>";
 					htmL+="		<button type='button' class='btn btn-primary' id='doInsertButton'>답변등록</button>";
 					htmL+="	</div>";
 					htmL+="</div>"; 
 					
 	              
 					
 					$("#answerForm").append(htmL);
 					doSelectOnee();
 				}else{ //등록 실패
 					alert(data.msgId+"\n"+data.msgContents);
 				}
 				
 			}); 
 		 
 		};


 		//답변 삭제
 		function doDeleteButton(answerNo) {
 			console.log("doDelete");
 			console.log(answerNo);
 			
 			if(members.auth != '1')  { alert("관리자만 삭제가능합니다."); return; }
 			
 			let url = "${hContext}/answer/do_delete.do";
 			let parameters ={"answerNo":answerNo};
 			let method = "POST";
 			let async = true;
 		
 			if(confirm("삭제 하시겠습니까?")==false) return;
 			
 			EClass.callAjax(url, parameters, method, async, function(data){
 			
 				if("1"==data.msgId)	{//삭제 성공
 					alert(data.msgContents);
 					
 					doSelectOnee();
 				}else{ //삭제 실패
 					alert(data.msgId+"\n"+data.msgContents);
 				}
 			
 			}); 
 			
 		}

 		//문의에 대한 답변 데이터 
 		function doSelectOnee() {
 			$.ajax({
 				type: "GET",
 				url:"${hContext}/answer/do_selectone.do",
 				asyn:"true",
 				dataType:"html",
 				data:{	
 					"questionNo": $("#questionNo").val()
 				},
 				
 				success:function(data){//통신 성공
 		    		console.log("success data:"+data);
 		
 					var parseData = JSON.parse(data);
 					
 					$("#answerList").empty();
 					//$("#answerForm").empty();

 					var html = "";

 					
 					if(parseData.length>0){
 						
 						$.each(parseData, function(i, value){
 							console.log(i+","+value.name);
 							

 							//---------------------------------------------------------------------------------------------
 							html+=" 	<div class='border p-2'>";
 							html+=" 		<div class='row m-0'>";
 							html+=" 			<div class='flex-grow-1 pl-2'>";
 							html+=" 				<a class='text-decoration-none' href='#' id='aUser'><h5>"+value.aUser+"</h5></a> ";
 							html+=" 				<p class='small text-secondary m-0 mt-1' id='regDt'>"+value.regDt+"</p>";
 							html+=" 			</div>";
 							html+=" 			<div class='dropdown'id='dropdownBtn'  >";
 							html+=" 				<a class='' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>";
 							html+=" 				<i class='fas fa-chevron-down'></i>";
 							html+=" 				</a>";
 							html+=" 			<div class='dropdown-menu'  aria-labelledby='dropdownMenuLink'>";
 							html+=" 				<button class='dropdown-item text-primary' onclick='doUpdate("+value.answerNo+");'>수정</button>";
 							html+=" 				<button class='dropdown-item text-primary' onclick='doDeleteButton("+value.answerNo+");'>삭제</button>";
 							html+=" 			</div>";
 							html+=" 			</div>";
 							html+=" 		</div>";
 							html+=" 	<div class=''>";
 							html+=" 		<p class='my-2' id='contents'>"+value.contents+"</p>";
 							html+=" 	</div>";
 							html+=" 	<hr class='my-1'>";
 							html+=" 	</div>";  
 							
 						
 						});
 						
 		
 					}else {		//data가 없는 경우
 						//$("#answerForm").append(htmL);
 						html +="<tr>";
 						html +="	<td class='text-center' colspan='99' >등록된 답변이 없습니다.</td> ";
 						html +="</tr>";
 					}
 					
 					//데이터 추가
 					$("#answerList").append(html);

 					
 		    	},
 		    	error:function(data){//실패시 처리
 		    		console.log("error:"+data);
 		    	},
 		    	complete:function(data){//성공/실패와 관계없이 수행!
 		    		console.log("complete:"+data);
 		    	}
 			});
 			
 		}


 		//답변 등록
 		$("#doInsertButton").on("click",function(e){
 			console.log("doInsertBtn");
 			e.preventDefault();
 			

 	 		if(eUtil.ISEmpty($("#answer").val()) == true){
 				alert("내용을 입력하세요");
 				$("#answer").focus();
 				return;
 			} 
 			
 			console.log($("#answer").val());
 			
 			if(confirm("등록 하시겠습니까?")==false) return;
 			
 			let url = "${hContext}/answer/do_insert.do";
 			let parameters = {
 				
 					"questionNo" : $("#questionNo").val(),
 					"aUser"	:	$("#regId").val(),
 					"contents"	:	$("#answer").val()
 			};
 			
 			let method = "POST";
 			let async  = true;
 			
 			
 			EClass.callAjax(url, parameters, method, async, function(data){

 				console.log("data msgContents:"+data.msgContents);
 				
 				if("1"==data.msgId)	{//등록 성공
 					alert(data.msgContents);
 					doSelectOnee();
 					$("#answer").val('');
 				}else{ //등록 실패
 					alert(data.msgId+"\n"+data.msgContents);
 				}
 				
 			}); 
 		
 		});

		
    </script>
    <!--// javascript -->    

</body>
</html>