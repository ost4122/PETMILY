<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
#adoptwrite{
	margin-top:100px;
	margin-left:680px;
}
</style>
<body>

<%@include file="header-area.jsp"%>
<br>
<form action="a_write" method="post" id="adoptwrite">
<table>
<h1>분양 신청</h1>
<tr>
<td> 신청자 아이디 <input type="text" name="a_id" value="${sessionScope.sessionId}" readonly></td>
</tr>
<tr>
<td> 신청자 이름 <input type="text" name="a_name" required></td>
</tr>
<tr>
<td> 펫번호 <input type="text" name="a_petnumber" value="${petNumber}" readonly></td>
</tr>
<tr>
<td> 펫종류 <input type="text" name="a_petcategorie" value="${petCategorie}" readonly></td>
</tr>
<tr>
<td> 펫종 <input type="text" name="a_petname" value="${petName}" readonly></td>
</tr>
<tr>
<td> 분양가격 <input type="text" name="a_petprice" value="${petPrice}" readonly></td>
</tr>
<tr>
<td> 예약금 <input type="text" name="reservation" value="10,000원" readonly></td>
</tr>
<tr>
<td><input type="submit" value="예약신청">
<input type="reset" value="다시작성"></td>
</tr>
</table>
</form>
<br>
<br>
<%@include file="footer-area.jsp"%>
</body>
</html>