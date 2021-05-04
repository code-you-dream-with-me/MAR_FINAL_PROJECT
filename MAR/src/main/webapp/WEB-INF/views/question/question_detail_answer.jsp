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
    <title>1:1 문의</title>
    
    <!-- 부트스트랩 -->
	<link rel="stylesheet" href="${hContext}/resources/css/bootstrapcdn.css" > 
 	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" > 
 
    
	<link rel="stylesheet" href="${hContext}/resources/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${hContext}/resources/css/layout.css">
 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script> 
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" > </script> 

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
     <script src="${hContext}/resources/js/bootstrap.min.js"></script>  
      <script src="${hContext}/resources/js/eclass.js"></script>
     <script src="${hContext}/resources/js/eutil.js"></script>
    <script src="${hContext}/resources/js/jquery.bootpag.js"></script> 

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
   </style>
</head>

</head>
<body>

		<!-- header -->
		<!--//header -->

	
		
		 <div class="work_container"><!-- col-xs-12 col-md-10 -->
                    <h6>1:1문의</h6>
                    <hr>
                    <table class="work_table">
                    
                   	<div class = "col-md-10 col-lg-10 text-right">
						<input type = "button" class = "btn btn-primary btn-sm" value = "수정" id = "doUpdateBtn" /> 
						<input type = "button" class = "btn btn-primary btn-sm" value = "삭제" id = "doDeleteBtn" /> 
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
                                    <input id = "orderNo" type="text" readonly="readonly" >
                                </td>
                            </tr>
                            <tr>
                                <td class="td_title">내용</td>
                                <td class="td_body02">
                                    <textarea readonly="readonly" id = "contents"></textarea>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    
                   <!-- answer -->
		
	<input type="hidden" name="questionNo" id="questionNo"  value= "${questionNo }" /> 
	<input type="hidden" name="regId" id="regId" value="${sessionScope.member.memberId}"/>
	
	<div class="container-fluid my-5">
		<div class="row">
			<div class="col-12">
			
				<!--- Form Begins -->
                <section class="card" id="answerForm">
                <div class="card-header" >
				</div>
				<div class="card-body">
					<div class="tab-content" >
						<div class="tab-pane fade show active"  role="tabpanel" aria-labelledby="posts-tab">
							<div class="form-group">
								<textarea class="form-control" id="answer" rows="3" placeholder="문의에 대한 답변을 달아주세요"></textarea>
							</div>
						</div>
				</div>
				<div class="text-right">
					<button type="button" class="btn btn-primary" id="doInsertButton">답변등록</button>
				</div>
				</div>
                </section>
                <!--- Form Ends -->

				<!--contents begins -->
				<section class="card mt-4">
					<div id="answerList" class="border p-2">
					</div>
				</section>
				<!--contents  end -->
			</div>
		</div>
	</div>
		<!-- //answer -->
                    
                    
                </div>
		

		

		
		
		  
		<!-- footer -->
		<!--// footer -->


	
	<!-- //div container -->


    <!-- javascript -->
	<script type="text/javascript">
	
		var member = { memberId: '${sessionScope.member.memberId}', 
	 		       	       auth: '${sessionScope.member.auth}' };
	
		//jquery 객체생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
			
			doSelectOne();
			
			doSelectOnee();
			   
			if(member.auth != '1')  { $("#answerForm").empty();}
	
		});//--document ready

		
		function doSelectOne(){
			
			console.log("doSelectOne");
			
			$.ajax({
			  		type: "GET",
			  		url : "${hContext}/question/do_selectOne.do",
			  		asyn: false,
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
			  		    
			  		    document.getElementById("title").innerHTML = title;
			  		    $("#orderNo").val(orderNo);
			  		    $("#contents").val(contents);
			  		    //$("#qUser").val(qUser);
			  		    //$("#regDt").val(regDt);
			  	
			  		    
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
 			
 			if(member.auth != '1')  { alert("관리자만 수정가능합니다."); return; }
 			
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
 			        html+="                    <div class='tab-pane fade show active'  role='tabpanel' aria-labelledby='posts-tab'>";
 			        html+="                        <div class='form-group'>";
 			        html+="                            <textarea class='form-control' id='answer' rows='3'>"+contents+"</textarea>";
 			        html+="                        </div>";
 			        html+="                    </div>";
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
 					htmL+="		<div class='tab-pane fade show active'  role='tabpanel' aria-labelledby='posts-tab'>";
 					htmL+="			<div class='form-group'>";
 					htmL+="				<textarea class='form-control' id='answer' rows='3' placeholder='문의에 대한 답변을 달아주세요'></textarea>";
 					htmL+="			</div>";
 					htmL+="		</div>";
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
 			
 			if(member.auth != '1')  { alert("관리자만 삭제가능합니다."); return; }
 			
 			let url = "${hContext}/answer/do_delete.do";
 			let parameters ={"answerNo":answerNo};
 			let method = "POST";
 			let async = true;
 			
 		      if(member.memberId == ""){
 		    	  alert("관리자 계정이 아니면 이용할 수 없는 페이지 입니다.");
 		    	  window.location.href = "${hContext}/member/sign_in_view.do";
 		      }
 		
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