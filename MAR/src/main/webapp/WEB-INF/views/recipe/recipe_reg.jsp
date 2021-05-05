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
    </style>


    <!-- 사용자 정의 스타일 입력 스타일시트 -->
    <link href="${hContext}/resources/assets/recipe/woogie.css" rel="stylesheet">

  </head>
  <body class="bg-light">
  <div class="container01">  
	<div class="container">
	  <main>
	    <div class="py-5 text-center">
	      <%-- <img class="d-block mx-auto mb-4" src="${hContext}/resources/image_source/good_food-wallpaper-800x480.jpg" alt="" style="height: 100px; border-radius: 10px;" > --%>
	      <h2>레시피 등록</h2><br/>
	      <p class="lead">레시피 등록화면 입니다. 안내에 따라 각 항목을 정확히 작성바랍니다.</p>
	    </div>
	
	    <div class="row g-5">
	
	      <!-- 이미지 등록 칸으로 만들 곳 -->
	      <div class="col-md-5 col-lg-4 order-md-last">
	
	        <form name="image_frm" id="image_frm">
	        
	          <input type="hidden" name="fromTb" id="fromTb" value="2"/>
				
	          <h4 class="d-flex justify-content-between align-items-center mb-3">
	            <span class="text-primary">이미지</span>
	            
	            <!-- 등록한 이미지가 몇 개인지 보여 줄 칸 -->
	            <div id="imageCntView"></div>
	            
	            <button type="button" class="btn btn-outline-primary me-2" onclick="showPopup(this.form);">추가</button>
	          
	          </h4>
				
			  <!-- 업로드한 이미지 목록 출력 -->	
	          <div id="uploadImageView" name="uploadImageView">
	          </div>
	
					
			  		
	
	        </form>
	      </div>
	      <!--// 이미지 등록 칸으로 만들 곳 -->
	
	      <div class="col-md-7 col-lg-8">
	        <form class="needs-validation" name="recipe_frm" id="recipe_frm" novalidate>
	          <div class="row g-3">

				<!-- 변경필요 -->
				<input type="hidden" name="regId" id="regId" value=""/>
				<input type="hidden" name="imageList" id="imageList" value=""/>
				<input type="hidden" name="whichMainImage" id="whichMainImage" value="0"/>
				
	
	            <div class="col-12">
	              <label for="title" class="form-label">레시피 이름<span class="text-muted"></span></label>
	              <input type="text" class="form-control" name="title" id="title" placeholder="레시피 이름을 입력해 주세요" required>
	              <div class="invalid-feedback">
	                레시피 이름을 입력 해주세요.
	              </div>
	            </div>
	
	            <div class="col-12">
	              <label for="contents" class="form-label">레시피 내용</label>
	              <textarea class="form-control" name="contents" id="contents" rows="10" placeholder="레시피 순서는 아라비아 숫자를 사용해 주세요" required></textarea>
	              <div class="invalid-feedback">
	                레시피 내용을 입력 해주세요.
	              </div>
	            </div>
	
	            <div class="col-12">
	              <label for="ingredients" class="form-label">레시피 재료<span class="text-muted"></span></label>
	              <input type="text" class="form-control" name="ingredients" id="ingredients" placeholder="레시피 재료를 입력해주세요. 각 재료는 꼭 쉼표(,) 로 구분해야 합니다" required>
	              <div class="invalid-feedback">
	                레시피 재료를 입력 해주세요.
	              </div>
	            </div>
	            <div class="col-12">
	              <label for="urlAddr" class="form-label">유튜브 영상 주소<span class="text-muted"></span></label>
	              <input type="text" class="form-control" name="urlAddr" id="urlAddr" placeholder="유튜브 영상의 주소를 입력바랍니다." required>
	              <div class="invalid-feedback">
	                레시피 재료를 입력 해주세요.
	              </div>
	            </div>
	          </div>
	
	          <hr class="my-4">
	
	        </form>
	          <button class="w-100 btn btn-primary btn-lg" name="register_button" id="register_button" type="submit">레시피 등록</button>
	      </div>
	    </div>
	  </main>
	</div>
  <br/><br/><br/>
  
	<script src="${hContext}/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${hContext}/resources/assets/recipe/form-validation.js"></script>
	    
  </div>
  </body>

  <script type="text/javascript">

  $(document).ready(function() {
      
	  
      var member = {
    		  memberId: '${sessionScope.member.memberId}', 
    		  pw: '${sessionScope.member.pw}', 
    		  name: '${sessionScope.member.name}', 
    		  phone: '${sessionScope.member.phone}', 
    		  address: '${sessionScope.member.address}', 
    		  auth: '${sessionScope.member.auth}', 
    		  regDt: '${sessionScope.member.regDt}', 
    		  modDt: '${sessionScope.member.modDt}'
      };
      
      if(member.memberId == ""){
    	  alert("관리자가 아니면 불가능 합니다.");
    	  window.location.href = "${hContext}/member/sign_in_view.do";
      }
      
      $("#regId").val(member.memberId);
      
    });
  
  //팝업 창 띄우기
  function showPopup(frm) { 
	console.log('showPopup()');
	$("#whichMainImage").val('');
	
	var title ="파일 업로드";
	var option  ="toolbar=0,scrollbars=no,resizable=no,status=yes,width=500,height=300,left=300,top=50";
	
	window.open("",title,option);
	
	frm.target = title;
	frm.method = "get";
	frm.action = "<c:url value='/image/image_view.do' />";
	frm.submit();
	}
  
  $("#register_button").on("click", function(e){

	console.log($("#regId").val());
	console.log($("#title").val());
	console.log($("#contents").val());
	console.log($("#ingredients").val());
	console.log($("#urlAddr").val());
	  
  	$.ajax({
  		type: "GET",
  		url:"${hContext}/recipe/do_insert.do",
  		asyn:"false",
  		dataType:"html",
  		data:{
  			regId: $("#regId").val(),
  			title: $("#title").val(),
  			contents: $("#contents").val(),
  			ingredients: $("#ingredients").val(),
  			urlAddr: $("#urlAddr").val()	
  		},
  		success:function(data){//통신 성공
      		var message = JSON.parse(data);
  			console.log(message.msgContents);
  			
  			var mainImageNum = $("#whichMainImage").val();
  			if("" == mainImageNum){
  				mainImageNum = "0";
  			}
  			
  			$.ajax({
  		  		type: "GET",
  		  		url:"${hContext}/image/do_insert.do",
  		  		asyn:"false",
  		  		dataType:"html",
  		  		data:{
  		  			imageList: $("#imageList").val(),
  		  			fromTb: $("#fromTb").val(),
  		  		    MainImage: mainImageNum
  		  		},
  		  		success:function(data){//통신 성공
  		      		var message = JSON.parse(data);
  		  			console.log(message.msgContents);
  		  			alert("레시피 등록이 완료되었습니다.");
  		  			window.location.href = "${hContext}/admin/admin_view.do";
  		      	},
  		      	error:function(data){//실패시 처리
  		      		console.log("error:"+data);
  		      	},
  		      	complete:function(data){//성공/실패와 관계없이 수행!
  		      		console.log("complete:"+data);
  		      	}
  		  	});
  			
      	},
      	error:function(data){//실패시 처리
      		console.log("error:"+data);
      	},
      	complete:function(data){//성공/실패와 관계없이 수행!
      		console.log("complete:"+data);
      	}
  	});
  	
  });
  	
  
	//popup에서 가져온 값 처리
	function setSendChild(param) {
		
		var jsonString = JSON.stringify(param);
		$("#imageList").val(jsonString); 
		
		var tmpImageList = JSON.parse(jsonString);
		//console.log(tmpImageList);
		
		var cnt = 0;
		
		$("#uploadImageView").empty();
		var html = "";
		html += "<ul class='list-group mb-3'>";
		
		$.each(tmpImageList, function(i, value) {
			
			if(cnt == 0){
				html += "<li class='list-group-item d-flex justify-content-between lh-sm'> "+ value.orgName +" <input type = 'radio' class='form-check-input' name = 'selectImage' value = '"+i+"' checked='checked' onclick='clickWhichMainImage("+i+");'></li>";
			} else {
				html += "<li class='list-group-item d-flex justify-content-between lh-sm'> "+ value.orgName +" <input type = 'radio' class='form-check-input' name = 'selectImage' value = '"+i+"' onclick='clickWhichMainImage("+i+");' ></li>";
			}
			
			cnt += 1;
		});
		
		html += "</ul>";
		
		$("#uploadImageView").append(html);
		
		$("#imageCntView").empty();
		var html2 = "<span class='badge bg-primary rounded-pill'>"+cnt+"</span>";
		$("#imageCntView").append(html2);
		
		
	}
    
	function clickWhichMainImage(i){
		console.log("clickWhichMainImage"+i);
		$("#whichMainImage").val(i);
	}
	
	
	
  </script>
  

</html>
