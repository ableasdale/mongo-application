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
                .bucket("ableasdale-tf-mongo-backup")
                .build();
        ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsV2Request);

        List<S3Object> contents = listObjectsV2Response.contents();

       // LOG.info("Number of objects in the bucket: " + contents.stream().count());
        contents.stream().forEach(System.out::println);
        // contents.stream().collect(Collectors.toList());
        List<S3Object> list = contents.stream().toList();

        s3Client.close();
        return list;

        /*
        S3Client s3 = S3Client.create();

        try {
            ListObjectsV2Request listReq = ListObjectsV2Request.builder()
                    .bucket("ableasdale-tf-mongo-backup")
                    .maxKeys(1)
                    .build();

            s3.
            ListObjectsV2Iterable listRes = s3.listObjectsV2Paginator(listReq);
            listRes.stream()
                    .flatMap(r -> r.contents().stream())
                    .forEach(content -> LOG.info(" Key: " + content.key() + " size = " + content.size()));

        } catch (S3Exception e) {
            LOG.error(e.awsErrorDetails().errorMessage());
        } */
    }




    public static String getMongoDBPublicDNSName() {
        String name = "";
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
                                name = instance.publicDnsName();
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
        return name;
    }
}
