<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���̵� �ߺ�</title>
<% 
	request.setCharacterEncoding("euc-kr");
	String id = request.getParameter("userId"); 
%>
<link href="css/common.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript">
	function check(id) {
		if(id == 'ok') {
			<%-- opener.document.join.userId.value = <%=id%>; --%>
			window.close();
		} else {
			document.checkIdForm.action="Controller?action=checkId&userId="+document.checkIdForm.userId.value;
			document.checkIdForm.submit();
		}
	}
</script>

</head>
<body><p><p>
<form name="checkIdForm" method="post">
	${requestScope.message} 
	
	<c:if test="${requestScope.message eq '��밡���� ���̵� �Դϴ�.'}">
		<input type="button" value="����ϱ�" onclick="check('ok')"><p><p>
	</c:if>
	<p><p><input class="text_box" type="text" name="userId" placeholder="ID">
	<input type="button" value="Ȯ��" onclick="check('reCheck')"> 
</form>
</body>
</html>