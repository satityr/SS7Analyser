package com.inov.ss7analayser.testes;

import io.vertx.core.*;

/**
 * 
 * @author Abderrahim OUBIDAR
 */

public class TestThree {

	public static void main(String[] args) {

		Vertx vertx = Vertx.vertx();

		vertx.deployVerticle(new EventBusReceiverVerticle("R1"));
		vertx.deployVerticle(new EventBusReceiverVerticle("R2"));
		vertx.deployVerticle(new EventBusReceiverVerticle("R3"));
		vertx.deployVerticle(new EventBusReceiverVerticle("R4"));

		try {
			
			for(int i = 0 ; i < 3 ;  i++){
				System.out.println((i+1));
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vertx.deployVerticle(new EventBusSenderVerticle());
	}

}



class EventBusReceiverVerticle extends AbstractVerticle {

    private String name = null;

    public EventBusReceiverVerticle(String name) {
        this.name = name;
    }

    public void start(Future<Void> startFuture) {
    	
    	System.out.println(this.name + " started");
        vertx.eventBus().consumer("anAddress", message -> {
            System.out.println(this.name + 
                " received message: " +
                message.body());
            try {
				Thread.sleep(4000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println(this.name + " is tired");
        });
    }
}

class EventBusSenderVerticle extends AbstractVerticle {

    public void start(Future<Void> startFuture) {
    	
    	System.out.println("sender started");
    //  vertx.eventBus().publish("anAddress", "message 2");
    	for(int i = 0; i < 7 ; i++ ){
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println("sender is sending!");
    		vertx.eventBus().send   ("anAddress", "message 1");
    	}
        System.out.println("sender done!");
        
    }
}