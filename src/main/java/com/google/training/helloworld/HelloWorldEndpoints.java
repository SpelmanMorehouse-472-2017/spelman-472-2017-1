package com.google.training.helloworld;


import com.google.cloud.bigquery.BigQuery.TableField;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.cloud.bigquery.*;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Dataset;
import com.google.cloud.bigquery.DatasetInfo;
import java.util.List;
import java.util.UUID;
import java.util.*;
import java.io.*;

import java.lang.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.BigqueryScopes;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryResponse;
import com.google.cloud.bigquery.QueryResult;
import java.util.UUID;


/**
 * Defines endpoint functions APIs.
 */
@Api(name = "helloworldendpoints", version = "v1",
scopes = {Constants.EMAIL_SCOPE },
        clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID },
        description = "API for hello world endpoints.")

public class HelloWorldEndpoints {

    // Declare this method as a method available externally through Endpoints
    @ApiMethod(name = "sayHello", path = "sayHello",
            httpMethod = HttpMethod.GET)

    public HelloClass sayHello() {
        ArrayList<String> descriptionList = new ArrayList<String>();
        descriptionList.add("No results found");
        return new HelloClass(descriptionList);
    }

    // Declare this method as a method available externally through Endpoints
    @ApiMethod(name = "sayHelloByName", path = "sayHelloByName",
            httpMethod = HttpMethod.GET)

    public HelloClass sayHelloByName(@Named("loc") String loc, @Named("sal") String sal, @Named("schoolTypes") String schoolTypes) throws InterruptedException {

       // loc = loc.toLowerCase();

        //sals.toLowerCase();
        //schoolTypes = schoolTypes.toLowerCase();
        ArrayList<String> array = new ArrayList<String>();
        ArrayList<String> descriptionList = new ArrayList<String>();


        ArrayList<String> breakfast = new ArrayList<>();

        if(sal != null && !sal.matches("[-+]?\\d*\\.?\\d+"))
        {
            String temp = "Please enter a number for salary";
            return new HelloClass(temp);
        }

        BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                        "SELECT "
                                + "SchoolName FROM [spelman-472-2017-1:degreesthatpayback.MegreSalaries] WHERE StartingSalary > " + sal + "AND SchoolType = " + schoolTypes + ";").setUseLegacySql(true)
                        .build();
        // Use standard SQL syntax for queries.
        // See: https://cloud.google.com/bigquery/sql-reference/


        // Create a job ID so that we can safely retry.
        JobId jobId = JobId.of(UUID.randomUUID().toString());
        Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());


        queryJob = queryJob.waitFor();
        //try {
// Wait for the query to complete.
         //       queryJob = queryJob.waitFor();
         //  } catch (InterruptedException e) {
         //     return new HelloClass("error");
      //     }


// Check for errors
        if (queryJob == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (queryJob.getStatus().getError() != null) {
            // You can also look at queryJob.getStatus().getExecutionErrors() for all
            // errors, not just the latest one.
            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }

// Get the results.
        QueryResponse response = bigquery.getQueryResults(jobId);

        QueryResult result = response.getResult();



        ArrayList<String> arrays = new ArrayList<String>();


/*
        while (result != null) {
            for (List<FieldValue> row : result.iterateAll()) {
                List<FieldValue> values = row.get(0).getRepeatedValue();
              //  System.out.println("titles:");

                for (FieldValue titleValue : values) {
                    List<FieldValue> titleRecord = titleValue.getRecordValue();
                    String value = titleRecord.get(0).getStringValue();
                    arrays.add(value);
                   // long uniqueWords = titleRecord.get(1).getLongValue();
                  //  System.out.printf("\t%s: %d\n", title, uniqueWords);
                }

               // long uniqueWords = row.get(1).getLongValue();
              //  System.out.printf("total unique words: %d\n", uniqueWords);
            }

            result = result.getNextPage();
        }
        */

        //ArrayList<String> breakfast = new ArrayList<>();
        while(result != null)
        {
            for(List<FieldValue> row : result.iterateAll())
            {
                breakfast.add(row.get(0).getStringValue());
            }
            result = result.getNextPage();
        }




        return new HelloClass(breakfast);


    }




}