package com.inov.ss7analyser.beans;

/** 
 * this is the object that will englobe our packet
 * an SS7Packet may contains any type of packets, but we assume that we're using it for ss7 
 * it contains a PcapPacket object that conatins our packet (data and states)
 * and si, opc and dpc extracted from each packet
 * 
 */

import org.jnetpcap.packet.PcapPacket;

import com.inov.ss7analyser.protocoles.M3uaData;

/**
 * 
 * @author Abderrahim OUBIDAR
 */

public class SS7Packet {

	private PcapPacket ss7Packet;
	private long DPC;
	private long OPC;
	private int SI;

	public SS7Packet() {
		super();
	}

	public SS7Packet(PcapPacket ss7Packet) {
		super();
		this.ss7Packet = ss7Packet;
	}

	public PcapPacket getSs7Packet() {
		return ss7Packet;
	}

	public void setSs7Packet(PcapPacket ss7Packet) {
		this.ss7Packet = ss7Packet;
	}

	public void extractRoutingLabel() {

		M3uaData m3uaData = new M3uaData();
		if (this.ss7Packet.hasHeader(m3uaData)) {

			this.OPC = m3uaData.opc();
			this.DPC = m3uaData.dpc();
			this.SI = m3uaData.si();

		}

	}

	public long getDPC() {
		return DPC;
	}

	public long getOPC() {
		return OPC;
	}

	public String getSIdescription() {
		switch (this.SI) {
		case 3:
			return ("SCCP");
		case 5:
			return ("ISUP");
		case 7:
			return ("DUP ");
		case 13:
			return ("BICC");
		case 14:
			return ("GCP  ( H.248 )");
		default:
			return ("!!!!");
		}
	}

	@Override
	public String toString() {
		return this.ss7Packet.toString();
	}

}
