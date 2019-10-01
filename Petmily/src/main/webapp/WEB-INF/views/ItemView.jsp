<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<style>
#btn {
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
	margin-left: -3px;
	margin-right: -4px;
	border: 1px solid skyblue;
	background-color: rgba(0, 0, 0, 0);
	color: skyblue;
	padding: 5px;
}

#btn:hover {
	color: white;
	background-color: skyblue;
}
.reviewImg{
	display : flex;
}
.reviewSubTitle{
	margin-right : 35px;
}
</style>


<body>
	<script>
   	function buy(){
   		
   		if(${sessionScope.sessionId == null}){
   			alert("로그인 후 이용 가능합니다 !");
   			
   		}else{
   			location.href="Item_Buy?item_number=${Item.item_number}&id=${sessionId}";
   		}
   		
   	}
    </script>


	<%@include file="header-area.jsp"%>
	<img
		src="${pageContext.request.contextPath}/resources/shopIMG/${Item.item_Profile}"
		width="500px" height="300px" />
	<h2>${Item.item_name}</h2>
	<p>${Item.item_reviewCount }개상품평</p>
	<strong>${Item.item_price }원</strong>
	<button onclick="buy();" class="inputBtn">구매하기</button>

	<hr>

	<c:forEach items="${multyImg}" var="multyImg">
		<img
			src="${pageContext.request.contextPath}/resources/shopIMG/multyImg/${multyImg.multiImg}"
			width="300px" height="200px" />
		<br>
	</c:forEach>


	<input type="hidden" id="item_name" value="${Item.item_name }" />
	<input type="hidden" id="item_price" value="${Item.item_price }" />
	<br><br>
	<h1>구매후기</h1>
	<br>
	<c:forEach items="${Review}" var="review">
		<!-- 구매후기 첨부파일 -->
		<div class="reviewImg">
		<c:forEach items="${Review_Img}" var="review_Img">
			<img src="${pageContext.request.contextPath}/resources/
				purchase_Review_IMG/${review_Img}" width="200px;" height="150px;"><br>
		</c:forEach>
		</div>
		<strong class="reviewSubTitle">디자인</strong>${review.review_design } <br>
		<strong class="reviewSubTitle">편리성</strong>${review.review_handiness } <br>
		<strong class="reviewSubTitle">한줄평</strong>${review.review_contents } <br>
	</c:forEach>
	



	<%@include file="footer-area.jsp"%>
</body>
</html>