package entity.root;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.root.fact.Fact;
import entity.root.forecasts.Forecast;
import entity.root.info.Info;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Root {
    @JsonProperty("now")
    private int now;

    @JsonProperty("now_dt")
    private Date now_dt;
    private Info info;
    private Fact fact;
    private ArrayList<Forecast> forecasts;
}
