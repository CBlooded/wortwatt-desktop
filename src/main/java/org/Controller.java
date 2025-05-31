package org;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.model.Computer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Controller {
    public static void startWork(Computer computer, String url) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(computer);
            // dalej robisz coś z json, np. wysyłasz zapytanie
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            // Tworzenie klienta HTTP i wysłanie zapytania
            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
