<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" type="text/css" media="all" />
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<div id="wrapper" class="container">
		<%@ include file="topMenu.jsp" %>
		<div id="page"><h2>Register</h2>
			<form action="Controller?action=registerFood" method="post" name="register">
				<input class="text_box" type="text" name="foodNo" placeholder="No."><p><p>
				<input class="text_box" type="text" name="kind" placeholder="업종"><p>
				<input class="text_box" type="text" name="country" placeholder="나라"><p>
				<input class="text_box" type="text" name="storeName" placeholder="가게명"><p>
				
				<input class="postcode_box" type="text" id="postcode" placeholder="우편번호" name="postcode">
				<input class="postcode_box" type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
				<input class="address_box" type="text" id="address" placeholder="주소" name="address">
				<input class="address_box" type="text" id="address2" placeholder="상세주소" name="address2"><p>
					
				<input class="text_box" type="text" name="homepage" placeholder="홈페이지 주소"><p>
					
				<select class="mobile_box" name="mobile1">
					<option value="02">02</option>
					<option value="031">031</option>
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="017">017</option>
					<option value="018">018</option>
				</select>
				-<input class="mobile_box" type="text" name="mobile2">
				-<input class="mobile_box" type="text" name="mobile3"><p>
					
				<input class="text_box" type="text" name="fileName" placeholder="파일명"><p>
				<p><input type="submit" value="Register" class="link-style"></p>
			</form>
		</div>
		<%@ include file="footerMenu.jsp" %>
	</div>
</body>
</html>