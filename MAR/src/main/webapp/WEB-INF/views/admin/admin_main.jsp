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
    <title>관리자 페이지</title>

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
    </style>

    
    <!-- Custom styles for this template -->
    <link href="${hContext}/resources/assets/admin/cheatsheet.css" rel="stylesheet">

  </head>
  <body class="bg-light">
  
  	<!-- header -->
	<header class="bd-header bg-dark py-3 d-flex align-items-stretch border-bottom border-dark">
	  <div class="container-fluid d-flex align-items-center">
	    <h1 class="d-flex align-items-center fs-4 text-white mb-0">
	      <img src="${hContext}/resources/assets/brand/bootstrap-logo-white.svg" width="38" height="30" class="me-3" alt="Bootstrap">
	      Cheatsheet
	    </h1>
	    <a href="../examples/cheatsheet-rtl/" class="ms-auto link-light" hreflang="ar">RTL cheatsheet</a>
	  </div>
	</header>
	<!--// header -->
	
	<!-- 오른쪽 네비게이션 -->
	<aside class="bd-aside sticky-xl-top text-muted align-self-start mb-3 mb-xl-5 px-2">
	  <h2 class="h6 pt-4 pb-3 mb-4 border-bottom">On this page</h2>
	  <nav class="small" id="toc">
	    <ul class="list-unstyled">
	      <li class="my-2">
	        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#member-collapse" aria-controls="contents-collapse">회원</button>
	        <ul class="list-unstyled ps-3 collapse" id="member-collapse">
	          <li><a class="d-inline-flex align-items-center rounded" href="#member_list">회원 목록</a></li>
	        </ul>
	      </li>
	      <li class="my-2">
	        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#product-collapse" aria-controls="forms-collapse">상품</button>
	        <ul class="list-unstyled ps-3 collapse" id="product-collapse">
	          <li><a class="d-inline-flex align-items-center rounded" href="#product_list">상품 목록</a></li>
	        </ul>
	      </li>
	      <li class="my-2">
	        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#order-collapse" aria-controls="forms-collapse">주문</button>
	        <ul class="list-unstyled ps-3 collapse" id="order-collapse">
	          <li><a class="d-inline-flex align-items-center rounded" href="#order_list">주문 목록</a></li>
	        </ul>
	      </li>
	      <li class="my-2">
	        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#recipe-collapse" aria-controls="components-collapse">레시피</button>
	        <ul class="list-unstyled ps-3 collapse" id="recipe-collapse">
	          <li><a class="d-inline-flex align-items-center rounded" href="#recipe_list">레시피 목록</a></li>
	        </ul>
	      </li>
	    </ul>
	  </nav>
	</aside>
	<!--// 오른쪽 네비게이션 -->
	
	<div class="bd-cheatsheet container-fluid bg-body">
	  <section id="content">
	    <h2 class="sticky-xl-top fw-bold pt-3 pt-xl-5 pb-2 pb-xl-3">관리자</h2>
	    
	    <!-- 회원 목록 -->
	    <article class="my-3" id="member_list">
	    
	      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
	        <h3>회원 목록</h3>
	      </div>
		  
	      <div class="bd-example">
		    <table class="table table-hover">
	          <thead>
	          <tr>
	            <th scope="col">Class</th>
	            <th scope="col">Heading</th>
	            <th scope="col">Heading</th>
	          </tr>
	          </thead>
	          <tbody>
	          <tr>
	            <th scope="row">Default</th>
	            <td>Cell</td>
	            <td>Cell</td>
	          </tr>
	          
	          <tr class="table-primary">
	            <th scope="row">Primary</th>
	            <td>Cell</td>
	            <td>Cell</td>
	          </tr>
	          <tr class="table-secondary">
	            <th scope="row">Secondary</th>
	            <td>Cell</td>
	            <td>Cell</td>
	          </tr>
	          <tr class="table-success">
	            <th scope="row">Success</th>
	            <td>Cell</td>
	            <td>Cell</td>
	          </tr>
	          <tr class="table-danger">
	            <th scope="row">Danger</th>
	            <td>Cell</td>
	            <td>Cell</td>
	          </tr>
	          <tr class="table-warning">
	            <th scope="row">Warning</th>
	            <td>Cell</td>
	            <td>Cell</td>
	          </tr>
	          <tr class="table-info">
	            <th scope="row">Info</th>
	            <td>Cell</td>
	            <td>Cell</td>
	          </tr>
	          <tr class="table-light">
	            <th scope="row">Light</th>
	            <td>Cell</td>
	            <td>Cell</td>
	          </tr>
	          <tr class="table-dark">
	            <th scope="row">Dark</th>
	            <td>Cell</td>
	            <td>Cell</td>
	          </tr>
	          </tbody>
	        </table>

	      </div>
	      
	      <br/><br/>
	      
	      <div class="bd-example">
		    <table class="table table-hover">
	          <thead>
	          	<tr>
	              <th scope="col" width="25%">이메일</th>
	              <th scope="col" width="15%">이름</th>
	              <th scope="col" width="45%">주소</th>
	              <th scope="col" width="15%">전화번호</th>
	          	</tr>
	          </thead>
	          <tbody>
	          	<tr>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	          	</tr>
	          
	          
	          </tbody>
	        </table>
	      </div>
	      
	    </article>
	    
	    
	    <!-- 상품 목록 -->
	    <article class="my-3" id="product_list">
	    
	      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
	        <h3>상품 목록</h3>
	      </div>
	      
		  	
		  	
	      <div class="bd-example">
	        <table class="table table-hover">
	          <thead>
	          	<tr>
	              <th scope="col" width="10%">상품번호</th>
	              <th scope="col" width="30%">상품명</th>
	              <th scope="col" width="20%">종류</th>
	              <th scope="col" width="10%">가격</th>
	              <th scope="col" width="10%">할인률</th>
	              <th scope="col" width="10%">최종가격</th>
	              <th scope="col" width="10%">판매량</th>
	          	</tr>
	          </thead>
	          <tbody>
	          	<tr>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	          	</tr>
	          
	          
	          </tbody>
	        </table>
	      </div>
	    </article>
	    
	    <!-- 주문 목록 -->
	    <article class="my-3" id="order_list">
	    
	      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
	        <h3>주문 목록</h3>
	      </div>
	      
		  	
		  	
	      <div class="bd-example">
	        <table class="table table-hover">
	          <thead>
	          	<tr>
	              <th scope="col" width="10%">주문번호</th>
	              <th scope="col" width="30%">주문자</th>
	              <th scope="col" width="25%">상품명</th>
	              <th scope="col" width="15%">금액</th>
	              <th scope="col" width="10%">주문상태</th>
	              <th scope="col" width="10%">결제일</th>
	          	</tr>
	          </thead>
	          <tbody>
	          	<tr>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	          	</tr>
	          
	          
	          </tbody>
	        </table>
	      </div>
	    </article>
	    
	    
	    <!-- 레시피 목록 -->
	    <article class="my-3" id="recipe_list">
	    
	      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
	        <h3>레시피 목록</h3>
	      </div>
		  
	      <div class="bd-example">
	        <table class="table table-hover">
	          <thead>
	          	<tr>
	              <th scope="col" width="10%">번호</th>
	              <th scope="col" width="55%">레시피 제목</th>
	              <th scope="col" width="25%">작성자</th>
	              <th scope="col" width="10%">조회수</th>
	          	</tr>
	          </thead>
	          <tbody>
	          	<tr>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	              <td>Cell</td>
	          	</tr>
	          
	          
	          </tbody>
	        </table>

	      </div>
	    </article>
	    
	  </section>  
	</div>    
    
	
	<script src="${hContext}/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
  	<script src="${hContext}/resources/assets/admin/cheatsheet.js"></script>
  </body>

  <script type="text/javascript">
  	
  </script>

</html>
