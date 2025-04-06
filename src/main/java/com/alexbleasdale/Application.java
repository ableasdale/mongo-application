package com.alexbleasdale;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static com.mongodb.client.model.Filters.eq;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Document Structure:
     * {
     *   _id: '01001',
     *   city: 'AGAWAM',
     *   loc: [ -72.622739, 42.070206 ],
     *   pop: 15338,
     *   state: 'MA'
     * }
     * @param args
     */
    public static void main(String[] args) {
        LOG.info("Application starting...");
        // Re-use this connection; it's thread safe
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString("mongodb://admin:admin-password@ec2-52-54-111-193.compute-1.amazonaws.com:27017"))
                        .build());
        LOG.info(mongoClient.getClusterDescription().toString());

        MongoDatabase database = mongoClient.getDatabase("zips-db");
        MongoCollection<Document> collection = database.getCollection("zips");

        Document doc = collection.find(eq("state", "MN")).first();
        if (doc != null) {
            LOG.info(doc.toJson());
        } else {
            LOG.info("No matching documents found.");
        }
    }
}