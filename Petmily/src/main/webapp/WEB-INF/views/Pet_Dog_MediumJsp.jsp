<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#1{
border: 8px solid #FF6464;
border-radius: 10px;
display:block;
margin : 30px;
clear : nono;
-moz-border-radius: 7px;
-khtml-border-radius: 7px;
-webkit-border-radius: 7px;
float : left;

}
#1:hover{
float : left;
clear : nono;
border: 8px solid #800080;
border-radius: 7px;
-moz-border-radius: 7px;
-khtml-border-radius: 7px;
-webkit-border-radius: 7px;
display:block;
margin : 30px;
}



</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
/* 좋아요 */
function like_func(num){
 
console.log("pet_number값은 넘어가냐 ㅅㅂ"+pet_number);
var pet_number = num;
  $.ajax({
	type: "GET",
    url: "liketo",
    data:{"pet_number" : pet_number},
     dataType: "text",
    success: function(data){
    	var output ="";
    if(data =="1"){
    	output += "<img src='"${pageContext.request.contextPath}+"/resources/petUploadFile/하투투.png'>";
    	} else { 
    	output += "<img src='"${pageContext.request.contextPath}+"/resources/petUploadFile/하투.JPG'>";
    }      
    $("#a").html(output);
    },
    error: function(){
      alert("아주뭔가 잘못이야");
    }
  });
}

/* 분ㅇ양추천 취소 */
function like_func2(num){
 console.log("pet_number값은 넘어가냐 ㅅㅂ"+pet_number);
 var pet_number = num;
  $.ajax({
	type: "GET",
    url: "likecancle",
    data:{"pet_number" : pet_number},
     dataType: "text",
    success: function(data){
    	var output ="";
    if(data =="0"){
    	output += "<img src='"${pageContext.request.contextPath}+"/resources/petUploadFile/하투.JPG'>";
    	} 
    
    if(data =="1"){
    		output += "<img src='"${pageContext.request.contextPath}+"/resources/petUploadFile/하투투.png'>";
    }      
    $("#1").html(output);
    },
    error: function(){
      alert("아주뭔가 잘못이야2");
    }
  });
}

</script>
</head>
<body>
<%@include file="header-area.jsp"%>
<h2>중형견 리스트  </h2>
<a href="Pet_name?pet_name=불독">불독</a>
<a href="Pet_name?pet_name=코카스파니엘">코카스파니엘</a>
<a href="Pet_name?pet_name=웰시코기">웰시코기</a>
<a href="Pet_name?pet_name=보더콜리">보더콜리</a>
<a href="Pet_name?pet_name=샤페이">샤페이</a>
<a href="Pet_name?pet_name=사모예드">사모예드</a>
<a href="Pet_name?pet_name=비글">비글</a>
<a href="Pet_name?pet_name=스피츠">스피츠</a>
<a href="Pet_name?pet_name=시바이누">시바이누(시바견)</a>
<a href="Pet_name?pet_name=퍼그">퍼그</a>
<a href="Pet_name?pet_name=보스턴테리어">보스턴테리어</a>
<c:forEach var="List" items="${Pet_Dog_Medium}">
<div id="1">
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
<c:if test="${paging.page<=1}">
	[이전]&nbsp; 
	
</c:if>
<c:if test="${paging.page>1}">
	<a href="Pet_Dog_Medium?page=${paging.page-1}&pet_number=${view.pet_number}">[이전]</a>
</c:if>

<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
	<c:choose>
	
		<c:when test="${i eq paging.page}">
			${i}
		</c:when>
		<c:otherwise> 
		<a href="Pet_Dog_Medium?page=${i}&pet_number=${view.pet_number}">${i}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:if test="${paging.page>=paging.maxPage}">
[다음]
</c:if>
<c:if test="${paging.page<paging.maxPage}">
<a href="Pet_Dog_Medium?page=${paging.page+1}&pet_number=${view.pet_number}">[다음]</a>
</c:if>

<%@include file="footer-area.jsp"%>
</body>
</html>