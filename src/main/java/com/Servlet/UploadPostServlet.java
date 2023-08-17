package com.Servlet;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.DAO.FriendsDAO;
import com.DAO.MessageDAO;
import com.DAO.PostsDAO;
import com.DAO.UserDAO;
import com.Db.DBConnect;
import com.User.FriendsDetails;
import com.User.MessageDetails;
import com.User.PostDetails;
import com.User.UserDetails;

/**
 * Servlet implementation class UploadPostServlet
 */

import javax.servlet.annotation.MultipartConfig;
@WebServlet("/UploadPostServlet")
//Add the @MultipartConfig annotation to enable multi-part support
@MultipartConfig
public class UploadPostServlet extends HttpServlet {
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     HttpSession session = request.getSession();
     UserDetails user = (UserDetails) session.getAttribute("userD");
     String imagePath = null;

     // Check if a file was uploaded
     Part filePart = request.getPart("post");
     if (filePart != null && filePart.getSize() > 0) {
         // Get the submitted file name
         String fileName = filePart.getSubmittedFileName();

         // Set the image path to be stored in the database
         imagePath = "images/" + fileName;
     }
     
     // Store the imagePath in the database
     PostsDAO pdao = new PostsDAO(DBConnect.getConn());
     pdao.insertPost(user.getUsername(), imagePath);
     response.sendRedirect("UploadPostServlet");
 }
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	HttpSession session = request.getSession();
	 	ArrayList<PostDetails> posts = new ArrayList<>();
	 	List<FriendsDetails> friends = new ArrayList<>();
	 	UserDetails userDetails = (UserDetails)session.getAttribute("userD");
 	if (userDetails != null) {
 		PostsDAO postsDAO = new PostsDAO(DBConnect.getConn());
 		FriendsDAO friendsDAO = new FriendsDAO(DBConnect.getConn());
 		friends = friendsDAO.getFriends(userDetails.getUsername());
 		posts=postsDAO.getAllPosts();
 		request.setAttribute("posts", posts);
 		request.setAttribute("friends", friends);
 	} else {
 		response.sendRedirect("login.jsp");
 	}
	 	request.getRequestDispatcher("UserHome.jsp").forward(request, response);
	 }
}
