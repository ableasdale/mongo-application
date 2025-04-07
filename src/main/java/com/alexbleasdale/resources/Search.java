package com.alexbleasdale.resources;

import com.alexbleasdale.providers.MongoDBProvider;
import com.alexbleasdale.util.Consts;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.TextSearchOptions;
import jakarta.ws.rs.*;
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

@Path("/search")
public class Search extends BaseResource {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    String term = "";
    List<Document> searchResults = null;

    public List<Document> doSearch(String inputTerm) {
        MongoDatabase database = MongoDBProvider.getInstance().getDatabase(Consts.MONGO_DB_DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(Consts.MONGO_DB_APPLICATION_COLLECTION_NAME);
        TextSearchOptions options = new TextSearchOptions().caseSensitive(false);
        Bson filter = Filters.text(inputTerm, options);
       // collection.find(filter).sort(new Document("city", 1)).forEach(doc -> LOG.info(doc.toJson()));

        Iterable<Document> resultEntity = collection.find(filter).sort(new Document("city", 1));
        // TODO - if I can fix the freemarker config, I can prevent having to do this collect step: https://stackoverflow.com/questions/6191025/treat-a-java-lang-iterable-as-a-list-expression-in-freemarker
        List<Document> resultList = StreamSupport.stream(resultEntity.spliterator(), true).collect(Collectors.toList());
        return resultList;
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    public Viewable doPost(@FormParam("term") String searchTerm) {
        term = searchTerm;
        searchResults = doSearch(searchTerm);
        return getSearchResults();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable getSearchResults() {
        LOG.info("getDashboard() :: Rendering view");
        Map view = createModel();
        view.put("title", "The Mongo DB Application - Search");
        view.put("toast_heading", "Search");
        view.put("toast_notification", "Searched for: "+term);
        view.put("term", term);
        view.put("searchResults", searchResults);
        return new Viewable("/search", view);
    }
}
