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
<caption><h1>AdoptReview List</h1></caption>
<tr>
<th>글 번호</th><th>작성 날짜</th><th>작성자</th><th>제목</th><th>내용</th><th>조회수</th>
<c:if test="${sessionScope.sessionId eq 'admin'}">
<th>수정/삭제</th>
</c:if>
</tr>
<c:forEach var="arList" items="${arList}">
<tr>
<td>${arList.ar_number}</td>
<td>${arList.ar_writedate}</td>
<td>${arList.ar_writer}</td>
<td><a href="orderHit?ar_number=${arList.ar_number}&page=${paging.page}">${arList.ar_title}</a></td>
<td>${arList.ar_contents}</td>
<td>${arList.ar_viewhit}</td>
</tr>
</c:forEach>
</table>
<a href="ar_gowrite">글쓰기</a>
<br>
<%@include file="footer-area.jsp"%>
</body>
</html>