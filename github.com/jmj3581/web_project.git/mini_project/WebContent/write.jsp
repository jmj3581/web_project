<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript">
	function actionChange(state) {
		if(state == 'register') {
			document.writeForm.action='Controller?action=registerBoard';
			document.writeForm.submit();
			alert('게시글이 등록 되었습니다.');
		} else {
			document.writeForm.action='Controller?action=listBoard&pageNum=1';
			document.writeForm.submit();
		}
	}
</script>
</head>
<body>
	<div id="wrapper" class="container">
		<%@ include file="topMenu.jsp" %>
		<div id="page"><h2>Write</h2>
			<form name="writeForm" method="post">
				<input class="text_box" type="text" name="title" placeholder="제목"><p>
				<input class="text_box" type="text" name="userId" value="${sessionScope.userId}" readonly><p>
				<textarea style="width: 300px; height: 200px;" name="contents" placeholder="내용"></textarea><p>
				<input type="submit" class="link-style" value="등록" onclick="actionChange('register')">
				<input type="submit" class="link-style" value="취소" onclick="actionChange('list')">
				<p>
			</form>
		</div>
		<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>