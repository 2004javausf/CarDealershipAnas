package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Customer;

public interface CustomerDAO {
	
	// Register
	public void createCustomer(String username, String password, String firstName, String lastName) throws SQLException;
	
	// Read all
	public List<Customer> getCustomerList() throws SQLException;
		

}
