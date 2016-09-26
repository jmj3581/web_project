<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="work.model.dto.Board" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardList</title>
<script type="text/javascript">
	function actionChange(state) {
		if(state == 'search') {
			document.boardListForm.action='Controller?action=searchBoard';
			document.boardListForm.submit();
		} else {
			document.boardListForm.action='Controller?action=listBoard&pageNum=1';
			document.boardListForm.submit();
		}
	}
</script>
</head>
<body>
	<div id="wrapper" class="container">
		<%@ include file="topMenu.jsp" %>
		<div id="page"><h2>Board</h2>
			<form name="boardListForm" method="post" action="Controller?action=listBoard&pageNum=1">
				<div>
					<select class="email_box" name="key">
						<option value="user_id">아이디</option>
						<option value="title">제목</option>
						<option value="contents">내용</option>
					</select>
					<input class="text_box" type="text" name="data">
					<input type="submit" value="search" class="link-style" onclick="actionChange('search')">
				</div>
				<p><p><p>
				<div id="member_table">
					<div class="row">
						<span class="cell col1">NO.</span>
						<span class="cell col3">제목</span>
						<span class="cell col2">작성자</span>
						<span class="cell col2">등록일</span>
						<span class="cell col1">조회수</span>
					</div>
				</div>
				
				<c:forEach var="dto" items="${requestScope.list}">
					<div class="row">
						<span class="cell col1">
							<a name="boardNo" href="Controller?action=boardOne&boardNo=${dto.boardNo}">${dto.boardNo}</a>
						</span>
						<span class="cell col3">${dto.title}</span>
						<span class="cell col2">${dto.userId}</span>
						<span class="cell col2">${dto.regDate}</span>
						<span class="cell col1">${dto.count}</span>
					</div>
				</c:forEach>
				<c:choose>
					<c:when test="${requestScope.pageNum<=1}">
						<<
					</c:when>
					<c:otherwise>
						<a style="text-decoration: none;" href="Controller?action=listBoard&pageNum=${requestScope.pageNum-1}">&nbsp;<<</a>
					</c:otherwise>
				</c:choose>
				
				<c:forEach var="i" begin="1" end="${requestScope.maxPage}">
					<c:choose>
						<c:when test="${i eq requestScope.pageNum}">
							[${i}]
						</c:when>
						<c:otherwise>
							<a style="text-decoration: none;" href="Controller?action=listBoard&pageNum=${i}">[${i}]</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:choose>
					<c:when test="${requestScope.pageNum >= requestScope.maxPage}">
						>>
					</c:when>
					<c:otherwise>
						<a style="text-decoration: none;" href="Controller?action=listBoard&pageNum=${requestScope.pageNum+1}">>></a>
					</c:otherwise>
				</c:choose>
				
				<p><a href="write.jsp" class="link-style">글쓰기</a>	
			</form>
		</div>	
		<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>