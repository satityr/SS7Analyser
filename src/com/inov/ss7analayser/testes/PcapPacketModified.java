package com.inov.ss7analayser.testes;

import org.jnetpcap.packet.PcapPacket;

public class PcapPacketModified {

	public PcapPacket packet ;
	public String name ;
	
	
	public PcapPacketModified(PcapPacket packet, String name) {
		super();
		this.packet = packet;
		this.name = name;
	}
	
	public PcapPacket getPacket() {
		return packet;
	}
	public void setPacket(PcapPacket packet) {
		this.packet = packet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
