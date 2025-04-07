package com.alexbleasdale.resources;

import com.alexbleasdale.providers.MongoDBProvider;
import com.alexbleasdale.util.Consts;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.TextSearchOptions;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.BsonArrayCodec;
import org.bson.codecs.DecoderContext;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;
import org.bson.json.JsonReader;
import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Path("/avgcitypop")
public class AvgCityPop extends BaseResource {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /* Example output (from logger):
    [2025-04-07 14:24:40,571] [INFO] {"_id": "HI", "avgCityPop": 15831.842857142858}
    [2025-04-07 14:24:40,571] [INFO] {"_id": "MO", "avgCityPop": 5672.195338512764}
     */
    public List<JsonObject> doAggregation() {
        String json = "[\n" +
                "   { $group: { _id: { state: \"$state\", city: \"$city\" }, pop: { $sum: \"$pop\" } } },\n" +
                "   { $group: { _id: \"$_id.state\", avgCityPop: { $avg: \"$pop\" } } }\n" +
                "]";

        List<BsonDocument> pipeline = new BsonArrayCodec().decode(new JsonReader(json), DecoderContext.builder().build())
                .stream().map(BsonValue::asDocument)
                .collect(Collectors.toList());

        List<JsonObject> results = MongoDBProvider.getInstance().getDatabase(Consts.MONGO_DB_DATABASE_NAME).getCollection(Consts.MONGO_DB_APPLICATION_COLLECTION_NAME).withDocumentClass(JsonObject.class)
                .aggregate(pipeline).into(new ArrayList<>());
        return results;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable getAggregationResults() {
        LOG.info("Creating View for aggregation");
        Map view = createModel();
        view.put("title", "The Mongo DB Application - Average City Population");
        view.put("toast_heading", "Search by State");
        view.put("toast_notification", "Viewing Average City Population by State");
        view.put("aggregation_results", doAggregation());
        return new Viewable("/avgcitypop", view);
    }
}
