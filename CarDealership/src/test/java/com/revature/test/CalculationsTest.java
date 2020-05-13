package com.revature.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.revature.beans.Car;
import com.revature.daoimpl.CarDAOImpl;
import com.revature.util.Calculations;

class CalculationsTest {

	@Test
	void testCalcMonthlyPmt() {
		CarDAOImpl cardi = new CarDAOImpl();
		double monthlyPmt = 0;
		double carPrice = 0;
		int carId = 1;
		double expctdMthlyPmt = 0;
		
		try {
			List<Car> carList = cardi.getCarList();
			for(int i=0; i<carList.size();i++) {
				if(carId == carList.get(i).getCarId()) {
					carPrice = carList.get(i).getCarPrice();
				}
			}
			expctdMthlyPmt = (carPrice - 20000)/40;	
		} catch (SQLException e) {	
			e.printStackTrace();
		}

		finally {
			double payment = Calculations.calcMonthlyPmt(1, 20000, 40);
			assertEquals(expctdMthlyPmt,payment);
		}
	}

}
