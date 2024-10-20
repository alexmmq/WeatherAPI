package aleks;

import aleks.JsonOperations.JsonBeautify;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.root.forecasts.Forecast;
import entity.root.Root;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Для реализации функционала используются библиотеки Jackson и Lombok.
 * При работе с json происходит чтение данных напрямую в создаваемые POJO.
 * Автор: Александр Михайлов
 */
public class App 
{
    public static void main( String[] args ) {
        final String key = "84ed91c4-c86d-4168-a383-ba357930d573";
        final String lat = "55.76042";
        final String lon = "49.19029";
        final String limit = "7";
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
            output = response.body();
        } catch (Exception e) {
            System.err.println("Error making HTTP request: " + e.getMessage());
        }

        JsonBeautify jsonBeautify = new JsonBeautify(output);

        //выводим красивый JSON
        System.out.print(jsonBeautify.getPrettyJson());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Root root = objectMapper.readValue(output, Root.class);

            //выводим сегодняшнюю дату
            System.out.println();
            System.out.println("Время запроса: " + root.getNow_dt());

            //выводим температуру на данный момент и сервис предоставивший информацию
            System.out.println("Температура на данный момент: " + root.getFact().getTemp() + " "
                    + getCorrectForm(root.getFact().getTemp()));

            System.out.println("Информация предоставлена со следующего сервиса: " + root.getInfo().getUrl());

            //получаем отдельный лист для последующей работы с данными
            ArrayList<Forecast> forecasts = root.getForecasts();

            //создаем отдельные мапы для последущих операций над информацией полученной от сервиса - для
            //анализа средних температур днем, ночью, вечером и утром
            HashMap <String, Integer> mapDayAvg = new HashMap<>();
            HashMap <String, Integer> mapNightAvg = new HashMap<>();
            HashMap <String, Integer> mapMorningAvg = new HashMap<>();
            HashMap <String, Integer> mapEveningAvg = new HashMap<>();
            for(Forecast forecast : forecasts) {
                printAndSaveData(mapDayAvg, "днем", "day",
                        forecast.getParts().getDay().getTemp_avg(), forecast.getDate());
                printAndSaveData(mapNightAvg, "ночью", "night",
                        forecast.getParts().getNight().getTemp_avg(), forecast.getDate());
                printAndSaveData(mapMorningAvg, "утром", "morning",
                        forecast.getParts().getMorning().getTemp_avg(), forecast.getDate());
                printAndSaveData(mapEveningAvg, "вечером", "evening",
                        forecast.getParts().getEvening().getTemp_avg(), forecast.getDate());
            }

            //выводим данные по средним температурам
            System.out.println();
            printAvgTemperature(mapDayAvg);
            printAvgTemperature(mapNightAvg);
            printAvgTemperature(mapMorningAvg);
            printAvgTemperature(mapEveningAvg);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //метод для избавления от повторного кода
    public static Map<String, Integer> printAndSaveData(Map<String, Integer> map, String timeOfDay,
                                                        String timeOfDayEng, int temp, String date){
        System.out.println("Средняя температура " + timeOfDay + " : " + temp + " на " + date);
        map.put(timeOfDayEng + date, temp);
        return map;
    }

    //метод для вывода средней температуры на основе переданной мапы с данными
    public static void printAvgTemperature(Map<String, Integer> map){
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<String> keys = new ArrayList<>();
        double avgTemp;
        int total = 0;
        String partOfDay = "";
        String correctWord = "";
        for(Map.Entry<String, Integer> entry:map.entrySet()){
            values.add(entry.getValue());
            keys.add(entry.getKey());
        }

        if(keys.get(0).contains("day")) {
            partOfDay = "днем";
        } else if (keys.get(0).contains("night")) {
            partOfDay = "ночью";
        } else if (keys.get(0).contains("morning")) {
            partOfDay = "утром";
        } else if (keys.get(0).contains("evening")) {
            partOfDay = "вечером";
        }

        switch(values.size()){
            case 1:
                correctWord = "день";
                break;
            case 2:
                correctWord = "дня";
                break;
            case 3:
                correctWord = "дня";
                break;
            case 4:
                correctWord = "дня";
                break;
            default:
                correctWord = "дней";
        }
        for(int value: values){
            total +=value;
        }

        avgTemp = (double) total /values.size();
        System.out.println("Средняя температура " + partOfDay + " " + avgTemp + " градуса за период в " + values.size()
                + " " + correctWord);
    }

    //работа с падежами великого могучего
    public static String getCorrectForm(int value){
        String output = "";
        if((value>-5 && value<1)||(value>1 && value <5)){
            output = "градуса";
        } else if((value == 1) || (value == -1)){
            output = "градус";
        }
        else {
            output = "градусов";
        }
        return output;
    }
}
