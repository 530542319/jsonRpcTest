package com.aop;

public class TbUser {
	
	private String firstName;
	
	private String userName;
	
	private String password;
	
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "TbUser [firstName=" + firstName + ", userName=" + userName
				+ ", password=" + password + ", email=" + email + "]";
	}

}
