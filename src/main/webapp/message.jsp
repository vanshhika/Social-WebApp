<%@page import="com.DAO.UserDAO"%>
<%@page import="com.User.MessageDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.User.FriendsDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Db.DBConnect"%>
<%@page import="com.DAO.FriendsDAO"%>
<%@page import="com.User.UserDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
if (session == null || session.getAttribute("userD") == null) {
	response.sendRedirect("login.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SOCIAL</title>
<link rel="stylesheet" type="text/css" href="style.css">
<%@include file="components/allcss.jsp"%>
<style>
  .modal {
    z-index: 9999;
  }
</style>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
<div class="avoid">
	<div class="container">
		<%
		ArrayList<MessageDetails> messages = (ArrayList<MessageDetails>) request.getAttribute("messagesD");
		String username = ((UserDetails) session.getAttribute("userD")).getUsername().toString();
		if (messages.size() == 0) {
		%><h4 style="text-align: center;">No Messages.</h4>
		<%
		}

		for (int i = 0; i < messages.size(); i++) {
		if (messages.get(i).getMSender().equals(username)) {
		%>
		<div class="card mb-3">
			<div class="card-body">
				<div class="row">
				<div class="col-2">
							  		<img style="width: 100%; border-radius: 100%;" src="<%= new UserDAO(DBConnect.getConn()).getUser(messages.get(i).getMReceiver()).getProfilePic() %>" />
							  	</div>
					<div class="col-10" style="cursor: pointer;"
						onclick="window.location.href='ChatServlet?id=<%=messages.get(i).getMReceiver()%>'">
						<h5 class="card-title"><%=new UserDAO(DBConnect.getConn()).getUser((messages.get(i).getMReceiver())).getUsername()%></h5>
						<h6 class="card-subtitle mb-2 text-muted"><%=messages.get(i).getMessage()%></h6>
						<p class="card-text"><%=messages.get(i).getChattime()%></p>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<a
							href="MessageServlet?delete=<%=messages.get(i).getMReceiver() %>"
							class="card-link" style="float: right;"><i
							style="font-size: 25px; color: red;" class="fa fa-trash" aria-hidden="true"></i></a>
					</div>
				</div>
			</div>
		</div>
		<%
		} else {
		%>
		<div class="card mb-3">
			<div class="card-body">
				<div class="row">
				<div class="col-2">
						  		<img style="width: 100%; border-radius: 100%;" src="<%= new UserDAO(DBConnect.getConn()).getUser(messages.get(i).getMSender()).getProfilePic() %>" />
						  	</div>
					<div class="col-10" style="cursor: pointer;"
						onclick="window.location.href='ChatServlet?id=<%=messages.get(i).getMReceiver()%>'">
						<h5 class="card-title"><%=new UserDAO(DBConnect.getConn()).getUser(messages.get(i).getMSender()).getUsername()%></h5>
						<h6 class="card-subtitle mb-2 text-muted"><%=messages.get(i).getMessage()%></h6>
						<p class="card-text"><%=messages.get(i).getChattime()%></p>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<a
							href="MessageServlet?delete=<%=messages.get(i).getMSender() %>"
							class="card-link" style="float: right;"><i
							style="font-size: 25px; color: red;" class="fa fa-trash" aria-hidden="true"></i></a>
					</div>
				</div>
			</div>
		</div>
		<%
		}
		}
		%>

	</div>

	<button id="newMessageBtn" style="border-radius: 50%"
		class="float-right" data-toggle="modal" data-target="#newChatModal">
		<i class="fa fa-plus" aria-hidden="true"></i>
	</button>
</div>
	<!-- Modal -->
	<div class="modal fade" style="margin-top: 100px; z-index: 9999;" id="newChatModal" tabindex="-1" role="dialog" aria-labelledby="chatModalTitle" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="chatModalTitle">Friends List</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body"
					style="overflow-y: scroll; max-height: 350px;">
					<%
					List<FriendsDetails> list = new ArrayList<>();
					list = (List<FriendsDetails>) request.getAttribute("friendsD");
					if (list != null) {
						for (FriendsDetails frie : list) {
					%>
					<div style="cursor: pointer; margin: 5px;" class="card"
						onclick="javascript:window.location='ChatServlet?id=<%=frie.getFriend()%>';">
						<div class="card-body">
							<b><%=frie.getFriend()%></b><%="  " + frie.getFriendName()%>
							<i style="float: right;" class="fa fa-paper-plane"></i>
						</div>
					</div>
					<%
					}
					}
					%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
