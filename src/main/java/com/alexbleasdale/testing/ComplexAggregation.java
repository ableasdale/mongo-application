package com.alexbleasdale.testing;

import com.alexbleasdale.providers.MongoDBProvider;
import com.alexbleasdale.util.Consts;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.BsonArrayCodec;
import org.bson.codecs.DecoderContext;
import org.bson.json.JsonObject;
import org.bson.json.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComplexAggregation {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        String json = "[\n" +
                "   { $group:\n" +
                "      {\n" +
                "        _id: { state: \"$state\", city: \"$city\" },\n" +
                "        pop: { $sum: \"$pop\" }\n" +
                "      }\n" +
                "   },\n" +
                "   { $sort: { pop: 1 } },\n" +
                "   { $group:\n" +
                "      {\n" +
                "        _id : \"$_id.state\",\n" +
                "        biggestCity:  { $last: \"$_id.city\" },\n" +
                "        biggestPop:   { $last: \"$pop\" },\n" +
                "        smallestCity: { $first: \"$_id.city\" },\n" +
                "        smallestPop:  { $first: \"$pop\" }\n" +
                "      }\n" +
                "   },\n" +
                "  { $project:\n" +
                "    { _id: 0,\n" +
                "      state: \"$_id\",\n" +
                "      biggestCity:  { name: \"$biggestCity\",  pop: \"$biggestPop\" },\n" +
                "      smallestCity: { name: \"$smallestCity\", pop: \"$smallestPop\" }\n" +
                "    }\n" +
                "  }\n" +
                "]";

        List<BsonDocument> pipeline = new BsonArrayCodec().decode(new JsonReader(json), DecoderContext.builder().build())
                .stream().map(BsonValue::asDocument)
                .collect(Collectors.toList());

        List<JsonObject> results = MongoDBProvider.getInstance().getDatabase(Consts.MONGO_DB_DATABASE_NAME).getCollection(Consts.MONGO_DB_APPLICATION_COLLECTION_NAME).withDocumentClass(JsonObject.class)
                .aggregate(pipeline).into(new ArrayList<>());

        for (JsonObject cur: results) {
            LOG.info(cur.getJson());
        }
        LOG.info(MessageFormat.format("Total results: {0}", results.size()));
    }
}
