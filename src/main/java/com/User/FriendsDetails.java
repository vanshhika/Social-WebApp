package com.User;

import java.util.Objects;

public class FriendsDetails {

	private String username;
	private String friends;
	private String friendsfn;

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		FriendsDetails other = (FriendsDetails) obj;
		return Objects.equals(username, other.username) && Objects.equals(friends, other.friends)
				&& Objects.equals(friendsfn, other.friendsfn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, friends, friendsfn);
	}

	public FriendsDetails() {
		super();
	}

	public FriendsDetails(String username, String friends, String friendsfn) {
		super();
		this.username = username;
		this.friends = friends;
		this.friendsfn = friendsfn;
	}

	public String getName() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public String getFriend() {
		return friends;
	}

	public void setFriend(String friends) {
		this.friends = friends;
	}

	public String getFriendName() {
		return friendsfn;
	}

	public void setFriendName(String friendsfn) {
		this.friendsfn = friendsfn;
	}

}
