package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Customer;
import com.revature.dao.CustomerDAO;
import com.revature.util.ConnFactory;

public class CustomerDAOImpl implements CustomerDAO {

	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public List<Customer> getCustomerList() throws SQLException {
		List<Customer> customerList = new ArrayList<Customer>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMERS");
		Customer c = null;
		while(rs.next()) {
			c = new Customer(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			customerList.add(c);
		}
		
		return customerList;
	}

	@Override
	public void createCustomer(String username, String password, String firstName, String lastName)
			throws SQLException {

		Connection conn = cf.getConnection();
		String sql = "INSERT INTO CUSTOMERS VALUES (CUSTSEQ.NEXTVAL, ?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username); 
		ps.setString(2, password);
		ps.setString(3, firstName);
		ps.setString(4, lastName);
		ps.executeUpdate();
		
	}

}
