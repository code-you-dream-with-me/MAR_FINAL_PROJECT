<%--
	Class Name : 
	Description :
	Modification information
	
	수정일		  수정자	  수정내용
	----		  ----	  -------------------
	2021. 4. 24.  hansol  최초작성
	
	author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="${hContext}/resources/css/bootstrapcdn.css" > 
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" >


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src="${hContext}/resources/js/jquery.min.js"></script>
	<script src="${hContext}/resources/js/bootstrap.min.js"></script>
	<script src="${hContext}/resources/js/eclass.js"></script>
	<script src="${hContext}/resources/js/eutil.js"></script>
	
	<title>문의 답변</title>
<body>
	<!-- hidden -->
	
	<input type="hidden" name="questionNo" id="questionNo" value=""/>
	<input type="hidden" name="regId" id="regId" value="sinangsong@gmail.com"/>
	<input type="hidden" name="answerNo" id="answerNo" value="${answerNo }"/>				
				
				
	<div class="container-fluid my-5">
		<div class="row">
			<div class="col-6">

				<!--  Begins -->
				<section class="card mt-4">
					<div id="answerList" class="border p-2">
				<!--- Form Begins -->
                <section class="card">
                    <div class="card-header">
                    </div>
                    <div class="card-body">
                        <div class="tab-content" >
                            <div class="tab-pane fade show active"  role="tabpanel" aria-labelledby="posts-tab">
                                <div class="form-group">
                                    <textarea class="form-control" id="answer" rows="3"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="text-right">
                        	<button type="button" class="btn btn-primary" id="doUpdateBtn">답변수정</button>
                        </div>
                    </div>
                </section>
                <!--- Form Ends -->
				<section class="card mt-4">
					<div class="border p-2">
						<div class="row m-0">
							<div class="flex-grow-1 pl-2">
								<a class="text-decoration-none" href="#" ><h5></h5></a> 
								<p class="small text-secondary m-0 mt-1" id="regDt"></p>
							</div>
						</div>
					<div class="">
						<p class="my-2" id="contents"></p>
					</div>
					<hr class="my-1">
					</div>
				</section>
					</div>
				</section>
				<!--  end -->
			</div>
		</div>
	</div>
<script type="text/javascript">


	$(document).ready(function() {
		console.log("1.document:최초수행!");
		console.log("answerNo :"+answerNo);
		doSelectAns();
	
	});


	
	function doSelectAns() {
		$.ajax({
			type: "GET",
			url:"${hContext}/answer/do_selectans.do",
			asyn:"true",
			dataType:"html",
			data:{	
				"answerNo": $("#answerNo").val()
			},
			
			success:function(data){//통신 성공
	    		console.log("success data:"+data);
				var parseData = JSON.parse(data);
	
				var answerNo = parseData.answerNo;
				var questionNo = parseData.questionNo;
				var aUser = parseData.aUser;
				var contents = parseData.contents;
				var regDt = parseData.regDt;
				
				 $("#answerNo").val(answerNo);
		  		 $("#questionNo").val(questionNo);
		  		 $("#aUser").val(aUser);
		  		 $("#answer").val(contents);
		  		 $("#regDt").val(regDt);
		  		 
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
	$("#doUpdateBtn").on("click",function(e){
		console.log("doUpdateBtn");
		e.preventDefault();
		

		if(eUtil.ISEmpty($("#answer").val()) == true){
			alert("내용을 입력하세요");
			$("#answer").focus();
			return;
		}
		
		console.log($("#answer").val());
		
		if(confirm("수정 하시겠습니까?")==false) return;
		
		let url = "${hContext}/answer/do_update.do";
		let parameters = {
				"answerNo"  : $("#answerNo").val(),
				"contents"	: $("#answer").val()
		};
		
		let method = "POST";
		let async  = true;
		
		
		EClass.callAjax(url, parameters, method, async, function(data){

			console.log("data msgContents:"+data.msgContents);
			
			if("1"==data.msgId)	{//등록 성공
				alert(data.msgContents);
				let questionNo = $("#questionNo").val();
				window.location.href = "${hContext}/answer/answer_view.do?questionNo="+questionNo;
			}else{ //등록 실패
				alert(data.msgId+"\n"+data.msgContents);
			}
			
		}); 
	
});


</script>
	

</body>
</html>