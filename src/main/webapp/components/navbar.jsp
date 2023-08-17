

<%@page import="com.User.UserDetails"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-light">
	<a class="navbar-brand" href="#">SOCIAL</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="#"><i
					class="fa fa-home" aria-hidden="true"></i>Home <span
					class="sr-only">(current)</span></a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <i class="fa fa-users" aria-hidden="true"></i>Find
					People
			</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="#">Friends</a> <a
						class="dropdown-item" href="#">Followers</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">People don't follow back</a>
				</div></li>
				
			<li class="nav-item"><a class="nav-link" href="MessageServlet"><i
					class="fa fa-comments" aria-hidden="true"></i>Messages</a></li>
		</ul>

		<%
		UserDetails user = (UserDetails) session.getAttribute("userD");
		if (user != null) {
		%>
		<a class="btn btn-light my-2 my-sm-0 mr-2" type="submit"
			href="ProfileServlet" ><%= user.getUsername()%></a> 
		<a class="btn btn-light my-2 my-sm-0"
			type="submit" href="logoutServlet">Logout</a>


		<%
		} else {%>
		
		<a class="btn btn-light my-2 my-sm-0 mr-2" type="submit"
			href="login.jsp">Sign In</a> 
		<a class="btn btn-light my-2 my-sm-0"
			type="submit" href="register.jsp">Sign Up</a>
		<%} %>

	</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</nav>