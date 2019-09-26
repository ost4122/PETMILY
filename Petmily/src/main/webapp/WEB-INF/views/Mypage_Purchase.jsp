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

#purchaseTitle{
	text-align : center;
	color : blue;
}

</style>


<body>

<%@include file="header-area.jsp" %>

<h3 id="purchaseTitle">구매내역</h3>

<ul class="purchase">
<li><h4>상품이름    상품가격    결제방법    구매날짜</h4></li> 
<c:choose>

	<c:when test = "${not empty purchase}" >

				<c:forEach items="${purchase}" var="purchase">

<li>
		<img src="${pageContext.request.contextPath}/resources/shopIMG/${purchase.item_profile}" width="100px" height="80px"/>
		${purchase.item_name}
		${purchase.item_price }
		${purchase.deliveryPrice }
		${purchase.paymentOption }
		${purchase.purchaseDate }
		<a href="purchase_select?delivery_number=${purchase.delivery_number}">상품 후기 쓰러가기</a>
																	</li>
</c:forEach>
		</c:when>

			<c:otherwise>
				<h3><strong>구매 내역이 없습니다.</strong></h3>
					</c:otherwise>
								</c:choose>
</ul>


<%@include file="footer-area.jsp"%>


</body>
</html>