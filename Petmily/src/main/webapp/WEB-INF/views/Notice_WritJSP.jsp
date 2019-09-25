<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
//이미지 정보들을 담을 배열
function fileInfo(f){
   
   var file = f.files; 
    var review_File = $("#notice_FILE").val();
    for(var i=0; i<file.length; i++){
     var review_File =new FileReader(); 
      review_File.onload = function(rst){
         $('#img_box').append('<img src="' + rst.target.result + '">'+"<br>");
         }
      review_File.readAsDataURL(file[i]); 
      }
      
   
     }

</script>
</head>
<body>
<%@include file="header-area.jsp"%>
<br>
<h2>공지사항 글쓰기 페이지 </h2>

<form action="admin_Notice_Writ" method="post" enctype="multipart/form-data">
제목 : <input type="text" name ="notice_title"><br>
작성자 : <input type="text" name ="notice_writer"><br>
본문 내용 : <input type="text" name ="notice_contents"><br>

<div style="text-align:center; margin:0 0 100px 0; background:whilt; line-height:60px;">
본문 이미지 :  <input type="file" name ="notice_filename" id ="notice_filename"
 style= "width:500px;" onchange="fileInfo(this)"><br>
 <div id="img_box"></div>
 
 </div>
  <input type="submit" value="공지사항올리기"><br>
</form>
<br>
<%@include file="footer-area.jsp"%>
</body>
</html>