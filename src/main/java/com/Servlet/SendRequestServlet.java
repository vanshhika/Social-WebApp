package com.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.RequestsDAO;
import com.DAO.UserDAO;
import com.Db.DBConnect;
import com.User.RequestDetails;
import com.User.UserDetails;

@WebServlet("/SendRequestServlet")
public class SendRequestServlet extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String SenderID= request.getParameter("SenderID");
		String ReceiverID= request.getParameter("ReceiverID");
		RequestDetails req=new RequestDetails();
		req.setSenderID(SenderID);
		req.setReceiverID(ReceiverID);
		
		RequestsDAO dao = new RequestsDAO(DBConnect.getConn());
		boolean f = dao.addRequest(req);
		HttpSession session;
		
		if(f) {
			session  = request.getSession();
			session.setAttribute("req-sent","Request Sent");
			response.sendRedirect("UserHome.jsp");
		}
		else {
			session = request.getSession();
			session.setAttribute("req-failed","Something went wrong on server or you have used registered email or username");
			response.sendRedirect("UserHome.jsp");
		}
	}
}
