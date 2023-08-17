package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FriendsDAO;
import com.DAO.RequestsDAO;
import com.DAO.UserDAO;
import com.Db.DBConnect;
import com.User.FriendsDetails;
import com.User.UserDetails;

/**
 * Servlet implementation class DeclineRequestServlet
 */
@WebServlet("/DeclineRequestServlet")
public class DeclineRequestServlet extends HttpServlet {
	public class AcceptRequestServlet extends HttpServlet {
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Retrieve the senderID parameter
	        String senderID = request.getParameter("senderID");
	        String receiverID = request.getParameter("receiverID");
	        // Perform the accept action using the senderID
	        RequestsDAO dao = new RequestsDAO(DBConnect.getConn());
	        boolean removed = dao.removeRequest(senderID,receiverID);
	        HttpSession session;
	        
	        // Check if the request was successfully removed
	        if (removed) {
	            // Redirect back to the same page or any other desired page
	        	session  = request.getSession();
	            response.sendRedirect("UserHome.jsp");
	        } else {
	            // Handle the case when the request removal fails
	            // You can redirect to an error page or display an error message
	        	session  = request.getSession();
	            response.getWriter().println("Failed to remove the request.");
	        }
	        
	    }
	}}