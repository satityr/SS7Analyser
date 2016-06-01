package com.inov.ss7analayser.testes;

import com.inov.ss7analyser.db.MongoVerticle;

import io.vertx.core.Vertx;

public class DBTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Vertx vertx = Vertx.vertx();

		vertx.deployVerticle(new MongoVerticle());
	}

}
