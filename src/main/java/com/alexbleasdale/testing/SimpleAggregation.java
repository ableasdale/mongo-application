package com.alexbleasdale.testing;

import com.alexbleasdale.providers.MongoDBProvider;
import com.alexbleasdale.util.Consts;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.BsonArrayCodec;
import org.bson.codecs.DecoderContext;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;
import org.bson.json.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Aggregates.group;

public class SimpleAggregation {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        String json = "[\n" +
                "   { $group: { _id: { state: \"$state\", city: \"$city\" }, pop: { $sum: \"$pop\" } } },\n" +
                "   { $group: { _id: \"$_id.state\", avgCityPop: { $avg: \"$pop\" } } }\n" +
                "]";


        List<BsonDocument> pipeline = new BsonArrayCodec().decode(new JsonReader(json), DecoderContext.builder().build())
                .stream().map(BsonValue::asDocument)
                .collect(Collectors.toList());



        List<JsonObject> results = MongoDBProvider.getInstance().getDatabase(Consts.MONGO_DB_DATABASE_NAME).getCollection(Consts.MONGO_DB_APPLICATION_COLLECTION_NAME).withDocumentClass(JsonObject.class)
                .aggregate(pipeline).into(new ArrayList<>());

        for (JsonObject cur: results) {
            LOG.info(cur.getJson());
        }
        LOG.info("Total results: "+results.size());
    }
}
