<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>

				<div class="sidebar">
                    <div>
                        <h6>1:1문의</h6>
                    </div>
                    <ul>
						<li><a href="#" onclick="sidebarToQnA()">1:1 문의</a></li>
                    </ul>
                   
                 </div>
                 

<script>

	$(document).ready(function () {
		
	});

	
	//1:1문의 이동-----------------------------------------------------------
	function sidebarToQnA(ths) {
		console.log("sidebarToRetrieve");
	}

</script>