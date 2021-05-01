<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
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


        .moveToMain {
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

        .moveToOrderList {
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
                    <h4>${memberName}님의 주문이 완료되었습니다!</h4>
                    <hr>
                    <div class="price-part">
                        <h4>결제금액</h4>
                        <h3>${price}원</h3>
                    </div>
                    <button class="moveToMain">메인으로 이동</button></br>
                    <button class="moveToOrderList">주문내역 보기</button>
                    <h5>결제 후 하루 이내에 주문상세페이지에서 직접 주문 취소가 가능합니다.</h5>
                </div>
            </div>
        </div>
        <!--// center -->
    

    <script>
		// 메인으로 이동 ------------------------------------------------
		$(".moveToMain").on("click", function(e){
			console.log("moveToMain");
			window.location.href = "${hContext}/main/main_view.do";
		});
		
		// 주문 내역으로 이동 ---------------------------------------------
		$(".moveToOrderList").on("click", function(e){
			console.log("moveToOrderList");
			window.location.href = "${hContext}/ordering/ordering_view.do";
		});
		
		
    </script>

</body>

</html>