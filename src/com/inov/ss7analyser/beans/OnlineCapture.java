package com.inov.ss7analyser.beans;




import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;








import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/**
 * 
 * @author Abderrahim OUBIDAR
 */

public class OnlineCapture extends AbstractVerticle {
	
	

		Device device = new Device();
		StringBuilder message = new StringBuilder();
		private Pcap openedDevice ;
		
		JsonObject jobj = new JsonObject() ;
		
		
		
		PcapPacket packetToSend ;
	
		
	
		
		
		
		public OnlineCapture(Device device) {
			super();
			this.device = device;
		}

		PcapPacketHandler<JsonObject> handler = new PcapPacketHandler<JsonObject>() {
	          public void nextPacket(PcapPacket packet, JsonObject obj) {
	        	  
	        	obj.put("packet", packet.toString());
	        	
	        	vertx.eventBus().send ("com.inov.analyser", obj );
	        	
	          }
	        };
	        
	    PcapPacketHandler<PcapPacket> packetHandler = new PcapPacketHandler<PcapPacket>(){

			@Override
			public void nextPacket(PcapPacket packet, PcapPacket PermanentPacket) {
				
				PermanentPacket = new PcapPacket(packet);
				

				vertx.eventBus().send ("com.inov.analyser", PermanentPacket );
				
			}
	    	
	    };
	    
		
		public void start(Future<Void> startFuture) throws Exception {
			
			

			
    	
			openedDevice = device.openDevice(device.getChoosenDevice());
			vertx.eventBus().registerDefaultCodec(PcapPacket.class, new PacketCodec());
			
			if(device.isStatus())
			vertx.executeBlocking(future -> {
				openedDevice.loop(5, packetHandler, packetToSend);
				future.complete();
			}, false, result -> {});
		}
		
		public void stop(Future<Void> startFuture) throws Exception {
	    	
			openedDevice.close();
		}
}
