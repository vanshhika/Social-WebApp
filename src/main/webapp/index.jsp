
<%@page import="com.Db.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SOCIAL</title>
<link rel="stylesheet" type="text/css" href="style.css">
<%@include file="components/allcss.jsp"%>

</head>
<body class="registerlogin">
	<%@include file="components/navbar.jsp"%>
	<%
	try {
		Connection conn = DBConnect.getConn();
		System.out.print(conn);
	} catch (Exception e) {
		e.printStackTrace();
	}
	%>
	<div class="avoid">
	<div class="center-index">
		<form class="form-inline my-2 my-lg-0">
			<a class="btn btn-light my-2 my-sm-0 mr-2" type="submit"
				href="login.jsp">Sign In</a> <a class="btn btn-light my-2 my-sm-0"
				type="submit" href="register.jsp">Sign Up</a>
		</form>
	</div>
</div>

</body>
</html>