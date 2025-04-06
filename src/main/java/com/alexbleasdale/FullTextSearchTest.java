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

public class FullTextSearchTest {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        MongoDatabase database = MongoDBProvider.getInstance().getDatabase("zips-db");
        MongoCollection<Document> collection = database.getCollection("zips");
        collection.createIndex(Indexes.ascending("city", "state"));
        collection.createIndex(Indexes.ascending("city"));
        //collection.createSearchIndex(Indexes.ascending("city", "state"));
        collection.createIndex(new Document("city", "text"));
        collection.createIndex(new Document("city.$**", 1));
        collection.listIndexes().forEach(doc -> LOG.info("Idx: "+doc.toJson()));

        //collection.listSearchIndexes().forEach(doc -> LOG.info("Srch Idx: "+doc.toJson()));
        /*
        city: 'AGAWAM',
     *   loc: [ -72.622739, 42.070206 ],
     *   pop: 15338,
     *   state: 'MA'

         */
        //
        //final Document doc = new Document("city", "text");
       // final String jsonString = doc.toJson();// db.zips.createIndex( { "city": "text" } )

        TextSearchOptions options = new TextSearchOptions().caseSensitive(false);
        Bson filter = Filters.text("city", options);
        collection.find(filter).sort(new Document("city", 1)).forEach(doc -> System.out.println(doc.toJson()));
    }
}
