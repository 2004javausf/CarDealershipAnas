package com.revature.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Car;
import com.revature.beans.Customer;
import com.revature.beans.Employee;
import com.revature.beans.Offer;
import com.revature.daoimpl.CarDAOImpl;
import com.revature.daoimpl.CustomerDAOImpl;
import com.revature.daoimpl.EmployeeDAOImpl;
import com.revature.daoimpl.OfferDAOImpl;

public class UserInfo {

	public static List<Employee> employeeList = new ArrayList<Employee>();
	//public static List<Car>	carList =  new ArrayList<Car>();
	
	// Validate unique user name creation
	public static boolean validateUniqueUsername(String inputUserName) {
		boolean unique = true;
		CustomerDAOImpl custdi = new CustomerDAOImpl();
		List<Customer> customerList = null;
		
		try {
			customerList = custdi.getCustomerList();
			
			for (int i = 0; i < customerList.size(); i++) {
				String userName = customerList.get(i).getUsername();
				if (inputUserName.equals(userName)) {
					unique = false;
				}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unique;
	}
	
	
	// Find Customer by user name
	public static Customer findCustomerByUsername(String inputUserName) {
		CustomerDAOImpl custdi = new CustomerDAOImpl();
		List<Customer> customerList = null;
		Customer c = new Customer();
		
		try {
			customerList = custdi.getCustomerList();
			
			for (int i = 0; i < customerList.size(); i++) {
				String userName = customerList.get(i).getUsername();
				if (inputUserName.equals(userName)) {
					c = customerList.get(i);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return c;
	}	
	
	// Find Employee by user name
	public static Employee findEmployeeByUsername(String inputUserName) {
		EmployeeDAOImpl empdi = new EmployeeDAOImpl();
		List<Employee> employeeList = null;
		Employee c = new Employee();
		
		try {
			employeeList = empdi.getEmployeeList();
			
			for (int i = 0; i < employeeList.size(); i++) {
				String userName = employeeList.get(i).getUsername();
				if (inputUserName.equals(userName)) {
					c = employeeList.get(i);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return c;
	}	
	
	// Find Car by Car id
	public static Car findCarbyCarId(int carId) {
		CarDAOImpl cardi = new CarDAOImpl();
		List<Car> carList = null;
		Car c = new Car();
		
		try {
			carList = cardi.getCarList();
			
			for (int i = 0; i < carList.size(); i++) {
				int id = carList.get(i).getCarId();
				if (carId == id) {
					c = carList.get(i);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}	
	
	// Find Offer by Offer ID
	public static Offer findOfferbyOfferId(int offerId) {
		OfferDAOImpl cardi = new OfferDAOImpl();
		List<Offer> offerList = null;
		Offer c = new Offer();
		
		try {
			offerList = cardi.getOfferList();
			
			for (int i = 0; i < offerList.size(); i++) {
				int id = offerList.get(i).getOfferId();
				if (offerId == id) {
					c = offerList.get(i);
				}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}	
	
}
