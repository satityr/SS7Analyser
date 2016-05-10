package com.inov.ss7analyser.beans;




import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;








import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;


public class OnlineCapture extends AbstractVerticle {
	
	

		Device device = new Device();
		StringBuilder message = new StringBuilder();
		private Pcap openedDevice ;
		
		JsonObject jobj = new JsonObject() ;
		
		StringBuilder[] devicesToChooseFrom ;
		
	
		
	
		
		
		
		PcapPacketHandler<JsonObject> handler = new PcapPacketHandler<JsonObject>() {
	          public void nextPacket(PcapPacket packet, JsonObject obj) {
	        	  
	        	obj.put("packet", packet.toString());
	        	vertx.eventBus().send ("com.inov.analyser", obj );
	        	
	          }
	        };
		
		public void start(Future<Void> startFuture) throws Exception {
			
			

			devicesToChooseFrom = device.getDevicesListName();
			
			
			if(device.isStatus()){
			
			for(StringBuilder deviceInfo : devicesToChooseFrom){
				
				System.out.print(deviceInfo.toString());
				
			}
			
			// here the user should choose an interface (device) to capture from a list
			// but for testing we will work with the wi-fi by default
			
			device.setChoosenDevice(devicesToChooseFrom[3]);
			
			System.out.println();
			System.out.println("choosen device : ");
			System.out.println(device.getChoosenDevice().getDescription());
			}
			
			else {
				System.out.println(devicesToChooseFrom[0]);
			}
    	
			openedDevice = device.openDevice(device.getChoosenDevice());
			
			if(device.isStatus())
			openedDevice.loop(5, handler, jobj);
		}
		
		public void stop(Future<Void> startFuture) throws Exception {
	    	
			openedDevice.close();
		}
}
