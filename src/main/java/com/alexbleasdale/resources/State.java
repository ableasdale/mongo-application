package com.alexbleasdale.resources;

import com.alexbleasdale.providers.MongoDBProvider;
import com.alexbleasdale.util.Consts;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Path("/state")
public class State extends BaseResource {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public List<Document> doSearch(String stateCode) {
        MongoDatabase database = MongoDBProvider.getInstance().getDatabase(Consts.MONGO_DB_DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(Consts.MONGO_DB_APPLICATION_COLLECTION_NAME);
        Bson filter = Filters.eq("state", stateCode);
        //collection.find(filter).sort(new Document("city", 1)).forEach(doc -> LOG.info(doc.toJson()));

        Iterable<Document> resultEntity = collection.find(filter).sort(new Document("city", 1));
        // TODO - if I can fix the freemarker config, I can prevent having to do this collect step: https://stackoverflow.com/questions/6191025/treat-a-java-lang-iterable-as-a-list-expression-in-freemarker
        List<Document> resultList = StreamSupport.stream(resultEntity.spliterator(), true).collect(Collectors.toList());
        return resultList;
    }


    @GET
    @Path("{stateCode}")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getSearchResults(@PathParam("stateCode") String stateCode) {
        LOG.info("State :: getDashboard() :: Rendering view");
        Map view = createModel();
        view.put("title", "The Mongo DB Application - State View");
        view.put("toast_heading", "Search by State");
        view.put("toast_notification", "Viewing by State Code: " + stateCode);
        view.put("stateCode", stateCode);
        view.put("searchResults", doSearch(stateCode));
        return new Viewable("/state", view);
    }
}
