package com.User;

public class UserDetails {
	private String firstname;
	private String lastname;
	private String username;
	private String email;
	private String password;
	private String profilepic;
	public UserDetails() {
		super();
	}
	public UserDetails(String firstname, String lastname, String username, String email, String password, String profilepic) {
		super();
		this.firstname=firstname;
		this.lastname=lastname;
		this.username=username;
		this.email=email;
		this.password=password;
		this.profilepic=profilepic;
	}
	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String firstname) {
		this.firstname=firstname;
	}
	public String getlastName() {
		return lastname;
	}
	public void setLastName(String lastname) {
		this.lastname=lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUserName(String username) {
		this.username=username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getProfilePic() {
		return profilepic;
	}
	public void setProfilePic(String profilepic) {
		this.profilepic = profilepic;
	}
}
