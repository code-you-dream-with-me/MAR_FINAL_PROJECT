<%--
	Class Name : 
	Description :
	Modification information
	
	수정일		  수정자	  수정내용
	----		  ----	  -------------------
	2021. 4. 21.  hansol  최초작성
	
	author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- core태그 -->
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 국제화 태그 -->
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.82.0">
    
    <title>상품 수정</title>

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
	      <img class="d-block mx-auto mb-4" src="${hContext}/resources/image_source/item.jpg" alt="" style="height: 100px; border-radius: 10px;" >
	      <h2>상품 수정</h2><br/>
	      <p class="lead">상품 수정화면 입니다. 안내에 따라 각 항목을 정확히 작성바랍니다.</p>
	    </div>
	
	    <div class="row g-5">
	
	      <!-- 상품 등록 칸으로 만들 곳 -->
	      <div class="col-md-5 col-lg-4 order-md-last">
	
	        <form name="image_frm" id="image_frm">
	        
	          <input type="hidden" name="fromTb" id="fromTb" value="1"/>
				
	          <h4 class="d-flex justify-content-between align-items-center mb-3">
	            <span class="text-primary">이미지</span>
	            
	            <!-- 등록한 이미지가 몇 개인지 보여 줄 칸 -->
	            <span class="badge bg-primary rounded-pill">3</span>
	
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
	        <form class="needs-validation" name="item_frm" id="item_frm" novalidate>
	          <div class="row g-3">

				<!-- 변경필요 -->
				<input type="hidden" name="regId" id="regId" value="${sessionScope.member.memberId}"/>
				<input type="hidden" name="imageListDel" id="imageListDel" value=""/>
				<input type="hidden" name="imageList" id="imageList" value=""/>
				<input type="hidden" name="itemNo" id="itemNo" value=""/>
				<input type="hidden" name="whichMainImage" id="whichMainImage" value="0"/>
				
	            <div class="col-12">
	              <label for="title" class="form-label">상품명<span class="text-muted"></span></label>
	              <input type="text" class="form-control" name="name" id="name" placeholder="상품의 이름을 입력해 주세요" required>
	              <div class="invalid-feedback">
	                상품의 이름을 입력 해주세요.
	              </div>
	            </div>
	            
	            <div class="col-12">
	              <label for="title" class="form-label">가격<span class="text-muted"></span></label>
	              <input type="text"  numberOnly class="form-control" name="price" id="price" placeholder="상품의 가격을 입력해 주세요 (단위: 원)" required>
	              <div class="invalid-feedback">
	                상품의 가격을 입력 해주세요.
	              </div>
	            </div>
	            
	            <div class="col-12">
	              <label for="title" class="form-label">생산지<span class="text-muted"></span></label>
	              <input type="text" class="form-control" name="production" id="production" placeholder="상품의 생산지를 입력해 주세요" required>
	              <div class="invalid-feedback">
	                상품의 생산지를 입력 해주세요.
	              </div>
	            </div>
	            
	             <div class="col-12">
	              <label for="title" class="form-label">중량/용량<span class="text-muted"></span></label>
	              <input type="text" class="form-control" name="weight" id="weight" placeholder="상품의 중량/용량을 입력해 주세요" required>
	              <div class="invalid-feedback">
	                상품의 중량/용량을 입력 해주세요.
	              </div>
	            </div>
	            
	            <div class="col-12">
	              <label for="title" class="form-label">유통기한<span class="text-muted"></span></label>
	              <input type="text" class="form-control" name="expired" id="expired" placeholder="상품의 유통기한을 입력해 주세요" required>
	              <div class="invalid-feedback">
	                상품의 유통기한을 입력 해주세요.
	              </div>
	            </div>
	            
	            <div class="col-12">
	              <label for="title" class="form-label">상세설명<span class="text-muted"></span></label>
	              <input type="text" class="form-control" name="detail" id="detail" placeholder="상품의 상세설명을 입력해 주세요" required>
	              <div class="invalid-feedback">
	                상품의 상세설명을 입력 해주세요.
	              </div>
	            </div>
		         <div class="col-12">
	              <label for="title" class="form-label">카테고리선택<span class="text-muted"></span></label>
					<select id="categoryNo" name="categoryNo" class="form-control">
					  <option value="10">채소</option>
					  <option value="20">과일/견과/쌀</option>
					  <option value="30">수산/해산/건어물</option>
					  <option value="40">정육/계란</option>
					  <option value="50">국/반찬/메인요리</option>
					  <option value="60">샐러드/간편식</option>
					  <option value="70">면/양념/오일</option>
					  <option value="80">생수/음료/우유/커피</option>
					  <option value="90">간식/과자/떡</option>
					  <option value="100">베이커리/치즈</option>
					  </select>
	              <div class="invalid-feedback">
	                상품의 카테고리를 선택해주세요.
	              </div>
	            </div>
	          </div>
	
	          <hr class="my-4">
	        </form>
	          <button class="w-100 btn btn-primary btn-lg" name="register_button" id="register_button" type="submit">상품 수정</button>
	      </div>
	    </div>
	  </main>
	</div>
  <br/><br/><br/>
  
	<script src="${hContext}/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${hContext}/resources/assets/recipe/form-validation.js"></script>
	    


  <script type="text/javascript">
  
  var itemNo = getParameter('itemNo');
  
  $(document).ready(function() {
	    console.log("상품 번호:"+itemNo);
	    doSelectOne(itemNo);
	    doRetrieveImage(itemNo);
	});
  
  function doSelectOne(itemNo){
	  
		$.ajax({
		  		type: "GET",
		  		url:"${hContext}/item/do_selectone.do",
		  		asyn:"true",
		  		dataType:"html",
		  		data:{
		  			"itemNo":itemNo
		  		},
		  		success:function(data){//통신 성공
		  			console.log("success data:"+data);
		  		
		  			var parseData = JSON.parse(data);
		  			console.log(parseData);
		  		
	    			var name = parseData.name;
	    			var price = parseData.price;
	    			var production = parseData.production;
	    			var weight = parseData.weight;
	    			var expired = parseData.expired;
	    			var detail = parseData.detail;
	    			var categoryNo = parseData.categoryNo;
		  		    
		  		    $("#name").val(name);
		  		    $("#price").val(price);
		  		    $("#production").val(production);
		  		    $("#weight").val(weight);
		  		 	$("#expired").val(expired);
		  			$("#detail").val(detail);
		  			$('#categoryNo').val(categoryNo).prop("selected",true);

		  		    
		      	},
		      	error:function(data){//실패시 처리
		      		console.log("error:"+data);
		      	}
		      	
		  	});
	  }  
	  
  function doRetrieveImage(itemNo){
		
		console.log("doRetrieveImage");
		console.log("itemNo: "+itemNo);
		
		$.ajax({
	  		type: "GET",
	  		url:"${hContext}/image/do_retrieve.do",
	  		asyn:"false",
	  		dataType:"html",
	  		data:{
	  			fromTb:1,
	  			fromNo:itemNo
	  		},
	  		success:function(data){//통신 성공
	  		
	  			//console.log(parseData);
	  			$("#imageListDel").val(data);
	  			
	  			
	      	},
	      	error:function(data){//실패시 처리
	      		console.log("error:"+data);
	      	}
	      	
	  	});
		
		
 } 
  
  function getParameter(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}  
  
  //팝업 창 띄우기
  function showPopup(frm) { 
	console.log('showPopup()');
	$("#whichMainImage").val('');
	
	var title ="파일 업로드";
	var option  ="toolbar=0,scrollbars=no,resizable=no,status=yes,width=500,height=300,left=300,top=50";
	
	window.open("",title,option);
	
	frm.target = title;
	frm.method = "GET";
	frm.action = "<c:url value='/image/image_view.do' />";
	frm.submit();
	}
  
	//숫자만 입력 가능
	$("input:text[numberOnly]").on("keyup",function(){
		$(this).val($(this).val().replace(/[^0-9]/g,""));
	});
  
   //상품 수정
  $("#register_button").on("click", function(e){

	console.log($("#regId").val());
	console.log($("#name").val());
	console.log($("#price").val());
	console.log($("#production").val());
	console.log($("#weight").val());
	console.log($("#expired").val());
	console.log($("#detail").val());
	console.log($("select[name=categoryNo]").val());
	
  	$.ajax({
  		type: "POST",
  		url:"${hContext}/item/do_update.do",
  		asyn:"false",
  		dataType:"html",
  		data:{
  			itemNo : itemNo,
  			regId: $("#regId").val(),
  			name: $("#name").val(),
  			price: $("#price").val(),
  			finalPrice: $("#price").val(),
  			production: $("#production").val(),
  			weight: $("#weight").val(),
  			expired: $("#expired").val(),
  			detail: $("#detail").val(),
  			categoryNo: $("select[name=categoryNo]").val()	
  		},
  		success:function(data){//통신 성공
      		var message = JSON.parse(data);
  			console.log(message.msgContents);
  			
  			console.log($("#imageListDel").val());
  			
  			var mainImageNum = $("#whichMainImage").val();
  			if("" == mainImageNum){
  				mainImageNum = "0";
  			}
  			
  			if($("#imageList").val() == ""){
  				$("#imageList").val($("#imageListDel").val());
  			}

  			$.ajax({
  		  		type: "GET",
  		  		url:"${hContext}/image/do_update.do",
  		  		asyn:"false",
  		  		dataType:"html",
  		  		data:{
  		  			
  		  			fromTb: 1,
		  			fromNo: itemNo,
		  			imageListDel: $("#imageListDel").val(),
		  			imageListNew: $("#imageList").val(),
		  		    MainImage: mainImageNum
  		  		},
  		  		success:function(data){//통신 성공
  		      		var message = JSON.parse(data);
  		  			console.log(message.msgContents);
  		  			alert("상품 수정이 완료되었습니다.");
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
  </div>
  </body>
</html>
