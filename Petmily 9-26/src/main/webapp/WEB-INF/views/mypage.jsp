<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
#mypage{
    margin: 0 auto;
    display: table;
    text-align: -webkit-center;
}


</style>
<script>
function Mypage_Withdrawal(){
	location.href("")
}

</script>
<body>
<%@include file="header-area.jsp" %>
<div id="mypage">
<h2>'${sessionScope.sessionId}'님 의 마이페이지 입니다.</h2>
<a href="#" class="btn original-btn">분양 예약 현황</a>
<a href="purchase" class="btn original-btn">구매내역</a>
<a href="#" class="btn original-btn">내가 쓴 댓글</a><br>
<a href="#" class="btn original-btn">내가 쓴 리뷰</a>
<a href="Mypage_MyData?id=${sessionId}" class="btn original-btn">회원 정보 보기</a>
<a href="memberdelete" class="btn original-btn">회원탈퇴</a>
</div>
<%@include file="footer-area.jsp" %>
</body>
</html>