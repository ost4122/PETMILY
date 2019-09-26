<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
body{
text-align:center;

}

  table {
  float : center;
  
    width: 1200px;
    border-top: 1px solid black;
    text-align: center;
    margin-left: auto; 
    margin-right: auto;

  }
  .hiaa th{
   border-bottom: 3px solid black;
    padding: 10px;
     text-align:center;
  }
  .hiaa td{
    border-bottom: 1px solid #ddd;
     text-align:center;
  
  }
  .hiaa tr:hover td {
  color: #004;
}

</style>
</head>
<body>
<%@include file="header-area.jsp"%>
<h2>공지사항 게시판 </h2>
<a href="admin_Notice_WritView">공지사항 쓰러가기</a><br>

<table class="hiaa">
<tr>
<th>NO</th> <th>제목</th>  <th>작성자</th>
</tr>
<c:forEach var="List" items="${noticeList}">
<tr>
<td>${List.notice_number}</td>
<td bgcolor="pink"><a href="NoticeView?notice_number=${List.notice_number}">${List.notice_title}</a></td>
<td>${List.notice_writer}</td>
</tr>
</c:forEach>
</table>

<%@include file="footer-area.jsp"%>
</body>
</html>