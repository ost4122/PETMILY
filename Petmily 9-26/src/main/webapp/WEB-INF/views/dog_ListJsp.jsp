<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

#ca{
display:block;
border: 8px solid #FF6464;
border-radius: 10px;
margin : 30px;
clear : nono;
-moz-border-radius: 7px;
-khtml-border-radius: 7px;
-webkit-border-radius: 7px;
float : left;
}

#ca:hover{
display:block;
margin : 30px;
float : left;
clear : nono;
border: 8px solid #800080;
border-radius: 10px;
-moz-border-radius: 7px;
-khtml-border-radius: 7px;
-webkit-border-radius: 7px;
margin : 30px;

}

.gmd{ 
text-align:center;
}

</style>

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>


</script>
</head>
<body>
<jsp:include page="header-area.jsp" flush="true"></jsp:include>
<br>

<div class="gmd">
<h2>미분양된 강아지 리스트all </h2><br>

<a href="">분양가격순정렬</a>
<a href="">인기순</a>
<a href="">최신순</a> <br><!-- 꼭해야하나  -->

<a id="1" href="Pet_Dog_Big?page=1">대형견</a> <a id="2" href="Pet_Dog_Medium?page=1">중형견 </a><a id ="3" href="Pet_Dog_Small?page=1">소형견</a>
</div>
<c:forEach var="List" items="${Pet_Dog_allList}">

<div id="ca">
<a href="Pet_Dog_View?pet_number=${List.pet_number}&page=1"><img style="width:300px; height:400px;" src="${pageContext.request.contextPath}/resources/petUploadFile/${List.pet_profile}"></a><br>

<c:choose>
<c:when test="${(sessionScope.sessionHit eq '1')}">
<a href="javascript:like_func2(${List.pet_number});"><img style="width:30px; height :30px;" src="${pageContext.request.contextPath}/resources/petUploadFile/하투투.png">${List.pet_hit}</a>
</c:when>
<c:otherwise>
<a href="javascript:like_func(${List.pet_number});"><img style="width : 30px; height :30px;" src="${pageContext.request.contextPath}/resources/petUploadFile/하투.JPG">${List.pet_hit}</a><br>
</c:otherwise>
</c:choose>
${List.pet_name}
</div>

</c:forEach>

<!-- 페이징 처리부분 -->
<c:if test="${paging.page<=1}">
&nbsp; 
	[이전]&nbsp; 
</c:if>
<c:if test="${paging.page>1}">
	<a href="Pet_Dog_List?page=${paging.page-1}">[이전]</a>&nbsp;
</c:if>

<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
	<c:choose>
	
		<c:when test="${i eq paging.page}">
			${i}
		</c:when>
		<c:otherwise> 
		<a href="Pet_Dog_List?page=${i}">${i}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:if test="${paging.page>=paging.maxPage}">
[다음]
</c:if>
<c:if test="${paging.page<paging.maxPage}">
<a href="Pet_Dog_List?page=${paging.page+1}">[다음]</a>
</c:if>

<br>
<%@include file="footer-area.jsp"%>
</body>
</html>