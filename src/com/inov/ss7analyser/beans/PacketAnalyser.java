package com.inov.ss7analyser.beans;

import org.jnetpcap.packet.PcapPacket;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/**
 * 
 * @author Abderrahim OUBIDAR
 */

public class PacketAnalyser extends AbstractVerticle {
	
	public void start(Future<Void> startFuture) {
    	
		
        /*vertx.eventBus().consumer("com.inov.analyser", json -> {
        	
        		
        		JsonObject jo = (JsonObject) json.body();
        		
        		System.out.println(jo.getString("packet"));
        });*/
        
		
	
        vertx.eventBus().consumer("com.inov.analyser", message -> {
        	
         	SS7Packet SS7PacketRecieved = (SS7Packet) message.body();
         	System.out.println(SS7PacketRecieved.toString());
        	
        });
		
    }
	
}
