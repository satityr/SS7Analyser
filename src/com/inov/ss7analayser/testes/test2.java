package com.inov.ss7analayser.testes;

import org.jnetpcap.Pcap;

import io.vertx.core.Vertx;

import com.inov.ss7analyser.beans.Device;
import com.inov.ss7analyser.beans.OnlineCapture;
import com.inov.ss7analyser.beans.PacketAnalyser;
import com.inov.ss7analyser.protocoles.RegisterNewProtocole;

/**
 * 
 * @author Abderrahim OUBIDAR
 */

public class test2 {

	public static void main(String[] args) {

		Vertx vertx = Vertx.vertx();
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

			device.setChoosenDevice(devicesToChooseFrom[3]);

			System.out.println();
			System.out.println("choosen device : ");
			System.out.println(device.getChoosenDevice().getDescription());

			Pcap opening = device.openDevice(device.getChoosenDevice());

			if (device.isStatus()) {

				vertx.deployVerticle(new OnlineCapture(opening));

				vertx.deployVerticle(new PacketAnalyser());
			}

		}

		else {
			System.out.println(devicesToChooseFrom[0]);
		}

	}

}