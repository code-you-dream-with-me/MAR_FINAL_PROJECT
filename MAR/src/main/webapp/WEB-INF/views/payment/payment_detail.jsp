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
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${hContext}/resources/js/bootstrap.min.js"></script>
<title>order_complete</title>

    <style>
        #wrap {
            width: 90%; /*960px*/
            height: auto;
            margin: 0 auto;
            /* border: 4px solid #000; */
        }

        /*컨테이너 부분*/
        .container01 {
            width: 70%; /*672px%960px*/
            height: auto; /*화면 넣으실때 auto로 바꿔주세요*/
            margin: 0 auto;
            padding: 20px 2.083333%; /*20px%960px 가변패딩*/
            /* border: 2px solid #2F5597; */
        }

        .container01 h6 {
            color:rgb(77, 77, 77);
            margin: 0px 0px 30px 0px;
            font-size:30px;
            font-weight: bold;
        }

        /*점보트론 부분*/
        .jumbo-box {
            background-color: #FAFAFA;
            width: 50%;
            padding: 50px;
            margin: 0 auto;
            text-align: center;
        }

        .jumbo-box span {
            font-size: 50px;
            color:#2F5597;
            margin-bottom: 10px;
        }

        .jumbo-box h5 {
            text-align: left;
            line-height: 20px;
        }

        .price-part {
            text-align: left;
        }

        .price-part h3 {
            font-weight: bold;
            font-size: 30px;
            margin-top: 5px;
        }


        .button01 {
            width: 100%;
            padding: 10px;
            margin-bottom: 5px;
            background-color: #2F5597;
            border-radius: 4px;
            font-size: 16px;
            font-weight: bold;
            color: rgb(255, 255, 255);
            border: 1px solid #2F5597;
        }

        .button02 {
            width: 100%;
            padding: 10px;
            margin-bottom: 5px;
            background-color: white;
            border-radius: 4px;
            font-size: 16px;
            font-weight: bold;
            color: #757575;
            border: 1px solid #2F5597;
        }

      
        
    </style>
</head>

<body>
        <!-- center -->
        <div class="container01">
            <div class="jumbotron">
                <div class="jumbo-box">
                    <span class="material-icons-outlined"> check_circle </span>
                    <h4>${boardName}님의 주문이 완료되었습니다!</h4>
                    <hr>
                    <div class="price-part">
                        <h4>결제금액</h4>
                        <h3>${price}원</h3>
                    </div>
                    <button class="button01">홈으로 이동</button></br>
                    <button class="button02">주문내역 보기</button>
                    <h5>결제 후 하루 이내에 주문상세페이지에서 직접 주문 취소가 가능합니다.</h5>
                </div>
            </div>
        </div>
        <!--// center -->
    

    <script>
        // li의 마지막 메뉴 오른쪽 구분선 없애기
        // $(document).ready(function () {
        //     $("ul > li:last-child > a").css("border", "0 none");
        // });

    </script>

</body>

</html>