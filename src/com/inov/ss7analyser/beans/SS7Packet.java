package com.inov.ss7analyser.beans;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;

public class SS7Packet {

		private static Pcap openPacketSource ;
		private PcapPacket ss7Packet ;

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
		
		
		
		
}
