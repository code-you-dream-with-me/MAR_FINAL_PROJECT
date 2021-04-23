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
	<link href="${hContext}/resources/assets/dist/css/bootstrap.min.css" rel="stylesheet">

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
      textarea:focus  {
        outline: none;
      }
    </style>


    <!-- 사용자 정의 스타일 입력 스타일시트 -->
    <link href="${hContext}/resources/assets/recipe/woogie.css" rel="stylesheet">

  </head>

  <body>
  <br/><br/><br/>
	<div class="container">
	  <div class="row g-5">	
		  
		  
		  <table>
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
		  
		  
		  <textarea id="recipeContents" rows="50" style="border: none; resize: none; font-size: large;" readonly="readonly"></textarea>
		  
		  
		  
	  </div>	
	</div>
    <br/><br/><br/><br/>
	
	<script src="${hContext}/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${hContext}/resources/assets/recipe/form-validation.js"></script>  
	
	<script type="text/javascript">
	$(document).ready(function() {
		console.log("1.document:최초수행!");
	    var recipeNo = getParameter("recipeNo");
	    doSelectOne(recipeNo);
	    doRetrieveImage(recipeNo);
	});
	
	
	function getParameter(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	function doSelectOne(no){
		
		console.log("doSelectOne");
		console.log("no: "+no);
		
		$.ajax({
		  		type: "GET",
		  		url:"${hContext}/recipe/do_select.do",
		  		asyn:"false",
		  		dataType:"html",
		  		data:{
		  			recipeNo:no
		  		},
		  		success:function(data){//통신 성공
		  			var parseData = JSON.parse(data);
		  			console.log(parseData.title);
		  			
		  		    var title = parseData.title;
		  		    var regId = parseData.regId;
		  		    var regDt = parseData.regDt;
		  		    var readCnt = parseData.readCnt;
		  		    var urlAddr = parseData.urlAddr;
		  		    var contents = parseData.contents;
		  		    
		  		    document.getElementById("recipeTitle").innerHTML = title;
		  		    document.getElementById("recipeRegId").innerHTML = regId;
		  		    document.getElementById("recipeRegDt").innerHTML = regDt;
		  		    document.getElementById("recipeReadCnt").innerHTML = readCnt;
		  		    document.getElementById("recipeUrlAddr").innerHTML = "<iframe width='950px' height='540px' src='"+ urlAddr +"' title='YouTube video player'  allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>";
		  		    document.getElementById("recipeContents").innerHTML = contents;
		  		    
		  		    
		      	},
		      	error:function(data){//실패시 처리
		      		console.log("error:"+data);
		      	}
		      	
		  	});
	}
	
	function doRetrieveImage(no){
		
		console.log("doRetrieveImage");
		console.log("no: "+no);
		
		$.ajax({
	  		type: "GET",
	  		url:"${hContext}/image/do_retrieve.do",
	  		asyn:"false",
	  		dataType:"html",
	  		data:{
	  			fromTb:2,
	  			fromNo:no
	  		},
	  		success:function(data){//통신 성공
	  			var parseData = JSON.parse(data);
	  			console.log(parseData);
	  			
	  		    //var title = parseData.title;
	  		    $("#recipeImage").empty();
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
	
	
	
	
	</script>
  </body>

</html>