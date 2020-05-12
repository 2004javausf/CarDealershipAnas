package com.revature.util;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Car;
import com.revature.daoimpl.CarDAOImpl;

public class Calculations {
	
	// Calculate monthly payment
	public static double calcMonthlyPmt(int carId, double downPmt, int months) {
		double monthlyPmt = 0;
		double carPrice = 0;
		
		CarDAOImpl cardi = new CarDAOImpl();
		try {
			List<Car> carList = cardi.getCarList();
			
			for(int i=0; i<carList.size();i++) {
				if(carId == carList.get(i).getCarId()) {
					carPrice = carList.get(i).getCarPrice();
				}
			
			monthlyPmt = (carPrice - downPmt) / months;
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return monthlyPmt;
	}

	
//	// Calculate number of payments left
//	public static int paymentsLeft(double pmt, double monthlyPmt) {
//		int months;
//		
//		return months;
//	}

}
