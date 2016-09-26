<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function allCheck(objCheck) {
		alert(objCheck)
		var checks = document.getElementsByName("no");
		for(var i=0; i < checks.length; i++) {
			checks[i].checked = objCheck;
		}
	}
	
	function actionChange(state) {
		if(state == 'search') {
			document.memberListForm.action='Controller?action=searchMember';
			document.memberListForm.submit();
		} else {
			document.memberListForm.action='Controller?action=removeMultiMember';
			document.memberListForm.submit();
		}
	}
</script>
</head>
<body>
	<div id="wrapper" class="container">
		<%@ include file="topMenu.jsp" %>
		<div id="page"><h2>Member List</h2>
			<form name="memberListForm" method="post">
				<div>
					<select class="email_box" name="key"><!-- value�� Į�������� -->
						<option value="user_id">���̵�</option>
						<option value="name">�̸�</option>
						<option value="mobile">����ó</option>
						<option value="grade">���</option>
						<option value="visit_count">�湮Ƚ��</option>
					</select>
					<input class="text_box" type="text" name="data">
					<input type="submit" value="search" class="link-style" onclick="actionChange('search')">
				</div>
				<p><p><p>
				<div id="member_table">
					<div class="row">
						<span class="cell col1">NO.<input type="checkbox" name="no" onclick="allCheck(this.checked)"></span>
						<span class="cell col2">���̵�</span>
						<span class="cell col2">�̸�</span>
						<span class="cell col2">����ó</span>
						<span class="cell col2">�̸���</span>
						<span class="cell col3">�ּ�</span>
						<span class="cell col1">�湮Ƚ��</span>
						<span class="cell col2">������</span>
						<span class="cell col1">���</span>
					</div>
				</div>
				
				<!-- ȸ����ü���� ����� �ݺ� ���� -->
				<c:forEach var="dto" items="${requestScope.list}">
					<div class="row">
						<span class="cell col1"><input type="checkbox" name="no" value="${dto.userId}"></span>
						<!-- ������ ȸ�����̵��� ����ȸ ��û -->
						<span class="cell col2">
							<a name="userId" href="Controller?action=memberOne&userId=${dto.userId}">${dto.userId}</a>
						</span>
						<span class="cell col2">${dto.name}</span>
						<span class="cell col2">${dto.mobile}</span>
						<span class="cell col2">${dto.email}</span>
						<span class="cell col3">${dto.address}</span>
						<span class="cell col1">${dto.visitCount}</span>
						<span class="cell col2">${dto.entryDate}</span>
						<span class="cell col1">${dto.grade}</span>
					</div>
				</c:forEach>
				<c:if test="${not empty sessionScope.grade && sessionScope.grade eq 'M'}">
					<input type="submit" class="link-style" onclick="actionChange('delete')" value="�����ϱ�">
				</c:if>		
			</form>
		</div>	
		<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>