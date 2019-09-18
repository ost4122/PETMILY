<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table{
border-top: 1px solid #444444;
border-collapse: collapse;
 width: 1000px;
height: 150px;
}
 th, td {
border-bottom: 1px solid #FFC7AD;
 padding: 10px;
text-align: center;
      }
 th{
 background: #FFC7AD;
 }
body{

text-align: center;
}

#a{
display:block;
  width:60px;
  height:55px;
 display: block; 
   margin: 30px auto;


}
#b{
text-align: center;
display:block;
}
#commentList{
padding: 5px;
text-align :center;
display:block;
}
#view{
padding: 5px;
text-align :center;
display:block;
margin:50px auto;
}
#view label{ 
color : white;
margin-top: 10px;}


h1{
background-color : #034B61;
background: #FFC7AD;
text-align :center;
display:block;
padding: 5px;
}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(document).ready(function(){
	
	$('#commentWriteBtn').click(function(){
	   var commentContents = $("#commentContents").val();
	   var commentWriter = $("#commentWriter").val();
	   var commentBoardNumber ="${view.pet_number}";
	   var sessionid ="${sessionScope.sessionId}";
	   var commentModifyBtn = $("#commentModifyBtn");
	   var commentDeleteBtn = $("#commentDeleteBtn");
	   var commentSecret = $('#commentSecret').is(":checked");
	   var page = "${paging.page}";
	    console.log('게시글 번호 : '+commentBoardNumber);
	    // 한줄댓글 내용이 필수이므로 검사
          if(commentContents== '') {
                alert("내용은 필수 입력입니다.");
                 $(commentContents).focus(); return; }
	    if(sessionid==''){
	    	alert("로그인시 이용할수 있습니다.");
            $(commentContents).focus(); return; }
	    if(commentSecret){
	    	   $('#commentSecret').val('secret');
	     }
	    else{
	    	 $('#commentSecret').val('no');
	    }
  $.ajax({
		   type :"get",
	      url : "commentWrite",
	      data : {"commentContents" : commentContents,
	              "commentWriter" : commentWriter,
	              "comment_PET_NUMBER": commentBoardNumber,
	              "commentSecret" : commentSecret,
	              "page" : page },
	      dataType : "json",
	      success : function(result){
	    	   console.log("댓글 등록 성공!");
	    	   $("#commentContents").val("");
	    	    commentList();
	    },
	      error : function(){
	         alert("댓글등록실패");
	      }
	   });
	});
  
	$("#deletebtn").click(function(){
		$.ajax({
		type : "get",
		url : "boardDeleteAjax",
		data : {"pet_number" : "${paging.pet_number}"},
		dataType : "text",
		success : function(result){
		if(result=="OK"){
			alert("삭제성공.");
			commentList();
		}else{
			alert("삭제실패");
			history.back();
		}
		
		},
		error : function(){
			alert("ajax통신실패");
		}
	});  
 });
	
});

function commentModify(commentNumber){
	 console.log("수정commentNumber값 넘어오나여?"+commentNumber);
	var commentModifyBtn = $("#commentModifyBtn");
	var commentDeleteBtn = $("#commentDeleteBtn");
	var sessionid ="${sessionScope.sessionId}";
	var commentContents = $("#commentContents2").val();
	var commentWriter = $("#commentWriter").val();
	var commentNumber = commentNumber;
	console.log("수정commentContents값 넘어오나여?"+commentContents);
	console.log("수정commentWriter값 넘어오나여?"+commentWriter);
	$.ajax({
	type : "post",
	url :"commentModifyUpdate",
	data : {"commentNumber" : commentNumber,"commentContents":commentContents},
	dataType :"text",
	success:function(result){
	      if(result=="OK"){
		    	 alert("수정성공.");
		    	 commentList();
				}else{
					alert("수정실패");
					history.back();
				}
	      $("#commentList").html(output);
	},
	error : function(){
		alert("ajax통신실패");
	}
});
 }

