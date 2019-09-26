<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
#myview {
	margin: 0 auto;
	display: table;
	text-align-last: justify;
	text-align-last: center;
}

tr, td {
	width: 250px;
	height: 80px;
	font-size: x-large;
}

#modi {
	margin: 0 auto;
	display: table;
	font-size: x-large;
}
form {
	margin: 0 auto;
	display: table;
}
</style>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
	//우편번호를 확인하기위한 함수 선언
	function Join_Address() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById("sample4_roadAddress").value = roadAddr;
						document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

						var guideTextBox = document.getElementById("guide");
						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							guideTextBox.innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';
							guideTextBox.style.display = 'block';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							guideTextBox.innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';
							guideTextBox.style.display = 'block';
						} else {
							guideTextBox.innerHTML = '';
							guideTextBox.style.display = 'none';
						}
					}
				}).open();
	}

	$(document).ready(function() {

		$("input#phone").blur(function() {

			var num = $("#phone").val();

			blur(num)

		});

		$("input#phone").click(function() {

			var num = $("#phone").val();

			focus(num);

		});

	});

	function focus(num) {

		num = num.replace(/[^0-9]/g, '');

		$("#phone").val(num);

	}

	function blur(num) {

		num = num.replace(/[^0-9]/g, '');

		var tmp = '';

		tmp += num.substr(0, 3);

		tmp += '-';

		tmp += num.substr(3, 4);

		tmp += '-';

		tmp += num.substr(7);

		$("#phone").val(tmp);

	}

	function email_change() {

		if (document.join.selectemail.options[document.join.selectemail.selectedIndex].value == '0') {

			document.join.email_2.disabled = true;

			document.join.email_2.value = "";

		}

		if (document.join.selectemail.options[document.join.selectemail.selectedIndex].value == '9') {

			document.join.email_2.disabled = false;

			document.join.email_2.value = "";

			document.join.email_2.focus();

		} else {

			document.join.email_2.disabled = true;

			document.join.email_2.value = document.join.selectemail.options[document.join.selectemail.selectedIndex].value;

		}

	}
</script>
	
<body>
<%@include file="header-area.jsp"%>
<form action="memberModify2" name="join" method="post"
		 >
		<h1>정보 수정</h1>
		<br> <strong>아이디 </strong><br> <input  name="id"
			id="id" value="${memberModify.id}" readonly /><span id="confirmId"></span><br>
		 <strong>이름</strong><br> <input name="name" id="name" value="${memberModify.name}" readonly ><br>
		<strong>핸드폰 </strong><br> <input type="text" name="phone"
			id="phone" maxlength="13" value="${memberModify.phone}" required placeholder=" ('-'없이 번호만 입력해주세요)"
			style="width: 230px;"><br> <strong>생년월일 </strong><br>
		<input type="date" name="birth" id="birth" value="${memberModify.birth}"><br> <strong>주소
		</strong><br> <input type="text" id="sample4_postcode"
			name="address_number" placeholder="우편번호" value="${memberModify.address_number}" readonly> <input
			type="button" onclick="Join_Address()" value="우편번호 찾기"><br>
		<strong>도로명주소 </strong><br> <input type="text"
			id="sample4_roadAddress" name="address_a" style="width: 300px;"
			placeholder="도로명주소" value="${memberModify.address_a}" readonly><br> <strong>지번주소
		</strong><br> <input type="text" id="sample4_jibunAddress"
			name="address_b" style="width: 300px;" placeholder="지번주소" value="${memberModify.address_b}" readonly><br>
		<span id="guide" style="color: #999; display: none"></span> <strong>상세주소
		</strong><br> <input type="text" id="sample4_detailAddress"
			name="address_c" style="width: 300px;" placeholder="상세주소" value="${memberModify.address_c}" required><br>
		<Strong>이메일</Strong><br> <input type="text" name="email_1"
			onfocus="this.value='';">@ <input type="text" name="email_2"
			value="" disabled> <select name="selectemail"
			onchange="email_change()">
			<option value="0">선택하세요</option>
			<option value="9">직접입력</option>
			<option value="daum.net">daum.net</option>
			<option value="naver.com">naver.com</option>
			<option value="gmail.com">gmail.com</option>
			<option value="nate.com">nate.com</option>
		</select><br> <input type="submit" value="정보 수정하기 ">
			</form>
<%@include file="footer-area.jsp"%>
</body>
</html>