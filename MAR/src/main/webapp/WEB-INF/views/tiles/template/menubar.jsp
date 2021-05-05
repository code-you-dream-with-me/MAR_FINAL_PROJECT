<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>

        <!-- menu-bar -->
        <div class="menu_section">
            <nav role="navigation">
                <!--class="navbar navbar-default"-->
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                	<input type="hidden" name="menuPageSize" id="menuPageSize"/>
                    <ul class="nav navbar-nav" name="listDiv" id="listDiv" >
                        <li class="dropdown" value="10" class="e">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">카테고리 <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu" name="categoryNo" id="categoryNo">
                                <li><a href="#" onclick="headerToCate(this)">채소</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="headerToCate(this)">과일/견과/쌀</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="headerToCate(this)">수산/해산/건어물</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="headerToCate(this)">정육/계란</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="headerToCate(this)">국/반찬/메인요리</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="headerToCate(this)">샐러드/간편식</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="headerToCate(this)">면/양념/오일</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="headerToCate(this)">생수/음료/우유/커피</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="headerToCate(this)">간식/과자/떡</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="headerToCate(this)">베이커리/치즈</a></li>
                            </ul>
                        </li>
                        <li class="e"><a href="#" onclick="headerToRetrieve(this)">신상품</a></li>
                        <li class="e"><a href="#" onclick="headerToRetrieve(this)">베스트</a></li>
                        <li class="e"><a href="#" onclick="headerToRetrieve(this)">알뜰쇼핑</a></li>
                        <li><a href="#" onclick="headerToRecipe()">레시피</a></li>
                        <li><a href="#" onclick="headerToCart()">장바구니</a></li>
                    </ul>
                    <div class="nav navbar-form navbar-right search_container" role="search">
                        <table class="search_elements">
                            <tr>
                                <td>
                                    <input type="text" name="mainSearchWord" id="mainSearchWord" maxlength="100" placeholder="검색어를 입력하십시오" class="search_input">
                                </td>
                                <td>
                                    <a href="#" onclick="headerToSearchWord(this)"><img src="${hContext }/resources/image_source/black-24dp/1x/outline_search_black_24dp.png"></a>
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
            $(".dropdown-toggle").dropdown()
        });
        
     	
     	// 장바구니 이동 ---------------------------------------------------
     	function headerToCart() {
			if("" != member.memberId) {
    			window.location.href = "${hContext}/cart/cart_list.do";
			} else {
				alert("로그인이 필요합니다.");
				return;
			}
    	}
     	
     	
     	// 레시피 이동 ------------------------------------------------------------------------------
     	function headerToRecipe(ths) {
     		window.location.href = "${hContext}/main/do_recipe_retrieve.do";
     	}
     	
     	// 카테고리에 따른 이동 -----------------------------------------
     	function headerToCate(ths) {
    		console.log("headerToCate");
 			var cateNo = $(ths).text();
 			
    		if("채소" == cateNo){
    			cateNo = "10";
    		} else if("과일/견과/쌀" == cateNo) {
    			cateNo = "20";
    		} else if("수산/해산/건어물" == cateNo){
    			cateNo = "30";
    		} else if("정육/계란" == cateNo) {
    			cateNo = "40";
    		} else if("국/반찬/메인요리" == cateNo){
    			cateNo = "50";
    		} else if("샐러드/간편식" == cateNo) {
    			cateNo = "60";
    		} else if("면/양념/오일" == cateNo){
    			cateNo = "70";
    		} else if("생수/음료/우유/커피" == cateNo) {
    			cateNo = "80";
    		} else if("간식/과자/떡" == cateNo){
    			cateNo = "90";
    		} else if("베이커리/치즈" == cateNo) {
    			cateNo = "100";
    		} 
    		console.log("cateNo : " + cateNo);
     		window.location.href = "${hContext}/main/do_retrieve.do?listDiv=10&categoryNo="+cateNo;
    	}
    	
     	
     	// 알뜰, 베스트, 신상품 이동 ------------------------------------------
     	function headerToRetrieve(ths) {
    		console.log("headerToRetrieve");
 			var listDiv = $(ths).text();
 			console.log("listDiv : " + listDiv);
    		if("신상품" == listDiv){
    			listDiv = "20";
    		} else if("베스트" == listDiv) {
    			listDiv = "30";
    		} else if("알뜰쇼핑" == listDiv){
    			listDiv = "40";
    		}
    		console.log("listDiv : " + listDiv);
    		
    		window.location.href = "${hContext}/main/do_retrieve.do?listDiv="+listDiv;
    	}
    	
     	// 검색어에 따른 이동 ------------------------------------------------------------------------
    	$("#mainSearchWord").on("keypress",function(e) {
    		console.log(e.type+","+e.which);
        	if(e.which == 13){
        		console.log("Enter:"+e.which);
        		e.preventDefault();//두번 호출되지 않게 함
        		window.location.href = "${hContext}/main/do_retrieve.do?listDiv=20&searchWord="+$("#mainSearchWord").val(); 
        	}       
        });
     	
      	function headerToSearchWord(ths) {
			var tds = $(ths).closest("tr").find("input");
			window.location.href = "${hContext}/main/do_retrieve.do?listDiv=20&searchWord="+tds.val();
		} 
     	
    	
    	//레시피 상세로 이동 ------------------------------------------------------------------------
    	function moveToRecipe(recipeNo){
    		window.location.href = "${hContext}/recipe/recipe_view2.do?recipeNo="+recipeNo;
    	}
         
        
        
</script>