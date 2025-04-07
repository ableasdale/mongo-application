package com.alexbleasdale.resources;

import com.alexbleasdale.providers.MongoDBProvider;
import com.alexbleasdale.util.Consts;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.BsonArrayCodec;
import org.bson.codecs.DecoderContext;
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

@Path("/largest_and_smallest")
public class LargestSmallestCity extends BaseResource {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /* Example output (from logger):
[2025-04-07 14:49:52,370] [INFO] {"biggestCity": {"name": "MIAMI", "pop": 825232}, "smallestCity": {"name": "CECIL FIELD NAS", "pop": 0}, "state": "FL"}
[2025-04-07 14:49:52,370] [INFO] {"biggestCity": {"name": "BOISE", "pop": 165522}, "smallestCity": {"name": "KEUTERVILLE", "pop": 0}, "state": "ID"}
     */
    public List<JsonObject> doAggregation() {
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
        return results;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable getAggregationResults() {
        LOG.debug("Creating View for aggregation");
        Map view = createModel();
        view.put("title", "The Mongo DB Application - Largest and Smallest Cities by State");
        view.put("toast_heading", "Search by State");
        view.put("toast_notification", "Viewing the Largest and Smallest City in each State");
        view.put("aggregation_results", doAggregation());
        return new Viewable("/largest_and_smallest", view);
    }


}
