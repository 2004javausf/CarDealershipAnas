package com.revature.beans;

public class Employee {

	private String username;
	private String password;
	
	// Constructors
	public Employee() {
		super();
	}

	public Employee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	// Setter & Getter Methods
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + "]";
	}
		
}
