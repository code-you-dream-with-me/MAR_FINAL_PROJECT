<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일         수정자      수정내용
    -----       -----  -------------------------------------------
    2021. 4. 21.             최초작성 
    
    author woogieReal
    since 2020.11.23
    Copyright (C) by woogieReal All right reserved.
*/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- core태그 -->
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 국제화 태그 -->
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../cmn/common.jsp" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.82.0">
    <title>레시피 조회</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/checkout/">

	<script src="${hContext}/resources/js/jquery.min.js"></script>
	<script src="${hContext}/resources/js/bootstrap.min.js"></script>

    <!-- Bootstrap core CSS -->
	<%-- <link href="${hContext}/resources/assets/dist/css/bootstrap.min.css" rel="stylesheet"> --%>

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      table {
      	border-top: 3px solid black;
      }
      tr,td,th {
        line-height: 30px;
        border: 0.1em solid #f5f5f5;
        padding: 10px 10px 10px 30px;
      }
      th {
      	background-color: #f5f5f5;
      }
      textarea {
      	border: none; 
        resize: none; 
        font-size: large;
        width: 950px;
      }
      textarea:focus  {
        outline: none;
      }
      .recipe_item_entire_div {
      }
      .recipe_item_individual_div {
      	outline: 0.3px solid lightgray;
      	width: 200px;
      	height: 300px;
      	float: left;
      	margin: 0px 15px 20px 15px;
      }
      .recipe_item_image_div {
      	width: 200px;
      	height: 200px;
      	margin: 0px;
      	float: left;
      }
      .recipe_item_detail_div {
      	width: 200px;
      	height: 85px;
      	margin: 15px 0px 0px 0px;
      	float: left;
      	line-height: 30px;
      }
    </style>


    <!-- 사용자 정의 스타일 입력 스타일시트 -->
    <link href="${hContext}/resources/assets/recipe/woogie.css" rel="stylesheet">

  </head>

  <body>
  <br/><br/><br/>
  <div class="container01">
	<div class="container">
	  <div class="row g-5">	
		  
		  <input type="hidden" id="imageList" value="" />
  	  	  <input type="hidden" id="recipeNo" name="recipeNo" /> 
		  
		  <div class="bd-example" id="btnDiv" style="margin-bottom: 20px;">
		  	<button type="button" class="btn btn-primary" id="reicpeDeleteBtn" style="float: right; margin-right: 0px;">삭제</button>
		  	<button type="button" class="btn btn-primary" id="reicpeUpdateBtn" style="float: right; margin-right: 10px;">수정</button>
		  </div>
		  <br/>
		  
		  <table style="width: 100%; margin: 30px 0px;">
		  	<thead>
			  <tr>
			  	<th>제목</th>
			  	<td colspan="3" id="recipeTitle"></td>
			  </tr>
		  	</thead>
		  	<tbody>
			  <tr>
			  	<th>작성자</th>
			  	<td colspan="3" id="recipeRegId"></td>
			  </tr>
			  <tr>
			  	<th scope="col" width="15%">작성일</th>
			  	<td scope="col" width="20%" id="recipeRegDt">날짜</td>
			  	<th scope="col" width="15%">조회수</th>
			  	<td scope="col" width="50%" id="recipeReadCnt">조회수 숫자</td>
			  </tr>
		  	</tbody>
		  </table>
			
		  <div id="recipeUrlAddr"></div>
		  
		  <div id="recipeImage"></div>
		  
		  <textarea id="recipeContents" rows="80" readonly="readonly"></textarea>
		  
		  <h4>Recipe item</h4>
		  <hr/>
		  <div class="recipe_item_entire_div" id = "recipe_item">
		  </div>
		  
		  
	  </div>	
	</div>
    <br/><br/><br/><br/>
	
	<script src="${hContext}/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${hContext}/resources/assets/recipe/form-validation.js"></script>  
	
	<script type="text/javascript">
	
	//전역변수로 선언
	var recipeNo = 0;
	
	$(document).ready(function() {
	    recipeNo = getParameter("recipeNo");
	    doSelectOne();
	    doRetrieveImage();
	    doShowRelevantItem();
	    
	    var member = {
	  		  memberId: '${sessionScope.member.memberId}', 
	   		  auth: '${sessionScope.member.auth}'
	    };
	    
	    if(member.auth != '1'){ $("#btnDiv").empty(); }
	    
	    console.log("imageList:"+$("#imageList").val());
	    
	});
	
	
	function getParameter(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	function doSelectOne(no){
		
		$.ajax({
		  		type: "GET",
		  		url:"${hContext}/recipe/do_select.do",
		  		asyn:"false",
		  		dataType:"html",
		  		data:{
		  			recipeNo:recipeNo
		  		},
		  		success:function(data){//통신 성공
		  			var parseData = JSON.parse(data);
		  		
		  			//객체의 프로퍼티
		  		    var recipe = {
		  		    		recipeNo : parseData.recipeNo,
		  		    		regId : parseData.regId,
		  		    		title : parseData.title,
		  		    		contents : parseData.contents,
		  		    		readCnt : parseData.readCnt,
		  		    		ingredients : parseData.ingredients,
		  		    		urlAddr : parseData.urlAddr,
		  		    		regDt : parseData.regDt,
		  		    		modDt : parseData.modDt
		  		    };
		  		    
		  		    $("#recipeNo").val(recipe.recipeNo);
		  		    document.getElementById("recipeTitle").innerHTML = recipe.title;
		  		    document.getElementById("recipeRegId").innerHTML = recipe.regId;
		  		    document.getElementById("recipeRegDt").innerHTML = recipe.regDt;
		  		    document.getElementById("recipeReadCnt").innerHTML = recipe.readCnt;
		  		    document.getElementById("recipeUrlAddr").innerHTML = "<iframe width='950px' height='540px' style='margin-bottom: 15px;' src='"+ recipe.urlAddr +"' title='YouTube video player'  allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>";
		  		    document.getElementById("recipeContents").innerHTML = recipe.contents;
		  		    
		      	},
		      	error:function(data){//실패시 처리
		      		console.log("error:"+data);
		      	}
		      	
		  	});
	}
	
	function doShowRelevantItem(){
		
		$.ajax({
	  		type: "GET",
	  		url:"${hContext}/recipe/do_show_relevant_item.do",
	  		asyn:"false",
	  		dataType:"html",
	  		data:{
	  			recipeNo:recipeNo
	  		},
	  		success:function(data){//통신 성공
	  			
	  			var parseData = JSON.parse(data);
	  			console.log(parseData);
	  			
	  			
	  			
	  			
	  			var SimpleItemNo = 0;
	  			var SimpleItemName = "";
	  			var SimpleItemPrice = 0;
	  			var SimpleItemImageNo = 0;
	  			var SimpleItemImagePath = "";
	  			var SimpleItemImageName = "";
	  		    
				var html = "";
	  			
				if(parseData.length > 0){ 
	  				
	  				$.each(parseData, function(i, value) {
	  					
	  					
	  					SimpleItemNo = value.SimpleItemNo;
	  					SimpleItemName = value.SimpleItemName;
	  					SimpleItemPrice = value.SimpleItemPrice;
	  					SimpleItemImageNo = value.SimpleItemImageNo;
	  					SimpleItemImagePath = value.SimpleItemImagePath;
	  					SimpleItemImageName = value.SimpleItemImageName;
	  					
	  					
	  					console.log(value);
	  					
	  					html += "	<div class='recipe_item_individual_div' onclick='selectItem("+SimpleItemNo+");' >         ";
	  					html += "	  <div class='recipe_item_image_div'>      ";
	  					html += "     <img src='${hContext}"+SimpleItemImagePath+SimpleItemImageName+"' style='width: 200px; height: 200px; object-fit: cover;'/> "
	  					html += "     </div> "
	  					html += "	  <div class='recipe_item_detail_div' style='text-align: center;'>     ";
	  					html += "       <span style='font-weight: bold;'>"+SimpleItemName+"</span><br/> "
	  					html += "       <span>"+SimpleItemPrice+" 원</span> "
	  					html += "     </div>"
	  					html += "	</div>                                           ";
	  					
	  				});
	  				
	  			}
	  			
				
				$("#recipe_item").append(html);
	  		    
	      	},
	      	error:function(data){//실패시 처리
	      		console.log("error:"+data);
	      	}
	      	
	  	});
		
	}
	
	function selectItem(SimpleItemNo) {
		console.log("selectItem: "+SimpleItemNo);
		window.location.href = "${hContext}/item/item_deview.do?itemNo="+SimpleItemNo;
	}
	
	
	function doRetrieveImage(){
		$.ajax({
	  		type: "GET",
	  		url:"${hContext}/image/do_retrieve.do",
	  		asyn:"false",
	  		dataType:"html",
	  		data:{
	  			fromTb:2,
	  			fromNo:recipeNo
	  		},
	  		success:function(data){//통신 성공
	  			
	  			var parseData = JSON.parse(data);
	  			console.log(parseData);
	  			
	  			$("#imageList").val(data);
	  			//console.log($("#imageList").val());
	  			
	  		    var html = "";
	  		    
	  			if(parseData.length > 0){ 
	  				
	  				$.each(parseData, function(i, value) {
	  					
		  		    	html += "<img src='${hContext}"+value.path + value.saveName + "' style='width: 950px; padding-bottom: 30px;'>";
	  					
	  				});
	  				
	  			}
	  			
	  			$("#recipeImage").append(html);
	  		    
	      	},
	      	error:function(data){//실패시 처리
	      		console.log("error:"+data);
	      	}
	      	
	  	});
	}
	
	
	$("#reicpeUpdateBtn").on("click", function(e){
		window.location.href = "${hContext}/recipe/recipe_view3.do?recipeNo="+recipeNo;
	});
	
	//reicpeDeleteBtn
	$("#reicpeDeleteBtn").on("click", function(e){
		
		if(confirm("삭제 하시겠습니까?")==false)return;
		
		$.ajax({
	  		type: "GET",
	  		url:"${hContext}/recipe/do_delete.do",
	  		asyn:"false",
	  		dataType:"html",
	  		data:{
	  			recipeNo: recipeNo
	  		},
	  		success:function(data){//통신 성공
	  			
	  			console.log("imageList:"+$("#imageList").val());
	  			
	  			$.ajax({
	  		  		type: "GET",
	  		  		url:"${hContext}/image/do_delete.do",
	  		  		asyn:"false",
	  		  		dataType:"html",
	  		  		data:{
	  		  			imageList: $("#imageList").val()
	  		  		},
	  		  		success:function(data){//통신 성공
	  		  			var parseData = JSON.parse(data);
	  		  			alert(parseData.msgContents);
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
	
	</script>
  </div>
  </body>

</html>