<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>

        <!-- header -->
        <header class="header">
            <nav class="nav">
                <ul class="nav_items">
                    <li><a href="#">회원가입</a></li>
                    <!-- <li><a href="#">이상무 님</a></li> -->
                    <li><a href="#">로그인</a></li>
                    <!-- <li><a href="#">로그아웃</a></li> -->
                    <li><a href="#">고객센터</a></li>
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
                        <li id="moveToRecipe"><a href="#">레시피</a></li>
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
         });
         
         
        
        </script>