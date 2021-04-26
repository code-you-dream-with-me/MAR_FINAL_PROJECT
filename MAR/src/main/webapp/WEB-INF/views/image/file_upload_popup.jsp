<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- core태그 -->
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 국제화 태그 -->
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!-- 현위치에 다른 파일의 내용을 포함하고 이후 jsp파일을 java파일로 변환 -->    
<html lang='ko'>

<head>
  <meta charset="UTF-8">
  <title>eclass_2021. 3. 15</title>
  <meta charset="utf-8">
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${hContext}/resources/js/jquery.min.js"></script>
  <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
  <script src="${hContext}/resources/js/bootstrap.min.js"></script>
  <link href="${hContext}/resources/assets/recipe/woogie.css" rel="stylesheet">
</head>

<body>
  <!-- contents -->
  <c:choose>
	<c:when test="${fromTb == 1}">
	   <span class="page_title_name" style="font-size: x-large;"><c:out value="Item Images Upload"></c:out></span>
	</c:when>
	<c:when test="${fromTb == 2}">
	   <span class="page_title_name" style="font-size: x-large;"><c:out value="Recipe Images Upload"></c:out></span>
	</c:when>
  </c:choose>
  <input type="button" class="button" id="file_upload" value="등록" style="margin-left: 200px;" />
  <hr>
  
	<!-- 레시피 or 상품등록 페이지에서 넘겨받은 값 -->
	<input type="hidden" name="fromTb" id="fromTb" value="${fromTb}" />

  <form action="${hContext}/image/do_upload.do" id="uploadFrm" id="uploadFrm" method="post" enctype="multipart/form-data" accept-charset="UTF-8" >
	
	<span class="page_title_name">이미지</span>
	<input type="file" class="text_box_main" name="file_list" id="file_list" multiple="multiple" style="width: 400px" />
  
  </form>
  <!-- //contents -->



  <!-- javascript -->
  <script type="text/javascript">

    //파일 json으로 받기(동기식)
    $("#file_upload").on("click",function(e){

      console.log('file_upload');
      
      var form = new FormData(document.getElementById("uploadFrm"));
      
      $.ajax({
          url: "${hContext}/image/do_upload.do",
          enctype: 'multipart/form-data',
          data: form, 
          //dataType: 'text',
          type: 'POST',
          async: 'false',
          processData: false,
          contentType: false,
          success: function(data) {
        	
    		alert("이미지 등록이 완료되었습니다.");

            window.opener.setSendChild(data);
            window.self.close();

          },
          error: function(jqXHR) {
            console.log('error');
          }
        });

    });
  	
  	</script>
  
  <!--// javascript -->
</body>

</html>