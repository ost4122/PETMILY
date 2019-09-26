<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
h1, h3{
	text-align:center;
}
input{
	text-align:center;
}
</style>
<body>
<%@include file="header-area.jsp"%>
<br>
<input type="text" name="a_number">
<input type="text" name="a_adminconfirm">
<h1>분양 신청이 완료되었습니다.</h1>
<h3>관리자 확인 후 분양 상태 확인 가능합니다. 마이페이지에서 확인해주시기 바랍니다.</h3>
<br>
<%@include file="footer-area.jsp"%>
</body>
</html>