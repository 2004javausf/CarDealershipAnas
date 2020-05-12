package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Car;
import com.revature.dao.CarDAO;
import com.revature.util.ConnFactory;

public class CarDAOImpl implements CarDAO{

	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void insertCar(String make, String model, String color, double price) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO CARS VALUES (CARSEQ.NEXTVAL, ?,?,?,'Available',?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, make);
		ps.setString(2, model);
		ps.setString(3, color);
		ps.setDouble(4, price);
		ps.executeUpdate();
	}

	@Override
	public List<Car> getCarList() throws SQLException {
		List<Car> carList = new ArrayList<Car>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM CARS");
		Car s = null;
		while(rs.next()) {
			s = new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getDouble(6));
			carList.add(s);
		}
		
		return carList;
	}

	@Override
	public void removeCar(int carId) throws SQLException{
		Connection conn = cf.getConnection();
		String sql = "{ call DELETECAR(?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setInt(1, carId);
			ps.execute();	
	}
}
