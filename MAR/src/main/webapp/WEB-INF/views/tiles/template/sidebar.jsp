<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>

				<div class="sidebar">
                    <div>
                        <h6>마이마킷</h6>
                    </div>
                    <ul>
                        <li><a href="#" onclick="sidebarToOrderingList()">주문내역</a></li>
                        <li><a href="#" onclick="sidebarToWishList()">늘 사는 것</a></li>
                        <li><a href="#" onclick="sidebarToItemReview()">상품후기</a></li>
                        <li><a href="#" onclick="sidebarToMemberUpdate()">개인 정보 수정</a></li>
                    </ul>
                   
                 </div>
                 

<script>

	$(document).ready(function () {
		
	});

	
	//주문내역, 늘사는것, 상품후기 이동-----------------------------------------------------------
	function sidebarToOrderingList(ths) {
		console.log("sidebarToRetrieve");
		window.location.href = "${hContext}/ordering/ordering_view.do?memberId=${member.memberId}"
	}
	
	function sidebarToWishList(ths) {
		console.log("sidebarToWishList");
		window.location.href = "${hContext}/wishitem/wishitem_list.do?memberId=${member.memberId}"
	}
	
	function sidebarToItemReview(ths) {
		console.log("sidebarToItemReview");
		window.location.href = "${hContext}/review/review_view.do?searchDiv20=20&memberId=${member.memberId}"
	}
	
	function sidebarToMemberUpdate(ths) {
		console.log("sidebarToMemberUpdate");
		window.location.href = "${hContext}/member/mypage_view.do?memberId=${member.memberId}";
	}



</script>