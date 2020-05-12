package com.revature.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.daoimpl.CustomerDAOImpl;
import com.revature.util.UserInfo;

public class Registration {
	
	public static Scanner scan = new Scanner(System.in);
	Registration registeredUser = new Registration();
	
	public static void registerMenu() {
		
		CustomerDAOImpl custdi = new CustomerDAOImpl();
		boolean unique = false;
		String username;
		String password;
		String firstName;
		String lastName;
		
		do {
		System.out.println("Please create your desired username");
		username = scan.nextLine();
		
		// Validate that user name is unique
		 unique = UserInfo.validateUniqueUsername(username);
		} while(unique == false);
		
		System.out.println("Please create a password");
		password = scan.nextLine();
		
		System.out.println("Please enter your first name");
		firstName = scan.nextLine();
		
		System.out.println("Please enter your last name");
		lastName = scan.nextLine();
		
		// Create new customer object
		try {
			custdi.createCustomer(username, password, firstName, lastName);
			System.out.println("Thank you for registering an account with us!");
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		
	}
}
