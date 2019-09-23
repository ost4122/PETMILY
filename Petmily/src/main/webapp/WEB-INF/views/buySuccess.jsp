<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>구매완료</h1>
<table border = "1">
<tr><th><strong>${buy.item_name }</strong></th></tr>
<tr><td><img src="${pageContext.request.contextPath}/resources/shopIMG/${buy.item_profile}" width="200px" height="100px"/>"</td></tr>
<tr><td>${buy.deliveryRequest }</td></tr>

</table>
<h4>총 결제 금액</h4>
${totalPrice }원

</body>
</html>