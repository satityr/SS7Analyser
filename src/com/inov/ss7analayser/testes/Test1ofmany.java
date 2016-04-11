package com.inov.ss7analayser.testes;

public class Test1ofmany {


	public static void main(String[] args) {
		try { 
			doStuff(); 
			System.out.println("1");
		}	
		catch( RuntimeException re ) {
		System.out.println("2");
		}
		
	}

	public static void doStuff() {
		if (false) {
			
			throw  new RuntimeException() ;
			}
		doMoreStuff();
		System.out.println("3 ");
	}

	public static void doMoreStuff() {
		System.out.println("4");
	}
}