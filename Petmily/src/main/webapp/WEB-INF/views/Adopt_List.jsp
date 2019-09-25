<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header-area.jsp"%>
<br>
<table border="1">
<caption><h1>Adopt List</h1></caption>
<tr>
<th>신청글 번호</th><th>신청 날짜</th><th>신청자</th>
</tr>
<c:forEach var="aList" items="${aList}">
<tr>
<td>${aList.a_number}</td>
<td>${aList.a_Date}</td>
<td>${aList.a_name}</td>
<td><a href="hitCount?a_number=${aList.a_number}&page=${paging.page}">${aList.ar_title}</a></td>
</tr>
</c:forEach>
</table>
<br>
<%@include file="footer-area.jsp"%>
</body>
</html>