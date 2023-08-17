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
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card mt-4">
					<div class="card-header text-center text-white bg-custom">
						<i class="fa fa-user-plus text-center fa-3x" aria-hidden="true"></i>
						<h4>Let's get you signed up</h4>
					</div>

					<%
					String regMsg=(String)session.getAttribute("reg-success");
					if(regMsg!=null){%>
					
					<div class="alert alert-success" role="alert"><%= regMsg  %><a href="login.jsp">Click here</a></div>
					
						
					<%
					session.removeAttribute("reg-success");
						}
					%>
					<%
					String failedMsg=(String)session.getAttribute("failed-msg");
					if(failedMsg!=null){%>
					
					<div class="alert alert-danger" role="alert"><%= failedMsg  %></div>
					
						
					<%
					session.removeAttribute("failed-msg");
						}
					%>
					<div class="card-body">
						<form action="UserServlet" method="post">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text">First and last name</span>
									</div>
									<input type="text" aria-label="First name" class="form-control"
										required="required" name="fname" autocomplete="off"> <input type="text"
										aria-label="Last name" class="form-control"
										required="required" name="lname" autocomplete="off">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text" id="addon-wrapping">@</span>
									</div>
									<input type="text" class="form-control" placeholder="Username"
										aria-label="Username" aria-describedby="addon-wrapping"
										name="uname" autocomplete="off">
								</div>
							</div>

							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label> <input
									type="email" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" placeholder="Enter email"
									name="uemail" autocomplete="off">
							</div>

							<div class="form-group">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="exampleInputPassword1"
									placeholder="Password" name="upassword">
							</div>
							<div class="form-group form-check">
								<a href="login.jsp">Already a member?</a>
							</div>
							<div class="forbutton">
								<button type="submit"
									class="btn btn-primary badge-pill btn-block">Sign Up</button>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>