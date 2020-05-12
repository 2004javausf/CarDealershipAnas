package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Car;

public interface CarDAO {
	
	// Create
	public void insertCar(String make, String model, String color, double price) throws SQLException;
	
	// Read all
	public List<Car> getCarList() throws SQLException;
	
	// Remove car
	public void removeCar(int carId) throws SQLException;

	
}
