<%--
	Class Name : 
	Description :
	Modification information
	
	수정일		  수정자	  수정내용
	----		  ----	  -------------------
	2021. 4. 20.  hansol  최초작성
	
	author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	
	<title>상품 상세</title>
	
	<!-- 부트스트랩 -->
	<link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- css -->
 	<link href="${hContext}/resources/assets/item/item_default.css" rel="stylesheet"> 

	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
	<script src="${hContext}/resources/js/jquery.min.js"></script>
	<script src="${hContext}/resources/js/bootstrap.min.js"></script>
	<script src="${hContext}/resources/js/eclass.js"></script>
<%-- 	<script src="${hContext}/resources/js/eutil.js"></script> --%>
	<script src="${hContext}/resources/js/jquery.bootpag.js"></script>
	
	<!-- 윗부분은 상품전체 css, 아래는 관련상품만! -->
	<style>
	 .item_view { position: relative; margin: 10px; padding: 0 0 0 480px; width: 880px; box-sizing: border-box;}
	 .item_view .img { position: absolute; left: 0; top: 0; } 
     .item_view .img > img { width: 400px; height: 480px; border:1px solid #e8e8e8; }
     .detailimg {object-fit:cover; width: 900px; margin: 20px 0 100px 270px; padding: 100 0 100 100px; }
	 .item_view h2 { margin: 0 0 15px; padding: 0 0 20px; border-bottom:2px solid #333; font-size:24px; color:#232323; line-height: 26px;}
	  .h3 { margin: 0 0 15px; padding: 0 0 20px; border-bottom:2px solid #333; font-size:24px; color:#232323; line-height: 26px;}
	 .item_view table th,
	 .item_view table td {border-spacing: 10px 10px; padding:13px 0; font-size: 15px; color:#444; text-align: left;}
	 .item_view table td.price { font-size: 22px;}
	 .item_view table select { width:100%; border:1px solid #c6c6c6; box-sizing: border-box; }
	 .item_view table select::-ms-expand { display: none;}
	 .btns { padding: 20px 0 20px 0; text-align: center;}
	 .btns > a { display: inline-block; width: 140px; height:40px; font-size: 16px; color:#fff; line-height: 40px; }
	 .btns > a.btn1 { background: #666;}
	 .btns > a.btn2 { background: #2f5597;} 
	 .btn {float: center; background: #fff; text-aglin:center; display: inline-block; font-size: 14px; color:#2f5597; margin-right:5px; }
	 .hidden {display:none;}
	 
	 input[type=text] {font-size: 22px; }


	.thumbnail {
	    border: 1px solid white;
	    box-shadow: none;
	}
	.thumbnail img:hover {
	    cursor: pointer;
	    -webkit-transform:scale(1.05);
	    -moz-transform:scale(1.05);
	    -ms-transform:scale(1.05);   
	    -o-transform:scale(1.05);
	    transform:scale(1.05);
	}
	.container01 h3{
	    font-size: 18px;
	    font-family: 'Gothic A1', sans-serif;
	    font-weight: bold;
	    margin: 10px 0px 10px 0px;
	}
	.discount {
	    font-size: 18px;
	    font-family: 'Gothic A1', sans-serif;
	    font-weight: bold;
	    margin: 10px 0px 0px 0px;
	    color: tomato;
	}
	.final-price {
	    font-size: 18px;
	    font-family: 'Gothic A1', sans-serif;
	    font-weight: bold;
	    margin: 10px 0px 0px 0px;
	}
	.origin-price {
	    font-size: 18px;
	    font-family: 'Gothic A1', sans-serif;
	    font-weight: bold;
	    color:#949494;
	    margin: 10px 0px 0px 0px;
	    text-decoration:line-through;
	    text-decoration-color: #949494;
	}
	table thead th { 
		color: white; 
		font-size:10pt; }
	
</style>
	
</head>
<body onload="init();">

	<!-- header -->
	
	<!-- //header -->
	

	<div class="container01">

		<div class="btn" id="btnDiv" >
		  	<button type="button"  class="btn btn-primary btn-sm"id="deleteBtn" style="float: right;">삭제</button>
		  	<button type="button"  class="btn btn-primary btn-sm"id="updateBtn" style="float: right;">수정</button>
		</div>
		

	<div class="item_view" >
	

		<!-- hidden -->
		<%-- ${sessionScope.member} --%>
		<input type="hidden" name="itemNo" id="itemNo"/>
		<input type="hidden" id="imageList" />
		<input type="hidden" name="regId" id="regId" value="${sessionScope.member.memberId}"/>
		
		<div class="table">
			<table id="itemTable">
				<colgroup>
				<col style="width:220px;">
				</colgroup>
			
			<!-- tbody -->
			<tbody>
				<h2 id="name"></h2>
				<tr>
					<th>판매가격</th>
					<td>
					    <div class="caption" id="itemPrice">
                        </div>
					</td>
				</tr>
				<tr>
					<th>중량/용량</th>
					<td id="weight" name="weight"></td>
				</tr>
				<tr>
					<th>생산지/원산지</th>
					<td id="production" name="production"></td>
				</tr>
				<tr>
					<th>유통기한</th>
					<td id="expired" name="expired"></td>
				</tr>
				<tr>
					<th>배송비</th>
					<td>무료배송</td>
				</tr>
				<tr>
					<th>유의사항</th>
					<td id="detail" name="detail"></td>
				</tr>
	
				<tr>
					<th>구매수량</th>
					<td>
						<form name="buy_form" method="get">
							<input type="hidden" id="sell_price" name="sell_price" value=""  > 
							<input type="button" value=" - " onclick="del();">
							<input type="text" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"
							name="amount" id="amount" value="1" size="1" onchange="change();">
							<input type="button" value=" + " onclick="add();">
							
  							<tr class="">
							 <th>결제금액</th>
							 <td class="total"><b>
								<input style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"
								type="text" id="sum" name="sum" size="8"></b>원</td>
							</tr>   
							
						</form>
					</td>
				</tr>
			</tbody>
		  </table>
		</div>
		
		
		<!-- 버튼 -->
		<div class="btns">
			<a  type="button" class="btn1" id="addwish">늘 사는 것</a>
			<a  type="button" class="btn2" id="addcart">장바구니 담기</a>
		</div>
		

		
		<!-- 메인이미지 -->
 		<div class="img" id="mainimg">
		</div> 
		
	</div>
	
		<!-- 관련 상품 리스트 -->
		<div>
		<div class="page-header">
			<h3>Related Product</h3>
		</div>
	 		<div id="relatedList" >
	 		<div class="row"></div>
	        </div>
		</div>
		</div>
		
		<!-- 상세이미지 -->
  		<div class="detailimg" id="detailimg">
		</div> 
	
	
	<!-- review list -->
	<!-- div container -->
	<div class="wrap container">
	
		<!-- header -->
		<!--//header -->
	
		<!-- 제목 -->
		<div class="page-header">
			<h3>후기 목록</h3>
		</div>
		<!--// 제목 -->
	
	    <!-- 검색영역 -->
	    <div class="row">
	    	<form action="/MAR/review/review.do" method="get" name="review_frm" class="form-inline  col-lg-12 col-md-12 text-right" >
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
	    		 	<input type = "hidden"   id = "searchDiv"		 value = "${searchDiv}" />
	    		 	<input type = "hidden"   id = "searchWord"		 value = "${searchWord}" />
					<!-- // hidden -->
	    		 	
	    		</div>
	    	</form>
	    </div> </br>
	    <!--// 검색영역 -->
	
	
		<!-- table -->
		<div class="table-responsive">
			<!-- table -->
			<table id="reviewTable" class="table table-striped table-bordered table-hover table-condensed">
				<thead class="bg-primary">
					<th class="text-center col-lg-1 col-md-1  col-xs-1">글번호</th>
					<th class="text-center col-lg-1 col-md-1  col-xs-1">후기번호</th>
					<th class="text-center col-lg-6 col-md-6  col-xs-6">제목</th>
					<th class="text-center col-lg-2 col-md-2  col-xs-2">글쓴이(ID)</th>
					<th class="text-center col-lg-1 col-md-1  col-xs-1">등록일</th>
					<th class="text-center col-lg-1 col-md-1  col-xs-1">조회수</th>
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
</div>
<!-- javascript -->

<script type="text/javascript">


var itemNo = getParameterByName('itemNo');

//jquery 객체생성이 완료
$(document).ready(function() {
	console.log("1.document:최초수행!");
	doRetrieve(1);
	
	doSelectOne(itemNo);
	doRetrieveImage(itemNo);
	
    var member = {
	  		  memberId: '${sessionScope.member.memberId}', 
	   		  auth: '${sessionScope.member.auth}'
	    };
	    
	    if(member.auth != '1'){ $("#btnDiv").empty(); }
			

});//--document ready


// 숫자만 입력
// 사용법 <input type = "text" numberOnly 이런식으로 추가
$("input:text[numberOnly]").on("keyup", function(){
	$(this).val($(this).val().replace(/[^0-9]/g, ""));
	// $(this).val($(this).val().replace(/[^A-Za-z^0-9^_]/g, ""));
});


// 페이징 크기 조절 적용
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
		url:"${hContext }/review/do_retrieve.do",
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
    		$("#reviewTable>tbody").empty();
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
    				html += "	<td class='text-center'>"+ value.num + "</td>";
    				html += "	<td class='text-center'>"+ value.reviewNo + "</td>";
    				html += "	<td class='text-center'>"+ value.title + "</td>";
    				html += "	<td class='text-left'>"+ value.memberId +"</td>";
    				html += "	<td class='text-left'>"+ value.regDt +"</td>";
    				html += "	<td class='text-left'>"+ value.readCnt +"</td>";
    				html += "</tr>";
    				
    			});
    			
    		}else{ 	// Data가 없는 경우
    			html +="<tr>";
    			html +="	<td class='text-center' colspan='99'> 등록된 게시물이 없습니다. </td>";
    			html +="</tr>";
    		}
    		
    		// tbody에 데이터 추가	
    		$("#reviewTable>tbody").append(html);
    		
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
	$("#reviewTable>tbody").on("click", "tr", function(e){
	
	e.preventDefault();
	// console.log("userTable>tbody");
	
	let tds = $(this).children();
	var reviewNo = tds.eq(1).text();
	var memberId = tds.eq(3).text();
	
	console.log("reviewNo : " + reviewNo);
	console.log("memberId : " + memberId);
	
	window.location.href = "${hContext}/review/review_detail_view.do?reviewNo=" + reviewNo + "&memberId=" + memberId;
		
}); 

	<!-- item js-->

	function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }
		

	//해당 상품의 같은 카테고리의 상품 리스트	
	function getRelatedList(categoryNo){
		
		categoryNo = categoryNo;

		console.log("categoryNo:"+categoryNo);

		$.ajax({
    		type: "GET",
    		url:"${hContext}/item/get_relatedlist.do",
    		async: true,
    		dataType:"html",
    		data:{	
    			"itemNo"     : itemNo,
    			"categoryNo" : categoryNo
    		},
    		
    		success:function(data){//통신 성공
        		//console.log("success data:"+data);

    			var parseData = JSON.parse(data);
    			console.log(parseData.length);
    			
    			   $("#relatedList>.row").empty();
		  		  	var html = "";
		  		  	
		  			if(parseData.length > 0){ 
		  				
		  				$.each(parseData, function(i, value) {
		  					console.log(i+","+value.name);
		  					
		  				
		  					html+=" <div class='col-sm-3 col-md-3'>";
		  					html+="       <div class='thumbnail' onclick='moveToItem("+value.itemNo+")'>";
		  					html+="        <img src='${hContext}"+value.path + value.saveName +  "' style='width: 200px; height: 200px; object-fit: cover; '>";
		  					html+=" 		   <div class='caption'>";
		  					html+=" 			   <h3>"+value.name+"</h3>";  
		  					
		  					if(value.finalPrice == value.price){
		  						html+=" 			   <h3 class='final-price'>"+value.price+"</h3> ";
		  				
		  					}else{
		  						html+=" 			   <span class='origin-price'>"+value.price+"</span>";
			  					html+=" 			   <span class='discount'>"+value.discount+"%</span>"; 
			  					html+=" 			   <h3 class='final-price'>"+value.finalPrice+"</h3> ";
		  					}
		  					
		  					html+=" 		  </div>";
		  					html+="       </div>";
		  					html+=" </div>";                                                                         
		  		
		  				});
		  				
		  			}

		  		$("#relatedList>.row").append(html);

        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	},
        	complete:function(data){//성공/실패와 관계없이 수행!
        		console.log("complete:"+data);
        	}
    	});
		
	} 
	
	//상품 클릭하여 상품 상세로 이동
	function moveToItem(itemNo){
	
		console.log("itemNo:"+itemNo);
		window.location.href = "${hContext}/item/item_deview.do?itemNo="+itemNo;
	}
	

	function doSelectOne(itemNo) {
		$.ajax({
    		type: "GET",
    		url:"${hContext}/item/do_selectone.do",
    		async : true,
    		dataType:"html",
    		data:{	
    			"itemNo": itemNo
    		},
    		
    		success:function(data){//통신 성공
        		console.log("success data:"+data);

    			var parseData = JSON.parse(data);
    			
    			var name = parseData.name;
    			var price = parseData.price;
    			var finalPrice = parseData.finalPrice;
    			var production = parseData.production;
    			var weight = parseData.weight;
    			var expired = parseData.expired;
    			var detail = parseData.detail;
    			var discount = parseData.discount;
    			
    			document.getElementById("name").innerHTML = name;
    			document.getElementById("production").innerHTML = production;
    			document.getElementById("weight").innerHTML = weight;
    			document.getElementById("expired").innerHTML = expired;
    			document.getElementById("detail").innerHTML = detail;
    			
    			$("#itemPrice").empty();
    		  	var html = "";
    			if(price!=finalPrice){
    				
    				html+=" <span class='discount'>"+discount+"%</span>"; 
    				html+=" <span class='origin-price'>"+price+"</span>";
    				html+=" <h3 class='final-price'>"+finalPrice+"</h3> ";
        			
        			sell_price = parseData.finalPrice;
	    			$("#sell_price").val(sell_price);
	    			init(sell_price);
	    			
    			}else if(finalPrice == price ){
    				
    				html+=" <h3 class='final-price'>"+price+"</h3> ";

    				sell_price = parseData.price;
	    			$("#sell_price").val(sell_price);
	    			init(sell_price);
    				
    			}

    			$("#itemPrice").append(html);
    			
    			categoryNo = parseData.categoryNo;
    			console.log(categoryNo);
    			getRelatedList(categoryNo);

    			
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	},
        	complete:function(data){//성공/실패와 관계없이 수행!
        		console.log("complete:"+data);
        	}
        	
        	
    	});
		
		
	}
	

	function doRetrieveImage(itemNo){

		$.ajax({
	  		type: "GET",
	  		url:"${hContext}/image/do_retrieve.do",
	  		async : true,
	  		dataType:"html",
	  		data:{
	  			fromTb:1,
	  			fromNo:itemNo
	  		},
	  		success:function(data){//통신 성공
	  			
	  			$("#imageList").val(data);
	  			
	  			var parseData = JSON.parse(data);
	  			console.log(parseData);
	  			
	  		   $("#mainimg").empty();
	  		   $("#detailimg").empty();
	  		 	var htmL = "";
	  		  	var html = "";
	  			if(parseData.length > 0){ 
	  				
	  				$.each(parseData, function(i, value) {
	  					
		  		    	if(value.mainImage == 1){
		  		    		htmL +="<img src='${hContext}"+value.path + value.saveName + "' >";
		  		    	}else if(value.mainImage ==0){
		  		    		html +="<img src='${hContext}"+value.path + value.saveName + "' style='width: 950px; padding-bottom: 30px;'>";
		  		    	}
	  				});
	  				
	  			}
	  			$("#mainimg").append(htmL);
	  			$("#detailimg").append(html);
	  		    
	      	},
	      	error:function(data){//실패시 처리
	      		console.log("error:"+data);
	      	}
	      	
	  	});
		
		
	} 
		
	//장바구니 담기
	$("#addcart").on("click",function(){
		console.log("addcart click");
		
		amount = document.buy_form.amount.value;

		console.log(amount)				//구매 수량
		console.log(sum.value)			//결재 금액
		console.log($("#regId").val() )
		
		if ( "" == $("#regId").val() ){
			
			alert("회원전용입니다. 로그인을 먼저 해주세요.");
			return;
			
		}else if( null != $("#regId").val()  ){
			
			if(confirm("이 상품을 장바구니에 담으시겠습니까?")==false) return;
		}
		
		let url = "${hContext}/cart/do_insert.do";
		let parameters = {
				"memberId"	: $("#regId").val(),
				"itemNo"    : itemNo,
				"quantity"	: amount
		};
		
		let method = "POST";
		let async  = true;
		
		EClass.callAjax(url, parameters, method, async, function(data){

			console.log("data msgContents:"+data.msgContents);
			
			if("장바구니에 상품을 담았습니다."==data.msgContents)	{//등록 성공
			
				if(confirm(data.msgContents+"\n장바구니로 이동하시겠습니까?")==true)
					window.location.href ="${hContext}/cart/cart_list.do";
				
					else doSelectOne();
				
				
			}else{ //등록 실패
				if(confirm(data.msgContents+"\n장바구니로 이동하시겠습니까?")==true)
					window.location.href ="${hContext}/cart/cart_list.do";
			}
			
		}); 
		
	});
	
	
	
	//늘사는것 담기
	$("#addwish").on("click",function(){
		console.log("addwish click");
		
		if ( "" == $("#regId").val() ){
			
			alert("회원전용입니다. 로그인을 먼저 해주세요.");
			return;
			
		}else if( null != $("#regId").val()  ){
			
			if(confirm("이 상품을 늘사는것에 담으시겠습니까?")==false) return;
		}
		

		let url = "${hContext}/wishitem/do_insert.do";
		let parameters = {
			
				"itemNo"    : itemNo,
				"memberId"  : $("#regId").val()
		};
		
		let method = "POST";
		let async  = true;
		
		EClass.callAjax(url, parameters, method, async, function(data){

			console.log("data msgContents:"+data.msgContents);
			
			if("늘사는것에 상품을 담았습니다."==data.msgContents)	{//등록 성공
				
				if(confirm(data.msgContents+"\늘 사는 것으로 이동하시겠습니까?")==true)
					window.location.href ="${hContext}/wishitem/wishitem_list.do";
				
				else doSelectOne();
				
				
			}else{ //등록 실패
				if(confirm(data.msgContents+"\늘 사는 것으로 이동하시겠습니까?")==true)
					window.location.href ="${hContext}/wishitem/wishitem_list.do";
				
			}
			
		}); 
		
	});
	
	//삭제
	$("#deleteBtn").on("click", function(e){
		
		console.log(itemNo);
		if(confirm("삭제 하시겠습니까?")==false) return;
		
		$.ajax({
	  		type:"POST",
	  		url:"${hContext}/item/do_delete.do",
	  		asyn:"false",
	  		dataType:"html",
	  		data:{
	  			itemNo: itemNo
	  		},
	  		success:function(data){//통신 성공
	  			
	  			$.ajax({
	  		  		type: "GET",
	  		  		url:"${hContext}/image/do_delete.do",
	  		  		async: false,
	  		  		dataType:"html",
	  		  		data:{
	  		  			imageList: $("#imageList").val()
	  		  		},
	  		  		success:function(data){//통신 성공
	  		  			var parseData = JSON.parse(data);
	  		  			alert("상품이 삭제되었습니다.");
	  		  			window.location.href = "${hContext}/admin/admin_view.do";
	  		      	},
	  		      	error:function(data){//실패시 처리
	  		      		console.log("error:"+data);
	  		      	}
	  		      	
	  		  	});
	  			
	      	},
	      	error:function(data){//실패시 처리
	      		console.log("error:"+data);
	      	}
	      	
	  	});
		
	});
	
	//상품 수정 페이지로 이동
	$("#updateBtn").on("click", function(e){
		window.location.href = "${hContext}/item/item_mod.do?itemNo="+itemNo;
	});
	
	
	function init(sell_price) {
 		
		amount = document.buy_form.amount.value;
		document.buy_form.sum.value = sell_price;
		change();
		
	}

	function add() {
		hm = document.buy_form.amount;
		sum = document.buy_form.sum;
		let sellPrice = $("#sell_price").val();
		console.log("sellPrice:"+sellPrice);
		hm.value++;
		
		sum.value = parseInt(hm.value) * sell_price;
		
		
	}

	function del() {
		hm = document.buy_form.amount;
		sum = document.buy_form.sum;
		let sellPrice = $("#sell_price").val();
		console.log("sellPrice:"+sellPrice);
		if (hm.value > 1) {
			hm.value--;
			sum.value = parseInt(hm.value) * sell_price;
		}
	}

	function change() {

		hm = document.buy_form.amount;
		sum = document.buy_form.sum;

		if (hm.value < 0) {
			hm.value = 0;
		}
		sum.value = parseInt(hm.value) * sell_price;
	} 
	
</script>

</body>
</html>