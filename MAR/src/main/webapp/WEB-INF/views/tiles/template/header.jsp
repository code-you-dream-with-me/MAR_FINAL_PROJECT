<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>


        <!-- header -->
        <header class="header">
            <nav class="nav">
                <ul class="nav_items">
                	<c:choose>
                		<c:when test="${null != sessionScope.member}">
                			<li><a href="#" onclick="headerToMypage()">${member.name } 님</a></li>
                			<li><a href="#" onclick='goLogout()'>로그아웃</a></li> 
                		</c:when>
	                	<c:otherwise>
	                		<li><a href="${hContext }/member/sign_up_view.do">회원가입</a></li>
	                		<li><a href="${hContext }/member/sign_in_view.do">로그인</a></li>
	                	</c:otherwise>
                	</c:choose>
                    <li><a href="#" onclick="headerToQuestion()">고객센터</a></li>
                    <c:if test="${member.auth == 1}"><!-- 관리자 아이디 들어올 시 보임 -->
                    	 <li><a href="${hContext }/admin/admin_view.do">관리자영역</a></li>
                    </c:if>
                </ul>
            </nav>
            <div class="logo">
                <div>
                    <a href="javascript:headerToMain();"><img src="${hContext }/resources/image_source/markit_logo.png" alt="markit_logo"></a>
                </div>
            </div>
        </header>
        <!--// header -->

       
        
<script>
		
        // 전역변수 (세션정보) ---------------------------------------------
        var member;

       // document ----------------------------------------------------
         $(document).ready(function () {
        	 //li의 마지막 메뉴 오른쪽 구분선 없애기
             $("ul > li:last-child > a").css("border", "0 none");
             
             //로그인 세션
             member = {memberId : "${sessionScope.member.memberId}"};
             console.log("member : " + member.memberId);
             
         });
         
        // 로그아웃 버튼 ----------------------------------------------------
     	function goLogout() {
    		if(false == confirm("로그아웃 하시겠습니까?"))return;
    		window.location.href = '<c:out value="${hContext}/member/do_logoff.do" />'
    		
    		headerToMain()
    		
    	}

     	// 로고 클릭 시 메인으로 이동 -----------------------------------------
     	function headerToMain() {
    		window.location.href = "${hContext}/main/main_view.do";
    	}


    	//고객센터 클릭 ----------------------------------------------------------------------------
        function headerToQuestion() {
			if("" != member.memberId) {
				window.location.href = "${hContext}/question/question_view.do";
			} else {
				alert("로그인이 필요합니다.");
				return;
			}
		}
    	
    	//마이페이지 이동 ---------------------------------------------------------------------------
        function headerToMypage() {
			if("" != member.memberId) {
				window.location.href = "${hContext}/ordering/ordering_view.do?memberId=${member.memberId}";
			} else {
				alert("로그인이 필요합니다.");
				return;
			}
		}
</script>