package com.DAO;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.User.UserDetails;

public class UserDAO {
	private Connection conn;

	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean addUser(UserDetails us) {
		boolean f = false;
		if (check(us)) {
			try {
				String qurty = "INSERT INTO [dbo].[user]([firstname],[lastname],[email],[username],[password]) VALUES (?,?,?,?,?)";
				PreparedStatement pStatement = conn.prepareStatement(qurty);
				pStatement.setString(1, us.getFirstName());
				pStatement.setString(2, us.getlastName());
				pStatement.setString(3, us.getEmail());
				pStatement.setString(4, us.getUsername());
				pStatement.setString(5, us.getPassword());
				int i = pStatement.executeUpdate();
				if (i == 1) {
					f = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return f;
	}

	public boolean check(UserDetails us) {
		boolean f = true;
		try {
			String query = "SELECT * FROM [dbo].[user] where [username]=? or [email]=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, us.getUsername());
			ps.setString(2, us.getEmail());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				f = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public UserDetails loginUser(UserDetails us) {
		UserDetails user = null;
		try {
			String query = "SELECT * FROM [dbo].[user] where [username]=? and [password]=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, us.getUsername());
			ps.setString(2, us.getPassword());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new UserDetails();
				user.setUserName(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

	public UserDetails getUser(String s) {
		UserDetails user = null;
		try {
			String query = "SELECT * FROM [dbo].[user] where [username]=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new UserDetails();
				user.setUserName(rs.getString("username"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setProfilePic(rs.getString("profilepic"));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
