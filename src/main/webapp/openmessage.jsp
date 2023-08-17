<%@page import="com.Db.DBConnect"%>
<%@page import="com.DAO.UserDAO"%>
<%@page import="com.User.MessageDetails"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.ArrayList"%>
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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Message | Social</title>
<link rel="stylesheet" type="text/css" href="style.css">
<%@include file="components/allcss.jsp"%>

</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<div class="container">
<%			String senderUN = ((UserDetails)session.getAttribute("userD")).getUsername().toString();
			ArrayList<MessageDetails> messages = (ArrayList<MessageDetails>) request.getAttribute("messages");
			
			if(messages.size() == 0){
				%><h4 style="text-align: center; color: #ffffff;">No Messages.</h4><%
			}
			for(int i=0; i<messages.size(); i++){
				if(messages.get(i).getMSender().equals(senderUN)){
					%>
						<div class="row justify-content-end">
						<div class="col-8 alert alert-primary" role="alert">
							<h5>
								me <a href="${pageContext.request.contextPath}/ChatServlet?id=<%= request.getAttribute("receiverUN") %>&delete=<%= messages.get(i).getChat_id() %>" class="card-link" style="float: right;"><i
									style="color: red;" class="far fa-trash-alt"></i></a>
							</h5>
							 <%= messages.get(i).getMessage() %>
							<p style="text-align: right;"><%= messages.get(i).getChattime() %></p>
						</div>
						</div>
					<%
				} else {
					%>
					<div class="row justify-content-start">
					<div class="col-8 alert alert-secondary" role="alert"">
						<h5>
							<%= new UserDAO(DBConnect.getConn()).getUser((messages.get(i).getMSender())).getFirstName()%> <a href="${pageContext.request.contextPath}/ChatServlet?id=<%= request.getAttribute("receiverUN") %>&delete=<%= messages.get(i).getChat_id() %>" class="card-link" style="float: right;"><i
								style="color: red;" class="far fa-trash-alt"></i></a>
						</h5>
						<%= messages.get(i).getMessage() %>
						<p style="text-align: right;"><%= messages.get(i).getChattime() %></p>
					</div>
					</div>
					<%
				}
			}
		%>

		<div class="type_msg"
			style="padding-left: 10px; padding-right: 10px; bottom: 10px;">
			<form method="post" action="ChatServlet?id= "+ receiverUN.toString()>
				<div class="input_msg_write">
					<input type="hidden" name="receiverUN" value="<%=request.getAttribute("receiverUN")%>" />
					 <input type="text"	name="message" class="write_msg" placeholder="Type a message" style="padding: 5px; border-radius: 20px; outline: none" />
					<button class="msg_send_btn" type="submit" style="margin-right: 20px; margin-top: -2px; outline: none;">
						<i class="fa fa-paper-plane-o" aria-hidden="true"></i>
					</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		window.scrollTo(0, document.documentElement.scrollHeight);
	</script>
</body>
</html>