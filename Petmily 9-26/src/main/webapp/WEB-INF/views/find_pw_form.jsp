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
			var selectemail = document.getElementById("selectemail").value;
			$.ajax({
				url : "Login_PasswordFind",
				type : "POST",
				data : {
					id : $("#id").val(),
					email_1 : $("#email_1").val(),
					email_2 : selectemail,
					name : name

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
</script>
<title>비밀번호 찾기</title>
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
				<h3>비밀번호 찾기</h3>
			</div>
			<div>
				<form name="join">
					<label>ID</label> <input type="text" id="id" name="id" required><br>
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

				<button type="button" id="findBtn">비밀번호찾기</button>
				<button type="button" onclick="history.go(-1);">뒤로가기</button>
			</div>
		</div>
	</div>
</body>
</html>