package com.inov.ss7analayser.testes;

import java.util.Random;

import org.jnetpcap.Pcap;

import com.inov.ss7analyser.beans.OnlineCapture;
import com.inov.ss7analyser.beans.PacketAnalyser;
import com.inov.ss7analyser.protocoles.RegisterNewProtocole;

import io.vertx.core.*;

/**
 * 
 * @author Abderrahim OUBIDAR
 */

public class TestThree {

	public static void main(String[] args) {

		Vertx vertx = Vertx.vertx();
		
		String[] files = { "D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\BeniMellal2.pcap",
				"D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\CasaNU2.pcap",
				"D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\msgh2.pcap",
				"D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\pcapAAQ.pcap",
				"D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\pcapAINZ.pcap",
				"D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\pcapALGD2.pcap",
				"D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\pcapELJ.pcap",
				"D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\pcapLallarakia.pcap",
				"D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\pcapMechSidiGH.pcap",
				"D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\pcapMgueliz2.pcap",
				"D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\pcapSETTAT.pcap",
				"D:\\Study\\Stages\\INE3\\INOV\\pcapWireshark\\pcapSOK.pcap"
		};
		
		Random rand = new Random();
		StringBuilder errorMsg = new StringBuilder();
		int fileNumber = rand.nextInt(12);
		
		new RegisterNewProtocole().RegisterProtocoles();
		
		
		System.out.println("opening : " + files[fileNumber] + "... DONE!");
		
		Pcap openFile = Pcap.openOffline(files[fileNumber], errorMsg);
		
		
		if( openFile != null ){
			
			vertx.deployVerticle(new OnlineCapture(openFile));

			vertx.deployVerticle(new PacketAnalyser());
			
		}
		else {
			System.out.println("can't open file : " + files[fileNumber] + "/n => " + errorMsg.toString());
		}
		
	}
}