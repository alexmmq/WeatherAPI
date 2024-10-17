package aleks;

import aleks.JsonOperations.JsonBeautify;
import aleks.JsonOperations.JsonToMap;
import aleks.tasks.Fact;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        final String key = "84ed91c4-c86d-4168-a383-ba357930d573";
        final String lat = "55.76042";
        final String lon = "49.19029";
        final String limit = "1";
        String output = null;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.weather.yandex.ru/v2/forecast?"
                + "lat=" + lat + "&lon=" + lon + "&limit=" + limit))
                .setHeader("X-Yandex-Weather-Key", key)
                .GET()
                .build();
        try{
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println("Response: " + response.statusCode());
            output = response.body();
            //System.out.println(output);
        } catch (Exception e) {
            System.err.println("Error making HTTP request: " + e.getMessage());
        }
        JsonBeautify jsonBeautify = new JsonBeautify(output);
        System.out.print(jsonBeautify.getPrettyJson());

        try {
            JsonToMap jsonToMap = new JsonToMap(output);

            //1st iteration through original json
            Map<String, Object> testmap = jsonToMap.getJsonMap();
            printMap(testmap);

            //2nd iteration, looking for the entry of "Fact", sending found string to the fact class
            Fact fact = new Fact(findValueInMap(testmap, "fact"));
            //calling method findTemperature
            System.out.println();
            System.out.println("Current Temperature is: " + fact.findTemp());




        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printMap(Map<String, Object> map) {
        System.out.println();
        System.out.println("Beginning of printing of map");
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // looks for special entry within map
    public static String findValueInMap(Map<String, Object> map, String key) {
        String match = null;
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            if(entry.getKey().equals(key)) {
                match = entry.getValue().toString();
            }
        }
        return match;
    }
}
