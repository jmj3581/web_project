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
					<li>${sessionScope.name}�� ȯ���մϴ� </li>
				</ul>
			</c:when>
			<c:when test="${not empty sessionScope.userId}">
				<ul>
				
					<li class="current_page_item"><a href="index.jsp">Home</a></li>
					
					<li><a href="Controller?action=logout">Logout</a></li>
					<li><a href="Controller?action=myInfo">MyInfo</a></li>
					<li><a href="Controller?action=listBoard&pageNum=1">Board</a></li>
					<li><a href="Controller?action=listFood">Food List</a></li>
					<li>${sessionScope.name}�� ȯ���մϴ� </li>
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
		<li><a href="information.jsp">�Ұ�</a></li>
		<li><a href="#">�Խ���</a></li>
		<!-- <li><a href="register.jsp">������ ��û�ϱ�</a></li> -->
		<c:if test="${not empty sessionScope.grade && sessionScope.grade eq 'M'}">
			<li><a href="Controller?action=listMember">ȸ����ȸ</a></li>
			<li><a href="Controller?action=listFood">������ ����</a></li>
		</c:if>
		<!-- <li class="last"><a href="#">Contact</a></li> -->
	</ul>
</div> --%>

<!-- function logoutCheck(){
    if(confirm("�α׾ƿ��Ͻðڽ��ϱ�?") == true){
        location.href="BoardServlet?command=board_logout";
    }else{
        return false;
    }
    return
} -->