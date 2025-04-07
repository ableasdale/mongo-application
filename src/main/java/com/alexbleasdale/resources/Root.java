package com.alexbleasdale.resources;


import com.alexbleasdale.util.AWSTools;
import com.alexbleasdale.util.Consts;
import com.alexbleasdale.util.MongoDBTools;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Map;

@Path("/")
public class Root extends BaseResource {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @POST
    @Produces(MediaType.TEXT_HTML)
    public Viewable doPost() {
        return getDashboard();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable getDashboard() {
        LOG.debug("getDashboard() :: Rendering view");
        Map view = createModel();
        view.put("title", "The Mongo DB Application - Home");
        view.put("toast_heading", "Dashboard");
        view.put("toast_notification", "Configuration: Everything looks good");
        view.put("s3_data", AWSTools.getS3Information());
        view.put("collection_size", MongoDBTools.getCollectionSize());
        view.put("collection_name", Consts.MONGO_DB_APPLICATION_COLLECTION_NAME);
        view.put("database_name", Consts.MONGO_DB_DATABASE_NAME);
        view.put("database_list", MongoDBTools.getDatabases());
        // view.put("index_list", MongoDBTools.getConfiguredIndexes());
        return new Viewable("/dashboard", view);
    }
}
