package com.alexbleasdale.util;

import com.alexbleasdale.providers.MongoDBProvider;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBTools {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void getFirstDocumentByState(String state) {
        MongoDatabase database = MongoDBProvider.getInstance().getDatabase(Consts.MONGO_DB_DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(Consts.MONGO_DB_APPLICATION_COLLECTION_NAME);
        Document doc = collection.find(eq("state", state)).first();
        if (doc != null) {
            LOG.info(doc.toJson());
        } else {
            LOG.info("No matching documents found.");
        }
    }

    public static List<String> getDatabases() {
        List<String> databases = new ArrayList<String>();
        for (String s : MongoDBProvider.getInstance().listDatabaseNames()) {
            databases.add(s);
        }
        return databases;
    }

    public static List<Document> getConfiguredIndexes() {
        MongoDatabase database = MongoDBProvider.getInstance().getDatabase(Consts.MONGO_DB_DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(Consts.MONGO_DB_APPLICATION_COLLECTION_NAME);
        List<Document> resultList = StreamSupport.stream(collection.listIndexes().spliterator(), true).collect(Collectors.toList());
        resultList.forEach(doc -> LOG.info("Idx: "+doc.toJson()));
        return resultList;
    }

    public static long getCollectionSize() {
        return MongoDBProvider.getInstance().getDatabase(Consts.MONGO_DB_DATABASE_NAME).getCollection(Consts.MONGO_DB_APPLICATION_COLLECTION_NAME).countDocuments();
    }
}