function commentList(){
	 var commentNumber ="${cList.commentNumber}";
     var commentBoardNumber ="${view.pet_number}";
	 var sessionid ="${sessionScope.sessionId}";
	 var commentsDate = "${cList.commentsDate}";
	 var commentWriter="${cList.commentWriter}"
	 var commentSecret ="${cList.commentSecret}";
	 var page = "${paging.page}";
	 console.log("page????????????"+page);
	  $.ajax({
		   type :"post",
	      url : "commentList2",
	      data : { "comment_PET_NUMBER" : commentBoardNumber ,"page" :page},
	      dataType : "json",
	      success : function(result){
	    		var output="";
	        for(var i in result){
	        	 if(result[i].commentSecret=="secret" && sessionid!= result[i].commentWriter){
	        		 output += "<ol>";
	        		 output += "<p>";
	        		 output += "비밀댓글입니다.";
	        	 }else{
	        		    output += "<ol>";
		 	        	output += "<p>";
		 	        	output+= "("+ result[i].commentNumber +")";
		 	        	output += result[i].commentsDate+"<br>";
		 	            output +=result[i].commentWriter+"<br>";
		 	            output +=result[i].commentContents+"<br>";
		 	            if(sessionid ==result[i].commentWriter){
		 	            	output +="<button id='commentModifyBtn' onclick='commentModifyList("+commentNumber+")'>"+"수정"+
		 	            	"</button>"+"<button id='commentDeleteBtn' onclick='commentDelete("+commentNumber+")'>"+"X"+"</button>";
		 	             }
	        	 }
	        	  output += "</p>";
	             output += "</ol>";
	            
	       }
	      
	        if (page<=1){
           	 output += "[이전]";
           	 output += "&nbsp;";
            }
            if (page>1){
           	output += "<a href='Pet_Dog_View?page="+"page-1"+"&"+"pet_number="+'${view.pet_number}'+"'>" +"[이전]"+"</a>";
            }
            var begin="${paging.startPage}";
            var end = "${paging.endPage}";
            var step = "1";
            for(var i = "${paging.startPage}" ; i<"${paging.endPage}"; i++){
            	if(i==page){
            		output += i;
            		 output += "&nbsp;";
            	}
            	else{
            	output += "<a href='Pet_Dog_View?page="+"${paging.page}"+"&"+"pet_number="+'${view.pet_number}'+"'>"+i+"</a>";
            	 output += "&nbsp;";
            	}
            }
         
          if("${paging.page<paging.maxPage}"){
        	 output += "<a href='Pet_Dog_View?page="+'${paging.page+1}'+"&"+"pet_number="+'${view.pet_number}'+"'>"+"[다음]"+"</a>";
        	 output += "&nbsp;";
          }
          $("#commentList").html(output);
          
	      },
	      error : function(){
	         alert("댓글리스트불러오기실패");
	      }
	   });
}

function commentModifyList(num){
	  console.log("수정num값 넘어오나여?"+num);
	  var commentNumber ="${cList.commentNumber}";
	   var commentContents = $("#commentContents").val();
	   var commentWriter = $("#commentWriter").val();
	   var commentBoardNumber = "${view.pet_number}";
	   var sessionid ="${sessionScope.sessionId}";
	   var commentModifyBtn = $("#commentModifyBtn");
	   var commentDeleteBtn = $("#commentDeleteBtn");
	   var commentNumber = num;
	   var commentDate = "${cList.commentDate}";
	   var page = "${paging.page}";
	   
	     $.ajax({
		   type :"post",
	      url : "commentList2",
	      data : { "comment_PET_NUMBER" : commentBoardNumber , "commentNumber" : commentNumber ,"page" :page},
	      dataType : "json",
	      success : function(result){
	    	var output="";
	    	 for(var i in result){
	    		 var ha= result[i].commentContents;
	        	  var ah =result[i].commentWriter;
	        	    output += "<ol>";
	        		output += "<p>";
	        		output += "("+ result[i].commentNumber +")";
	        	    output += result[i].commentsDate+"<br>";
		            output += result[i].commentWriter;
		          if(commentNumber==result[i].commentNumber){
		            	 output +="<input type='text' value='"+result[i].commentContents+"'id='commentContents2'>";
		            	 if(sessionid == ah){
		            		 output +="<button id='commentModifyBtn' onclick='commentModify("+commentNumber+")'>"+"수정"+
			 	            	"</button>"+"<button id='commentDeleteBtn' onclick='commentDelete("+commentNumber+")'>"+"X"+"</button>";
		             }
		            }else{
		            	output += result[i].commentContents;
		            }
		            output += "</p>";
		            output += "</ol>";
		   }
	          $("#commentList").html(output);
	      },
	      error : function(){
	         alert("댓글수정리스트불러오기실패");
	      }
	   });
}
function commentDelete(num){
	  console.log("num값 넘어오나여?"+num);
	   var commentNumber = num;
	   $.ajax({
		type : "get",
		url :"commentDelete",
		data : {"commentNumber" : commentNumber},
		dataType : "text",
		success : function(result){
			if(result=="OK"){
				alert("삭제성공.");
				commentModifyList();
			}else{
				alert("삭제실패");
				history.back();
			}
			},
			error : function(){
			alert("ajax통신실패");
		}
	}); 

}

