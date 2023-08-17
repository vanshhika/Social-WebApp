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

@WebServlet("/AcceptRequestServlet")
public class AcceptRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the senderID parameter
        String senderID = request.getParameter("senderID");
        String receiverID = request.getParameter("receiverID");
        UserDAO dao1 = new UserDAO(DBConnect.getConn());
        UserDetails userDetails = dao1.getUser(receiverID);
        String username= senderID;
        String friends = userDetails.getUsername();
        String friendsfn = userDetails.getFirstName() +" " + userDetails.getlastName();
        // Perform the accept action using the senderID
        RequestsDAO dao = new RequestsDAO(DBConnect.getConn());
        boolean removed = dao.removeRequest(senderID,receiverID);
        HttpSession session;
        FriendsDetails fr = new FriendsDetails();
        
		fr.setName(username);
		fr.setFriend(friends);
		fr.setFriendName(friendsfn);
		UserDetails userdetails1 = dao1.getUser(senderID);
		String username1 = receiverID;
		String friends1 = userdetails1.getUsername();
		String friendsfn1 = userdetails1.getFirstName()+ " " + userdetails1.getlastName();
		FriendsDetails fd = new FriendsDetails();
		fd.setName(username1);
		fd.setFriend(friends1);
		fd.setFriendName(friendsfn1);
 		FriendsDAO dao2 = new FriendsDAO(DBConnect.getConn());
		boolean f1 = dao2.addFriend(fr);
		boolean f2 = dao2.addFriend(fd);
        // Check if the request was successfully removed
        if (removed && f1 && f2) {
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
}



