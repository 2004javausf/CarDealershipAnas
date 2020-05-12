package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Offer;
import com.revature.dao.OfferDAO;
import com.revature.util.ConnFactory;

public class OfferDAOImpl implements OfferDAO {
	
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public List<Offer> getOfferList() throws SQLException {
		List<Offer> offerList = new ArrayList<Offer>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM OFFERS");
		Offer c = null;
		while(rs.next()) {
			c = new Offer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6),rs.getInt(7), rs.getString(8));
			offerList.add(c);
		}
		
		return offerList;
	}

	
	@Override
	public void insertOffer(String customer, int carId, double carPrice, double downPmt, double monthlyPmt,
			int pmtLeft, String offerStatus) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO OFFERS VALUES (OFFERSEQ.NEXTVAL, ?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, customer );
		ps.setInt(2, carId);
		ps.setDouble(3, carPrice);
		ps.setDouble(4, downPmt);
		ps.setDouble(5, monthlyPmt);
		ps.setInt(6, pmtLeft);
		ps.setString(7, offerStatus);
		ps.executeUpdate();
		
	}

	@Override
	public void rejectOffer() throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void acceptOffer() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	

}
