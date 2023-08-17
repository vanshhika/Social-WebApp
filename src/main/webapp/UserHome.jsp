<%@page import="com.User.FriendsDetails"%>
<%@page import="com.DAO.UserDAO"%>
<%@page import="com.User.PostDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.User.RequestDetails"%>
<%@page import="com.DAO.RequestsDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.Db.DBConnect"%>
<%@page import="java.sql.Connection"%>
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
	<div class="avoid">
		<div class="container my-3">
			<form>
				<div class="input-group mb-3">
					<input type="text" name="searchbar" class="form-control"
						placeholder="Search User" autocomplete="off"
						aria-label="Recipient's username" aria-describedby="basic-addon2">
					<div class="input-group-append">
						<button class="btn btn-outline-secondary custombtn" type="button"
							name="searchbtn">Search</button>
					</div>
				</div>
			</form>
		</div>
		<div class="container my-2">
			<%
			Connection con;
			PreparedStatement pst;
			ResultSet rs;
			con = DBConnect.getConn();
			UserDetails user1 = (UserDetails) session.getAttribute("userD");
			String empid = request.getParameter("searchbar");
			if (empid == null || empid.isEmpty()) {

			} else {
				pst = con.prepareStatement("SELECT * FROM [SocialDB].[dbo].[user] WHERE [firstname]= ?");
				pst.setString(1, empid);
				rs = pst.executeQuery();

				out.print("<Table class=\"table table-striped\">");
				if (rs.next()) {
					do {
				if (!rs.getString("username").equals(user1.getUsername())) {
					out.print("<tr>");
					out.print("<td scope=\"col\">" + rs.getString("firstname") + " " + rs.getString("lastname") + "</td>");
					out.print("<td scope=\"col\">" + rs.getString("username") + "</td>");
					out.print("<td scope=\"col\">");
					out.print("<form action=\"SendRequestServlet\" method=\"post\">");
					out.print("<input type=\"hidden\" name=\"ReceiverID\" value=\"" + rs.getString("username") + "\" />");
					out.print("<input type=\"hidden\" name=\"SenderID\" value=\"" + user1.getUsername() + "\" />");
					out.print(
							"<input id=\"button\" class=\"btn btn-outline-secondary float-right custombtn\" type=\"submit\" value=\"Send Request\" >");
					out.print("</form>");
					out.print("</td>");
					out.print("</tr>");
				}
					} while (rs.next());
				} else {
			%>
			<h2>No such user found!!</h2>
			<%
			}
			out.print("</Table>");
			}
			%>
			<%
			String regMsg = (String) session.getAttribute("req-sent");
			if (regMsg != null) {
			%>
			<div class="alert alert-success" role="alert"><%=regMsg%></div>
			<%
			session.removeAttribute("req-sent");
			}
			%>
			<%
			String failedMsg = (String) session.getAttribute("req-failed");
			if (failedMsg != null) {
			%>
			<div class="alert alert-danger" role="alert"><%=failedMsg%></div>
			<%
			session.removeAttribute("req-failed");
			}
			%>
		</div>
		<div class="container my-2">
			<h2>Sender's Requests</h2>
			<%
			RequestsDAO dao = new RequestsDAO(DBConnect.getConn());
			RequestDetails requestDetails = new RequestDetails();
			requestDetails.setReceiverID(user1.getUsername()); // Set the sender's username in the requestDetails object
			List<RequestDetails> requestList = dao.showRequest(requestDetails);
			if (requestList.isEmpty()) {
			%>
			<p>No requests found.</p>
			<%
			} else {
			%>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Receiver ID</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (RequestDetails req : requestList) {
					%>
					<tr>
						<td><%=req.getSenderID()%></td>
						<td><form action="AcceptRequestServlet" method="post">
								<input type="hidden" name="senderID"
									value="<%=req.getSenderID()%>"> <input type="hidden"
									name="receiverID" value="<%=user1.getUsername()%>"> <input
									id="button"
									class="btn btn-outline-secondary float-right custombtn"
									type="submit" value="Accept">
							</form>
							<form action="DeclineRequestServlet" method="post">
								<input type="hidden" name="senderID"
									value="<%=req.getSenderID()%>"> <input type="hidden"
									name="receiverID" value="<%=user1.getUsername()%>"> <input
									id="button"
									class="btn btn-outline-secondary float-right custombtn"
									type="submit" value="Decline">
							</form></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<%
			}
			%>


		</div>
		<div class="container my-5">

			<div class="card">
				<div class="card-body">
					<form method="post" class="form-group" action="UploadPostServlet"
						enctype="multipart/form-data">
						<div class="form-group">
							<b><label for="post">Upload Post:</label></b> <input type="file"
								name="post" accept="image/png, image/gif, image/jpeg" /> <input
								type="submit" value="Upload"
								class="btn btn-success custombtn float-right">
						</div>
					</form>
				</div>
			</div>

			<div class="col-md-8 col-sm-12">
				<div style="padding: 20px;">

					<%
					ArrayList<PostDetails> posts = (ArrayList<PostDetails>) request.getAttribute("posts");
					List<FriendsDetails> friends = (List<FriendsDetails>) request.getAttribute("friends");
					if (posts.size() == 0) {
					%><h4 style="text-align: center;">No Posts.</h4>
					<%
					}
					for (int i = 0; i < posts.size(); i++) {
					UserDetails user0 = (UserDetails) session.getAttribute("userD");
					String username1 = posts.get(i).getUserID().toString();
					FriendsDetails fr = new FriendsDetails();
					fr.setFriend(posts.get(i).getUserID());
					fr.setName(user0.getUsername());
					fr.setFriendName(new UserDAO(DBConnect.getConn()).getUser(posts.get(i).getUserID()).getFirstName() + " "
							+ new UserDAO(DBConnect.getConn()).getUser(posts.get(i).getUserID()).getlastName());
					if (friends.contains(fr) || posts.get(i).getUserID().equals(user0.getUsername())) {
					%>

					<div class="card mb-3">
						<div class="card-body">
							<div class="row">
								<div class="col-2">
									<img style="width: 100%; border-radius: 100%;"
										src="<%=new UserDAO(DBConnect.getConn()).getUser(posts.get(i).getUserID()).getProfilePic()%>" />
								</div>
								<div class="col-10">
									<h5 class="card-title">
										<%
										if (posts.get(i).getUserID().equals(((UserDetails) session.getAttribute("userD")).getUsername().toString())) {
										%><h5 class="card-title">me</h5>
										<%
										} else {
										%>
										<h5 class="card-title">
											<%=new UserDAO(DBConnect.getConn()).getUser(posts.get(i).getUserID()).getFirstName()%>
										</h5>
										<%
										}
										%>
									</h5>
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
					%>

				</div>
			</div>
		</div>
	</div>

</body>
</html>