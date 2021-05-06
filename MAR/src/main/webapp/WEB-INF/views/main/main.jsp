<%@page import="com.sist.mar.cmn.StringUtil"%>
<%@page import="com.sist.mar.main.domain.CateSearchVO"%>
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

 		<div class="container01">
 		<input type="hidden" name="member" id="member" value="${sessionScope.member}"/>
		<form action="${hContext }/main/do_retrieve.do" id="searchFrm" name="searchFrm" method="get">
			<c:forEach var="vo" items="${list}" varStatus="status">
				<c:if test="${ status.index % 4 eq 0 }">
					<div class="row">
						<c:forEach var="j" begin="${ status.index }" end="${ status.index + (4 - 1) }" step="1">
							<c:if test="${ list[j] ne null }">
								<div class='col-sm-6 col-md-3'>
									<div class='thumbnail' onclick='moveToItem("${list[j].itemNo}")'>
										<img src='${hContext}${list[j].path}' alt='item_img'>
										<div class='caption'>
											<h3>${list[j].name}</h3>
											<c:choose>
												<c:when test="${list[j].finalPrice == list[j].price}">
													<span class='final-price'>${list[j].finalPrice}원</span>
												</c:when>
												<c:otherwise>
													<span class='discount'>${list[j].discount}%</span> 
													<span class='final-price'>${list[j].finalPrice}원</span>
													<h3 class='origin-price'>${list[j].price}원</h3>												
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</c:if>
			</c:forEach>
		</form>
		</div>
		

<%--  	    <div class="text-center">	
	 		<input type="button" class="btn btn-primary btn-lg" value="이전" id="doPreviousPage" onclick="doNumMinus()"/>  
	 		<input type="text" name="listDivv" id="listDivv" value="${search.listDiv}"/>
	 		<input type="text" name="cateNoo" id="cateNoo" value="${search.categoryNo}"/>
	 		
	 		현재페이지<input type="text" name="pageNum" id="pageNum" value="${search.pageNum}"/>
	 		총페이지<input type="text" name="lastNum" id="lastNum" value=""/>
				
			<input type="button" class="btn btn-primary btn-lg" value="다음" id="doNextPage"	onclick="doNumPlus()"/>  
		</div><br>  --%>
	
<script type="text/javascript">
	//jquery 객채생성 완료
	$(document).ready(function() {//화면이 로딩되면 바로 수행
		console.log("1.document:최초수행!");
	});
	
	
/*  	//페이징 변수 추가
	let totalCount = 0;
	let pageTotal  = 1;
	let pageSize = 12;
	let page = 1;
	var pageNum = $("#pageNum").val();

	
	console.log("pageNum:" + pageNum);
	totalCount = ${list[0].totalCnt}//totalCount:아이템개수
	console.log("totalCount: "+totalCount);
	pageTotal = (totalCount/pageSize);// 42/10 = 4.2
	console.log("pageTotal: "+pageTotal);
	pageTotal = Math.ceil(pageTotal);// 42/10 = 4.2 -> 5
	console.log("pageTotal: "+pageTotal);
	
	$("#lastNum").val(pageTotal);
	
	//paging(페이징): 총페이지, 현재글번호
	console.log(pageTotal+", "+page);
	
	function doNumMinus(listDiv,cateNo,pageNum){
		
		if(pageNum == 1){
			alert("첫 페이지 입니다");
			return;
		}
		pageNum = String(parseInt(pageNum) - 1);
		console.log("pageNum:" + pageNum);
		var listDiv = $("#listDivv").val();
		var cateNo = $("#cateNoo").val();
		
		window.location.href = "${hContext}/main/do_retrieve.do?listDiv="+listDiv+"&categoryNo="+cateNo+"&pageNum="+pageNum;
	}
	
	function doNumPlus(listDiv,cateNo,pageNum){
		
		if(pageTotal <= pageNum){
			alert("마지막 페이지 입니다");
			return;
		}
		pageNum = String(parseInt(pageNum) + 1);
		console.log("pageNum:" + pageNum);
		var listDiv = $("#listDivv").val();
		var cateNo = $("#cateNoo").val();
		
		window.location.href = "${hContext}/main/do_retrieve.do?listDiv="+listDiv+"&categoryNo="+cateNo+"&pageNum="+pageNum;
	}
 */

	//상품 상세로 이동----------------------------------------------------------------------
	function moveToItem(itemNo) {
		window.location.href = "${hContext}/item/item_deview.do?itemNo="+ itemNo;
	}
</script>

</body>
</html>