package com.alexbleasdale.testing;

import com.alexbleasdale.providers.MongoDBProvider;
import com.alexbleasdale.util.Consts;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class StateFilter {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        MongoDatabase database = MongoDBProvider.getInstance().getDatabase(Consts.MONGO_DB_DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(Consts.MONGO_DB_APPLICATION_COLLECTION_NAME);
        Bson filter = Filters.eq("state", "MN");
        collection.find(filter).sort(new Document("city", 1)).forEach(doc -> LOG.info(doc.toJson()));
    }
}
