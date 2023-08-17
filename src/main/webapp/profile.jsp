<%@page import="com.Db.DBConnect"%>
<%@page import="com.DAO.UserDAO"%>
<%@page import="com.User.PostDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SOCIAL</title>
<%@include file="components/allcss.jsp"%>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<%
	UserDetails user0 = (UserDetails) session.getAttribute("userD");
	%>
	<div class="avoid">
		<div class="my-container">
			<div class="row">
				<div class="col-md-4 col-sm-12 left-fixed">
					<!-- Content for the fixed left div -->
					<!-- For example, you can add a form or any other content here -->
					<img
						style="width: 100%; border-radius: 100%; padding-left: 10px; padding-top: 20px;"
						src="<%=new UserDAO(DBConnect.getConn()).getUser(user0.getUsername()).getProfilePic()%>" />
					
					<div
						style="display: flex; flex-direction: column; padding-top: 50px;">
						<div style="display: flex; align-items: center;">
							<h3>Name :</h3>
							<h5><%=user0.getFirstName() + " " + user0.getlastName()%></h5>
						</div>
						<div style="display: flex; align-items: center;">
							<h3>Username :</h3>
							<h5><%=user0.getUsername()%></h5>
						</div>
						<div style="display: flex; align-items: center;">
							<h3>Email :</h3>
							<h5><%=user0.getEmail()%></h5>
						</div>
					</div>
					<button method="post"
						action="ProfileServlet?deactivate=<%=user0.getUsername()%>"
						style="margin-left: 30%;" class="btn btn-danger">Deactivate</button>
					<button style="border-radius: 50%" class="float-right"
						data-toggle="modal" data-target="#profileUpdate">
						<i class="fa fa-plus" aria-hidden="true"></i>
					</button>
				</div>
				<div class="col-md-8 col-sm-12 right-scrollable">
				
					<!-- Content for the right div, which will be scrollable vertically if the content overflows -->
					<!-- Add your content here -->
					<!-- Example content: a list of posts -->
					<div style="padding: 20px;">
						<%
						ArrayList<PostDetails> posts = (ArrayList<PostDetails>) request.getAttribute("posts");
						boolean flag = false;
						for (int i = 0; i < posts.size(); i++) {
							String username1 = posts.get(i).getUserID().toString();
							if (posts.get(i).getUserID().equals(user0.getUsername())) {
								flag = true;
						%>
						<div class="card mb-3">
							<div class="card-body">
								<div class="row">
									<div class="col-2">
										<img style="width: 100%; border-radius: 100%;"
											src="<%=new UserDAO(DBConnect.getConn()).getUser(posts.get(i).getUserID()).getProfilePic()%>" />
									</div>
									<div class="col-10">
										<h5 class="card-title"><%=user0.getUsername()%>
											(me)
										</h5>
										<div class="col-2 float-right">
											<a href="ProfileServlet?delete=<%=posts.get(i).getPostID()%>"
												class="card-link" style="float: right;"><i
												style="font-size: 25px; color: red;" class="fa fa-trash"
												aria-hidden="true"></i></a>
										</div>

										<div class="col-10">
											<img style="width: 100%; height: auto; object-fit: cover;"
												src="<%=posts.get(i).getBody()%>" />
										</div>

										<p class="card-text float-right"><%=posts.get(i).getPosttime()%></p>
									</div>
								</div>
							</div>
						</div>

						<%
						}
						}
						if (!flag) {
						%><h4 style="text-align: center;">No Posts.</h4>
						<%
						}
						%>
						<!-- Add more cards or content here as needed -->
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade bd-example-modal-lg" id="profileUpdate"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">Edit
						Profile</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-6">
							<h4 style="margin-top: 10px;">Change Profile</h4>
							<form method="post" action="profile">
								<input type="hidden" name="type" value="change_profile">
								<div class="form-group">
									<label for="first_name">First name</label> <input type="text"
										class="form-control" name="first_name" id="first_name"
										required
										value="<%=new UserDAO(DBConnect.getConn()).getUser(user0.getUsername()).getFirstName()%>">
								</div>
								<div class="form-group">
									<label for="last_name">Last name</label> <input type="text"
										class="form-control" id="last_name" name="last_name" required
										value="<%=new UserDAO(DBConnect.getConn()).getUser(user0.getUsername()).getlastName()%>">
								</div>
								<div class="form-group">
									<label for="email">Email</label> <input type="email"
										class="form-control" id="email" name="email" required
										value="<%=new UserDAO(DBConnect.getConn()).getUser(user0.getUsername()).getEmail()%>">
								</div>
								<button type="submit" style="float: right;"
									class="btn btn-primary">Update Profile</button>
							</form>
						</div>
						<div class="col-md-6">
							<h4 style="margin-top: 10px;">Change Password</h4>
							<form method="post" action="profile">
								<input type="hidden" name="type" value="change_password">
								<div class="form-group">
									<label for="password">Password</label> <input type="password"
										class="form-control" id="password" name="password" required
										placeholder="Type your new password">
								</div>
								<div class="form-group">
									<label for="cpassword">Confirm Password</label> <input
										type="password" class="form-control" id="cpassword"
										name="cpassword" required
										placeholder="Type your confirm password">
								</div>
								<button type="submit" style="float: right;"
									class="btn btn-primary">Update Password</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



























