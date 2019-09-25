<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>
.purchase{
    margin: 0 auto;
    display: table;
    text-align: -webkit-center;
    letter-spacing : 2px;
    
}


</style>


<body>

<%@include file="header-area.jsp" %>

<h1>구매내역</h1>

<ul class="purchase">

<c:forEach items="${purchase}" var="purchase">

<li>
		<img src="${pageContext.request.contextPath}/resources/shopIMG/${purchase.item_profile}" width="100px" height="80px"/>
		${purchase.item_name}
		${purchase.item_price }
		${purchase.deliveryPrice }
		${purchase.paymentOption }
		${purchase.purchaseDate }
		<a href="#">상품 후기 쓰러가기</a>
																	</li>
</c:forEach>

</ul>


<%@include file="footer-area.jsp"%>


</body>
</html>