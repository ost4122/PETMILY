<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
h2 {
	font-color: red;
	font-size: 22px;
}

#deleteform {
	margin: 0 auto;
	display: table;
	text-align: -webkit-right;
}

#fofo {
	font-size: 22px;
	text-align: center;
}

.buy {
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
	margin-left: -3px;
	margin-right: -4px;
	border: 1px solid skyblue;
	background-color: rgba(0, 0, 0, 0);
	color: skyblue;
	padding: 5px;
}

.buy:hover {
	color: white;
	background-color: skyblue;
}

#gogo {
	font-size: x-large;
}
</style>
<body>
	<%@include file="header-area.jsp"%>
	<h2 style="color: red;" id="fofo">회원 탈퇴를 하게되면 모든 서비스를 이용할수없습니다.그래도
		진행하신다면 개인 정보를 위해 비밀번호를 입력해주세요.</h2>
	<form id="deleteform" name="deleteform" method="post"
		action="Mypage_Withdrawal" onsubmit="return checkValue()">
		아이디 <input name="id" value="${sessionId}" readonly><br>
		비밀번호 <input type="password" name="password" maxlength="50"> <br>
		<div id="gogo">
			<input type="submit" value="탈퇴" class="buy" /> <input type="button"
				value="취소" onclick="location='home'" class="buy">
		</div>
	</form>
	<%@include file="footer-area.jsp" %>
</body>
</html>