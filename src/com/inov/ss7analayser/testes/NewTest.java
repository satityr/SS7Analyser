package com.inov.ss7analayser.testes;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

import com.inov.ss7analyser.beans.Device;
import com.inov.ss7analyser.beans.OnlineCapture;
import com.inov.ss7analyser.beans.PacketAnalyser;
import com.inov.ss7analyser.beans.PacketCodec;
import com.inov.ss7analyser.beans.SS7Packet;
import com.inov.ss7analyser.protocoles.RegisterNewProtocole;

/**
 * 
 * @author Abderrahim OUBIDAR
 */

public class NewTest {

	public static void main(String[] args) {

		Vertx vertx = Vertx.vertx();
		
		// Must register our protocoles before opening the device for capture
		new RegisterNewProtocole().RegisterProtocoles();
		
		Device device = new Device();
		StringBuilder[] devicesToChooseFrom;

		devicesToChooseFrom = device.getDevicesListName();

		if (device.isStatus()) {

			for (StringBuilder deviceInfo : devicesToChooseFrom) {

				System.out.print(deviceInfo.toString());

			}

			// here the user should choose an interface (device) to capture from
			// a list
			// but for testing we will work with the wi-fi by default

			device.setChoosenDevice(devicesToChooseFrom[2]);

			System.out.println();
			System.out.println("choosen device : ");
			System.out.println(device.getChoosenDevice().getDescription());

			Pcap opening = device.openDevice(device.getChoosenDevice());
			

			if (device.isStatus()) {
			
				// register the default codec for our eventBus
				vertx.eventBus().registerDefaultCodec(PcapPacket.class, new PacketCodec());
				
				// deployment options
				DeploymentOptions options = new DeploymentOptions().setWorker(true);
				options.setHa(true);
				//deploy our sender
				vertx.deployVerticle(new OnlineCapture(opening), options);
				
				// deploy our reciever
				vertx.deployVerticle(new PacketAnalyser());
				vertx.deployVerticle(new PacketAnalyser());
			}

		}

		else {
			
			// devicesToChooseFrom[0] contains an error message
			System.out.println(devicesToChooseFrom[0]);
		}

	}

}