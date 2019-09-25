<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petmily memberjoin</title>
<!-- Style CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/animate.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/owl.carousel.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/classy-nav.css">
</head>
<style>
form {
	margin: 0 auto;
	display: table;
}
</style>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function RegularId() {
		var idCheck = document.getElementById("id").value;
		var confirmId = document.getElementById("confirmId");
		var regExpId = /^[a-z]+[a-z0-9]{5,19}$/g;
		if (!regExpId.test($("input[name=id]").val())) {
			alert("아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.");
			return;
		}
		/*ajax: Asynchronous JavaScript and XML (비동기 처리를 위한 자바스크립트)
		ajax 는 JSON 형식으로 데이터 전송
		 - JSON (JavaScript Object Notation) : 자바 스크립트 객체 표기법
		 */

		$.ajax({
			type : "post", //메소드 (GET이냐 post냐)
			url : "Join_IdCheck", //목적지 : 컨트롤러 주소
			data : {
				"id" : idCheck
			}, //주소로 가지고 갈 데이터 ID 중복이면 ID 가져간다.
			dataType : "text", //가져갈때  형식 
			success : function(data) {
				console.log("성공??");
				console.log("data값 :" + data);
				if (data == "OK") {
					confirmId.style.color = "#00ff00";
					confirmId.innerHTML = "사용가능한 아이디 입니다.";
				} else {
					confirmId.style.color = "#ff0000";
					confirmId.innerHTML = "사용중인 아이디입니다.";
				}
			},
			error : function() {
				alert("idOverlap 함수 통신실패!!");
			}
		});

	}
	//비밀번호 정규식을위한 함수
	function RegularPassword() {
		var exp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$/;
		var frm = document.join;
		var password = document.getElementById("password");
		if (frm.password.value != frm.password.value.match(exp)) {
			alert("특수문자,영어,숫자 조합해서 8~12자 입력해주세요");
			frm.password.focus();
			return false;
		}
		return true;

	}
	function pwdExp() {
		//비밀번호 검증을 위한 정규식 선언

		var exp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$/;
		var password = document.getElementById("password");
		if (password.value.match(exp)) {
			passWord.style.color = "green";
			passWord.innerHTML = "사용가능";
			return true;
		} else {
			passWord.style.color = "red";
			passWord.innerHTML = "특수문자,영어,숫자 조합해서 8~12자 입력해주세요";
			return false;
		}
	}
	//비밀번호 정규식확인과 같은지 비교를 위한 함수 선언
	function passwordCheck() {
		var exp = exp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$/;
		var password = document.getElementById("password");
		var passwordCheck = document.getElementById("passwordCheck");
		if (password.value == passwordcheck.value) {
			passwordCheck.style.color = "green";
			passwordCheck.innerHTML = "일치함";
		} else {
			passwordCheck.style.color = "red";
			passwordCheck.innerHTML = "비밀번호를 동일하게 입력해주세요";
		}

	}
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
	<br>
	<form action="navermemberjoin" name="join" method="post"
		 onSubmit="return formValidate();">
		<h1>네이버 회원가입</h1>
		<br> <strong>아이디 </strong><br> <input type="text" name="id"
			id="id" onchange="RegularId();" required /><span id="confirmId"></span><br>
		<strong>비밀번호 </strong><br> <input type="password" name="password"
			id="password" onchange="pwdExp();" size=20 maxlength="12"
			required><span id="passWord"></span><br> <strong>비밀번호
			확인 </strong><br> <input type="password" name="passwordcheck"
			id="passwordcheck" onchange="passwordCheck();"
			placeholder="비밀번호를 입력하세요" size=20 axlength="12" style="width: 180px;"
			required><span id="passwordCheck"></span><br> <strong>이름
		</strong><br> <input type="text" name="name" id="name" value="${naverName}" required readonly><br>
		<strong>휴대전화 </strong><br> <input type="text" name="phone"
			id="phone" maxlength="13" required placeholder=" ('-'없이 번호만 입력해주세요)"
			style="width: 230px;"><br> <strong>생년월일 </strong><br>
		<input type="text" name="birth" id="birth" value="${naverBirthday}"readOnly><br> <strong>주소
		</strong><br> <input type="text" id="sample4_postcode"
			name="address_number" placeholder="우편번호" readonly> <input
			type="button" onclick="Join_Address()" value="우편번호 찾기"><br>
		<strong>도로명주소 </strong><br> <input type="text"
			id="sample4_roadAddress" name="address_a" style="width: 300px;"
			placeholder="도로명주소" readonly><br> <strong>지번주소
		</strong><br> <input type="text" id="sample4_jibunAddress"
			name="address_b" style="width: 300px;" placeholder="지번주소" readonly><br>
		<span id="guide" style="color: #999; display: none"></span> <strong>상세주소
		</strong><br> <input type="text" id="sample4_detailAddress"
			name="address_c" style="width: 300px;" placeholder="상세주소" required><br>
		<Strong>이메일</Strong><br> <input type="text" name="email" value=" ${naverEmail}">
		<input type="submit" value="가입하기"> <input
			type="reset" value="다시쓰기">

	</form>
	<br>
	<%@include file="footer-area.jsp"%>
</body>
</html>