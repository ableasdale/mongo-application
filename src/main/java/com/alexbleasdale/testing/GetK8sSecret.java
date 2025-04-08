package com.alexbleasdale.testing;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1Secret;
import io.kubernetes.client.util.Config;

import java.io.IOException;

public class GetK8sSecret {

    public static void main(String[] args) {

        /** todo - examples that are documented don't seem to work!! commenting out for now...
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);

        CoreV1Api api = new CoreV1Api();
        String ns = "";
        //V1PodList list = api.listNamespacedPod(ns,null, null, null, null, null, null, null, null, 10, false);
        V1PodList list = api.listNamespacedPod(ns);
        for (V1Pod item : list.getItems()) {
            System.out.println(item.getMetadata().getName());
        }

        ApiClient client = null;
        try {
            client = Config.defaultClient();
            Configuration.setDefaultApiClient(client);
            CoreV1Api api = new CoreV1Api();
            CoreV1Api.APIreadNamespacedSecretRequest result = api.readNamespacedSecret("mdb", "");
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        /* TODO Looks like I'm going to run out of time getting this to work nicely with AWS.. :(
        https://docs.aws.amazon.com/secretsmanager/latest/userguide/integrating_ascp_irsa.html */
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");
        CoreV1Api apiInstance = new CoreV1Api(defaultClient);
        String name = "name_example"; // String | name of the Secret
        String namespace = "namespace_example"; // String | object name and auth scope, such as for teams and projects
        String pretty = "pretty_example"; // String | If 'true', then the output is pretty printed. Defaults to 'false' unless the user-agent indicates a browser or command-line HTTP tool (curl and wget).
        try {
            V1Secret result = apiInstance.readNamespacedSecret(name, namespace)
                    .pretty(pretty)
                    .execute();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CoreV1Api#readNamespacedSecret");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }

    }
}