/* 좋아요 */
function like_func(){
  var pet_number ="${view.pet_number}";
console.log("pet_number값은 넘어가냐 ㅅㅂ"+pet_number);
var shit = "${sessionScope.sessionHit}";

	 $.ajax({
			type: "post",
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
function like_func2(){
  var pet_number = "${view.pet_number}";
 console.log("pet_number값은 넘어가냐 ㅅㅂ"+pet_number);
  $.ajax({
	type: "GET",
    url: "likecancle",
    data:{"pet_number" : pet_number},
     dataType: "text",
    success: function(data){
    	var output ="";
    if(data =="0"){
    	output += "<img src='"${pageContext.request.contextPath}+"/resources/petUploadFile/하투.JPG'>";
    	} else {
    		output += "<img src='"${pageContext.request.contextPath}+"/resources/petUploadFile/하투투.png'>";
    }      
    $("#a").html(output);
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
<div id = "b">
<table style="margin-left: auto; margin-right: auto;">
<tr>
<th>견종</th> <th>나이</th> <th>분양 가</th> 
</tr>
<tr>
<td>${view.pet_name}</td>
<td>${view.pet_live}</td>
<td>${view.pet_price}</td>
</tr>
</table>
<br>
소개글 : ${view.pet_contents}
<br>
<iframe width="400" height="305" src="${view.pet_trailer}?autoplay=1" frameborder="0"></iframe><br>
<img id="hi" src="${pageContext.request.contextPath}/resources/petUploadFile/${view.pet_profile}">
<br>
<div id = "a">
<c:choose>
<c:when test="${(sessionScope.sessionHit eq '1')}">
<a href="javascript:like_func2();"><img id="1" src="${pageContext.request.contextPath}/resources/petUploadFile/하투투.png">${view.pet_hit}</a>
</c:when>
<c:otherwise>
<a href="javascript:like_func();"><img id="2" src="${pageContext.request.contextPath}/resources/petUploadFile/하투.JPG">${view.pet_hit}</a>
</c:otherwise>
</c:choose>
</div>
</div>
<h1>분양	Q&A </h1>
<div id="commentArea">
<div id="view">
<input type="text" id="commentWriter" value="${sessionScope.sessionId}" readonly />
<input type="hidden" id="pet_number" value="${view.pet_number}" />
<textarea rows="5" cols="30" id ="commentContents" placeholder="댓글내용"></textarea>
비밀댓글 <input type="checkbox" id="commentSecret"/><br>
<button id="commentWriteBtn">댓글작성</button>
</div>
</div>
<div id="commentList">
<c:forEach items="${commentList}" var ="cList">
<c:choose>
<c:when test="${(cList.commentSecret eq 'secret' && sessionScope.sessionId ne cList.commentWriter)}">
<p>비밀댓글입니다.</p>
</c:when>
<c:otherwise>
<ol>
<p>
댓글 <br>
(${cList.commentNumber})
${cList.commentsDate}<br>
작성자 : ${cList.commentWriter} <br>
${cList.commentContents}<br>
<c:if test="${(sessionScope.sessionId eq cList.commentWriter)}">
<button id="commentModifyBtn" onclick="commentModifyList(${cList.commentNumber})">수정</button>
<button id="commentDeleteBtn" onclick="commentDelete(${cList.commentNumber})">X</button><br>
</c:if>
</p>
</ol>
</c:otherwise>
</c:choose>
</c:forEach>


<!-- 댓글 페이징 처리  -->

<c:if test="${paging.page<=1}">
	[이전]&nbsp; 
	
</c:if>
<c:if test="${paging.page>1}">
	<a href="Pet_Dog_View?page=${paging.page-1}&pet_number=${view.pet_number}">[이전]</a>
</c:if>

<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
	<c:choose>
	
		<c:when test="${i eq paging.page}">
			${i}
		</c:when>
		<c:otherwise> 
		<a href="Pet_Dog_View?page=${i}&pet_number=${view.pet_number}">${i}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:if test="${paging.page>=paging.maxPage}">
[다음]
</c:if>
<c:if test="${paging.page<paging.maxPage}">
<a href="Pet_Dog_View?page=${paging.page+1}&pet_number=${view.pet_number}">[다음]</a>
</c:if>

</div>




<%@include file="footer-area.jsp"%>
</body>
</html>