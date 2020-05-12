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
					employeeMenu();
				} else System.out.println("Error 291: Wrong password");
			}else System.out.println("Error v1.0: Username not found");
			}while(logged == false);
		}	
		
		public static void employeeMenu() {
			
			System.out.println("\n====================================");
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
						System.out.println("[Car ID: " + carList.get(i).getCarId() + "] | " + carList.get(i).getCarColor() + " " + carList.get(i).getCarMake() + " " + carList.get(i).getCarModel()
								  + " | Price:" + carList.get(i).getCarPrice() + " |");
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
				
				System.out.println("\nThe car has successfully been added.");
				
			case 3:
				// Remove a car from the lot
				System.out.println("Please enter the car ID that you wish to remove:");
				int carId = scanInt.nextInt();
				
				cardi = new CarDAOImpl();
				try {
					cardi.removeCar(carId);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("\nThe car with ID [" + carId + "] has been successfully removed.");
				
			case 4:
				// View Payments
				
				
				break;
			case 5:
				// Review Offers 
				OfferDAOImpl offdi = new OfferDAOImpl();
				List<Offer> offerList = new ArrayList<Offer>();
				int offID;
				Offer o;
				boolean offerExist = false;
				
				// Print out all pending offers
				try {
					offerList = offdi.getOfferList();
					
					System.out.println("\nOffer list:");
					
					for(int i=0; i <offerList.size(); i++) {
						//if(offerList.get(i).getOfferStatus().equalsIgnoreCase("Pending")) {
						System.out.println("[Offer ID: " + offerList.get(i).getOfferId() + "] Customer: " + offerList.get(i).getUsername() + ", Down Payment: " + offerList.get(i).getDwnPmt() + 
								", Monthly Payment: " + offerList.get(i).getMnthlyPmt());
						//}
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
								offdi.acceptOffer();
								System.out.println("The offer has been accepted");
							}else {
								System.out.println("The offer has been rejected");
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
				Driver.mainMenu();
			default:
				System.out.println("Invalid input. Goodbye \n");
				
				//Terminate program
				System.exit(0);
			}
		}
	
	
	//----------------------------Methods for Customer Screen---------------------------------------//

}
