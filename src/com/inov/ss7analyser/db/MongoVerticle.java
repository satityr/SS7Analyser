package com.inov.ss7analyser.db;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoVerticle extends AbstractVerticle {



    @Override
    public void start() throws Exception {

        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        JsonObject config = Vertx.currentContext().config();

        String uri = config.getString("MongoDB://localhost");
        if (uri == null) {
            uri = "mongodb://localhost:27017";
        }
        String db = config.getString("SignalingPoint");
        if (db == null) {
            db = "SignalingPoint";
        }

        JsonObject mongoconfig = new JsonObject()
                .put("connection_string", uri)
                .put("db_name", db);

        MongoClient mongoClient = MongoClient.createShared(vertx, mongoconfig);

        // JsonObject query = new JsonObject().put("itemId", "12345").put("name", "Cooler").put("price", "100.0");
          JsonObject query = new JsonObject();
        //JsonObject query = new JsonObject().put("itemId", "12").put("name", "Coer").put("price", "4");
       
        
/*        mongoClient.count("products", query, res -> {

            if (res.succeeded()) {
          
                long num = res.result();
                System.out.println("count = " + num);

            } else {

                res.cause().printStackTrace();

            }

        }
        );*/
           
        mongoClient.find("PointCode", query, res -> {

            if (res.succeeded()) {

                   for (JsonObject json : res.result()) {
                    
                    System.out.println(json.encodePrettily());

                }
              
            } else {

                res.cause().printStackTrace();

            }

        }
        );

        /*mongoClient.insert("products", query, res -> {

            if (res.succeeded()) {

                String id = res.result();
                System.out.println("Inserted book with id " + id);

            } else {
                res.cause().printStackTrace();
            }

        });*/
    }
}



