<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>


        <!-- header -->
        <header class="header">
            <nav class="nav">
                <ul class="nav_items">
                	<c:choose>
                		<c:when test="${null != sessionScope.member}">
                			<li><a href="#">${member.name } 님</a></li>
                			<li><a href='javascript:goLogout();'>로그아웃</a></li> 
                		</c:when>
	                	<c:otherwise>
	                		<li><a href="${hContext }/member/sign_up_view.do">회원가입</a></li>
	                		<li><a href="${hContext }/member/sign_in_view.do">로그인</a></li>
	                	</c:otherwise>
                	</c:choose>
                    <li><a href="#">고객센터</a></li>
                    <c:if test="${member.auth == 1}"><!-- 관리자 아이디 들어올 시 보임 -->
                    	 <li><a href="${hContext }/admin/admin_view.do">관리자영역</a></li>
                    </c:if>
                </ul>
            </nav>
            <div class="logo">
                <div>
                    <a href="#"><img src="${hContext }/resources/image_source/markit_logo.png" alt="markit_logo"></a>
                </div>
            </div>
        </header>
        <!--// header -->

        <!-- menu-bar -->
        <div class="menu_section">
            <nav role="navigation">
                <!--class="navbar navbar-default"-->
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                	<input type="hidden" name="pageSize" id="pageSize"/>
                    <ul class="nav navbar-nav" name="listDiv" id="listDiv" >
                        <li class="dropdown" value="10" class="e">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">카테고리 <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu" name="categoryNo" id="categoryNo">
                                <li value="10"><a href="#">채소</a></li>
                                <li class="divider"></li>
                                <li value="20"><a href="#">과일/견과/쌀</a></li>
                                <li class="divider"></li>
                                <li value="30"><a href="#">수산/해산/건어물</a></li>
                                <li class="divider"></li>
                                <li value="40"><a href="#">정육/계란</a></li>
                                <li class="divider"></li>
                                <li value="50"><a href="#">국/반찬/메인요리</a></li>
                                <li class="divider"></li>
                                <li value="60"><a href="#">샐러드/간편식</a></li>
                                <li class="divider"></li>
                                <li value="70"><a href="#">면/양념/오일</a></li>
                                <li class="divider"></li>
                                <li value="80"><a href="#">생수/음료/우유/커피</a></li>
                                <li class="divider"></li>
                                <li value="90"><a href="#">간식/과자/떡</a></li>
                                <li class="divider"></li>
                                <li value="100"><a href="#">베이커리/치즈</a></li>
                            </ul>
                        </li>
                        <li value="20" class="e"><a href="#">신상품</a></li>
                        <li value="30" class="e"><a href="#">베스트</a></li>
                        <li value="40" class="e"><a href="#">알뜰쇼핑</a></li>
                        <li id="doRetriveRecipe"><a href="#">레시피</a></li>
                        <li id="moveToCart"><a href="#">장바구니</a></li>
                    </ul>
                    <div class="nav navbar-form navbar-right search_container" role="search">
                        <table class="search_elements">
                            <tr>
                                <td>
                                    <input type="text" name="searchWord" id="searchWord" maxlength="100" placeholder="검색어를 입력하십시오" class="search_input">
                                </td>
                                <td>
                                    <a href="#"><img src="${hContext }/resources/image_source/black-24dp/1x/outline_search_black_24dp.png"></a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div><!-- /.navbar-collapse -->
            </nav>
        </div>
        <!--// menu-bar -->
        
        <script>
         $(document).ready(function () {
        	 //li의 마지막 메뉴 오른쪽 구분선 없애기
             $("ul > li:last-child > a").css("border", "0 none");
             $(".dropdown-toggle").dropdown()
         });
         
     	function goLogout() {
    		if(false == confirm("로그아웃 하시겠습니까?"))return;
    		window.location.href = '<c:out value="${hContext}/member/do_logoff.do" />'
    		
    	}
     	
    	//장바구니 이동
     	$("#moveToCart").on("click",function(e){
    		console.log("moveToCart click");
    			
    		$.ajax({
    	  		type: "GET",
    	  		url:"${hContext}/main/move_to_cart.do",
    	  		asyn:"true",
    	  		dataType:"html",
    	  		data:{ memberId : member.memberId },
    	  		success:function(data){//통신 성공
    	  			var parseData = JSON.parse(data);//message데이터 받음
    	  			console.log("parseData"+parseData)
    	  			
    	  			if(parseData.msgId == 1){
    		  			window.location.href = "${hContext}/cart/cart_list.do ";
    	  			} else {
    		  			alert(parseData.msgContents);
    	  			}
    	  		},
    	  		error:function(data){//실패시 처리
    	  			console.log("error:"+data);
    	  		}
    	  	});
    	});
    	
    	//레시피 목록 조회
    	$("#doRetriveRecipe").on("click",function(e){
    		console.log("doRetriveRecipe click");
    		e.preventDefault();
    		let tds = $(this);
    		var listDivData = tds.val();
    		console.log("listDivData="+listDivData);
    		
    		$.ajax({
        		type: "GET",
        		url:"${hContext}/main/do_recipe_retrieve.do",
        		asyn:"true",
        		dataType:"html",
        		data:{ listDiv: listDivData },
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
        					console.log(i+","+value.title);
        					html += " <div class='col-sm-6 col-md-3'>                                          ";
        					html += " 	<div class='thumbnail'>                                                ";
        					html += " 		<img src='${hContext}"+value.path+"' alt='item_img'>                 ";
        					html += " 		<div class='caption'>                                              ";
        					html += " 			<h3>"+value.title+"</h3>                                        ";
        					html += " 			<span class='material-icons-outlined eye'> remove_red_eye </span>  ";
        					html += " 			<h4>"+value.readCnt+"</h4>                                                     ";
        					html += " 		</div>                                                             ";
        					html += " 	</div>                                                                 ";
        					html += " </div>																   ";
         
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
    	});
    	
    	//로고 클릭 시 메인화면 doRetrieve실행
    	$(".logo").on("click",function(e){
    		//console.log("logo click");
    		doRetrieve(1);
    	});
    	
    	//카테고리 조회
     	$(".dropdown-menu").on("click","li",function(e) {
    		e.preventDefault();
    		let lis = $(this);
    		var cateNo = lis.val();
    		console.log("cateNo="+cateNo);
    		
    		$.ajax({
        		type: "GET",
        		url:"${hContext}/main/do_retrieve.do",
        		asyn:"true",
        		dataType:"html",
        		data:{ categoryNo:  cateNo,
        			   listDiv : 10
        			  },
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
        					html += " 		<div class='thumbnail'>                                                                    ";
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
    	
    		
    	}); 
    	
    	//신상품,베스트,알뜰쇼핑 조회
    	//eq(1),(2),(3)인 요소만 선택하게 하고싶은데 진짜 안된다.. 결국 그냥 class=e 줘서 선택하게함..
    	//slice가 있는것 같은데 css적용예시만 있어서 on에는 어떻게 쓰여야 할지 모르겠다..
    	$("#listDiv").on("click","li.e",function(e){
    		e.preventDefault();
    		//console.log("listDiv click li");
    		let tds = $(this);
    		var listDivData = tds.val();
    		console.log("listDivData="+listDivData);
    		
    		$.ajax({
        		type: "GET",
        		url:"${hContext}/main/do_retrieve.do",
        		asyn:"true",
        		dataType:"html",
        		data:{ listDiv: listDivData },
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
        					html += " 		<div class='thumbnail'>                                                                    ";
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
    		
    	});
    	
    	//검색어 Enter Event처리
    	$("#searchWord").on("keypress",function(e) {
    		console.log(e.type+","+e.which);
        	if(e.which == 13){
        		console.log("Enter:"+e.which);
        		e.preventDefault();//두번 호출되지 않게 함
        		doRetrieve(1);//엔터 누를 시 doRetrive함수 실행
        	}       
        });
         
        
        </script>