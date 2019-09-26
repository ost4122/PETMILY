<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
#writeform{
	margin-top:100px;
	margin-left:680px;
}
</style>
<body>

<%@include file="header-area.jsp"%>
<br>
<form action="ar_write" method="post" id="writeform">
<table>
<h1>분양 후기</h1>
<tr>
<td>글 번호<input type="hidden" name="ar_number"></td>
</tr>
<tr>
<td> 펫번호 <input type="hidden" name="pet_number"></td>
</tr>
<tr>
<td> 펫종류 <input type="hidden" name="pet_kategorie"></td>
</tr>
<tr>
<td> 펫종 <input type="hidden" name="pet_name"></td>
</tr>
<tr>
<td> 작성자 <input type="text" name="ar_writer" value="${sessionScope.sessionId}" readonly></td>
</tr>
<tr>
<td> 글 제목  <input type="text" name="ar_title" required></td>
</tr>
<tr>
<td> 작성날짜 <input type="hidden" name="ar_writedate"></td>
</tr>
<tr>
<td> 조회수 <input type="hidden" name="ar_viewhit"></td>
</tr>
<tr>
<td> 글 내용 <textarea cols="50" rows="10" name="ar_contents" required></textarea></td>
</tr>
<tr>
<td><input type="submit" value="작성완료">
<input type="reset" value="다시작성"></td>
</tr>
</table>
</form>
<a href="ar_list?page=0">목록으로</a>
<br>
<br>
<%@include file="footer-area.jsp"%>
</body>
</html>