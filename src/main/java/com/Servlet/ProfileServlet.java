package com.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FriendsDAO;
import com.DAO.PostsDAO;
import com.Db.DBConnect;
import com.User.FriendsDetails;
import com.User.PostDetails;
import com.User.UserDetails;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<PostDetails> posts = new ArrayList<>();
		PostsDAO pDao = new PostsDAO(DBConnect.getConn());
		UserDetails userDetails = (UserDetails) session.getAttribute("userD");
		if(request.getParameter("delete") != null) {
			try {
				  pDao.DeletePost(Integer.parseInt(request.getParameter("delete")));  
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (userDetails != null) {
			PostsDAO postsDAO = new PostsDAO(DBConnect.getConn());
			posts = postsDAO.getAllPosts();
			request.setAttribute("posts", posts);
		} else {
			response.sendRedirect("login.jsp");
		}
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
