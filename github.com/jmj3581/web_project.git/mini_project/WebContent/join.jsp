<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" type="text/css" media="all" />
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	function chooseEmail(email) {
		if(email == "none") {
			document.join.email1.value="";
			document.join.email1.readOnly = true;
			document.join.email.focus();
		} else if(email == "txt") {
			document.join.email2.value="";
			document.join.email1.readOnly = false;
			document.join.email1.focus();
		} else {
			document.join.email1.value = email;
			document.join.email1.readOnly = true;
		}
	}
	
	function idCheck() {
		window.open("Controller?action=checkId&userId="+document.join.userId.value,"아이디 중복 확인","width=400 height=150");
		//window.open("checkId.jsp","아이디 중복 확인","width=400 height=150");
		//window.location = "Controller?action=join&userId="+id;
	}
</script>
</head>
<body>
	<div id="wrapper" class="container">
	<%@ include file="topMenu.jsp" %>
		<div id="page"><h2>JOIN</h2>
			<form action="Controller?action=join" method="post" name="join">
				<input class="text_box" type="text" name="userId" placeholder="ID">
					<input type="button" class="postcode_box" value="중복체크" onclick="return idCheck()">
					${requestScope.message}<p><p>
				<input class="text_box" type="password" name="userPw" placeholder="Password"><p>
				<input class="text_box" type="text" name="name" placeholder="Name"><p>
				<select class="mobile_box" name="mobile1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="017">017</option>
						<option value="018">018</option>
					</select>
					-<input class="mobile_box" type="text" name="mobile2">
					-<input class="mobile_box" type="text" name="mobile3"><p>
					<input class="email_box" type="text" name="email" placeholder="이메일">
					@<input class="email_box" type="text" name="email1" readonly>
					<select class="email_box" name="email2" onchange="chooseEmail(this.value)">
						<option value="none">=선택=</option>
						<option value="naver.com">naver.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="nate.com">nate.com</option>
						<option value="empal.com">empal.com</option>
						<option value="txt">직접입력</option>
					</select><p>
					<input class="postcode_box" type="text" id="postcode" placeholder="우편번호" name="postcode" readonly>
					<input class="postcode_box" type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
					<input class="address_box" type="text" id="address" placeholder="주소" name="address" readonly>
					<input class="address_box" type="text" id="address2" placeholder="상세주소" name="address2">
					<p><input type="submit" value="Join" class="link-style"></p>
			</form>
		</div>
	<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>