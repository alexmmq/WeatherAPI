package aleks.tasks;

import aleks.JsonOperations.JsonToMap;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//receives string and parses it to map (dedicated only for fact entry)

public class Fact {
    private String input;
    private Map<String, Object> fact = new HashMap<>();

    public Fact(String input) throws JsonProcessingException {
        this.input = input;
        JsonToMap jsonToMap = new JsonToMap(input);
        fact = jsonToMap.getJsonMap();
    }

    //method returns all the entries within json file
    public String findTemp(){
        String temp = null;
        for(Map.Entry<String, Object> entry : fact.entrySet()){
            if(entry.getKey().equals("temp")){
                temp = entry.getValue().toString();
            }
        }
        return temp;
    }
}
