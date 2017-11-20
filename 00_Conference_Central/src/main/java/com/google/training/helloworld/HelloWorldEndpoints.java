package com.google.training.helloworld;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;



import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Dataset;
import com.google.cloud.bigquery.DatasetInfo;

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
import com.google.cloud.bigquery.TableId;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;


/**
 * Defines endpoint functions APIs.
 */
@Api(
    name = "helloworldendpoints", 
    version = "v1",
    scopes = {Constants.EMAIL_SCOPE },
    clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID },
    description = "API for hello world endpoints."
    )
public class HelloWorldEndpoints {

   // Make this method available externally through Endpoints
    @ApiMethod(name = "sayHello", path = "sayHello", httpMethod = HttpMethod.GET)
    public HelloClass sayHello() {
        return new HelloClass();
    }

    // Make this method available externally through Endpoints
    @ApiMethod(name = "sayHelloByName", path = "sayHelloByName", httpMethod = HttpMethod.GET)
    public HelloClass sayHelloByName (@Named("name") String name) {
        return new HelloClass(name);
    }

    // Make this method available externally through Endpoints
    @ApiMethod(name = "greetByPeriod", path = "greetByPeriod", httpMethod = HttpMethod.GET)
    public HelloClass greetByPeriod (@Named("name") String name, @Named("period") String period) {
        return new HelloClass(name, period);
    }

    @ApiMethod(name = "sayHelloByNames", path = "sayHelloByNames", httpMethod = HttpMethod.GET)
    public HelloClass sayHelloByName_1() {
        BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                        "SELECT "
                                + "StartingSalary FROM [spelman-472-2017-1:degreesthatpayback.DegreeList] WHERE StartingSalary = 35600 LIMIT 1000;")
                        // Use standard SQL syntax for queries.
                        // See: https://cloud.google.com/bigquery/sql-reference/
                        .setUseLegacySql(false)
                        .build();

        // Create a job ID so that we can safely retry.
        JobId jobId = JobId.of(UUID.randomUUID().toString());
        Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());
try {
// Wait for the query to complete.
    queryJob = queryJob.waitFor();
} catch (InterruptedException e) {
    return new HelloClass();
}


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

// Print all pages of the results.
        /*
        while (result != null) {
            for (List<FieldValue> row : result.iterateAll()) {
                List<FieldValue> titles = row.get(0).getRepeatedValue();
                System.out.println("titles:");

                for (FieldValue titleValue : titles) {
                    List<FieldValue> titleRecord = titleValue.getRecordValue();
                    String title = titleRecord.get(0).getStringValue();
                    long uniqueWords = titleRecord.get(1).getLongValue();
                    System.out.printf("\t%s: %d\n", title, uniqueWords);
                }

                long uniqueWords = row.get(1).getLongValue();
                System.out.printf("total unique words: %d\n", uniqueWords);
            }

            result = result.getNextPage();

        }
*/
        String temp = result.toString();;

            return new HelloClass(temp);
    }



}

