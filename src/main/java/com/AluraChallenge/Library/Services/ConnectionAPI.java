package com.AluraChallenge.Library.Services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectionAPI {

    public static String getDataAPI(String url) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Error: " + response.statusCode());
                return null;
            }
        } catch (IOException e) {
            System.err.println("IOException : " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            System.err.println("InterruptedException : " + e.getMessage());
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return null;
        }

        return response.body();
    }

}
