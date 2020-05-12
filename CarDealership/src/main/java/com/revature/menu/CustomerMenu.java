package com.revature.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Car;
import com.revature.beans.Customer;
import com.revature.daoimpl.CarDAOImpl;
import com.revature.daoimpl.CustomerDAOImpl;
import com.revature.daoimpl.OfferDAOImpl;
import com.revature.driver.Driver;
import com.revature.util.Calculations;
import com.revature.util.UserInfo;

public class CustomerMenu {
	
	public static Scanner scanInt = new Scanner(System.in);
	public static Scanner scan = new Scanner(System.in);
	public static Scanner scanDub = new Scanner(System.in);
	static boolean logged = false;
	public static Customer c;
	public static Car car;
	static boolean carExist = false;
	
	// Customer Login
		public static void userLogin() {
			logged = false;
			do {
			System.out.println("Please enter your username");
			String inputUsername = scan.nextLine();
			c = UserInfo.findCustomerByUsername(inputUsername);
			if(c.getUsername().equals(inputUsername)) {
				System.out.println("Please enter your password");
				String inputPassword = scan.nextLine();
				if (inputPassword.equals(c.getPassword())) {
					logged = true;
					customerMenu();
				} else System.out.println("Wrong password");
			}else System.out.println("Username not found");
			}while(logged == false);
		}	
		
		public static void customerMenu() {
			
			System.out.println("\n====================================");
			System.out.println("Welcome back sir. How may I help you?");
			
			PrintMenu loginMenu = new PrintMenu("Menu", "View Cars in Lot", "View Owned Cars", "View Payments", "Make a Payment", "Make Offer", "Exit");
			loginMenu.display();
			int customerMenuChoice = scanInt.nextInt();
			
			// Switch statement to process option chosen
			switch(customerMenuChoice) {
			case 1:
				// View Cars
				CarDAOImpl cardi = new CarDAOImpl();
				List<Car>	carList =  new ArrayList<Car>();
				try {
					carList = cardi.getCarList();
					
					System.out.println("\nCurrent inventory: ");
					for(int i=0; i <carList.size(); i++) {
						if(carList.get(i).getCarStatus().equalsIgnoreCase("available")) {
						System.out.println("[Car ID: " + carList.get(i).getCarId() + "] | " + carList.get(i).getCarColor() + " " + carList.get(i).getCarMake() + " " + carList.get(i).getCarModel()
								  + " | Price:" + carList.get(i).getCarPrice() + " |");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				customerMenu();
				break;
			case 2:
				// View Owned Cars
				
			case 3:
				// View Payments
			
			case 4:
				// Make Payment
				break;
				
			case 5:
				// Make Offers
				int offer;
				
				do {
				System.out.println("Please enter the car ID for which you would like to put down an offer: ");
				offer = scanInt.nextInt();
				car = UserInfo.findCarbyCarId(offer);
				if(offer == car.getCarId()) {
					carExist = true;
					System.out.println("\nCar chosen: " + car.getCarColor() + " " + car.getCarMake() + " " + car.getCarModel());
					System.out.println("Price: $" + car.getCarPrice());
				}else {
					System.out.println("Car not found");
				}}while(carExist == false);
				
				System.out.println("Please enter the amount for your downpayment:");
				double dwnPmt = scanDub.nextDouble();	
				
				System.out.println("Please enter the number of months for you to pay off the car:");
				int months = scanInt.nextInt();
				
				double monthlyPmt = Calculations.calcMonthlyPmt(offer, dwnPmt, months);
				
				// add offer to Offers table
				OfferDAOImpl offdi = new OfferDAOImpl();
				cardi = new CarDAOImpl();
				
				try {
					carList =  cardi.getCarList();
					offdi.insertOffer(c.getUsername(), offer, carList.get(offer).getCarPrice(), dwnPmt, monthlyPmt, months, "Pending");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("Thank you for placing down your offer");	
				break;
			case 6:
				// Main menu
				Driver.mainMenu();
			default:
				System.out.println("Invalid input. Goodbye \n");
				
				//Terminate program
				System.exit(0);
			}
		}
	
		
	//----------------------------Methods for Customer Menu---------------------------------------//
	

	
	
}
