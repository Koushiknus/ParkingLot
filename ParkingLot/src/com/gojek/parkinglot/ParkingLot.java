package com.gojek.parkinglot;

public class ParkingLot {
	
	int slotNumber;
	boolean isSlotAvailable = true;
	Car car;
	
	
	public int getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}
	public boolean isSlotAvailable() {
		return isSlotAvailable;
	}
	public void setSlotAvailable(boolean isSlotAvailable) {
		this.isSlotAvailable = isSlotAvailable;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	
	

}
