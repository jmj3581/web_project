<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function actionChange(state) {
		if(state == 'search') {
			document.foodListForm.action='Controller?action=searchFood';
			document.foodListForm.submit();
		} else {
			/* var chk_obj = document.getElementsByName("fNo");
			var checked = 0;
			var ary = new Array();
			for(var i=0; i < chk_obj.length; i++) {
				if(chk_obj[i].checked == true) {
					ary[checked] = chk_obj[i].value;
					checked += 1;
				}
			} */
			document.foodListForm.action="Controller?action=removeMultiFood"; /*&fNo="+document.getElementsByName("fNo"); */
			document.foodListForm.submit();  
		}
	}
	
	function allCheck(objCheck) {
		var checks = document.getElementsByName("fNo");
		for(var i=0; i < checks.length; i++) {
			checks[i].checked = objCheck;
		}
	}
</script>
</head>
<body>
	<div id="wrapper" class="container">
		<%@ include file="topMenu.jsp" %>
		<div id="page"><h2>Food List</h2>
			<form name="foodListForm" method="post">
				<div>
					<select class="email_box" name="key">
						<option value="food_no">������ ��ȣ</option>
						<option value="kind">����</option>
						<option value="country">����</option>
						<option value="store_name">���Ը�</option>
						<option value="address">�ּ�</option>
					</select>
					<input class="text_box" type="text" name="data">
					<input type="submit" value="search" class="link-style" onclick="actionChange('search')">
				</div>
				<p><p><p>
				<div id="food_table">
					<div class="row">
						<span class="cell col1"><input type="checkbox" name="no" onclick="allCheck(this.checked)">NO.</span>
						<span class="cell col1">����</span>
						<span class="cell col1">����</span>
						<span class="cell col2">���Ը�</span>
						<span class="cell col3">�ּ�</span>
						<span class="cell col2">Ȩ�������ּ�</span>
						<span class="cell col2">��ȭ��ȣ</span>
					</div>
				</div>
		
				<c:forEach var="dto" items="${requestScope.list}">
					<div class="row">
						<span class="cell col1"><input type="checkbox" name="fNo" value="${dto.foodNo}">
							<a name="fooodNo" href="Controller?action=foodOne&foodNo=${dto.foodNo}">${dto.foodNo}</a></span>
						<span class="cell col1">${dto.kind}</span>
						<span class="cell col1">${dto.country}</span>
						<span class="cell col2">${dto.storeName}</span>
						<span class="cell col3">${dto.address}</span>
						<span class="cell col2">${dto.homepage}</span>							
						<span class="cell col2">${dto.phoneNumber}</span>
					</div>
				</c:forEach>
				<c:if test="${not empty sessionScope.grade && sessionScope.grade eq 'M'}">
					<a href="register.jsp" class="link-style">����ϱ�</a>
					<input type="submit" class="link-style" onclick="actionChange('delete')" value="�����ϱ�">
				</c:if>	
			</form>
		</div>
		<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>