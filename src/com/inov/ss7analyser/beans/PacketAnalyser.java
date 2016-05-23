package com.inov.ss7analyser.beans;

/**
 * our reciever verticle
 * 
 */

import org.jnetpcap.protocol.sigtran.Sctp;
import org.jnetpcap.protocol.sigtran.SctpData;

import com.inov.ss7analyser.protocoles.M3ua;
import com.inov.ss7analyser.protocoles.M3uaData;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * 
 * @author Abderrahim OUBIDAR
 */

public class PacketAnalyser extends AbstractVerticle {
	
	public void start(Future<Void> startFuture) {
    	
		

        vertx.eventBus().consumer("com.inov.analyser", message -> {
        	
         	SS7Packet SS7PacketRecieved = (SS7Packet) message.body();
         	System.out.println(SS7PacketRecieved.toString());
         	
         	
         	Sctp sctp = new Sctp();
         	SctpData sctpData = new SctpData();
         	
         	M3ua m3ua = new M3ua();
         	M3uaData m3uaData = new M3uaData();
        	
         	if( SS7PacketRecieved.getSs7Packet().hasHeader(sctp)) {
         		System.out.println("has sctp");
         		if( SS7PacketRecieved.getSs7Packet().hasHeader(sctpData) ){
         			System.out.println("has data");
         			if( SS7PacketRecieved.getSs7Packet().hasHeader(m3ua) ){
             			System.out.println("has M3ua");
             			if( SS7PacketRecieved.getSs7Packet().hasHeader(m3uaData) ){
                 			System.out.println("has M3ua data");
                 			System.out.println(" Opc : " + m3uaData.opc() +"\n Dpc : " + m3uaData.dpc());
                 			
                 		}
             		}
         		}
         		
         		
         	}
         	
        });
		
    }
	
}
