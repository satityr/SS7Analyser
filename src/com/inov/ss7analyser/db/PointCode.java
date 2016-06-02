package com.inov.ss7analyser.db;

public class PointCode {
	
	private long opc;
	private long dpc;
	
	public PointCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PointCode(long opc, long dpc) {
		super();
		this.opc = opc;
		this.dpc = dpc;
	}
	public long getOpc() {
		return opc;
	}
	public void setOpc(long opc) {
		this.opc = opc;
	}
	public long getDpc() {
		return dpc;
	}
	public void setDpc(long dpc) {
		this.dpc = dpc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (dpc ^ (dpc >>> 32));
		result = prime * result + (int) (opc ^ (opc >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PointCode))
			return false;
		PointCode other = (PointCode) obj;
		if (dpc != other.dpc)
			return false;
		if (opc != other.opc)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PointCode Info : [ opc = " + opc + "; dpc = " + dpc + " ]";
	}
	
	
	
	
	
	
	
	

}
