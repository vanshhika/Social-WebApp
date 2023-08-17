package com.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FriendsDAO;
import com.DAO.MessageDAO;
import com.DAO.UserDAO;
import com.Db.DBConnect;
import com.User.FriendsDetails;
import com.User.MessageDetails;
import com.User.UserDetails;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	FriendsDAO friendsDAO = new FriendsDAO(DBConnect.getConn());
    	UserDetails userDetails = (UserDetails) session.getAttribute("userD");
    	List<FriendsDetails> friends = new ArrayList<>();
    	ArrayList<MessageDetails> messages = new ArrayList<>();
    	MessageDAO messageDAO = new MessageDAO(DBConnect.getConn());
    	if(request.getParameter("delete") != null) {
			try {
				messageDAO.deleteChat(userDetails.getUsername(), request.getParameter("delete"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	if (userDetails != null) {
    		friends = friendsDAO.getFriends(userDetails.getUsername());
    		messages = messageDAO.getAllMessage(userDetails.getUsername());
    		request.setAttribute("messagesD", messages);
    		request.setAttribute("friendsD", friends);
    	} else {
    		response.sendRedirect("login.jsp");
    	}
    	request.getRequestDispatcher("message.jsp").forward(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


