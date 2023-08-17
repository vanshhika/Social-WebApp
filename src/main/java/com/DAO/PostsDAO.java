package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import com.Db.DBConnect;
import com.User.MessageDetails;
import com.User.PostDetails;

public class PostsDAO {
	private Connection conn;
	public PostsDAO(Connection conn) {
		super();
		this.conn=conn;
	}
	public void insertPost(String user_id, String body){
		Connection conn = DBConnect.getConn();
		try {
		PreparedStatement st = conn.prepareStatement("INSERT INTO [SocialDB].[dbo].[posts](user_id, body) VALUES (?,?);");
		st.setString(1, user_id);
		st.setString(2, body);
		st.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public ArrayList<PostDetails> getAllPosts() {
		Connection conn = DBConnect.getConn();
		ArrayList<PostDetails> array = new ArrayList<>();
		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM [SocialDB].[dbo].[posts] ORDER BY timeposted DESC;");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				PostDetails c = new PostDetails();
				c.setPostID(rs.getInt("post_id"));
				c.setUserID(rs.getString("user_id"));
				c.setBody(rs.getString("body"));
				c.setPosttime(rs.getString("timeposted"));
				array.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	public void DeletePost(int postid) {
		Connection conn = DBConnect.getConn();
		try {
		PreparedStatement st = conn.prepareStatement("DELETE FROM posts WHERE post_id = ?;");
		st.setInt(1, postid);
		st.executeUpdate();}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
