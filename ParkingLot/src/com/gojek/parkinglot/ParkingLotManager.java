package com.gojek.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

public class ParkingLotManager {
	
	boolean isProgramCommandMode;
	ParkingLot[] parkingLots;
	
	public static void main(String[] args)
	{
		ParkingLotManager manager = new ParkingLotManager();
		try
		{
		if(args.length >= 1)
		{
			
			BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
			manager.readFile(reader);
			
		}
		else
		{
			manager.isProgramCommandMode = true;
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Input:");	
				manager.readFile(reader);
			
		}
		
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public void readFile(BufferedReader reader) throws Exception
	{
		String line = null;
		
		while((line = reader.readLine())!=null)
		{
			
			//System.out.println(line);
			System.out.println(executeCommand(line));
			if(isProgramCommandMode)
			{
				System.out.println("Input:");
			}
		}
	}
	
	public String executeCommand(String query)
	{
		String[] result = query.split(" ");
		String commandResult = "";
		switch(result[0])
		{
		case KeywordConstant.CREATE_PARKING_LOT:
			commandResult = initializeParkingLot(Integer.parseInt(result[1]));
			break;
		case KeywordConstant.PARK_CAR:
			commandResult = parkCar(result);
			break;
		case KeywordConstant.LEAVE_PARKING_LOT:
			commandResult = leaveParkingSlot(Integer.parseInt(result[1]));
			break;
		case KeywordConstant.STATUS:
			commandResult = printParkingLotStatus();
			break;
		case KeywordConstant.CAR_BY_COLOUR:
			commandResult = getCarByColour(result[1]);
			break;
		case KeywordConstant.SLOT_BY_COLOUR:
			commandResult = getSlotByColour(result[1]);
			break;
			
		case KeywordConstant.SLOT_BY_REG_NUMBER:
			commandResult = getSlotByRegistrationNumber(result[1]);
			break;
			
		}
		if(isProgramCommandMode)
		{
			commandResult = "Output:\n" + commandResult;
		}
		return commandResult;
	}

	public String printParkingLotStatus()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Slot No.");
		buffer.append(KeywordConstant.TAB);
		buffer.append("Registration No");
		buffer.append(KeywordConstant.TAB);
		buffer.append("Colour");
		buffer.append(KeywordConstant.NEWLINE);
		
		
		for(int i=0;i<parkingLots.length;i++)
		{
			if(!parkingLots[i].isSlotAvailable)
			{
				Car car = parkingLots[i].getCar();
				buffer.append(parkingLots[i].slotNumber);
				buffer.append(KeywordConstant.TAB);
				buffer.append(car.getRegistrationNumber());
				buffer.append(KeywordConstant.TAB);
				buffer.append(car.getColour());
				buffer.append(KeywordConstant.NEWLINE);
			}
		}
		
		
		return buffer.toString();
	}
	
	public String getCarByColour(String colour)
	{
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<parkingLots.length;i++)
		{
			if(!parkingLots[i].isSlotAvailable)
			{
				Car car = parkingLots[i].getCar();
				
				if(car.getColour().equals(colour))
				{
					if(buffer.length()>0)
					{
						buffer.append(", ");
					}
					buffer.append(car.getRegistrationNumber());
					
				}
			}
		}
		
		return buffer.toString();
	}
	
	public String getSlotByColour(String colour)
	{
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<parkingLots.length;i++)
		{
			if(!parkingLots[i].isSlotAvailable)
			{
				Car car = parkingLots[i].getCar();
				
				if(car.getColour().equals(colour))
				{
					if(buffer.length()>0)
					{
						buffer.append(", ");
					}
					buffer.append(parkingLots[i].slotNumber);
					
				}
			}
		}
		
		return buffer.toString();
	}
	
	
	public String getSlotByRegistrationNumber(String regNumber)
	{
		int slotNumber = -1;
		for(int i=0;i<parkingLots.length;i++)
		{
			if(!parkingLots[i].isSlotAvailable)
			{
				Car car = parkingLots[i].getCar();
				
				if(car.getRegistrationNumber().equals(regNumber))
				{
					slotNumber = parkingLots[i].getSlotNumber();
				}
			}
		}
		if(slotNumber == -1)
		{
			return "Not found";
		}
		return String.valueOf(slotNumber);
	}
	
	public String leaveParkingSlot(int parkingSlot)
	{
		ParkingLot currentParkingLot = parkingLots[parkingSlot-1];
		currentParkingLot.setCar(null);
		currentParkingLot.setSlotAvailable(Boolean.TRUE);
		return "Slot number "+ parkingSlot +" is free";
	}
	
	public String parkCar(String[] carDetails)
	{
	
			Car car = new Car(carDetails[1],carDetails[2]);
			boolean isParkingLotFull = true;
			int status = 1;
			for(int i=0;i<parkingLots.length;i++)
			{
				if(parkingLots[i].isSlotAvailable)
				{
					isParkingLotFull = false;
					parkingLots[i].setCar(car);
					parkingLots[i].isSlotAvailable = false;
					status = i+1;
					break;
				}
			}
			if(isParkingLotFull)
			{
				return "Sorry, parking lot is full";
			}
	
		
		return "Allocated slot number: "+status;
	}
	
	public String initializeParkingLot(int parkingSlots)
	{
		parkingLots = new ParkingLot[parkingSlots];
		
		for(int i=0; i<parkingSlots;i++)
		{
			ParkingLot parkingLot = new ParkingLot();
			parkingLot.setSlotNumber(i+1);
			parkingLots[i] = parkingLot;
		}
		
		return "Created a parking lot with "+parkingSlots+" slots";
	}

}
