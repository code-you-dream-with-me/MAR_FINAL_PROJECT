<%--
/**
	Class Name:  ordering_list
	Description: 전체 주문 리스트
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
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "hContext" value = "${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<style>
	.header{
		text-align:center;
		margin:auto;
	}
	.middle{
		margin:auto;
		border-collapse: collapse;
		padding: 30px;
		text-align:center;
	}
	.middle2{
		margin:auto;
		border-collapse: collapse;
		padding: 30px;
		text-align:left;
	}
	.middle3{
		margin:auto;
		border-collapse: collapse;
		text-align:left;
	}
	#contents{
		width:600px;
		height:1200px;
		margin:auto;
	}
	#contentslabel{
		width:600px;
		height:200px;
		margin:auto;
		font-size:45px;
	}
	.centerfont{
		text-align:center;
	}
	.rightfont{
		text-align:right;
	}

	.btn-mine {
	  padding: 8px 14px;
	  font-size: 16px;
	  line-height: 1.5;
	  border-radius: 3px;
	}
</style>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title></title>
    
    <!-- 부트스트랩 -->
    <link href="${hContext }/resources/css/bootstrap.min.css" rel="stylesheet">

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
	<div class="container02">
	
		<!-- 제목 -->
		<div class="page-header">
			<h2>주문목록</h2>
		</div>
		<!--// 제목 -->
		
	    <!-- 리스트 출력크기 조절 -->
	    <div class="row">
	    	<form action="" method="get" id="ordering_frm" name="ordering_frm" class="form-inline col-lg-12 col-md-12 text-right" >
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
	    		 	<input type = "hidden"   name = "searchWord"	id = "searchWord"		 value = "${memberId }" />
					<!-- // hidden -->
	    		 	
	    		</div>
	    	</form>
	    </div>
	    <!--// 리스트 출력크기 조절 -->
	    
	    
	    <div class = middle>
	        <h4> <strong id = "totalCnt"> </strong> </h4>
		</div>	
		                                                                                  
	    
 		<!--// 주문리스트  -->
			<!-- table -->
			<div id = "orderingList">
                                                                                        
				<div class="table-responsive">      

<%-- 				

--%>                                                               
				</div>                                                                                                     			

			</div> 
			<!--// table -->
		<!--// 주문리스트  --> 
		
		
		<!-- pagenation(이번에는 javascpipt jquery.bootpag.js라는 api사용 -->
		<div class="text-center">
			<div id="page-selection" class="text-center page"></div>
		</div>
	    <!--// pagenation -->		
		
	</div>	
	<!-- //div container -->


	<script type="text/javascript">
    
		//jquery 객체생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
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
		
		
		// 페이징 처리
		function doRetrieve(page){
			console.log("page : " + page);
	      	$.ajax({
	    		type: "GET",
	    		url:"${hContext }/ordering/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			pageSize: $("#pageSize").val(),
	    			searchDiv: $("#searchDiv").val(),
	    			searchWord: $("#searchWord").val(),
	    			pageNum: page
	    		},
	    		success:function(data){//통신 성공
	    			//alert(data);
	        		console.log("success data:" + data);
	        		var parseData =  JSON.parse(data);
	        		
	        		// 기존 데이터 삭제.
	        		$("#orderingList").empty();
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
	        		
	        				document.getElementById("totalCnt").innerText = value.name + " 고객님! 지금까지 " + value.totalCnt + "건의 주문을 하셨습니다.";
	        				
	        				html += "	<div class='table-responsive'>                                                                                ";
	        				html += "																												  ";		        				
	        				html += "		<table class='middle3' width='900px' height='50px' border = '0' >                                         ";
	        				html += "			<tbody>                                                                                               ";
	        				html += "				<th>                                                                                              ";
	        				html += "					<h4><strong>" + new Date(value.orderDate).toLocaleDateString() + "  구매내역</strong></h4>      ";
	        				html += "				</th>                                                                                             ";
	        				html += "			</tbody>                                                                                              ";
	        				html += "		</table>                                                                                                  ";
	        				html += "																												  ";
	        				html += "		<!-- table -->                                                                                            ";
	        				html += "		<table id='orderingTable'                                                                                 ";
	        				html += "			class='middle2 table-striped table-bordered table-hover '                           				   ";
	        				html += "			width='900px' height='150px' border = '0' >                                                           ";
	        				html += "			<thead class='bg-primary'>                                                                            ";
	        				html += "				<th class='text-left' colspan='3'>                                                                ";
	        				html += "					<h4><strong> &ensp; " + value.item_name + " 포함 " + value.orderItemCnt + " 건</strong></h4>                                                              ";
	        				html += "				</th>                                                                                             ";
	        				html += "			</thead>                                                                                              ";
	        				html += "			<tbody> 											    											  ";
	        				html += "				<tr>                                                                                              ";
	        				html += "					<td width='150px' height='150px'>                                                             ";
	        				html += "						<image src='${hContext}" + value.image_path + "' alt='item_img' width='150px' height='150px' />";
	        				html += "					</td>                                                                                         ";
	        				html += "                   <div>                                                                					      ";
	        				html += "					<td width='600px' height='150px' onclick = 'doSelectOne("+ value.orderNo + ");'>  			  ";
	        				//html += "						<h5 name = 'orderNo'>                                                                     ";
	        				//html += "							<strong>&ensp;&ensp;주문번호 : " + value.orderNo + "번</strong>                          ";
	        				//html += "						</h5>                                                                                     ";
	        				html += "						<h5 name = 'price'>                                                                       ";
	        				html += "							<strong>&ensp;&ensp;결제금액 : " + value.price + "원</strong>                            ";
	        				html += "						</h5>                                                                                     ";
	        				//html += "						<h5 class = 'orderState'>                                                                 ";
	        				//html += "							<strong>&ensp;&ensp;주문상태 : " + value.orderState + "</strong>                        ";
	        				//html += "						</h5>                                                                                     ";
	        				html += "						<h5 name = 'request'>                                                                     ";
	        				
	        				if(value.request == null){
	        				
	        					html += "							<strong>&ensp;&ensp;요청사항 : </strong>                     						  ";
	        				
	        				}else{
	        				
	        					html += "							<strong>&ensp;&ensp;요청사항 : " + value.request + "</strong>                       ";
	        					
	        				}
	        				
	        				html += "						</h5>                                                                                     ";
	        				html += "					</td>                                                                                         ";
	        				html += "                                                                                                                 ";
	        				html += "					<td width='150px' height='150px' class='text-center'>                                         ";
	        				
	        				if(value.orderState == 4) {
        						
	        					html += "					<input type='button' class='btn-success btn-mine active' value='결제완료' disabled='disabled' /> </br> </br>   	";       				
	        					
	        				}else if(value.orderState == 2){
	        					
	        					html += "					<input type='button' class='btn-defalut btn-mine active' value='주문취소중' disabled='disabled' /> </br> </br>   	";

	        				}else if(value.orderState == 3){
	        					
	        					html += "					<h4><strong> 주문취소 완료 </strong></h4> </br>    	";

	        				}else{
	        					
	        					html += "					<input type='button' class='btn-primary btn-mine' aria-pressed='true' value='주문취소'  onclick = 'doUndoRequest("+ value.orderNo +");' /> </br> </br>   	";

	        				}
	        				
	        				html += "						<input type='button' class='btn-defalut btn-mine' value='1:1 문의' onclick = 'doQuestionInsert("+ value.orderNo +");' />			  		";
	        				html += "					</td>                                                                                         ";
	        				html += "				    </div>                                                                                        ";
	        				html += "				</tr>                                                                                             ";
	        				html += "			</tbody>                                                                                              ";
	        				html += "		</table> </br>  </br>                                                                                     ";
	        				html += "	</div> </br>   </br>                                                                                          ";

	        			});

	 
	        		}else{ 	// Data가 없는 경우
	        			html +="	<div class ='middle'> ";
	        			html +="	<h4> <strong> 고객님께서 구매하신 이력이 없습니다. </strong> </h4>";
	        			html +="	</div> "; 
	        		}
	        		
	        		// tbody에 데이터 추가	
	        		$("#orderingList").append(html);
	        		
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
		
		function doUndoRequest(num){
			
			console.log("doUndoRequest");
			
			let url = "${hContext}/ordering/do_undoRequest.do";
			let parameter = {"orderNo" : num};
			let method	= "GET";
			let async	= true;
			
			if(confirm("주문취소 요청을 하시겠습니까?") == false) return;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
				console.log("data : " + data);
				console.log("data.msgContents : " + data.msgContents);
				// "msgId":"1","msgContents"
				
				alert(data.msgContents);
				
				if("1" == data.msgId){	// 주문취소요청 성공
					doRetrieve(1);
				}else{	// 주문취소요청 실패
					alert(data.msgId + " \n " +data.msgContents);
				}
			});			
			
		}
		
		// 1:1 질의 입력
		function doQuestionInsert(num){

			console.log("doQuestionInsert");
			
			var orderNo = num;
			
			console.log("orderNo : " + orderNo);
			
			window.location.href = "${hContext}/question/question_reg_view.do?orderNo=" + orderNo;
			// window.location.href = "${hContext}/question/question_reg_view.do";

		}
			
					
		// 주문 상품 조회로 이동			
		function doSelectOne(num){
			
			console.log("doSelectOne");
			
			$.ajax({
			  		type: "GET",
			  		url : "${hContext}/ordering/do_selectOne.do",
			  		asyn: "true",
			  		dataType : "html",
			  		data:{
			  			orderNo : num
			  		},
			  		success:function(data){	//통신 성공
			  			
			  			var parseData = JSON.parse(data);
			  			console.log("parseData:" + data);
			  		
			  			var orderNo = parseData.orderNo;
			  		    var memberId = parseData.memberId;
			  		    var price = parseData.price;
			  		    var name = parseData.name;
			  		    var phone = parseData.phone;
			  		    var address = parseData.address;
			  		 	var request = parseData.request;
			  			var orderState = parseData.orderState;
			  			var orderDate = parseData.orderDate;
			  		  	
			  		 	 window.location.href = "${hContext}/ordering/ordering_item_detail_view.do?orderNo=" + orderNo + "&orderState=" + orderState  + "&orderDate=" + orderDate;

			      	},
			      	error:function(data){//실패시 처리
			      		console.log("error:"+data);
			      	}
			      	
			  	});
			
		}  		
						


			

		
		

    </script>
    <!--// javascript --> 
    
</body>
</html>
