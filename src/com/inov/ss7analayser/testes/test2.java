package com.inov.ss7analayser.testes;

import io.vertx.core.Vertx;

import com.inov.ss7analyser.beans.Device;
import com.inov.ss7analyser.beans.OnlineCapture;
import com.inov.ss7analyser.beans.PacketAnalyser;

/**
 * 
 * @author Abderrahim OUBIDAR
 */
	
	public class test2 {
		
		public static void main(String[] args){
			
			Vertx vertx = Vertx.vertx();
			
			
			
			 
			
			Device device = new Device();
			StringBuilder[] devicesToChooseFrom ;
			
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
			
			vertx.deployVerticle(new OnlineCapture(device));
			
			vertx.deployVerticle(new PacketAnalyser());
			
		
        /*Pcap pcap = Pcap.openLive((device.getChoosenDevice()).getName(), snaplen, flags, timeout, errbuf);  
  
        if (pcap == null) {  
            System.err.printf("Error while opening device for capture: ");  
                
            return;  
        }  

        PcapPacketHandler<Queue<SS7Packet>> handler = new PcapPacketHandler<Queue<SS7Packet>>() {
          public void nextPacket(PcapPacket packet, Queue<SS7Packet> queue) {
        	  
        	SS7Packet permanentPacket = new SS7Packet(packet);
            System.out.println("a packet copied!");
            queue.offer(permanentPacket);
            System.out.println("a packet offered!");
          }
        };

        Queue<SS7Packet> queue = new ArrayBlockingQueue<SS7Packet>(20);

        pcap.loop(5, handler, queue);

        System.out.println("we have " + queue.size() + " packets in our queue");
        for ( SS7Packet pp : queue ){
        	System.out.println(pp.toString());
        }

        pcap.close();*/
    }  
		
}