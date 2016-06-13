package com.inov.ss7analyser.beans;

/** 
 * this is the object that will englobe our packet
 * an SS7Packet may contains any type of packets, but we assume that we're using it for ss7 
 * it contains a PcapPacket object that conatins our packet (data and states)
 * and si, opc and dpc extracted from each packet
 * 
 */

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.sigtran.Sctp;

import com.inov.ss7analyser.protocoles.M3uaData;

/**
 * 
 * @author Abderrahim OUBIDAR
 */

public class SS7Packet {

	private PcapPacket ss7Packet;
	private long dpc;
	private long opc;
	private int si;
	private int ni;
	private String ipSource ;
	private String ipDestination ;
	private int portSource ;
	private int portDestination ;

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
		
		Ip4 ip = new Ip4();
		if (this.ss7Packet.hasHeader(ip)){
			this.ipSource =  FormatUtils.ip(ip.source()) ;
			this.ipDestination =  FormatUtils.ip(ip.destination()) ;
		}

		Sctp sctp = new Sctp();
		if(this.ss7Packet.hasHeader(sctp)){
			this.portSource = sctp.source();
			this.portDestination = sctp.destination();
		}
		M3uaData m3uaData = new M3uaData();
		if (this.ss7Packet.hasHeader(m3uaData)) {

			this.opc = m3uaData.opc();
			this.dpc = m3uaData.dpc();
			this.si = m3uaData.si();
			this.ni = m3uaData.ni();

		}

	}

	public long getDpc() {
		return dpc;
	}

	public long getOpc() {
		return opc;
	}
	
	public int getNi() {
		return ni;
	}

	public int getSi() {
		return si;
	}
	
	public String getIpSource() {
		return ipSource;
	}
	
	public String getIpDestination() {
		return ipDestination;
	}
	
	public int getPortSource() {
		return portSource ;
	}
	
	public int getPortDestination() {
		return portDestination;
	}
	
	public String getSiDescription() {
		switch (this.si) {
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
