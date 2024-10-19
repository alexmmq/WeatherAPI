package aleks.JsonOperations;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonBeautify {
    private String uglyJson;
    public JsonBeautify(String uglyJson) {
        this.uglyJson = uglyJson;
    }
    public String prettyPrintJsonUsingDefaultPrettyPrinter(String uglyJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Object jsonObject = mapper.readValue(uglyJson, Object.class);
        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        return prettyJson;
    }
    public String getPrettyJson() {
        try {
            return prettyPrintJsonUsingDefaultPrettyPrinter(uglyJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
