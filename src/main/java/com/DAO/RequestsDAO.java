package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.User.RequestDetails;
import com.User.UserDetails;

public class RequestsDAO {
	private Connection conn;
	public RequestsDAO(Connection conn) {
		super();
		this.conn=conn;
	}
	public boolean addRequest(RequestDetails req) {
		boolean f = false;
		try {
			String qurty = "INSERT INTO [dbo].[requests]([SenderID],[ReceiverID]) VALUES (?,?)";
			PreparedStatement pStatement = conn.prepareStatement(qurty);
			pStatement.setString(1, req.getSenderID());
			pStatement.setString(2, req.getReceiverID());
			int i = pStatement.executeUpdate();
			if(i==1) {
				f=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public List<RequestDetails> showRequest(RequestDetails req){
		List<RequestDetails> finaList =new ArrayList<>();
		try {
			String query = "SELECT * FROM [dbo].[requests] where [ReceiverID]=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, req.getReceiverID());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RequestDetails requestDetails = new RequestDetails();
				requestDetails.setReceiverID(rs.getString("ReceiverID"));
				requestDetails.setSenderID(rs.getString("SenderID"));
				finaList.add(requestDetails);
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return finaList;
	}
	public boolean removeRequest(String s, String r) {
		boolean f=false;
		try {
			String query = "DELETE FROM [SocialDB].[dbo].[requests] WHERE [ReceiverID] = ? AND [SenderID] = ?";
			PreparedStatement pStatement = conn.prepareStatement(query);
			pStatement.setString(1, r);
			pStatement.setString(2, s);
			int i = pStatement.executeUpdate();

			if (i == 1) {
			    f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return f;
	}
}
