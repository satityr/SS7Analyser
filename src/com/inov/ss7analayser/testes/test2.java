package com.inov.ss7analayser.testes;

import com.inov.ss7analyser.beans.Device;


	
	public class test2 {
		
		public static void main(String[] args){
			
			
			Device device = new Device();
			StringBuilder[] devicesToChooseFrom ;
			
			
			
			System.out.println(device.isStatus());
			
			devicesToChooseFrom = device.getDevicesListName();
			
			for(StringBuilder deviceInfo : devicesToChooseFrom){
				
				System.out.print(deviceInfo.toString());
				
			}
			
			// here the user should choose an interface (device) to capture from a list
			// but for testing we will work with the first one by default
			
			device.setChoosenDevice(devicesToChooseFrom[1]);
			
			System.out.println();
			System.out.println("choosen device : ");
			System.out.println(device.getChoosenDevice().getDescription());
			
		}
		
		
		
}