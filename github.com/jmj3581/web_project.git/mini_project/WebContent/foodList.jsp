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
						<option value="food_no">음식점 번호</option>
						<option value="kind">업종</option>
						<option value="country">나라</option>
						<option value="store_name">가게명</option>
						<option value="address">주소</option>
					</select>
					<input class="text_box" type="text" name="data">
					<input type="submit" value="search" class="link-style" onclick="actionChange('search')">
				</div>
				<p><p><p>
				<div id="food_table">
					<div class="row">
						<span class="cell col1"><input type="checkbox" name="no" onclick="allCheck(this.checked)">NO.</span>
						<span class="cell col1">업종</span>
						<span class="cell col1">나라</span>
						<span class="cell col2">가게명</span>
						<span class="cell col3">주소</span>
						<span class="cell col2">홈페이지주소</span>
						<span class="cell col2">전화번호</span>
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
					<a href="register.jsp" class="link-style">등록하기</a>
					<input type="submit" class="link-style" onclick="actionChange('delete')" value="삭제하기">
				</c:if>	
			</form>
		</div>
		<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>