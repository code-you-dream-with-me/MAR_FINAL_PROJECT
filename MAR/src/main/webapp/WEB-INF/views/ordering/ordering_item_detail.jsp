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
			<h2>주문상품 목록</h2>
		</div>
		<!--// 제목 -->
		
	    <!-- 전체주문 정보 doSelectOne을 위한 파라미터 저장(구매일이 필요함) -->
	    <div class="row">
	    	<form action="" method="get" id="ordering_frm" name="ordering_frm" class="form-inline col-lg-12 col-md-12 text-right" >
	    		<div class="form-group">
	    		 	
	    		 	<!-- hidden -->
	    		 	<input type = "hidden"   name = "orderNo"		id = "orderNo"			 	 value = "${ordering.orderNo}" />
	    		 	<input type = "hidden"   name = "price"			id = "price"			 	 value = "" />
	    		 	<input type = "hidden"   name = "name"			id = "name"					 value = "" />
	    		 	<input type = "hidden"   name = "orderState"	id = "orderState"			 value = "${ordering.orderState}" />
	    		 	<input type = "hidden"   name = "orderDate"		id = "orderDate"			 value = "${ordering.orderDate}" />
					<!-- // hidden -->	
	    		 	
	    		</div>
	    	</form>
	    </div> 
	    <!--// 전체주문 정보 -->
	    
 		<!--// 주문 상품 리스트  -->
			<!-- table -->
  
  
				            <!--<h5><strong> &emsp;&emsp; 주문번호 : ${ordering.orderNo}번 </strong></h5> -->
                                                                           
			<div class="table-responsive" id = "orderingItem">      
                                                            
			</div>                                                                                                     			


			<!--// table -->
		<!--// 주문 상품 리스트  --> 
		
		
		<!-- pagenation(이번에는 javascpipt jquery.bootpag.js라는 api사용 -->
	    <!--// pagenation -->		
		
	</div>	
	<!-- //div container -->


	<script type="text/javascript">
    
		//jquery 객체생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
			doRetrieveItem();

		});//--document ready

		
		// 숫자만 입력
		// 사용법 <input type = "text" numberOnly 이런식으로 추가
		$("input:text[numberOnly]").on("keyup", function(){
			$(this).val($(this).val().replace(/[^0-9]/g, ""));
			// $(this).val($(this).val().replace(/[^A-Za-z^0-9^_]/g, ""));
		});
		
		
		// 페이징 처리
		function doRetrieveItem(){
	      	$.ajax({
	    		type: "GET",
	    		url:"${hContext }/ordering/do_selectItemlist.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			orderNo : $("#orderNo").val()
	    		},
	    		success:function(data){//통신 성공
	    			// alert(data);
	        		console.log("success data:" + data);
	        		var parseData =  JSON.parse(data);
	        		
	        		// 기존 데이터 삭제.
	        		$("#orderingItem").empty();
	        		var html = "";
	        		
	        		console.log("parseData.length : " + parseData.length);
	        		
	        		let totalCount = 0;
	        		let pageTotal  = 1;
	        			
        			totalCount = parseData[0].totalCnt;
        			console.log("totalCount : " + totalCount);
        			

        			$.each(parseData, function(i, value) {
        				
        				//console.log(i + "," + value.name);
        				
        				// 1/1000초인 milliSecond 기준 하루는 86400000ms이다.
        				
        				// .getTime() : 지정된 시간 1970년 1월 1일 12시를 기준해서 지난 시간을 밀리초 단위로 구해줌
        				var sysdate = (new Date().getTime());
        				var orderDate = new Date(value.orderDate).getTime();
        				
        				console.log("sysdate : " + sysdate );
        				console.log("orderDate : " + orderDate);
        				
        				var vaildTime = (parseInt(sysdate, 10) - parseInt(orderDate, 10));
        				
        				console.log("vaildTime : " + vaildTime);
        				

        				
        				html += "																												  ";		        				
        				html += "		<!-- table -->                                                                                            ";
        				html += "		<table	class='middle2 table-striped table-bordered table-hover table-condensed border = 0'               ";
        				html += "			width='900px' height='120px'>                                                                         ";
        				html += "			<tbody> 																						      ";
        				html += "				<tr>                                                                                              ";
        				html += "					<td width='150px' height='120px'>                                                             ";
        				html += "						<image src='${hContext}" + value.image_path + "' alt='item_img' width='150px' height='120px'/>       ";
        				html += "					</td>                                                                                         ";
        				html += "                   <div>                                                                						  ";
        				html += "					<td width='400px' height='120px'>                                                             ";
        				html += "						<h4>                                                                       				  ";
        				html += "							<strong>상품명 : " + value.item_name + " </strong>                       				  ";
        				html += "						</h4>                                                                                     ";
        				html += "						<h6>   " + value.quantity + "개 구매하셨습니다  </h6>                                  	      ";
        				html += "					</td>                                                                                         ";
        				html += "                   <td width='200px' height='120px' class='text-center'>                                                             ";
        												
						if($("#orderState").val() == 4){
       						
							html += "					<h5>   " + new Date($("#orderDate").val()).toLocaleDateString() + " 에<br> 주문하신 물건들이 <br>결제완료 되었습니다 </h5> ";       				
        					
        				}else if($("#orderState").val() == 2){
        					
        					html += "					<h5>   " + new Date($("#orderDate").val()).toLocaleDateString() + " 에<br> 주문하신 내역이 <br>취소 중입니다 </h5> ";

        				}else if($("#orderState").val() == 3){
        					
        					html += "					<h5>   " + new Date($("#orderDate").val()).toLocaleDateString() + " 에<br> 주문하신 내역이 <br>취소 확정되었습니다 </h5> ";

        				}else if($("#orderState").val() == 1){
        					
        					html += "					<h5>   " + new Date($("#orderDate").val()).toLocaleDateString() + " 에<br> 주문하신 물건들이 <br>배송중에 있습니다 </h5> "; 

        				}

        				html += "					</td>                                                                                       						";
        				
        				html += "					<td width='150px' height='120px' class='text-center'>                                     						    ";

        				
         				if(value.reviewState == 1 && parseInt(new Date().getTime() - new Date($("#orderDate").val()).getTime(), 10) >= 86400000 * 7){
        					
        					html += "					<h4><strong> 후기 작성 <br> 기간 초과 </strong></h4> 																";

        				}else if(value.reviewState == 2){
        					
        					html += "					<input type='button' class='btn-info btn-mine' value='후기보기' onclick = 'doSelectMyOne("+ value.orderitemNo +");' /> ";

        				}else if($("#orderState").val() == 2 ){
        					
        					html += "					<input type='button' class='btn-defalut btn-mine active' disabled = 'disabled' value='주문취소 중' />						";
        					
        				}else if($("#orderState").val() == 3){
        					
        					html += "					<h4><strong> 주문취소 완료된 <br> 상품입니다. </strong></h4> 	 					";				
        					
        				}else if($("#orderState").val() == 1){ 
        				
        					html += "					<h4><strong> 결제완료 상품만 <br> 후기 작성 가능합니다.  </strong></h4> 	 		";
        					
        				}else if(value.reviewState == 1){
        					
        					html += "					<input type='button' class='btn-primary btn-mine' value='후기쓰기' onclick = 'doReviewInsert("+ value.orderitemNo +");' /> ";

        				}
        				
        				html += "					</td>                                                                                         ";
        				
        				html += "				    </div>                                                                                        ";
        				html += "				</tr>                                                                                             ";
        				html += "			</tbody>                                                                                              ";
        				html += "		</table> </br>                                                                                 			  ";
	        				
        			});
		
	        		// tbody에 데이터 추가	
	        		$("#orderingItem").append(html);
	    			
	        	},
	        	error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
	      	
		}
		
		
		// 리뷰 쓰기로 이동 (alert 설정은 왜 했냐고 하면, 이를 통해 원지 않는 조건을 뚫고 리뷰 쓰기를 2중으로 방지하는 장치라고 하면 됨)
		function doReviewInsert(num){

			console.log("doReviewInsert");
			
			if($("#orderState").val() == 2 || $("#orderState").val() == 3){
				
				alert("주문 취소 중이거나 취소된 상품은 후기를 작성하실 수 없습니다.");
				return;
				
			}else if($("#orderState").val() == 4 && parseInt(new Date().getTime() - new Date($("#orderDate").val()).getTime(), 10) >= 86400000 * 7){
				
				alert("후기 작성 가능 기간이 초과했습니다.");
				return;
				
			}else if($("#orderState").val() == 1){
				
				alert("후기는 결제완료가 되고나서 적을 수 있습니다.");
				return;
				
			}	
				
			var orderitemNo = num;
			
			console.log("orderitemNo : " + orderitemNo);
			
			window.location.href = "${hContext}/review/review_reg_view.do?orderitemNo=" + orderitemNo;
			// window.location.href = "${hContext}/question/question_reg_view.do";

		}
		
		// 내 리뷰 보기
		function doSelectMyOne(num){
			
			console.log("doReviewInsert");
			
			var orderitemNo = num;

			console.log("orderitemNo : " + orderitemNo);
			
			window.location.href = "${hContext}/review/review_detail_view.do?orderitemNo=" + orderitemNo;
			
		}
		
		
		
    </script>
    <!--// javascript --> 
    
</body>
</html>
