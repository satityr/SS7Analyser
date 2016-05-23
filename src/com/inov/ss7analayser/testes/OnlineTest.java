package com.inov.ss7analayser.testes;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JScanner;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;



public class OnlineTest extends AbstractVerticle {

	
	private Pcap openedDevice;
	public PcapPacketModified packetToSend;
	private String name ;

	public OnlineTest(Pcap pcap, String name) {
		super();
		this.openedDevice = pcap;
		this.name = name ;
	}

	PcapPacketHandler<PcapPacketModified> packetHandler = new PcapPacketHandler<PcapPacketModified>() {

		@Override
		public void nextPacket(PcapPacket packet, PcapPacketModified PermanentPacket) {

			PermanentPacket = new PcapPacketModified(packet, name);

			vertx.eventBus().send("com.inov.analyser", PermanentPacket);

		}

	};

	public void start(Future<Void> startFuture) throws Exception {

		
		

		
		vertx.executeBlocking(future -> {
				JScanner.getThreadLocal().setFrameNumber(1);
				openedDevice.loop(5, packetHandler, packetToSend);
				future.complete();
			}, false, result -> {
			});
	}

	public void stop(Future<Void> startFuture) throws Exception {

		openedDevice.close();
	}
}
