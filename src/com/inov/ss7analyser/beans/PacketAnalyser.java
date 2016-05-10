package com.inov.ss7analyser.beans;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.json.simple.JSONObject;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public class PacketAnalyser extends AbstractVerticle {
	
	public void start(Future<Void> startFuture) {
    	
		
        vertx.eventBus().consumer("com.inov.analyser", json -> {
        	
        		
        		JsonObject jo = (JsonObject) json.body();
        		
        		System.out.println(jo.getString("packet"));
        });
        
        
    }
	
}
