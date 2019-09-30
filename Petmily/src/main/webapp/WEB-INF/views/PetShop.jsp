<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petmily</title>
</head>

<style>
.itemList{
    display: inline-block;
	margin-left : 420px;
}

</style>

<body>
<%@include file="header-area.jsp" %>
	<div class="itemList">
	<c:forEach items="${ItemList}" var="ItemList">
		<div class="catagory-title" style="display:inline-block; text-align:center;">
		<a href="Shop_View?item_number=${ItemList.item_number}">
	<table border='1'>
		<tr><td><img src="${pageContext.request.contextPath}/resources/shopIMG/${ItemList.item_Profile}" width="300px" height="200px"/></td></tr>
		<tr><td>${ItemList.item_name}<strong style="color:red;">[${ItemList.item_reviewCount}]</strong> </td></tr>
		<tr><td>${ItemList.item_kind} </td></tr>
		<tr><td>${ItemList.item_katagorie}</td></tr>
		<tr><td><strong style="color:red;">${ItemList.item_price}원</strong></td></tr>
	</table>
		</a>
		</div>
	
	</c:forEach>
	</div>


<%@include file="footer-area.jsp" %>

</body>
</html>