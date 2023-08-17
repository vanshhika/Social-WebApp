package com.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.MessageDAO;
import com.Db.DBConnect;
import com.User.MessageDetails;
import com.User.UserDetails;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(false);
			System.out.println("Inside do get");
			String senderUN = ((UserDetails)session.getAttribute("userD")).getUsername().toString();
			if (session == null || session.getAttribute("userD") == null) {
				response.sendRedirect("login.jsp");
				return;
			}
			
			MessageDAO messageDAO = new MessageDAO(DBConnect.getConn());
			if(request.getParameter("delete") != null) {
				try {
					//messageDAO.deleteMessage(Integer.parseInt(request.getParameter("delete")));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			String receiverUN = "";
			ArrayList<MessageDetails> messages = new ArrayList<>();
			try {
				receiverUN = request.getParameter("id").toString();
				messages = messageDAO.getChat(senderUN, receiverUN);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("receiverUN", receiverUN);
			request.setAttribute("messages", messages);

		    // Obtain the RequestDispatcher object for the JSP file
		    RequestDispatcher dispatcher = request.getRequestDispatcher("openmessage.jsp");
		    // Forward the request and response objects to the JSP file
		    dispatcher.forward(request, response);
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String senderUN = ((UserDetails)session.getAttribute("userD")).getUsername().toString();
		String message = request.getParameter("message");
		String receiverUN = request.getParameter("receiverUN");
		if (!message.trim().equals("") && !receiverUN.trim().equals("")) {
			try {
			MessageDAO messageDAO = new MessageDAO(DBConnect.getConn());
			messageDAO.addMessage(senderUN, receiverUN, message);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("heyyyyyyy");
		response.sendRedirect("ChatServlet?id=" + receiverUN);
	}

}
