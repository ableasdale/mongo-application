package com.alexbleasdale.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class Consts {

    public static final String RESOURCE_PACKAGES = "com.alexbleasdale.resources"; /*,io.swagger.jaxrs.listing"; */
    public static final String BASE_DIRECTORY_ROOT = new StringBuilder().append(System.getProperty("user.dir")).append(File.separator).append(StringUtils.join(new String[]{"src", "main", "resources"}, File.separator)).toString();
    public static final String STATIC_RESOURCE_DIRECTORY_ROOT = new StringBuilder().append(BASE_DIRECTORY_ROOT).append(File.separator).append("assets").toString();
    public static final String URI_BASE = "http://0.0.0.0/";
    public static final int GRIZZLY_HTTP_PORT = 9992;
    public static final String ASSETS_DIRECTORY_PATH = "/assets";
    public final static String FREEMARKER_TEMPLATE_PATH = "freemarker";

    public static final String MONGO_DB_APPLICATION_COLLECTION_NAME = "zips";
    public static final String MONGO_DB_DATABASE_NAME = "zips-db";
    public static final String S3_BUCKET_NAME = "ableasdale-tf-mongo-backup";
}