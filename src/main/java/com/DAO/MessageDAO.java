package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Db.DBConnect;
import com.User.MessageDetails;
import com.User.UserDetails;

public class MessageDAO {
	private Connection conn;

	public MessageDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public ArrayList<MessageDetails> getChat(String fromUser, String toUser) throws SQLException {
		Connection conn = DBConnect.getConn();
		PreparedStatement st = conn.prepareStatement(
				"SELECT * FROM [SocialDB].[dbo].[message] WHERE (sender = ? OR receiver = ?) AND (sender = ? OR receiver = ?) ORDER BY chattime;");
		st.setString(1, fromUser);
		st.setString(2, fromUser);
		st.setString(3, toUser);
		st.setString(4, toUser);
		ResultSet rs = st.executeQuery();
		ArrayList<MessageDetails> array = new ArrayList<>();
		while (rs.next()) {
			MessageDetails c = new MessageDetails();
			c.setChat_id(rs.getInt("chat_id"));
			c.setMSender(rs.getString("sender"));
			c.setMReceiver(rs.getString("receiver"));
			c.setMessage(rs.getString("msg"));
			c.setChattime(rs.getString("chattime"));
			array.add(c);
		}
		return array;
	}

	public ArrayList<MessageDetails> getAllMessage(String user) {
		Connection conn = DBConnect.getConn();
		ArrayList<MessageDetails> array = new ArrayList<>();
		try {
			PreparedStatement st = conn.prepareStatement(
					"SELECT * FROM [SocialDB].[dbo].[message] WHERE sender = ? AND chattime IN (SELECT MAX(chattime) FROM [SocialDB].[dbo].[message] GROUP by receiver) ORDER BY chattime DESC;");
			st.setString(1, user);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				MessageDetails c = new MessageDetails();
				c.setChat_id(rs.getInt("chat_id"));
				c.setMSender(rs.getString("sender"));
				c.setMReceiver(rs.getString("receiver"));
				c.setMessage(rs.getString("msg"));
				c.setChattime(rs.getString("chattime"));
				array.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	public void addMessage(String senderUN, String receiverUN, String message) throws SQLException {
		Connection conn = DBConnect.getConn();
		PreparedStatement st = conn
				.prepareStatement("INSERT INTO [SocialDB].[dbo].[message](sender, receiver, msg) VALUES (?,?,?);");
		st.setString(1, senderUN);
		st.setString(2, receiverUN);
		st.setString(3, message);
		st.executeUpdate();
	}
	public void deleteChat(String senderUN, String receiverUN){
		
		Connection conn = DBConnect.getConn();
		try {
		PreparedStatement st = conn.prepareStatement("DELETE FROM message WHERE (sender = ? AND receiver = ?) OR (receiver = ? AND sender = ?);");
		st.setString(1, senderUN);
		st.setString(2, receiverUN);
		st.setString(3, senderUN);
		st.setString(4, receiverUN);
		st.executeUpdate();}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
