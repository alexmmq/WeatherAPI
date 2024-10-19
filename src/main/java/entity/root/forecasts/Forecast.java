package entity.root.forecasts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Forecast {
    @JsonProperty("date")
    private String date;
    private int date_ts;
    private int week;
    private String sunrise;
    private String sunset;
    private String rise_begin;
    private String set_end;
    private int moon_code;
    private String moon_text;
    private Parts parts;
    private ArrayList<Hour> hours;
    private Biomet biomet;
}
