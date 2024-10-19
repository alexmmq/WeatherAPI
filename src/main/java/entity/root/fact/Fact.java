package entity.root.fact;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Fact {

    private String daytime;
    private int obs_time;
    private String season;
    private String source;
    private int uptime;
    private int cloudness;
    private String condition;
    private int feels_like;
    private int humidity;
    private String icon;
    private boolean is_thunder;
    private boolean polar;
    private int prec_prob;
    private int prec_strength;
    private int prec_type;
    private int pressure_mm;
    private int pressure_pa;
    @JsonProperty("temp")
    private int temp;
    private int uv_index;
    private int wind_angle;
    private String wind_dir;
    private double wind_gust;
    private int wind_speed;
}
