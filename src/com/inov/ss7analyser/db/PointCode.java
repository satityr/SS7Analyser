package com.inov.ss7analyser.db;




/**
 * @author OUBIDAR Abderrahim 
 *
 */

/* 
 * 
 *   this class will contain all the point codes stored in the database, also the ones we will get 
 *   from analysed packets
 *   
 *   it uses the singelton design pattern to ensure working with one PointCode instance
 *   
 *   in ITU, PC is coded in 14 bits which gives a range from 0 to 16383
 *   
 *   pointCode[3637] equals true means that the PC:3637 exists in the DB
 * 
 */

public class PointCode {
	
	
	private final static int MAX_PC_VALUE = 16384 ;
	
	private static PointCode instance = null ;
	
	private boolean[] pointCode = new boolean[MAX_PC_VALUE];

	private PointCode() { }
	
	public static PointCode getInstance(){
		
		if( instance == null ){
			
			synchronized(PointCode.class) {
				
				if( instance == null ){
					
					instance = new PointCode();
					
					for(int i = 0 ; i < MAX_PC_VALUE ; i++ ){
						instance.pointCode[i] = false ;
					}
				}
					
			}
			
		}
		
		return instance;
		
	}
	
	
	
	public boolean isItNew(int pc){
		
		return instance.pointCode[pc];
	}
	
	
	public boolean isItNew(long pc){
		
		int i = (int) pc;
		
		return instance.pointCode[i];
		
	}
	
	public void setPointCode(int pc){
		
		instance.pointCode[pc] = true ;
	}
	
	public void setPointCode(long pc){
		
		int i = (int) pc;
		
		instance.pointCode[i] = true ;
	}
	
	

}
