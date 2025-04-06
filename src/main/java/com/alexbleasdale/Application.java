package com.alexbleasdale;

import com.alexbleasdale.util.AWSTools;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import software.amazon.awscdk.services.ssm.StringParameter;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.SsmException;

import java.lang.invoke.MethodHandles;

import static com.mongodb.client.model.Filters.eq;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Document Structure:
     * {
     *   _id: '01001',
     *   city: 'AGAWAM',
     *   loc: [ -72.622739, 42.070206 ],
     *   pop: 15338,
     *   state: 'MA'
     * }
     */
    public static void main(String[] args) {
        LOG.info("Application starting...");
        String hostname = AWSTools.getMongoDBPublicDNSName();

        // ec2-54-166-160-96.compute-1.amazonaws.com

        // Re-use this connection; it's thread safe
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        // TODO - fix this concatenation
                        .applyConnectionString(new ConnectionString("mongodb://admin:admin-password@"+hostname+":27017"))
                        .build());
        LOG.info(mongoClient.getClusterDescription().toString());

        MongoDatabase database = mongoClient.getDatabase("zips-db");
        MongoCollection<Document> collection = database.getCollection("zips");

        Document doc = collection.find(eq("state", "MN")).first();
        if (doc != null) {
            LOG.info(doc.toJson());
        } else {
            LOG.info("No matching documents found.");
        }



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
        }

    }
}
