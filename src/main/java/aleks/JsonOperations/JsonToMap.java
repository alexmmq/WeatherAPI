package aleks.JsonOperations;

import aleks.App;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.util.Map;

public class JsonToMap {
    private String json;

    public JsonToMap(String json) throws JsonProcessingException {
        this.json = json;
    }

    public Map<String, Object> getJsonMap()  {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = objectMapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonMap;
    }




}
