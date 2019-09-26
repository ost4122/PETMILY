<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#idfind {
	margin: 0 auto;
	display: table;
}
</style>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(function() {
		$("#findBtn").click(function() {
			var name = document.getElementById("name").value;
			var selectemail = document.getElementById("selectemail").value;
			$.ajax({
				url : "Login_IdFind",
				type : "POST",
				data : {
					name : name,
					phone : $("#phone").val(),
					email_1 : $("#email_1").val(),
					email_2 : selectemail,
				},
				success : function(result) {
					alert(result);
				},
			});
		});
	});
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
</script>
<title>아이디 찾기</title>
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

<body>
	<%@include file="header-area.jsp"%>
	<div class="w3-content w3-container w3-margin-top">
		<div id="idfind">
			<div class="w3-center w3-large w3-margin-top">
				<h3>아이디 찾기</h3>
			</div>
			<div>
				<form name="join">
					<strong>이름</strong><br> <input type="text" id="name"
						name="name" required><br> <strong>휴대전화 </strong><br>
					<input type="text" name="phone" id="phone" maxlength="13" required
						placeholder=" ('-'없이 번호만 입력해주세요)" style="width: 230px;"><br>
					<Strong>이메일</Strong><br> <input type="text" name="email_1"
						id="email_1" onfocus="this.value='';">@ <input type="text"
						name="email_2" id="email_2" value="" disabled> <select
						name="selectemail" id="selectemail" onchange="email_change()">
						<option value="0">선택하세요</option>
						<option value="9">직접입력</option>
						<option value="daum.net">daum.net</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="nate.com">nate.com</option>
					</select><br>
				</form>

				<button type="button" id="findBtn">아이디찾기</button>
				<button type="button" onclick="history.go(-1);">뒤로가기</button>
			</div>
		</div>
	</div>

	<%@include file="footer-area.jsp"%>
</body>
</html>