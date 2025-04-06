package com.alexbleasdale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.lang.invoke.MethodHandles;

public class MongoInstance {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        // find the mongo EC2 instance
        Ec2Client ec2 = Ec2Client.create();

        String nextToken = null;

        try {

            do {
                DescribeInstancesRequest request = DescribeInstancesRequest.builder().maxResults(6).nextToken(nextToken).build();
                DescribeInstancesResponse response = ec2.describeInstances(request);

                for (Reservation reservation : response.reservations()) {
                    for (Instance instance : reservation.instances()) {
                        for (Tag t : instance.tags()) {
                            if (t.key().equals("Name") && t.value().contains("MongoDB")) {
                                LOG.info("Found MDB instance: " + instance.publicDnsName());
                            }
                        }
                    }
                }
                nextToken = response.nextToken();
            } while (nextToken != null);

        } catch (Ec2Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        ec2.close();

    }
}