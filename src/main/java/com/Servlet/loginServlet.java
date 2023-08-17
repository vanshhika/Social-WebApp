package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;

import com.DAO.UserDAO;
import com.Db.DBConnect;
import com.User.UserDetails;


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname");
		String password = request.getParameter("upassword");
		UserDetails us = new UserDetails();
		us.setUserName(username);
		us.setPassword(password);
		
		UserDAO dao = new UserDAO(DBConnect.getConn());
		UserDetails user = dao.loginUser(us);
		if(user!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("userD", user);
			response.sendRedirect("UploadPostServlet");
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("login-failed", "Invalid UserName or Password");
			response.sendRedirect("login.jsp");
		}
	
	}

}
