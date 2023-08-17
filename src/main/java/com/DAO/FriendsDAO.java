package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.User.FriendsDetails;
import com.User.RequestDetails;
import com.User.UserDetails;

public class FriendsDAO {
	private Connection conn;
	public FriendsDAO(Connection conn) {
		super();
		this.conn=conn;
	}
	public boolean addFriend(FriendsDetails fr) {
		boolean f = false;
		try {
			String qurty = "INSERT INTO [dbo].[friendlist]([username],[friends],[friendsfn]) VALUES (?,?,?)";
			PreparedStatement pStatement = conn.prepareStatement(qurty);
			pStatement.setString(1, fr.getName());
			pStatement.setString(2, fr.getFriend());
			pStatement.setString(3, fr.getFriendName());
			int i = pStatement.executeUpdate();
			if(i==1) {
				f=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public List<FriendsDetails> getFriends(String username){
		List<FriendsDetails> finaList =new ArrayList<>();
		try {
			String query = "SELECT * FROM [dbo].[friendlist] where [username]=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				FriendsDetails fd = new FriendsDetails();
				fd.setName(rs.getString("username"));
				fd.setFriend(rs.getString("friends"));
				fd.setFriendName(rs.getString("friendsfn"));
				finaList.add(fd);
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return finaList;
	}
}
