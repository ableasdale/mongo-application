package com.alexbleasdale;

import com.alexbleasdale.providers.JerseyServer;
import com.alexbleasdale.providers.MongoDBProvider;
import com.alexbleasdale.util.Consts;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.SsmException;

import java.lang.invoke.MethodHandles;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        LOG.info("Application starting...");

        LOG.info("Creating the necessary indexes on startup...");
        MongoDatabase database = MongoDBProvider.getInstance().getDatabase(Consts.MONGO_DB_DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(Consts.MONGO_DB_APPLICATION_COLLECTION_NAME);
        collection.createIndex(Indexes.ascending("city", "state"));
        collection.createIndex(Indexes.ascending("city"));
        collection.createIndex(new Document("city", "text"));
        collection.createIndex(new Document("city.$**", 1));

        /*  TODO - Commenting out until I figure out the creds issue when running on EKS
        Region region = Region.US_EAST_1;
        SsmClient ssmClient = SsmClient.builder()
                //.credentialsProvider()
                //.withCredentials(new AWSCredentialsProviderChain())
                .region(region)
                .build();

        try {
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name("mongo-host")
                    .build();

            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
            LOG.info("The parameter value is "+parameterResponse.parameter().value());

        } catch (SsmException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } */

        final Thread t = new JerseyServer();
        LOG.info("Starting JerseyServer");
        t.start();
    }
}
