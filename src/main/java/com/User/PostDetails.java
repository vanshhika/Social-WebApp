package com.User;

public class PostDetails {
	private int post_id;
	private String user_id;
	private String body;
	private String posttime;
	
	public PostDetails() {
		super();
	}
	public PostDetails(int post_id, String userid, String body, String posttime) {
		super();
		this.post_id=post_id;
		this.user_id=user_id;
		this.body=body;
		this.posttime=posttime;
	}
	public int getPostID() {
		return post_id;
	}
	public void setPostID(int post_id) {
		this.post_id=post_id;
	}
	public String getUserID() {
		return user_id;
	}
	public void setUserID(String user_id) {
		this.user_id=user_id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body=body;
	}
	public String getPosttime() {
		return posttime;
	}
	public void setPosttime(String posttime) {
		this.posttime=posttime;
	}
}

