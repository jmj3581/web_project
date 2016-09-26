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
			alert('게시글이 수정되었습니다.');
		} else if(state == 'delete') {
			document.selectBoardForm.action='Controller?action=removeBoard';
			document.selectBoardForm.submit();
			alert('게시글이 삭제되었습니다.');
		} else {
		}
	}
</script>
</head>
<body>
	<div id="wrapper" class="container">
		<%@ include file="topMenu.jsp" %>
		<div id="page"><h2>게시글 조회</h2>
			<form name="selectBoardForm" method="post">
				<c:choose>
					<c:when test="${sessionScope.userId eq requestScope.dto.userId}">
						<b>NO.</b><input style="margin-left: 15px; border:none;" class="board_box" type="text" name="boardNo" value="${requestScope.dto.boardNo}" readonly><p><p>
						<b>제목</b><input  style="margin-left: 15px" class="board_box" type="text" name="title"  value="${requestScope.dto.title}"><p>
						<b>작성자</b><input style="border:none; margin-left: 5px" class="board_box" type="text" name="userId" value="${requestScope.dto.userId}" readonly><p>
						<b>작성일</b><input style="border:none; margin-left: 5px;" class="board_box" type="text" name="regDate" value="${requestScope.dto.regDate}" readonly><p>
						<b>조회수</b><input style="border:none; margin-left: 5px;" class="board_box" type="text" name="count" value="${requestScope.dto.count}" readonly><p>
						<b>내용</b><textarea class="textarea" style="margin-left: 15px;" name="contents">${requestScope.dto.contents}</textarea><p>
						<p><a href="Controller?action=listBoard" class="link-style">글목록</a>
						<input type="submit" value="수정" class="link-style" onclick="actionChange('update')">
						<input type="submit" value="삭제" class="link-style" onclick="actionChange('delete')">
						<!-- <a href="write.jsp" class="link-style">답글</a></p> -->
					</c:when>
					<c:otherwise>
						<b>NO.</b><input style="margin-left: 15px; border:none;" class="board_box" type="text" name="boardNo" value="${requestScope.dto.boardNo}" readonly><p><p>
						<b>제목</b><input style="margin-left: 15px; border:none;" class="board_box" type="text" name="title"  value="${requestScope.dto.title}" readonly><p>
						<b>작성자</b><input style="margin-left: 5px; border:none;" class="board_box" type="text" name="userId" value="${requestScope.dto.userId}" readonly><p>
						<b>작성일</b><input style="margin-left: 5px; border:none;" class="board_box" type="text" name="regDate" value="${requestScope.dto.regDate}" readonly><p>
						<b>조회수</b><input style="border:none; margin-left: 5px;" class="board_box" type="text" name="count" value="${requestScope.dto.count}" readonly><p>
						<b>내용</b><textarea class="textarea" style="margin-left: 15px;" name="contents" readonly>${requestScope.dto.contents}</textarea><p>
						<p><a href="Controller?action=listBoard" class="link-style">글목록</a>
						<!-- <a href="write.jsp" class="link-style">답글</a></p> -->
					</c:otherwise>
				</c:choose>
			</form>
		</div>
		<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>