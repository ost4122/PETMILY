<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petmily</title>

</head>
<body>
<%@include file="header-area.jsp" %>

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
<hr>	
<!-- 페이징 처리 -->
<%-- <c:if test="${paging.page<=1}">
	[이전]&nbsp;
</c:if>
<c:if test="${paging.page>1}">
	<a href="Shop_List?page=${paging.page-1}">[이전]</a>&nbsp;	
</c:if>
<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
	<c:choose>
								
		<c:when test="${i eq paging.page}">
			${i}
		</c:when>
		<c:otherwise>
			<a href="Shop_List?page=${i}">${i}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:if test="${paging.page>=paging.maxPage}">
[다음]
</c:if>
<c:if test="${paging.page<paging.maxPage}">
<a href="Shop_List?page=${paging.page+1}">[다음]</a>
</c:if> --%>

<%@include file="footer-area.jsp" %>

</body>
</html>