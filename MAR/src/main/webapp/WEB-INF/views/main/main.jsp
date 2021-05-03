<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${hContext}/resources/js/jquery.min.js"></script>
<!-- 페이징js (꼭 이 위치에 있어야 인식함)--> 
<script src="${hContext}/resources/js/jquery.bootpag.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${hContext}/resources/js/bootstrap.min.js"></script>
<title>main</title>

<style type="text/css">

/* 썸네일 */
.thumbnail {
    border: 1px solid white;
    box-shadow: none;
}

.thumbnail img:hover {
    cursor: pointer;
    -webkit-transform:scale(1.05);
    -moz-transform:scale(1.05);
    -ms-transform:scale(1.05);   
    -o-transform:scale(1.05);
    transform:scale(1.05);
}
  
.container01 h3{
    font-size: 18px;
    font-family: 'Gothic A1', sans-serif;
    font-weight: bold;
    margin: 10px 0px 10px 0px;
    
}

.discount {
    font-size: 18px;
    font-family: 'Gothic A1', sans-serif;
    font-weight: bold;
    margin: 10px 0px 0px 0px;
    color: tomato;
}

.final-price {
    font-size: 18px;
    font-family: 'Gothic A1', sans-serif;
    font-weight: bold;
    margin: 10px 0px 0px 0px;
}

.origin-price {
    font-size: 18px;
    font-family: 'Gothic A1', sans-serif;
    font-weight: bold;
    color:#949494;
    margin: 10px 0px 0px 0px;
    text-decoration:line-through;
    text-decoration-color: #949494;
}
 
.eye {
    float: left;
    vertical-align: middle;
    margin: -3px 3px 0px 0px;
} 

 
</style>
</head>
<body>
<%-- 		 <c:set var="item" value="${MAIN_PAGE_SIZE }"/>
 		<input type="text" name="pageSize" id="pageSize" value="<c:out value='${item.detCode }'"/> --%>
 		${list.listDiv }
 		<div class="container01" id="listContainer">
 		<input type="hidden" name="member" id="member" value="${sessionScope.member}"/>
			<div class="row">
				<!-- 동적 html추가 -->
			</div>
        </div>
        
        <!-- pagenation -->
		<div class="text-center">
		    <div id="page-selection" class="text-center"></div>
		</div>
	    <!--// pagenation -->	
	
<script type="text/javascript">

	//jquery 객채생성 완료
	$(document).ready(function() {//화면이 로딩되면 바로 수행
		console.log("1.document:최초수행!");
	
		if( listDiv == null) {
			doMainRetrieve(1); 
		}
		
	});

	
	//기본 조회(메인화면 로드시 실행)
	function doMainRetrieve(page) {
      	$.ajax({
    		type: "GET",
    		url:"${hContext}/main/do_retrieve.do",
    		asyn:"true",
    		dataType:"html",
    		data:{  listDiv: $("#listDiv").val(),
    				searchWord: $("#mainSearchWord").val(),
    				pageNum: page },
    		success:function(data){//통신 성공
        		console.log("success data:"+data);
    			var parseData = JSON.parse(data);
    			console.log("parseData.length:"+parseData.length);

    			//기존데이터 삭제 (id가 listContainer인 요소의 클래스값이 row인 div요소를 제거)
    			$("#listContainer>.row").empty();
    			var html = ""; 
    			
    			if(parseData.length>0) {//데이터가 있는경우

    				//목록 추가
    				$.each(parseData, function(i, value) {
    					console.log(i+","+value.name);
    					html += " 	<div class='col-sm-6 col-md-3'>                                                                ";
    					html += " 		<div class='thumbnail' onclick='moveToItem("+value.itemNo+")'>                                                                    ";
    					html += " 			<img src='${hContext}"+value.path+"' alt='item_img'>                                   ";
    					html += " 			<div class='caption'>                                                                  ";
    					html += " 				<h3>"+value.name+"</h3>                                                            ";
    					html += " 				<span class='discount'>"+value.discount+"%</span>                                  ";
    					html += " 				<span class='final-price'>"+value.finalPrice+"원</span>                             ";
    					html += " 				<h3 class='origin-price'>"+value.price+"원</h3>                                     ";
    					html += " 			</div>                                                                                 ";
    					html += " 		</div>                                                                                     ";
    					html += " 	</div>                                                                                         ";

    				});
    			}
    			//container에 데이터 추가
    			$("#listContainer>.row").append(html); 
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	},
        	complete:function(data){//성공/실패와 관계없이 수행!
        		console.log("complete:"+data);
        	}
    	});
	}
	
	//상품 상세로 이동
	function moveToItem(itemNo){
		window.location.href = "${hContext}/item/item_deview.do?itemNo="+itemNo;
	}
	
	
	

</script>

</body>
</html>