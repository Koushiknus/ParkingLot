package com.gojek.parkinglot;

public class Car {
	
	String mRegistrationNumber;
	String mColour;
	
	public Car(String registrationNumber, String colour)
	{
		this.mRegistrationNumber = registrationNumber;
		this.mColour = colour;
	}
	public String getRegistrationNumber() {
		return mRegistrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.mRegistrationNumber = registrationNumber;
	}
	public String getColour() {
		return mColour;
	}
	public void setColour(String colour) {
		this.mColour = colour;
	}
	
	
	

}
