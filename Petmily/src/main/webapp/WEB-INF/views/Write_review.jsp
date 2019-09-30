<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>



<style>
.review-textArea {
	width: 100%;
	height: 146px;
	/* border: 0; */
	resize: none;
	background-color: #f2f4f8;
}

.reviewTD {
	width: 200px;
}

form {
	margin-left: 100px;
}
.checkboxTitle{
	width: 200px;
	display: inline-block;
	float:right;
}
.checkbox{
	width: 200px;
	display: inline-block;
	float:right;

}
.subTitle{
	    margin-left: 117px;
}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
/* 사진미리보기 */
var sel_files= [];

$(document).ready(function(){
		$("#InputImg").on("change",handleImgsFilesSelect);
});

function handleImgsFilesSelect(e){
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	
	filesArr.forEach(function(f){
		if(!f.type.match("image.*")){
			alert("확장자는 이미지 확장자만 가능합니다.");
			return;
		}
		
		sel_files.push(f);
		
		var reader = new FileReader();
		reader.onload = function(e){
			var img_html = "<img src=\""+e.target.result+"\" width='150px;'/>";
			$(".imgs_wrap").append(img_html);
		}
		reader.readAsDataURL(f);
	});
}



</script>

<body>
	<%@include file="header-area.jsp"%>

	<hr>

	<h2>구매한 상품</h2>
	<ul>
		<li><img
			src="${pageContext.request.contextPath}/resources/shopIMG/${purchase.item_profile}"
			width="300px" height="200px" /> <strong>${purchas.item_name }</strong>

		</li>
	</ul>
	<hr>

	<!-- 구매후기 작성란 -->
	<form action="write_review" method="post" enctype="multipart/form-data">
		<table class="review_table">
			<tbody>
				<tr>
					<td class="reviewTD"><strong>구매 후기</strong></td>
					<td><textArea name="review_contents" class="review-textArea"
							rows="5" cols="70" placeholder="자세한 구매후기는 고객의 구매에 많은 도움이 됩니다."></textArea></td>
				</tr>
				<!-- 사진 첨부 -->
				<tr>
					<td><strong>사진 첨부 </strong> <input multiple="multiple"
						type="file" name="inputImages" id="InputImg"></td>
					<td> <div class="imgs_wrap" > </div> </td>	
				</tr>
			<tr>
				<!-- 상품 평가 -->
				<tr>
				<td>
				<strong>평가</strong>
				<div class="checkboxTitle">
					<strong>디자인</strong></div>
					</td> </tr>
					<tr><td>
					<div class="checkbox">
						<input type="radio" name="review_design" value="아주 마음에 들어요"/> 아주마음에 들어요<br/>
						<input type="radio" name="review_design" value="마음에 들어요"/> 마음에 들어요<br/>
						<input type="radio" name="review_design" value="마음에 들지 않아요"/> 마음에 들지 않아요
					</div>
						</td></tr>
					<tr><td>
					
						<strong class="subTitle">편리성</strong></td></tr>
						<tr><td>
					<div class="checkbox">
						<input type="radio" name="review_handiness" value="사용하기 편리해요"/> 사용하기 편리해요<br/>
						<input type="radio" name="review_handiness" value="그저 그래요"/> 그저 그래요<br/>
						<input type="radio" name="review_handiness" value="사용하기 불편해요"/> 사용하기 불편해요
					</div>
						</td></tr>
						
			</tbody>
		</table>
						
						<input type ="hidden" name= item_number value="${purchase.item_number }"/>
						<input type="submit" value="리뷰등록" class="inputBtn"/>
	</form>









	<%@include file="footer-area.jsp"%>
</body>
</html>