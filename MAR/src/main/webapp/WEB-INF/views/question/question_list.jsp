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

		<div class="page-header" id = "authTitle">
			
		</div>

		<!--// 제목 -->
	
	    <!-- 리스트 출력크기 조절 -->
	    <div class="row">
	    	<form action="/MAR/question/question.do" method="get" id="question_frm" name="question_frm" class="form-inline  col-lg-12 col-md-12 text-right" >
	    		<div class="form-group">

					<select name = "pageSize" id = "pageSize" class = "form-control input-sm text-center">
						<c:forEach var="item" items="${LIST_PAGE_SIZE}">
					  		  	<option value="${item.detCode}"> ${item.detNm} </option>
						</c:forEach>
					</select>	
	    		  	
	    		  	<!-- 버튼 -->
	    		 	<input type="button" class="btn btn-primary btn-sm"  value="목록 크기 적용" id = "listSizeRefresh" />
	    		 	<!-- //버튼 -->
	    		 	
	    		 	<!-- hidden -->
	    		 	<input type = "hidden"   name = "searchDiv"	id = "searchDiv" 	 value = "${searchDiv }" />
	    		 	<input type = "hidden"   name = "searchWord"	id = "searchWord" 	 value = "${searchWord }" />
					<!-- // hidden -->
	    		 	
	    		</div>
	    	</form>
	    </div> </br>
	    <!--// 리스트 출력크기 조절 -->
	

		<!-- table -->
		<div class="table-responsive">
			<!-- table -->
			<table id="questionTable" class="table table-striped table-bordered table-hover table-condensed">
				<thead class="bg-primary">
					<!-- <th class="text-center col-lg-1 col-md-1  col-xs-1" style="visibility:hidden;">글번호</th> -->
					<th class="text-center col-lg-1 col-md-1  col-xs-1">질의번호</th>
					<th class="text-center col-lg-1 col-md-1  col-xs-1">답변여부</th>
					<th class="text-center col-lg-6 col-md-6  col-xs-6">제목</th>
					<th class="text-center col-lg-1 col-md-1  col-xs-1">글쓴이(ID)</th>
					<th class="text-center col-lg-1 col-md-1  col-xs-1">주문번호</th>
					<th class="text-center col-lg-1 col-md-1  col-xs-1">등록일</th>
					<th class="text-center col-lg-1 col-md-1  col-xs-1"  style="visibility:hidden;">답변여부</th>

				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<!--// table -->
	
		<!-- pagenation(이번에는 javascpipt jquery.bootpag.js라는 api사용 -->
		<div class="text-center">
			<div id="page-selection" class="text-center page"></div>
		</div>
	    <!--// pagenation -->
	    
		<!-- footer -->
		<!--// footer -->

	</div>
	<!-- //div container -->
	
    <!-- javascript -->
	<script type="text/javascript">
    
		//jquery 객체생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
			authTitle();
			doRetrieve(1);

		});//--document ready

		
		// 숫자만 입력
		// 사용법 <input type = "text" numberOnly 이런식으로 추가
		$("input:text[numberOnly]").on("keyup", function(){
			$(this).val($(this).val().replace(/[^0-9]/g, ""));
			// $(this).val($(this).val().replace(/[^A-Za-z^0-9^_]/g, ""));
		});
		
		
		// 리스트 출력 크기 조절 적용
		$("#listSizeRefresh").on("click", function(e){
			console.log("listSizeRefresh");
			e.preventDefault();	// 두번 호출 방지
	
			doRetrieve(1);
		});

		// memberId가 관리자냐 소비자냐에 따라 제목 변경
		function authTitle(){
			
			if($("#searchDiv").val() == 10){
				
				document.getElementById("authTitle").innerHTML = "<h2>나의 1:1문의</h2>";
					
				console.log("searchDiv : "  + $("#searchDiv").val());
				
			}else if($("#searchDiv").val() == 20){
				
				document.getElementById("authTitle").innerHTML = "<h2>1:1문의(관리자용)</h2>";
				
				console.log("searchDiv : "  + $("#searchDiv").val());
				
			}
				
		}
		

		
		// 페이징 처리
		function doRetrieve(page){
			console.log("page : " + page);
	      	$.ajax({
	    		type: "GET",
	    		url:"${hContext }/question/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			pageSize: $("#pageSize").val(),
	    			searchDiv: $("#searchDiv").val(),
	    			searchWord: $("#searchWord").val(),
	    			pageNum: page
	    		},
	    		success:function(data){//통신 성공
	    			// alert(data);
	        		console.log("success data:" + data);
	        		var parseData =  JSON.parse(data);
	        		
	        		// 기존 데이터 삭제.
	        		$("#questionTable>tbody").empty();
	        		var html = "";
	        		
	        		console.log("parseData.length : " + parseData.length);
	        		
	        		let totalCount = 0;
	        		let pageTotal  = 1;
	        		
	        		
	        		// Data가 있는 경우
	        		if(parseData.length > 0){
	        			
	        			totalCount = parseData[0].totalCnt;
	        			console.log("totalCount : " + totalCount);
	        			
	        			pageTotal  = (totalCount / $("#pageSize").val());		// 40/10 = 4.2
	        			console.log("pageTotal:"+pageTotal);
	        			
	        			pageTotal = Math.ceil(pageTotal) ;				// 42/10 = 4.2 -> 5
	        			
	        			$.each(parseData, function(i, value) {
	        				//console.log(i + "," + value.name);
	        				
	        				
	        				html += "<tr>";
	        				// html += "	<td class='text-center'  style='visibility:hidden;'>"+ value.num + "</td>";
	        				html += "	<td class='text-center'>"+ value.questionNo +"</td>";
	        				if(value.answerCheck == 0){
	        					
	       
	        					html += "	<td class='text-center'></td>";
	        					
	        				}else if(value.answerCheck == 1){
	        				
	        					html += "	<td class='text-center'><img alt='Brand' src='${hContext }/resources/image_source/comment01.png' width='30%></td>";
	        					html += "	<td class='text-center'></td>";
	        				}
	        				
	        				html += "	<td class='text-left'>"+ value.title +"</td>";
	        				html += "	<td class='text-left'>"+ value.qUser +"</td>";
	        				html += "	<td class='text-center'>"+ value.orderNo +"</td>";
	        				html += "	<td class='text-center'>"+ value.regDt +"</td>";
	        				html += "	<td class='text-center'  style='visibility:hidden;'>"+ value.answerCheck +"</td>";
	        				html += "</tr>";
	        				
	        			});
	        			
	        		}else{ 	// Data가 없는 경우
	        			html +="<tr>";
	        			html +="	<td class='text-center' colspan='99'> 등록된 게시물이 없습니다. </td>";
	        			html +="</tr>";
	        		}
	        		
	        		// tbody에 데이터 추가	
	        		$("#questionTable>tbody").append(html);
	        		
	        		
	        		// 페이징
	        		console.log(pageTotal + " , " + page);
	        		renderingPage(pageTotal, page);
	    			
	        	},
	        	error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
	      	
		}
		
		// paging 
		// pageTotal  : 총페이지수 = 총글수(totalCount)/페이지사이즈(10)
		// page 	  : 현재 페이지
		// maxVisible : bottom page (바닥)
		function renderingPage(pageTotal , page){
			
			//이전 연결된 Event 핸들러를 요소에서 제거
			$("#page-selection").unbind('page');
			
			$('#page-selection').bootpag({
			    total: pageTotal,
			    page: page,
			    maxVisible: 10,
			    leaps: true,
			    firstLastUse: true,
			    first: '←',
			    last: '→',
			    wrapClass: 'pagination',
			    activeClass: 'active',
			    disabledClass: 'disabled',
			    nextClass: 'next',
			    prevClass: 'prev',
			    lastClass: 'last',
			    firstClass: 'first'
			}).on("page", function(event, num){
				console.log("num : " + num);
				doRetrieve(num); // or some ajax content loading...
			}); 			
		}
		
		
		
		// table click 시 테이블의 데이터 박스로 전달
 		$("#questionTable>tbody").on("click", "tr", function(e){
			
			e.preventDefault();
			// console.log("userTable>tbody");
			
			let tds = $(this).children();
			

			var questionNo = tds.eq(0).text();
			var qUser 		= tds.eq(3).text();
			var answerCheck = tds.eq(6).text();
			
			console.log("questionNo : " + questionNo);
			console.log("answerCheck : " + answerCheck);
			console.log("qUser : " + qUser);
			
			window.location.href = "${hContext}/question/question_detail_view.do?questionNo=" + questionNo + "&answerCheck=" + answerCheck+ "&qUser=" + qUser;
				
		}); 
			
		
		
		
    </script>
    <!--// javascript -->    
	
	
</body>
</html>
