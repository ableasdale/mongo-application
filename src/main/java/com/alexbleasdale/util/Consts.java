package com.alexbleasdale.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Consts {

    public static final Path CURRENT_DIR_PATH = Paths.get("");
    public static final String RESOURCE_PACKAGES = "com.alexbleasdale.resources"; /*,io.swagger.jaxrs.listing"; */
    public static final String BASE_DIRECTORY_ROOT = new StringBuilder().append(CURRENT_DIR_PATH.toAbsolutePath().toString()).append(File.separator).append(StringUtils.join(new String[]{"src", "main", "resources"}, File.separator)).toString();
    public static final String STATIC_RESOURCE_DIRECTORY_ROOT = new StringBuilder().append(BASE_DIRECTORY_ROOT).append(File.separator).append("assets").toString();
    public static final String URI_BASE = "http://0.0.0.0/";
    public static final int GRIZZLY_HTTP_PORT = 9992;
    public static final String ASSETS_DIRECTORY_PATH = "/assets";
    public final static String FREEMARKER_TEMPLATE_PATH = "freemarker";

    // TODO - forced to hard-code Public DNS due to multiple earlier issues (and time) - note that it's resolving the AWS Elastic IP - so if the machine is terminated and re-provisioned, we will be able to connect.
    public static final String MONGODB_CONNECTION_STRING = "mongodb://database-reader:_1ID}1UhpQbPKr`R~}*y@ec2-13-219-113-233.compute-1.amazonaws.com:27017";
    public static final String MONGO_DB_APPLICATION_COLLECTION_NAME = "zips";
    public static final String MONGO_DB_DATABASE_NAME = "zips-db";
    public static final String S3_BUCKET_NAME = "ableasdale-tf-mongo-backup";
}