package com.alexbleasdale.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Publisher;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;


public class AWSTools {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static List<S3Object> getS3Information() {

        S3Client s3Client = S3Client.builder()
                .region(Region.US_EAST_1)
                .build();

        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
                .bucket(Consts.S3_BUCKET_NAME)
                .build();
        ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsV2Request);
        List<S3Object> contents = listObjectsV2Response.contents();

        // LOG.info("Number of objects in the bucket: " + contents.stream().count());
        //contents.stream().forEach(System.out::println);
        // contents.stream().collect(Collectors.toList());
        List<S3Object> list = contents.stream().toList();
        s3Client.close();
        return list;
    }


    public static String getMongoDBPublicDNSName() {
        String name = "";
        Ec2Client ec2 = Ec2Client.builder()
                .region(Region.US_EAST_1)
                .build();

        String nextToken = null;
        try {
            do {
                DescribeInstancesRequest request = DescribeInstancesRequest.builder().maxResults(6).nextToken(nextToken).build();
                DescribeInstancesResponse response = ec2.describeInstances(request);

                for (Reservation reservation : response.reservations()) {
                    for (Instance instance : reservation.instances()) {
                        for (Tag t : instance.tags()) {
                            if (t.key().equals("Name") && t.value().contains("MongoDB") && instance.state().nameAsString().equals("running")) {
                                LOG.debug("Found MDB instance: " + instance.publicDnsName());
                                LOG.debug("AWS Instance Id is: " + instance.instanceId());
                                name = instance.publicDnsName();
                                return name;
                            }
                        }
                    }
                }
                nextToken = response.nextToken();
            } while (nextToken != null);

        } catch (Ec2Exception e) {
            LOG.error("Ec2Exception: " + e.awsErrorDetails().errorMessage(), e);
        }
        ec2.close();
        return name;
    }
}
