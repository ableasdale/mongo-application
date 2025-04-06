package com.alexbleasdale;

import com.alexbleasdale.providers.MongoDBProvider;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.TextSearchOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class StateFilter {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        MongoDatabase database = MongoDBProvider.getInstance().getDatabase("zips-db");
        MongoCollection<Document> collection = database.getCollection("zips");
        Bson filter = Filters.eq("state", "MN");
        collection.find(filter).sort(new Document("city", 1)).forEach(doc -> LOG.info(doc.toJson()));
    }
}
