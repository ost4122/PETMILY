<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>

.review-textArea{
	width: 100%;
    height: 146px;
    /* border: 0; */
    resize:none;
    background-color:#f2f4f8;
}
.reviewTD{
		width:200px;
}
form{
	margin-left : 100px;
}

</style>

<body>
<%@include file="header-area.jsp" %>

<hr>

<h2>구매한 상품 </h2>
<ul>
	<li>
	
<img src="${pageContext.request.contextPath}/resources/shopIMG/${purchase.item_profile}" width="300px" height="200px"/>
		<strong>${purchas.item_name }</strong>
		
		</li>
			</ul>
				<hr>

<!-- 구매후기 작성란 -->
<form action="write_review" method="post" enctype="multipart/form-data">
	<table class="review_table">
		<tbody>
				<tr>	
					<td class="reviewTD"><strong>구매 후기</strong></td>
						<td><textArea name="review_contents" class="review-textArea" rows="5" cols="70"
						placeholder="자세한 구매후기는 고객의 구매에 많은 도움이 됩니다."></textArea></td>	
																					</tr>
					<tr> <td> 
																						</tr>
																						</tbody>
																							</table>
																								</form>				









<%@include file="footer-area.jsp"%>
</body>
</html>