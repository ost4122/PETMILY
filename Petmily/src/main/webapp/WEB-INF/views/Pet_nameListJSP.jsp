<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header-area.jsp"%>

<h2> 펫 종류별로 검색기능 </h2>
<c:forEach var="List" items="${Pet_nameList}">
<div id="su">
<a href="Pet_Dog_View?pet_number=${List.pet_number}&page=1"><img style="width:300px; height:400px;"src="${pageContext.request.contextPath}/resources/petUploadFile/${List.pet_profile}"></a><br>

<c:choose>
<c:when test="${(sessionScope.sessionHit eq '1')}">
<a href="javascript:like_func2(${List.pet_number});"><img style="width:10px; height :20px;" src="${pageContext.request.contextPath}/resources/petUploadFile/하투투.png">${List.pet_hit}</a>
</c:when>
<c:otherwise>
<a href="javascript:like_func(${List.pet_number});"><img style="width : 10px; height :20px;" src="${pageContext.request.contextPath}/resources/petUploadFile/하투.JPG">${List.pet_hit}</a><br>
</c:otherwise>
</c:choose>

${List.pet_name}
</div>
</c:forEach>
<%@include file="footer-area.jsp"%>
</body>
</html>