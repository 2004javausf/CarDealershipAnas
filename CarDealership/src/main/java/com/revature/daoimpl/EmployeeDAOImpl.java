package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDAO;
import com.revature.util.ConnFactory;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public List<Employee> getEmployeeList() throws SQLException {
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");
		Employee c = null;
		while(rs.next()) {
			c = new Employee(rs.getString(2), rs.getString(3));
			employeeList.add(c);
		}
		
		return employeeList;
	}

}
