<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="work.model.dto.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript">
	function actionChange(state) {
		if(state == 'update') {
			document.foodOneForm.action='Controller?action=changeFood';
			document.foodOneForm.submit();
			alert('음식점 정보가 수정되었습니다.');
		} else if(state == 'delete'){
			document.foodOneForm.action='Controller?action=removeFood';
			document.foodOneForm.submit();
			alert('음식점이 삭제되었습니다.');
		} else {
			document.foodOneForm.action='Controller?action=listFood';
			document.foodOneForm.submit();
		}
	}
</script>
</head>
<body>
	<div id="wrapper" class="container">
	<%@ include file="topMenu.jsp" %>
		<div id="page"><h2>Food INFO</h2>
			<form method="post" name="foodOneForm">
				<div id="two-column">
					<div id="tbox1">
						<c:choose>
							<c:when test="${not empty sessionScope.grade && sessionScope.grade eq 'M'}">
								<b>No.</b><input style="margin-left: 35px; border: none;" class="info_box" type="text" name="foodNo" value="${requestScope.dto.foodNo}" readonly><p><p>
								<b>업종</b><input style="margin-left: 30px" class="info_box" type="text" name="kind" value="${requestScope.dto.kind}"><p>
								<b>나라</b><input style="margin-left: 30px" class="info_box" type="text" name="country" value="${requestScope.dto.country}"><p>
								<b>가게명</b><input style="margin-left: 17px" class="info_box" type="text" name="storeName" value="${requestScope.dto.storeName}"><p>
								<b>주소</b><input style="margin-left: 30px" class="info_box" type="text" name="address" value="${requestScope.dto.address}"><p>
								<b>홈페이지</b><input style="margin-left: 5px" class="info_box" type="text" name="homepage" value="${requestScope.dto.homepage}"><p>
								<b>전화번호</b><input style="margin-left: 5px" class="info_box" type="text" name="phoneNumber" value="${requestScope.dto.phoneNumber}"><p>
								<p><input type="submit" value="목록으로" class="link-style" onclick="actionChange('list')">
								<input type="submit" value="수정" class="link-style" onclick="actionChange('update')">
								<input type="submit" value="삭제" class="link-style" onclick="actionChange('delete')"></p>
							</c:when>
							<c:otherwise>
								<b>No.</b><input style="margin-left: 35px; border: none;" class="info_box" type="text" name="foodNo" value="${requestScope.dto.foodNo}" readonly><p><p>
								<b>업종</b><input style="margin-left: 30px; border: none;" class="info_box" type="text" name="kind" value="${requestScope.dto.kind}" readonly><p>
								<b>나라</b><input style="margin-left: 30px; border: none;" class="info_box" type="text" name="country" value="${requestScope.dto.country}"readonly><p>
								<b>가게명</b><input style="margin-left: 17px; border: none;" class="info_box" type="text" name="storeName" value="${requestScope.dto.storeName}" readonly><p>
								<b>주소</b><input style="margin-left: 30px; border: none;" class="info_box" type="text" name="address" value="${requestScope.dto.address}" readonly><p>
								<b>홈페이지</b><input style="margin-left: 5px; border: none;" class="info_box" type="text" name="homepage" value="${requestScope.dto.homepage}" readonly><p>
								<b>전화번호</b><input style="margin-left: 5px; border: none;" class="info_box" type="text" name="phoneNumber" value="${requestScope.dto.phoneNumber}" readonly><p>
								<p><input type="submit" value="목록으로" class="link-style" onclick="actionChange('list')"></p>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="tbox2">
						<img style="width: 400px; height: 300px;" src="images/food/${requestScope.dto.fileName}">
					</div>
				</div>
			</form>
		</div>
		<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>