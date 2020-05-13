package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Offer;

public interface OfferDAO {
	
	// Read all
	public List<Offer> getOfferList() throws SQLException;
	
	// Insert new offer into table
	public void insertOffer(String customer, int carId, double carPrice, double downPmt, double monthlyPmt, int pmtLeft, String offerStatus) throws
	SQLException;
	
	// Accept Offer
	public void acceptOffer(int offerID, int carID) throws SQLException;
	
	// Reject offer
	public void rejectOffer(int offerID) throws SQLException;

}
