package com.alexbleasdale.providers;

import com.alexbleasdale.util.AWSTools;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBProvider {

    private static class LazyHolder {
        static final MongoClient INSTANCE = MongoClients.create(
                MongoClientSettings.builder()
                        // TODO - fix this yukky concatenation
                        .applyConnectionString(new ConnectionString("mongodb://admin:admin-password@" + AWSTools.getMongoDBPublicDNSName() + ":27017"))
                        .build());
    }

    public static MongoClient getInstance() {
        return LazyHolder.INSTANCE;
    }

}
