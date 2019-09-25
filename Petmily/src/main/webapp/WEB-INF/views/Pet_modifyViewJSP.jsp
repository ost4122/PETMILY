<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
function setVlues(){
	var pet_kategorie = document.getElementById("pet_kategorie");
	var tt = document.getElementById("pet_kategorie");
	tt.value = pet_kategorie.options[pet_kategorie.selectedIndex].text;
	     if (tt.value == "강아지"){
	   document.getElementById("dog_size").style.display="block";
	    }
	     if(tt.value =="고양이"){
	    	 document.getElementById("cat_name").style.display="block";
	     }
	     if(tt.value =="파충류"){
	    	 document.getElementById("reptile_name").style.display="block";
	     }

}

function setpet_size(){
	var pet_size = document.getElementById("pet_size");
	var tt = document.getElementById("pet_size");
	tt.value = pet_size.options[pet_size.selectedIndex].text;
	     if (tt.value =="소형견"){
	   document.getElementById("dog_name1").style.display="block";
	    }
	     if(tt.value =="중형견"){
	    	 document.getElementById("dog_name2").style.display="block";
	     }
	     if(tt.value =="대형견"){
	    	 document.getElementById("dog_name3").style.display="block";
	     }
}

//이미지 정보들을 담을 배열
function fileInfo(f){
	
	var file = f.files; 
	 var review_File = $("#pet_profile_file").val();
	 for(var i=0; i<file.length; i++){
  	var review_File =new FileReader(); 
		review_File.onload = function(rst){
			$('#img_box').append('<img src="' + rst.target.result + '">'+"<br>");
			}
		review_File.readAsDataURL(file[i]); 
		}
		
	
	  }


</script>
<style>
body{
text-align:center;
}
#cat_name,#reptile_name,#dog_size,
#dog_name1,#dog_name2,#dog_name3,#reptile_name { 
text-align:center;
 display: none; 
 }
</style>


</head>
<body>
<%@include file="header-area.jsp"%>
<h2>admin만 할수 있는 펫분양글 수정 페이지 </h2>
<form action="Pet_Modify" method="post" enctype="multipart/form-data">
<input type="hidden" name="pet_number" value="${Pet_modifyView.pet_number}" />

펫 종류 : 
<select name = "pet_kategorie" id ="pet_kategorie" onChange ="setVlues();">
<option selected ="selected">==선택==</option>
<option value="강아지">강아지</option>
<option value="고양이">고양이</option>
<option value="파충류">파충류</option>
</select><br>

<div id ="dog_size">
<select name = "pet_size" id ="pet_size" onChange ="setpet_size();">
<option value="" selected ="selected">===펫크기===</option>
<option value="소형견">소형견</option>
<option value="중형견">중형견</option>
<option value="대형견">대형견</option>
</select>
</div>

<div id ="dog_name1">
<select name ="pet_name">
<option value="" selected ="selected">===소형견품종===</option>
<option value="포메라니안">포메라니안</option>
<option value="치와와">치와와</option>
<option value="미니핀">미니핀</option>
<option value="파피용">파피용</option>
<option value="토이푸들">토이푸들</option>
<option value="닥스훈트">닥스훈트</option>
<option value="요크셔테리어">요크셔테리어</option>
<option value="말티즈">말티즈</option>
<option value="비숑프리제">비숑프리제</option>
<option value="슈나우저">슈나우저</option>
<option value="시츄">시츄</option>
<option value="아펜핀셔">아펜핀셔</option>
</select>
</div>

<div id ="dog_name2">
<select name ="pet_name">
<option value="">===중형견품종===</option>
<option value="불독">불독</option>
<option value="코카스파니엘">코카스파니엘</option>
<option value="웰시코기">웰시코기</option>
<option value="보더콜리">보더콜리</option>
<option value="샤페이">샤페이</option>
<option value="사모예드">사모예드</option>
<option value="비글">비글</option>
<option value="스피츠">스피츠</option>
<option value="시바이누">시바이누(시바견)</option>
<option value="퍼그">퍼그</option>
<option value="보스턴테리어">보스턴테리어</option>
</select>
</div>

<div id ="dog_name3">
<select name = "pet_name">
<option value="">===대형견품종===</option>
<option value="골든리트리버">골든리트리버</option>
<option value="그레이트데인">그레이트데인</option>
<option value="도베르만">도베르만</option>
<option value="그레이트피레니즈">그레이트피레니즈</option>
<option value="올드잉글리시쉽독">올드잉글리시쉽독</option>
<option value="시베리안허스키">시베리안허스키</option>
<option value="아이리쉬세터">아이리쉬세터</option>
<option value="포인터">포인터</option>
<option value="콜리">콜리</option>
<option value="아프간하운드">아프간하운드</option>
</select>
</div>


<div id ="cat_name">
<select name = "pet_name">
<option value="">===고양이품종===</option>
<option value="페르시안">페르시안</option>
<option value="메인쿤">메인쿤</option>
<option value="브리티쉬쇼트헤어">브리티쉬쇼트헤어</option>
<option value="먼치킨">먼치킨</option>
<option value="시암고양이">시암고양이</option>
<option value="아메리칸쇼트헤어">아메리칸쇼트헤어</option>
<option value="랙돌">랙돌</option>
<option value="아비시니안">아비시니안</option>
<option value="러시안블루">러시안블루</option>
</select>
</div>
<div id ="reptile_name">
<select name ="pet_name">
<option value="">===파충류품종===</option>
<option value="육지거북이">육지거북이</option>
<option value="물거북이">물거북이</option>
<option value="비어디드레곤">비어디드레곤</option>
<option value="카멜레온">카멜레온</option>
<option value="레오파드게코">레오파드게코</option>
<option value="크레/가고일">크레/가고일</option>
<option value="이구아나/도마뱀">이구아나/도마뱀</option>
<option value="모니터">모니터</option>
<option value="콘/킹/밀크스네이크">콘/킹/밀크스네이크</option>
<option value="보아/파이톤">보아/파이톤</option>
<option value="양서류">양서류</option>
<option value="갑각류">갑각류</option>
</select>
</div>
<br>
 생후 개월수 :<input type="text" name ="pet_live" value="${Pet_modifyView.pet_live}"><br>
 분양가 : <input type="text" name ="pet_price" value="${Pet_modifyView.pet_price}"><br>
 <select name = "pet_adopt">
 <option value='' label="===분양 예약 유무===" selected ="selected">===분양 예약 유무===</option>
 <option value="0">무</option>
<option value="1">유</option>
 </select><br>
 <div style="text-align:center; margin:0 0 100px 0; background:whilt; line-height:60px;">
 프로필 사진 : <input type="file" name ="pet_profile_file" id ="pet_profile_file"
 style= "width:500px;" onchange="fileInfo(this)"><br>
 <div id="img_box"></div>
 </div>
 펫 소개 동영상링크를 넣으시오 
 <input type="text" name ="pet_trailer" value="${Pet_modifyView.pet_trailer}"><br>
 펫 소개글 :<input type="text" name ="pet_contents" placeholder="소개글" value="${Pet_modifyView.pet_contents}"><br>
펫 접종 :  <input type="text" name ="pet_inoculation" value="${Pet_modifyView.pet_inoculation}"><br>
  <input type="submit" value="수정하기"><br>
</form>


<%@include file="footer-area.jsp"%>
</body>
</html>