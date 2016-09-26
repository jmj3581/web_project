<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
<div id="wrapper" class="container">
	<%@ include file="topMenu.jsp" %>
	<form method="post" action="Controller?action=searchFood">
		<div style="text-align: center">
			<input class="text_box" type="text" name="data" placeholder="국가를 입력하세요.">
			<input type="submit" value="search" class="link-style">
		</div>
	</form>
	<div id="banner"><img src="images/map.png" width="1100" height="500" /></div>
	<div id="two-column">
		<div id="tbox1">
			<h2>오늘의 추천 음식</h2>
			<p><img src="images/food/na_01.jpg" width="500" height="300" alt="" /></p>
		</div>
		<div id="tbox2"><br><br><br>
			<h2>업종 : 양식<br>
			나라 : 캐나다<br>
			가게명 : 소살리토 바닷가제<br>
			위치 : 서울시 종로구 인사동 155<br>
			홈페이지 : <a href="http://www.menupan.com/Restaurant/onepage.asp?acode=R105330">클릭</a><br>
			전화번호 : 02-720-5077<br>			
			</h2>
		</div>
	</div>
	<%@ include file="footerMenu.jsp" %>
</div>
</body>
</html>