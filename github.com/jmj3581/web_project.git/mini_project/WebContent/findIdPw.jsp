<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript">
	function actionChange(state) {
		if(state == 'findId') {
			document.findForm.action='Controller?action=findId';
			document.findForm.submit();
		} else {
			document.findForm.action='Controller?action=findPw';
			document.findForm.submit();
		}
	}
	
	function btnChk(value) {
		if (value == "1") {
			document.getElementById('chk1').style.display = ""; // ���̰�
			document.getElementById('chk2').style.display = "none";
		} else {
			document.getElementById('chk1').style.display = "none"; // ���̰�
			document.getElementById('chk2').style.display = "";
		}
	}
	
	function btnChk2(value) {
		if (value == "3") {
			document.getElementById('chk3').style.display = ""; // ���̰�
			document.getElementById('chk4').style.display = "none";
		} else {
			document.getElementById('chk3').style.display = "none"; // ���̰�
			document.getElementById('chk4').style.display = "";
		}
	}
	
	function chooseEmail(email) {
		if(email == "none") {
			document.findForm.email1.value="";
			document.findForm.email1.readOnly = true;
			document.findForm.email.focus();
		} else if(email == "txt") {
			document.findForm.email2.value="";
			document.findForm.email1.readOnly = false;
			document.findForm.email1.focus();
		} else {
			document.findForm.email1.value = email;
			document.findForm.email1.readOnly = true;
		}
	}
	
	function chooseEmail2(email) {
		if(email == "none") {
			document.findForm.email4.value="";
			document.findForm.email4.readOnly = true;
			document.findForm.email3.focus();
		} else if(email == "txt") {
			document.findForm.email5.value="";
			document.findForm.email4.readOnly = false;
			document.findForm.email4.focus();
		} else {
			document.findForm.email4.value = email;
			document.findForm.email4.readOnly = true;
		}
	}
</script>
</head>
<body>
	<div id="wrapper" class="container">
		<%@ include file="topMenu.jsp" %>
		<form method="post" name="findForm">
			<div id="page"><h2>FIND ID</h2>
					<div>
						<input type="radio" name="type" value="1" onclick="btnChk(this.value)" checked>����ó�� ã��
						<input type="radio" name="type" value="2" onclick="btnChk(this.value)">�̸��Ϸ� ã��<br><p>
					</div>
					<div id="chk1">
						<input class="text_box" type="text" name="name1" placeholder="�̸�"><p><p>
						<select class="mobile_box" name="mobile1">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="017">017</option>
							<option value="018">018</option>
						</select>
						-<input class="mobile_box" type="text" name="mobile2">
						-<input class="mobile_box" type="text" name="mobile3"><p>
					</div>
					<div id="chk2" style="display:none;">
						<input class="text_box" type="text" name="name2" placeholder="�̸�"><p><p><p>
						<input class="email_box" type="text" name="email" placeholder="�̸���">
						@<input class="email_box" type="text" name="email1" readonly>
						<select class="email_box" name="email2" onchange="chooseEmail(this.value)">
							<option value="none">=����=</option>
							<option value="naver.com">naver.com</option>
							<option value="daum.com">hanmail.net</option>
							<option value="nate.com">nate.com</option>
							<option value="empal.com">empal.com</option>
							<option value="txt">�����Է�</option>
						</select><p>
					</div>
					<p><input type="submit" value="Find" class="link-style" onclick="actionChange('findId')"></p>
			</div><p>
			<c:if test="${requestScope.userId ne null}">
				<div><p>
					<span>ã���ô� ���̵�� ${requestScope.userId}�Դϴ�</span>
				</div>
			</c:if>
			<div id="page"><h2>FIND PW</h2>
				<input type="radio" name="type2" value="3" onclick="btnChk2(this.value)" checked>����ó�� ã��
				<input type="radio" name="type2" value="4" onclick="btnChk2(this.value)">�̸��Ϸ� ã��<br><p>
				<div id="chk3">
					<input class="text_box" type="text" name="userId1" placeholder="���̵�"><p><p>
					<select class="mobile_box" name="mobile4">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="017">017</option>
						<option value="018">018</option>
					</select>
					-<input class="mobile_box" type="text" name="mobile5">
					-<input class="mobile_box" type="text" name="mobile6"><p>
				</div>
				<div id="chk4" style="display:none;">
					<input class="text_box" type="text" name="userId2" placeholder="���̵�"><p><p>
					<input class="email_box" type="text" name="email3" placeholder="�̸���">
					@<input class="email_box" type="text" name="email4" readonly>
					<select class="email_box" name="email5" onchange="chooseEmail2(this.value)">
						<option value="none">=����=</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.com">hanmail.net</option>
						<option value="nate.com">nate.com</option>
						<option value="empal.com">empal.com</option>
						<option value="txt">�����Է�</option>
					</select><p>
				</div>
				<p><input type="submit" value="Find" class="link-style" onclick="actionChange('findPw')"></p>
			</div>
			<c:if test="${requestScope.userPw ne null}">
				<div class="font_center">
					<span class="basic_font">�ӽ� �߱� ��й�ȣ�� ${requestScope.userPw} �Դϴ�</span>
				</div><p>
			</c:if>
		</form>
		<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>