package com.inov.ss7analyser.beans;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;

/**
 * 
 * @author Abderrahim OUBIDAR
 */

public class SS7Packet {

		private static Pcap openPacketSource ;
		private PcapPacket ss7Packet ;
		private int DPC;
		private int OPC;
		
		
		
		

		public SS7Packet() {
			super();
		}
		
		

		public SS7Packet(PcapPacket ss7Packet) {
			super();
			this.ss7Packet = ss7Packet;
		}

		public SS7Packet(PcapPacket ss7Packet, Pcap openPacketSource) {
			super();
			this.ss7Packet = ss7Packet;
			SS7Packet.openPacketSource = openPacketSource;
		}

		public PcapPacket getSs7Packet() {
			return ss7Packet;
		}

		public void setSs7Packet(PcapPacket ss7Packet) {
			this.ss7Packet = ss7Packet;
		}

		public Pcap getOpenPacketSource() {
			return openPacketSource;
		}

		public void setOpenPacketSource(Pcap openPacketSource) {
			SS7Packet.openPacketSource = openPacketSource;
		}



		@Override
		public String toString() {
			return this.ss7Packet.toString();
		}
		 
		
		
		
}
