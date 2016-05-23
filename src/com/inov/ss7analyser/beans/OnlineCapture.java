package com.inov.ss7analyser.beans;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JScanner;
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

	
	private Pcap openedDevice;
	public PcapPacket packetToSend;

	public OnlineCapture(Pcap pcap) {
		super();
		this.openedDevice = pcap;
	}

	PcapPacketHandler<PcapPacket> packetHandler = new PcapPacketHandler<PcapPacket>() {

		@Override
		public void nextPacket(PcapPacket packet, PcapPacket PermanentPacket) {

			PermanentPacket = new PcapPacket(packet);

			vertx.eventBus().send("com.inov.analyser", PermanentPacket);

		}

	};

	public void start(Future<Void> startFuture) throws Exception {

		
		vertx.eventBus().registerDefaultCodec(PcapPacket.class,
				new PacketCodec());

		
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
