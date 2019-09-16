<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petmily</title>

	<!-- Style CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css?ver=2" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/animate.css?ver=2" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css?ver=2" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css?ver=2" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/owl.carousel.css?ver=2" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/classy-nav.css?ver=2" >

</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/SmartEditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
/* 스마트 에디터 */
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "${pageContext.request.contextPath}/resources/SmartEditor/SmartEditor2Skin.html",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,         
              fOnBeforeUnload : function(){
                   
              }
          }, 
          fCreator: "createSEditor2"
      });
      
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
          oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#frm").submit();
      });    
});
 
 
 
</script>

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

<form action = "Shop_Input" method="post" enctype="multipart/form-data">
<table>

<tr> <td> 상품 이름 : <input type="text" name="item_name" required> </td> </tr>
<tr> <td> 상품 카테고리 : <select name="item_kind">
						<option value="강아지 용품">강아지 용품</option>
						<option value="고양이 용품">고양이 용품</option>
																</select> </td> </tr>
<tr> <td> 상품 종류 : <select name="item_katagorie">
						<option value ="장난감">장난감</option>
						<option value ="미용">미용</option>
						<option value ="옷/잡화">옷/잡화</option>
						<option value ="사료">사료</option>
						<option value ="기타">기타</option>
																</select> </td> </tr>
																
<tr> <td> 상품 가격 : <input type="text" name="item_price" required> </td> </tr>
<tr> <td> 상품 프로필 사진 : <input type="file" name="img" />  </td></tr>
<tr> <td> 상세 사진 : <input multiple="multiple" type="file" name="inputImages" id="InputImg"> </td> </tr>
<tr> <td> <div class="imgs_wrap" > </div> </td> </tr>


<tr> <td> 상품 상세내용 :</td> </tr>
</table>
 <textarea rows="40" cols="60" id="ir1" name="item_contents" style="width:650px; height:350px; "></textarea>
 
 <input type="submit" value="상품등록">

</form>







</body>
</html>