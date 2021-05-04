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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>후기 상세</title>
    
    <!-- 부트스트랩 : 합쳐지고 최소화된 최신 CSS-->
    <link href="/boot/css/bootstrap.min.css" rel="stylesheet"> 
    <!-- https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css -->
​
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="/boot/css/bootstrap-theme.min.css">
    <%-- <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet"> --%>
    <!-- https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css -->
​
    <!-- font -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
​
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="/boot/js/bootstrap.min.js"></script>
    <%-- <script src="${hContext}/resources/js/bootstrap.min.js"></script> --%>
    <!-- https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js -->
	
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" > </script>
	
    <style>
    /* 이 부분은 tiles합칠때 지우셔야 합니다 */
        #wrap {
            width: 90%;/*960px*/
            height: auto;
            margin: 0 auto;
        }
        /*부모컨테이너 부분 : 사이드바 + 작업컨테이너*/
        .parent_container {
            width: 70%; /*672px%960px*/
            height: auto;
            margin: 0 auto;
            padding: 20px 2.083333%; /*20px%960px 가변패딩*/
            /* border: 2px solid #2F5597;/*표시선*/
        }
        .container_row {
            display: flex;
            flex-direction: row;
        }
    /* 이 부분은 tiles합칠때 지우셔야 합니다 */   
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
       
       .anwer.form-control {
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
		}
	
	     /* 답변부분 */
        .answer_box { 
            width: 100%;
            margin-top: 20px;
        }

        .jumbotron {
            border-radius: 4px;
            padding-top: 10px;
        }

        .jumbotron textarea {
            margin: 0 auto;
            width: 95%;
            height: 250px;
            resize: none;
            border: none;
        }

        .button_space button {
            width: 100px;
            padding: 5px;
            background-color: #2F5597;
            border-radius: 4px;
            font-size: 14px;
            color: rgb(255, 255, 255);
            border: 1px solid #2F5597;
            margin: 10px 22px 0px 0px;
        }
        
   </style>
</head>

</head>
<body>

	<!-- header -->
	<!--//header -->

	<div class="work_container"><!-- col-xs-12 col-md-10 -->

		<h6>후기 상세</h6>	 <hr>
	                    
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
			        <td class="td_title">후기번호</td>
			        
			        <td class="td_body01" >
			            <h5 id = "reviewNo!"> </h5>
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
		
	   	<!-- hidden -->
	   	 <input type = "hidden"   id = "reviewNo"	 	 value = "${reviewNo}" />
 		 <input type = "hidden"   id = "orderitemNo"	 value = "${orderitemNo}" />
 		 <input type = "hidden"   id = "searchDiv"		 value = "${searchDiv}" />
 		 <input type = "hidden"   id = "searchWord"		 value = "${searchWord}" />
		<!-- // hidden -->

	</div>                    
		  
		<!-- footer -->
		<!--// footer -->
	
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

			  		    document.getElementById("title").innerHTML = "<h5>" + title + "</h5>";
			  		    document.getElementById("reviewNo!").innerText = reviewNo;
			  			document.getElementById("contents").innerText = contents;

			  		    
			      	},
			      	error:function(data){//실패시 처리
			      		console.log("error:"+data);
			      	}
			      	
			  	});
		}  		
		
		
		// 내가 쓴 후기 게시판을 통한 접근
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
			  		    
			  		    document.getElementById("title").innerHTML = "<h5>" + title + "</h5>";
			  		    document.getElementById("reviewNo!").innerText = reviewNo;
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
			
			if($("sessionScope.member") == null )  {
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
			
			
			if($("sessionScope.member") == null ) {
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
