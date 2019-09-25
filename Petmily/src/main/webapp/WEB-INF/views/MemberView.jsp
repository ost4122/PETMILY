<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<style>
#myview {
	margin: 0 auto;
	display: table;
	text-align-last: justify;
	text-align-last: center;
}

tr, td {
	width: 250px;
	height: 80px;
	font-size: x-large;
}

#modi {
	margin: 0 auto;
	display: table;
	font-size: x-large;
}
</style>

<body>
	<%@include file="header-area.jsp"%>
	<table border="1" id="myview">

		<tr>
			<!-- 첫번째 줄 시작 -->
			<td>아이디</td>
			<td>${memberView.id}</td>
		</tr>
		<!-- 첫번째 줄 끝 -->
		<tr>
			<!-- 두번째 줄 시작 -->
			<td>이름</td>
			<td>${memberView.name}</td>
		</tr>
		<!-- 두번째 줄 끝 -->
		<tr>
			<!-- 세번째 줄 시작 -->
			<td>핸드폰</td>
			<td>${memberView.phone}</td>
		</tr>
		<!-- 세번째 줄 종료 -->
		<tr>
			<!-- 네번째 줄 시작 -->
			<td>생년월일</td>
			<td>${memberView.birth}${sessionBirthday.birthday}</td>
		</tr>
		<!-- 네번째 줄 종료 -->
		<tr>
			<!-- 다섯번째 줄 시작 -->
			<td>우편번호</td>
			<td>${memberView.address_number}</td>
		</tr>
		<!-- 다섯번째 줄 종료 -->
		<tr>
			<!-- 여섯번째 줄 시작 -->
			<td>도로명주소</td>
			<td>${memberView.address_a}</td>
		</tr>
		<!-- 여섯번째 줄 종료 -->
		<tr>
			<!-- 일곱번째 줄 시작 -->
			<td>지번주소</td>
			<td>${memberView.address_b}</td>
		</tr>
		<!-- 일곱번째 줄 종료 -->
		<tr>
			<!-- 여덟번째 줄 시작 -->
			<td>상세주소</td>
			<td>${memberView.address_c}</td>
		</tr>
		<!-- 여덟번째 줄 종료 -->
		<tr>
			<!-- 아홉번째 줄 시작 -->
			<td>이메일</td>
			<td>${memberView.email}${sessionEmail.email}</td>
		</tr>
		<!-- 아홉번째 줄 종료 -->
	</table>
	<a href="Mypage_MyDataModify?id=${memberView.id}" class="btn original-btn"
		id="modi">회원 정보 수정</a>
	<%@include file="footer-area.jsp"%>
</body>
</html>