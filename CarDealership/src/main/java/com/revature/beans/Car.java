package com.revature.beans;

public class Car {

	private int carId;
	private String carMake;
	private String carModel;
	private String carColor;
	private String carStatus;
	private double carPrice;

	
	// Constructors
	public Car() {
		super();
	}

	public Car(int carId, String carMake, String carModel, String carColor, String carStatus, double carPrice) {
		super();
		this.carId = carId;
		this.carMake = carMake;
		this.carModel = carModel;
		this.carColor = carColor;
		this.carStatus = carStatus;
		this.carPrice = carPrice;
	}
	
	public Car(String carMake, String carModel, String carColor, String carStatus, double carPrice) {
		super();
		this.carMake = carMake;
		this.carModel = carModel;
		this.carColor = carColor;
		this.carStatus = carStatus;
		this.carPrice = carPrice;
	}

	// Setter & Getter Methods
	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getCarMake() {
		return carMake;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", carMake=" + carMake + ", carModel=" + carModel + ", carColor=" + carColor
				+ ", carStatus=" + carStatus + ", carPrice=" + carPrice + "]";
	}



}
