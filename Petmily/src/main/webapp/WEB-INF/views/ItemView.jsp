<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header-area.jsp" %>
<img src="${pageContext.request.contextPath}/resources/shopIMG/${Item.item_Profile}" width="500px" height="300px"/>
<h2>${Item.item_name}</h2>
<p>${Item.item_reviewCount }개 상품평</p>
<strong>${Item.item_price }원</strong>

<hr>

<c:forEach items="${multyImg}" var="multyImg">
<img src="${pageContext.request.contextPath}/resources/shopIMG/${multyImg.multiImg}" width="300px" height="200px"/>
</c:forEach>

<%@include file="footer-area.jsp" %>
</body>
</html>