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
	<div class=avoid>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4 offset-md-4">
					<div class="card mt-4">
						<div class="card-header text-center text-white bg-custom">
							<i class="fa fa-user-plus text-center fa-3x" aria-hidden="true"></i>
							<h4>Welcome Back</h4>
						</div>

						<%
						String invaliMSG = (String) session.getAttribute("login-failed");
						if (invaliMSG != null) {
						%>
						<div class="alert alert-danger" role="alert"><%=invaliMSG%></div>
						<%
						session.removeAttribute("login-failed");
						}
						%>
						<%
						String logout_msg = (String) session.getAttribute("logoutMSG");
						if (logout_msg != null) {
						%>

						<div class="alert alert-success" role="alert"><%=logout_msg%></div>

						<%
						session.removeAttribute("logoutMSG");
						}
						%>
						<div class="card-body">
							<form action="loginServlet" method="post">

								<div class="form-group">
									<div class="input-group flex-nowrap">
										<div class="input-group-prepend">
											<span class="input-group-text" id="addon-wrapping">@</span>
										</div>
										<input type="text" class="form-control"
											placeholder="Username or Email ID" aria-label="Username"
											aria-describedby="addon-wrapping" name="uname"
											autocomplete="off">
									</div>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Password</label> <input
										type="password" class="form-control"
										id="exampleInputPassword1" placeholder="Password"
										name="upassword">
								</div>
								<div class="form-group form-check">
									<a href="register.jsp">Create an account</a>
								</div>
								<div class="forbutton">
									<button type="submit"
										class="btn btn-primary badge-pill btn-block">Sign In</button>
								</div>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>