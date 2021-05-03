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

<!-- 부트스트랩 -->
<link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="${hContext}/resources/css/bootstrap-theme.min.css">

<!-- layout css -->
<link rel="stylesheet" href="${hContext}/resources/css/layout.css">

<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
<!-- icon -->
<link href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined" rel="stylesheet">
<!-- favicon -->
<link rel="shortcut icon" href="${hContext}/resources/image_source/markit_favicon.ico" type="image/x-icon">
<link rel="icon" href="${hContext}/resources/image_source/markit_favicon.ico" type="image/x-icon">

<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${hContext}/resources/js/jquery.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${hContext}/resources/js/bootstrap.min.js"></script>
<!-- js간략화 -->
<script src="${hContext}/resources/js/eclass.js"></script><!-- ajax js -->
<script src="${hContext}/resources/js/eutil.js"></script><!-- 빈칸인식js -->

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
		<!-- manubar -->
		<tiles:insertAttribute name="menubar"/>
		<!--// manubar -->
		
		<!-- container -->
		<tiles:insertAttribute name="container"/>
		<!--// container -->
		
		<!-- footer -->
		<tiles:insertAttribute name="footer"/>
		<!--// footer -->
	</div>
	
	<script type="text/javascript">
		
	</script>
	
</body>
</html>