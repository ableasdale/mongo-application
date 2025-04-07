package com.alexbleasdale.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.lang.invoke.MethodHandles;

public class AWS {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {


        Ec2Client ec2 = Ec2Client.create();


            String nextToken = null;

            try {

                do {
                    DescribeInstancesRequest request = DescribeInstancesRequest.builder().maxResults(6).nextToken(nextToken).build();
                    DescribeInstancesResponse response = ec2.describeInstances(request);

                    for (Reservation reservation : response.reservations()) {
                        for (Instance instance : reservation.instances()) {
                            System.out.println("Instance Id is " + instance.instanceId());
                            System.out.println("Image id is "+  instance.imageId());
                            System.out.println("Instance type is "+  instance.instanceType());
                            System.out.println("Instance state name is "+  instance.state().name());
                            System.out.println("monitoring information is "+  instance.monitoring().state());
                            System.out.println("Instance KN is "+  instance.keyName());
                            for (Tag t : instance.tags()) {
                                LOG.info("K:"+t.key()+" : "+"V: "+t.value());
//                                if (t.key().equals("Name") && t.value().contains("MongoDB")) {
//                                    LOG.info("Found MDB instance: " + instance.publicDnsName());
//                                }
                            }

                        }
                    }
                    nextToken = response.nextToken();
                } while (nextToken != null);

            } catch (Ec2Exception e) {
                System.err.println(e.awsErrorDetails().errorMessage());
                System.exit(1);
            }
        }

}
