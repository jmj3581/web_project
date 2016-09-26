<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href="css/common.css">
<div id="header">
	<div id="logo">
		<h1><a href="index.jsp">RESTAURANTS</a></h1>
	</div>
	<div id="top">
		<c:choose>
			<c:when test="${not empty sessionScope.grade && sessionScope.grade eq 'M'}">
				<ul>
					<li class="current_page_item"><a href="index.jsp">Home</a></li>
					<li><a href="Controller?action=logout">Logout</a></li>
					<li><a href="Controller?action=listBoard&pageNum=1">Board</a></li>
					<li><a href="Controller?action=listMember">Member List</a></li>
					<li><a href="Controller?action=listFood">Food List</a></li>
					<li>${sessionScope.name}님 환영합니다 </li>
				</ul>
			</c:when>
			<c:when test="${not empty sessionScope.userId}">
				<ul>
				
					<li class="current_page_item"><a href="index.jsp">Home</a></li>
					
					<li><a href="Controller?action=logout">Logout</a></li>
					<li><a href="Controller?action=myInfo">MyInfo</a></li>
					<li><a href="Controller?action=listBoard&pageNum=1">Board</a></li>
					<li><a href="Controller?action=listFood">Food List</a></li>
					<li>${sessionScope.name}님 환영합니다 </li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul>
					<li class="current_page_item"><a href="index.jsp">Home</a></li>
					<li><a href="introduce.jsp">Introduce</a></li>
					<li><a href="login.jsp">Login</a></li>
					<li><a href="join.jsp">Join</a></li>
					<li><a href="findIdPw.jsp">Find ID/PW</a></li>
					<li><a href="Controller?action=listBoard&pageNum=1">Board</a></li>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<%-- <div id="menu">
	<ul>
		<li><a href="information.jsp">소개</a></li>
		<li><a href="#">게시판</a></li>
		<!-- <li><a href="register.jsp">음식점 신청하기</a></li> -->
		<c:if test="${not empty sessionScope.grade && sessionScope.grade eq 'M'}">
			<li><a href="Controller?action=listMember">회원조회</a></li>
			<li><a href="Controller?action=listFood">음식점 관리</a></li>
		</c:if>
		<!-- <li class="last"><a href="#">Contact</a></li> -->
	</ul>
</div> --%>

<!-- function logoutCheck(){
    if(confirm("로그아웃하시겠습니까?") == true){
        location.href="BoardServlet?command=board_logout";
    }else{
        return false;
    }
    return
} -->