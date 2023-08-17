package com.User;

public class RequestDetails {
	private String SenderID;
	private String RecieverID;
	public RequestDetails() {
		super();
	}
	public RequestDetails(String SenderID, String ReceiverID) {
		super();
		this.SenderID=SenderID;
		this.RecieverID=ReceiverID;
	}
	public String getSenderID() {
		return SenderID;
	}
	public void setSenderID(String SenderID) {
		this.SenderID=SenderID;
	}
	public String getReceiverID() {
		return RecieverID;
	}
	public void setReceiverID(String ReceiverID) {
		this.RecieverID=ReceiverID;
	}
	
}
