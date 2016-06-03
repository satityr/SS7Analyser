package com.inov.ss7analyser.db;



//  USE SINGLETON PATTERN

public class PointCode {
	
	
	private final static int MAX_PC_VALUE = 16384 ;
	
	private boolean[] pointCode = new boolean[MAX_PC_VALUE];

	public PointCode() {
		super();

		for(int i = 0 ; i < MAX_PC_VALUE ; i++ ){
			pointCode[i] = false ;
		}
		
	}
	
	public boolean isItNew(int pc){
		
		return pointCode[pc];
	}
	
	
	public boolean isItNew(long pc){
		
		int i = (int) pc;
		
		return pointCode[i];
		
	}
	
	public void setPointCode(int pc){
		
		pointCode[pc] = true ;
	}
	
	public void setPointCode(long pc){
		
		int i = (int) pc;
		
		pointCode[i] = true ;
	}
	
	

}
