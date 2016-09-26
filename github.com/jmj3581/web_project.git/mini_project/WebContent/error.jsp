<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE HTML>
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
	<div id="page">
		<h2>ERROR</h2>
		<span style="color:red;font-size:12px;font-weight:bold;"><%= request.getAttribute("message") %></span>
	</div>
	<%@ include file="footerMenu.jsp" %>
</body>
</html>
			

