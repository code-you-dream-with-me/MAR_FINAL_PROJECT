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
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.82.0">
    <title>레시피 등록</title>

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
      body{
      }
    </style>


    <!-- 사용자 정의 스타일 입력 스타일시트 -->
    <link href="${hContext}/resources/assets/recipe/woogie.css" rel="stylesheet">

  </head>

  <body>
  <br/><br/><br/>
	<div class="container">
	  <div class="row g-5">	
		<div class="col-md-7 col-lg-8">
		  <p class="h1">레시피 제목</p>
		  <hr/>
		  
		  
		  <svg class="bd-placeholder-img figure-img img-fluid rounded" width="500" height="300" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 400x300" preserveAspectRatio="xMidYMid slice" focusable="false">
		    <rect width="100%" height="100%" fill="#868e96"></rect>
		    <text x="50%" y="50%" fill="#dee2e6" dy=".3em">이미지 넣을칸</text>
		  </svg>
		  
		  
		  
		  
		</div>
	  </div>	
	</div>
	
	<script src="${hContext}/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${hContext}/resources/assets/recipe/form-validation.js"></script>  
	
	<script type="text/javascript">
	
	</script>
  </body>

</html>