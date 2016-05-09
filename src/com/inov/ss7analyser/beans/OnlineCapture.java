package com.inov.ss7analyser.beans;

import java.util.Queue;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class OnlineCapture extends AbstractVerticle {
	
	

		private Device device;
		public SS7Packet ss7packet ;
		private Pcap openedDevice ;
		
		public OnlineCapture(Device device){
			
			this.device = device ;
		}
		
		PcapPacketHandler<SS7Packet> handler = new PcapPacketHandler<SS7Packet>() {
	          public void nextPacket(PcapPacket packet, SS7Packet ss7packet) {
	        	  
	        	ss7packet  = new SS7Packet(packet, openedDevice);
	        	vertx.eventBus().send ("com.inov.analyser", ss7packet);
	          }
	        };
		
		public void start(Future<Void> startFuture) throws Exception {
    	
			openedDevice = device.openDevice(device.getChoosenDevice());
			
			if(device.isStatus())
			openedDevice.loop(Pcap.LOOP_INFINITE, handler, ss7packet);
		}
		
		public void stop(Future<Void> startFuture) throws Exception {
	    	
			openedDevice.close();
		}
}
