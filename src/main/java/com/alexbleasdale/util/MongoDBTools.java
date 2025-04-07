package com.alexbleasdale.util;

import com.alexbleasdale.providers.MongoDBProvider;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBTools {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void getFirstDocumentByState(String state) {
        MongoDatabase database = MongoDBProvider.getInstance().getDatabase("zips-db");
        MongoCollection<Document> collection = database.getCollection("zips");
        Document doc = collection.find(eq("state", state)).first();
        if (doc != null) {
            LOG.info(doc.toJson());
        } else {
            LOG.info("No matching documents found.");
        }
    }

    public static void getDatabases() {
        for (String s : MongoDBProvider.getInstance().listDatabaseNames()) {
            LOG.info("DB:" + s);
        }
    }

    public static long getCollectionSize() {
        return MongoDBProvider.getInstance().getDatabase("zips-db").getCollection("zips").countDocuments();
    }
}
