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
	   <h2><c:out value="Item Images Upload"></c:out></h2>
	</c:when>
	<c:when test="${fromTb == 2}">
	   <h2><c:out value="Recipe Images Upload"></c:out></h2>
	</c:when>
  </c:choose>
  <hr>
  <div>
    <input type="button" class="button" value="이미지 추가" id="file_add" style="margin-left: 7px; width: 100px; ">
    <input type="button" class="button" id="file_upload" value="등록" />
  </div>
  <form action="mar/upload/upload.do" id="uploadFrm" id="uploadFrm" method="post" enctype="multipart/form-data">
	<input type="hidden" value="${fromTb}" />
  </form>
  <!-- //contents -->



  <!-- javascript -->
  <script type="text/javascript">
    //파일선택 JavaScript-------------------------------------------------------------------------
    var count = 0; //count를 증가시키기

    //class는 아래와 같이 준다
    $(document).on('click', '.btn_dele_file', function(e) { //function의 e는 사용자 지정(event든 e든 아무거나 줘도 됨)
      console.log('btn_del_file');

      //$(this) : btn_dele_file을 말하는거
      $(this).parent().parent().remove();
    });

    $("#file_add").on("click", function(e) {
      //console.log("file_add click");
      count++; //count를 증가시키기 

      //이미 ""로 싸여있는 속성은 ''으로 해야 오류가 안남 (id이름이 달라야 오류가 안남)
      var html = "<div class='form-group'>";
      html += "<span class='page_title_name'>이미지 " + count + "</span>";
      html += "<div class='col-xs-8 col-sm-9 col-md-10 col-lg-10'>";
      html += "<input type='file' class='text_box_main' style='width: 400px;' name='file_" + count + "'/>";
      html += "<input type='button' class='button' style='width: 40px;' value='X'/>"
      html += "</div>";
      html += "</div>";
      console.log(html);
      //.append : id가 uploadFrm인 태그 아래에 추가 
      $("#uploadFrm").append(html);

    });
    //파일선택 JavaScript-------------------------------------------------------------------------

    //파일 json으로 받기(동기식)
    $("#file_upload").on('click', function() {

      console.log('file_upload');
      var form = new FormData(document.getElementById('uploadFrm'));
      $.ajax( //화면은 가만히 있고 업데이트 치고 싶을때
        {
          url: "${hContext}/image/do_insert.do",
          data: form, //form자체를 데이터로 넘김 (서블릿에서 json으로 데이터 받는것으로 해줘야 데이터 화면에 나옴)
          dataType: 'text',
          type: 'POST',
          async: 'false',
          processData: false,
          contentType: false,
          success: function(response) {
            alert("이미지 등록 성공!");
            console.log("response: " + response);
            var jsonObj = JSON.parse(response);
            console.log('jsonObj' + jsonObj);

            window.self.close();

            window.opener.setSendChild(jsonObj);

          },
          error: function(jqXHR) {
            console.log('error');
          }
        }
      );


    });
  </script>
  <!--// javascript -->
</body>

</html>