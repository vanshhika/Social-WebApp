package com.User;

public class MessageDetails {
	private int chat_id;
	private String sender;
	private String receiver;
	private String msg;
	private String chattime;

	public MessageDetails() {
		super();
	}

	public MessageDetails(int chat_id, String sender, String receiver, String msg, String chattime) {
		super();
		this.chat_id = chat_id;
		this.sender = sender;
		this.receiver = receiver;
		this.msg = msg;
		this.chattime = chattime;
	}

	public int getChat_id() {
		return chat_id;
	}

	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}

	public String getMSender() {
		return sender;
	}

	public void setMSender(String sender) {
		this.sender = sender;
	}

	public String getMReceiver() {
		return receiver;
	}

	public void setMReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return msg;
	}

	public void setMessage(String msg) {
		this.msg = msg;
	}

	public String getChattime() {
		return chattime;
	}

	public void setChattime(String chattime) {
		this.chattime = chattime;
	}

}
