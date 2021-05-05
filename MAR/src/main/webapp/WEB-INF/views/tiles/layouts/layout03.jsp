<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<!-- layout css -->
<link rel="stylesheet" href="${hContext}/resources/css/layout.css">

<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
<!-- favicon -->
<link rel="shortcut icon" href="${hContext}/resources/image_source/markit_favicon.ico" type="image/x-icon">
<link rel="icon" href="${hContext}/resources/image_source/markit_favicon.ico" type="image/x-icon">


<title><tiles:getAsString name="title"/></title>

<style type="text/css">
/*container01 컨테이너 부분*/
.container01 {
    width: 70%; /*672px%960px*/
    height: auto; /*화면 넣을때 auto로 바꿔주세요*/
    margin: 0 auto;
    padding: 20px 2.083333%; /*20px%960px 가변패딩*/
    /*표시선▼*/
    /*border: 2px solid #2F5597;*/
}

</style>
</head>
<body>

	<div id="wrap">
		<!-- header -->
		<tiles:insertAttribute name="header"/>
		<!--// header -->
		
		<!-- container -->
		<tiles:insertAttribute name="container"/>
		<!--// container -->
		
		<!-- footer -->
		<tiles:insertAttribute name="footer"/>
		<!--// footer -->
	</div>

</body>
</html>