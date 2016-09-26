<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="work.model.dto.Member" %>
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
		if(state == 'update') {
			document.memberInfoForm.action='Controller?action=changeMember';
			document.memberInfoForm.submit();
			alert('회원 정보가 수정되었습니다.');
		} else {
			document.memberInfoForm.action='Controller?action=removeMember';
			document.memberInfoForm.submit();
			alert('회원 탈퇴가 완료되었습니다.');
		}
	}
</script>
</head>
<body>
	<div id="wrapper" class="container">
	<%@ include file="topMenu.jsp" %>
		<div id="page"><h2>Member INFO</h2>
			<form method="post" name="memberInfoForm">
				<b>ID</b><input style="margin-left: 50px; border:none;" class="info_box" type="text" name="userId" value="${requestScope.dto.userId}" readonly><p><p>
				<b>PW</b><input style="margin-left: 45px" class="info_box" type="password" name="userPw" value="${requestScope.dto.userPw}"><p>
				<b>Name</b><input style="margin-left: 30px" class="info_box" type="text" name="name" value="${requestScope.dto.name}"><p>
				<b>Mobile</b><input style="margin-left: 25px" class="info_box" type="text" name="mobile" value="${requestScope.dto.mobile}"><p>
				<b>Email</b><input style="margin-left: 35px" class="info_box" type="text" name="email" value="${requestScope.dto.email}"><p>
				<b>Address</b><input style="margin-left: 20px" class="info_box" type="text" name="address" value="${requestScope.dto.address}"><p>
				<b>Visit Count</b><input style="margin-left: 5px;" class="info_box" type="text" name="visitCount" value="${requestScope.dto.visitCount}"><p>
				<b>Entry Date</b><input style="margin-left: 5px;" class="info_box" type="text" name="entryDate" value="${requestScope.dto.entryDate}"><p>
				<b>Grade</b><input style="margin-left: 35px;" class="info_box" type="text" name="grade" value="${requestScope.dto.grade}"><p>
				<p><input type="submit" value="수정" class="link-style" onclick="actionChange('update')">
				<input type="submit" value="탈퇴" class="link-style" onclick="actionChange('delete')"></p>
			</form>
		
		</div>
		<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>