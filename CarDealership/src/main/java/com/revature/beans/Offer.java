package com.revature.beans;

public class Offer {

	private int offerId;
	private String username;
	private String offerStatus;
	private double dwnPmt;
	private double mnthlyPmt;
	private int pmtLeft;
	private int carId;
	
	
	public Offer() {
		super();
	}


	public Offer(int offerId, String username, int carId, double carPrice, double dwnPmt, double mnthlyPmt, int pmtLeft, String offerStatus) {
		super();
		this.offerId = offerId;
		this.username = username;
		this.offerStatus = offerStatus;
		this.dwnPmt = dwnPmt;
		this.mnthlyPmt = mnthlyPmt;
		this.pmtLeft = pmtLeft;
		this.setCarId(carId);
	}


	public int getOfferId() {
		return offerId;
	}


	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getOfferStatus() {
		return offerStatus;
	}


	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}


	public double getDwnPmt() {
		return dwnPmt;
	}


	public void setDwnPmt(double dwnPmt) {
		this.dwnPmt = dwnPmt;
	}


	public double getMnthlyPmt() {
		return mnthlyPmt;
	}


	public void setMnthlyPmt(double mnthlyPmt) {
		this.mnthlyPmt = mnthlyPmt;
	}


	public int getPmtLeft() {
		return pmtLeft;
	}


	public void setPmtLeft(int pmtLeft) {
		this.pmtLeft = pmtLeft;
	}

	public int getCarId() {
		return carId;
	}


	public void setCarId(int carId) {
		this.carId = carId;
	}
	

	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", username=" + username + ", offerStatus=" + offerStatus + ", dwnPmt="
				+ dwnPmt + ", mnthlyPmt=" + mnthlyPmt + ", pmtLeft=" + pmtLeft + "]";
	}

}
