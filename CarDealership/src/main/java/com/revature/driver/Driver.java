package com.revature.driver;

import java.util.Scanner;

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
			System.out.println("=====================================================");
			System.out.println("Welcome to Luxury's Car Dealership!");
			System.out.println("Please select an option:");
						
			PrintMenu mainMenu = new PrintMenu("Main Menu", "Register Account", "Login", "Employee", "Exit");
			mainMenu.display();
			int mainMenuChoice = scan.nextInt();
			// Switch statement to process option chosen
			
			switch(mainMenuChoice) {
			default:
				System.out.println("Invalid input\n");
				mainMenu();

				break;
			case 1:
				// Register new account for customers
				Registration.registerMenu();
				break;
			case 2:
				// Customer login
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
