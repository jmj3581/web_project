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
		if(state == 'update') {
			document.selectBoardForm.action='Controller?action=changeBoard';
			document.selectBoardForm.submit();
			alert('�Խñ��� �����Ǿ����ϴ�.');
		} else if(state == 'delete') {
			document.selectBoardForm.action='Controller?action=removeBoard';
			document.selectBoardForm.submit();
			alert('�Խñ��� �����Ǿ����ϴ�.');
		} else {
		}
	}
</script>
</head>
<body>
	<div id="wrapper" class="container">
		<%@ include file="topMenu.jsp" %>
		<div id="page"><h2>�Խñ� ��ȸ</h2>
			<form name="selectBoardForm" method="post">
				<c:choose>
					<c:when test="${sessionScope.userId eq requestScope.dto.userId}">
						<b>NO.</b><input style="margin-left: 15px; border:none;" class="board_box" type="text" name="boardNo" value="${requestScope.dto.boardNo}" readonly><p><p>
						<b>����</b><input  style="margin-left: 15px" class="board_box" type="text" name="title"  value="${requestScope.dto.title}"><p>
						<b>�ۼ���</b><input style="border:none; margin-left: 5px" class="board_box" type="text" name="userId" value="${requestScope.dto.userId}" readonly><p>
						<b>�ۼ���</b><input style="border:none; margin-left: 5px;" class="board_box" type="text" name="regDate" value="${requestScope.dto.regDate}" readonly><p>
						<b>��ȸ��</b><input style="border:none; margin-left: 5px;" class="board_box" type="text" name="count" value="${requestScope.dto.count}" readonly><p>
						<b>����</b><textarea class="textarea" style="margin-left: 15px;" name="contents">${requestScope.dto.contents}</textarea><p>
						<p><a href="Controller?action=listBoard" class="link-style">�۸��</a>
						<input type="submit" value="����" class="link-style" onclick="actionChange('update')">
						<input type="submit" value="����" class="link-style" onclick="actionChange('delete')">
						<!-- <a href="write.jsp" class="link-style">���</a></p> -->
					</c:when>
					<c:otherwise>
						<b>NO.</b><input style="margin-left: 15px; border:none;" class="board_box" type="text" name="boardNo" value="${requestScope.dto.boardNo}" readonly><p><p>
						<b>����</b><input style="margin-left: 15px; border:none;" class="board_box" type="text" name="title"  value="${requestScope.dto.title}" readonly><p>
						<b>�ۼ���</b><input style="margin-left: 5px; border:none;" class="board_box" type="text" name="userId" value="${requestScope.dto.userId}" readonly><p>
						<b>�ۼ���</b><input style="margin-left: 5px; border:none;" class="board_box" type="text" name="regDate" value="${requestScope.dto.regDate}" readonly><p>
						<b>��ȸ��</b><input style="border:none; margin-left: 5px;" class="board_box" type="text" name="count" value="${requestScope.dto.count}" readonly><p>
						<b>����</b><textarea class="textarea" style="margin-left: 15px;" name="contents" readonly>${requestScope.dto.contents}</textarea><p>
						<p><a href="Controller?action=listBoard" class="link-style">�۸��</a>
						<!-- <a href="write.jsp" class="link-style">���</a></p> -->
					</c:otherwise>
				</c:choose>
			</form>
		</div>
		<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>