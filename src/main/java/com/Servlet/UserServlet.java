package com.Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FriendsDAO;
import com.DAO.MessageDAO;
import com.DAO.PostsDAO;
import com.DAO.UserDAO;
import com.Db.DBConnect;
import com.User.FriendsDetails;
import com.User.MessageDetails;
import com.User.PostDetails;
import com.User.UserDetails;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServerException, IOException{
		String firstname= request.getParameter("fname");
		String lastname= request.getParameter("lname");
		String username= request.getParameter("uname");
		String email= request.getParameter("uemail");
		String password= request.getParameter("upassword");
		
		UserDetails us=new UserDetails();
		us.setFirstName(firstname);
		us.setLastName(lastname);
		us.setUserName(username);
		us.setEmail(email);
		us.setPassword(password);
		
		
		UserDAO dao = new UserDAO(DBConnect.getConn());
		boolean f = dao.addUser(us);
		HttpSession session;
		
		if(f) {
			session  = request.getSession();
			session.setAttribute("reg-success","Registration Successful..");
			response.sendRedirect("register.jsp");
		}
		else {
			session = request.getSession();
			session.setAttribute("failed-msg","Something went wrong on server or you have used registered email or username");
			response.sendRedirect("register.jsp");
		}
	}
	 

}
