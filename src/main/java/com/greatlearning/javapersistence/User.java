package com.greatlearning.javapersistence;

public class User {
	public int userId;
	public String firstName;
	public String lastName;
	public String email;
	public User(int userId, String firstName, String lastName, String email) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public User(){
	}

		@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}
	
}
