package com.revature.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Car;
import com.revature.beans.Employee;
import com.revature.beans.Offer;
import com.revature.daoimpl.CarDAOImpl;
import com.revature.daoimpl.OfferDAOImpl;
import com.revature.driver.Driver;
import com.revature.util.LogThis;
import com.revature.util.UserInfo;

public class EmployeeMenu {
	
	public static Scanner scanInt = new Scanner(System.in);
	public static Scanner scan = new Scanner(System.in);
	public static Scanner scanDub = new Scanner(System.in);
	static boolean logged = false;
	public static Employee c;
	
	// Customer Login
		public static void userLogin() {
			logged = false;
			do {
			System.out.println("Please enter your username");
			String inputUsername = scan.nextLine();
			c = UserInfo.findEmployeeByUsername(inputUsername);
			if(c.getUsername().equals(inputUsername)) {
				System.out.println("Please enter your password");
				String inputPassword = scan.nextLine();
				if (inputPassword.equals(c.getPassword())) {
					logged = true;
					LogThis.LogIt("info", c.getUsername() + " has logged into the employee portal");
					employeeMenu();
				} else System.out.println("Error: Wrong password");
			}else System.out.println("Error: Username not found");
			}while(logged == false);
		}	
		
		public static void employeeMenu() {
			
			System.out.println("\n==================================================");
			System.out.println("Welcome back boss. Let's make some big bucks.");
			
			PrintMenu loginMenu = new PrintMenu("Menu", "View Cars in Lot", "Add Car", "Remove Car", "View Payments", "Review Offers", "Exit");
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
				employeeMenu();
				break;
			case 2:
				// Add a car to the lot
				System.out.println("Please enter the car make:");
				String carMake = scan.nextLine();
				System.out.println("Please enter the car model:");
				String carModel = scan.nextLine();
				System.out.println("Please enter the car's color:");
				String carColor = scan.nextLine();
				System.out.println("Please enter the car's price:");
				double carPrice = scan.nextDouble();
				
				cardi = new CarDAOImpl();
				try {
					cardi.insertCar(carMake, carModel, carColor, carPrice);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				LogThis.LogIt("info", c.getUsername() + " has added a new car to the lot");
				System.out.println("\nThe car has successfully been added to the lot.");
				employeeMenu();
				break;
			case 3:
				// Remove a car from the lot
				OfferDAOImpl offdi = new OfferDAOImpl();
				List<Offer> offerList = new ArrayList<Offer>();
				boolean pendingOffer = false;
				
				System.out.println("Please enter the car ID that you wish to remove:");
				int carId = scanInt.nextInt();
				
				cardi = new CarDAOImpl();
				try {
					offerList = offdi.getOfferList();
					
					for(int i=0; i <offerList.size(); i++) {										
						if(offerList.get(i).getCarId() == carId) {
							pendingOffer = true;
						}
					}
					
					if(pendingOffer == true) {
						System.out.println("\nThere exist pending offers on the car that you wish to remove");
						System.out.println("Please resolve the pending offers before you proceed");
					}else {
						cardi.removeCar(carId);
						LogThis.LogIt("info", c.getUsername() + " has removed a car from the lot");
						System.out.println("\nThe car with ID [" + carId + "] has been successfully removed.\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				employeeMenu();
				break;
			case 4:
				// View Payments
				// Initialize variables
				offdi = new OfferDAOImpl();
				offerList = new ArrayList<Offer>();
				int offID;
				Offer o;
				
				// Print out all payments
				try {
					// Retrieve all offers from database into list
					offerList = offdi.getOfferList();
					
					System.out.println("\nPayments list:");
					
					for(int i=0; i <offerList.size(); i++) {
						Car carOffer = UserInfo.findCarbyCarId(offerList.get(i).getCarId());
						String carName = carOffer.getCarColor() + " " + carOffer.getCarMake() + " " + carOffer.getCarModel();
						
						if(offerList.get(i).getOfferStatus().equalsIgnoreCase("Accepted")) {
						System.out.println("Customer: " + offerList.get(i).getUsername() + ", Car: " + carName + ", Down Payment: $" + offerList.get(i).getDwnPmt() + 
								", Monthly Installment: $" + offerList.get(i).getMnthlyPmt());
						}
					}
				}catch (SQLException e) {
						e.printStackTrace();
					}
	
				employeeMenu();
				break;
			case 5:
				// Review Offers 				
				// Print out all pending offers
				offdi = new OfferDAOImpl();
				boolean offerExist = false;
				
				try {
					offerList = offdi.getOfferList();
					
					System.out.println("\nOffer list:");
					
					for(int i=0; i <offerList.size(); i++) {
						
						Car carOffer = UserInfo.findCarbyCarId(offerList.get(i).getCarId());
						String carName = carOffer.getCarColor() + " " + carOffer.getCarMake() + " " + carOffer.getCarModel() + " (CarID: " + offerList.get(i).getCarId() + ")";
					
						
						if(offerList.get(i).getOfferStatus().equalsIgnoreCase("Pending")) {
						System.out.println("[Offer ID: " + offerList.get(i).getOfferId() + "] Customer: " + offerList.get(i).getUsername() + ", Car: " + carName 
							+	", Down Payment: $" + offerList.get(i).getDwnPmt() + ", Monthly Payment: $" + offerList.get(i).getMnthlyPmt());
						}
					}
					
				// Review an offer via offer ID
					do {
						System.out.println("\nPlease enter the offer ID that you would like to review: ");

						offID = scanInt.nextInt();
						o = UserInfo.findOfferbyOfferId(offID);
						Car carOffer = UserInfo.findCarbyCarId(o.getCarId());
						String carName = carOffer.getCarColor() + " " + carOffer.getCarMake() + " " + carOffer.getCarModel();
					
						if(offID == o.getOfferId()) {
							offerExist = true;
							System.out.println("\nOffer to be reviewed: ");
							System.out.println("Customer: " + o.getUsername() );
							System.out.println("Car: " + carName);
							System.out.println("Down Payment: $" + o.getDwnPmt());
							System.out.println("Monthly Installment: $" + o.getMnthlyPmt());
							System.out.println("Payment duration: " + o.getPmtLeft() + " months");
							
							System.out.println("Would you like to accept this offer? [Y/N]");
							String offerAnswer = scan.nextLine();
							
							if(offerAnswer.equalsIgnoreCase("Y")) {
								offdi.acceptOffer(offID,o.getCarId());
								System.out.println("The customer's offer has been accepted");
								LogThis.LogIt("info", c.getUsername() + " has accepted offerID:" + offID);
							}else if(offerAnswer.equalsIgnoreCase("N")){
								offdi.rejectOffer(offID, o.getCarId());
								System.out.println("The customer's offer has been rejected");
								LogThis.LogIt("info", c.getUsername() + " has rejected offerID:" + offID);
							} else {
								System.out.println("Invalid input. Returning to the menu.");
							}
						}else {
							System.out.println("Offer not found");
						}}while(offerExist == false);
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				employeeMenu();
				break;
			case 6:
				// Main menu
				System.out.println("Logging out...");
				LogThis.LogIt("info", c.getUsername() + " has logged out");
				Driver.mainMenu();
			default:
				System.out.println("Goodbye \n");
				
				//Terminate program
				System.exit(0);
			}
		}

}
