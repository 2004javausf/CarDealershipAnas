package com.revature.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Car;
import com.revature.beans.Customer;
import com.revature.beans.Offer;
import com.revature.daoimpl.CarDAOImpl;
import com.revature.daoimpl.CustomerDAOImpl;
import com.revature.daoimpl.OfferDAOImpl;
import com.revature.driver.Driver;
import com.revature.util.Calculations;
import com.revature.util.LogThis;
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
					LogThis.LogIt("info", c.getUsername() + " has logged into the customer portal");
					customerMenu();
				} else System.out.println("Wrong password");
			}else System.out.println("Username not found");
			}while(logged == false);
		}	
		
		public static void customerMenu() {
			
			System.out.println("\n====================================");
			System.out.println("Welcome back sir. How may I help you?");
			
			PrintMenu loginMenu = new PrintMenu("Menu", "View Cars in Lot", "View Owned Cars", "View Payments", "Make An Offer", "Exit");
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
								  + " | Price: $" + carList.get(i).getCarPrice() + " |");
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
				cardi = new CarDAOImpl();
				carList =  new ArrayList<Car>();
				OfferDAOImpl offdi = new OfferDAOImpl();
				List<Offer> offerList = new ArrayList<Offer>();
				int offID;
				Offer o;
				
				// Print out cars owned by customer
				try {
					// Retrieve all offers from database into list
					offerList = offdi.getOfferList();
					
					System.out.println("\nYour inventory:");
					
					for(int i=0; i <offerList.size(); i++) {
						Car carOffer = UserInfo.findCarbyCarId(offerList.get(i).getCarId());
						String carName = carOffer.getCarColor() + " " + carOffer.getCarMake() + " " + carOffer.getCarModel();
						
						if(offerList.get(i).getOfferStatus().equalsIgnoreCase("Accepted") && offerList.get(i).getUsername().equals(c.getUsername())) {
						System.out.println("Car: " + carName + ", Bought Price: $" + carOffer.getCarPrice());
						}
					}
					
				}catch (SQLException e) {
						e.printStackTrace();
					}
				customerMenu();
				break;
			case 3:
				// View Remaining Payments
				offdi = new OfferDAOImpl();
				
				// Print out all payments
				try {
					// Retrieve all offers from database into list
					offerList = offdi.getOfferList();
					
					System.out.println("\nPayments list:");
					
					for(int i=0; i <offerList.size(); i++) {
						Car carOffer = UserInfo.findCarbyCarId(offerList.get(i).getCarId());
						String carName = carOffer.getCarColor() + " " + carOffer.getCarMake() + " " + carOffer.getCarModel();
						
						if(offerList.get(i).getOfferStatus().equalsIgnoreCase("Accepted") && offerList.get(i).getUsername().equals(c.getUsername())) {
							System.out.println("[Car]  " + carName);
							System.out.println("Down Payment Made: $" + offerList.get(i).getDwnPmt() + 
								", Monthly Installments: $" + offerList.get(i).getMnthlyPmt() + ", Remaining Payments: " + offerList.get(i).getPmtLeft() + "\n");
				
						}
					}
					
					System.out.println("\n**Your monthly installments will be charged at the end of the month.");
				}catch (SQLException e) {
						e.printStackTrace();
					}
				customerMenu();
				break;
			case 4:
				// Make an offer
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
				
				// Insert offer into Offers table
				offdi = new OfferDAOImpl();
				cardi = new CarDAOImpl();
				
				try {
					carList =  cardi.getCarList();
					offdi.insertOffer(c.getUsername(), offer, carList.get(offer).getCarPrice(), dwnPmt, monthlyPmt, months, "Pending");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("Thank you for placing down your offer");
				LogThis.LogIt("info", c.getUsername() + " has placed an offer on carID " + offer);
				customerMenu();
				break;
			case 5:
				// Return to main menu
				System.out.println("Logging out..\n");
				LogThis.LogIt("info", c.getUsername() + " has logged out");
				Driver.mainMenu();
			default:
				System.out.println("Goodbye \n");
				
				//Terminate program
				System.exit(0);
			}
		}
	
	
}
