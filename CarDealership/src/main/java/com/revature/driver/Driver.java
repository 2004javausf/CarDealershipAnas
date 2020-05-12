package com.revature.driver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Offer;
import com.revature.daoimpl.CarDAOImpl;
import com.revature.daoimpl.OfferDAOImpl;
import com.revature.menu.CustomerMenu;
import com.revature.menu.EmployeeMenu;
import com.revature.menu.PrintMenu;
import com.revature.menu.Registration;

public class Driver {

	static Scanner scan = new Scanner(System.in);
	static Scanner scString = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// Print out the main menu
		mainMenu();
	}
		
	
	//----------------------Main Menu Method--------------------------------------------//
	
		public static void mainMenu() {
			System.out.println("Welcome to Luxury's Car Dealership!");
			System.out.println("Please select an option:");
			
//			EmployeeDAOImpl custdi = new EmployeeDAOImpl();
//			CarDAOImpl cardi = new CarDAOImpl();
//			OfferDAOImpl offdi = new OfferDAOImpl();
//			List<Offer> offerList = new ArrayList<Offer>();
//			try {
//				System.out.println(offdi.getOfferList());
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}
			
			PrintMenu mainMenu = new PrintMenu("Main Menu", "Register Account", "Login", "Employee", "Exit");
			mainMenu.display();
			int mainMenuChoice = scan.nextInt();
			// Switch statement to process option chosen
			
			switch(mainMenuChoice) {
			default:
				System.out.println("Invalid input\n");
				mainMenu();
				//Terminate program
				System.exit(0);
			case 1:
				// Register new account as a customer
				Registration.registerMenu();
				break;
			case 2:
				// Login for customer
				CustomerMenu.userLogin();
				break;
			case 3:
				// Employee login
				EmployeeMenu.userLogin();	
				break;
			case 4:
				// Exit program
				System.out.println("\nThank you for doing business with us.");
				break;
			}
			
		}

}
