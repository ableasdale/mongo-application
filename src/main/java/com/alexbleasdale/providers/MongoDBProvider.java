package com.alexbleasdale.providers;

import com.alexbleasdale.util.Consts;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBProvider {

    private static class LazyHolder {
        static final MongoClient INSTANCE = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(Consts.MONGODB_CONNECTION_STRING))
                        .build());
    }
    public static MongoClient getInstance() {
        return LazyHolder.INSTANCE;
    }
}
