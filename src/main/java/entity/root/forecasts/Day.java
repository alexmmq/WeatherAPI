package entity.root.forecasts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Day {
    private String daytime;
    private String _source;
    private Biomet biomet;
    private double cloudness;
    private String condition;
    private int fresh_snow_mm;
    private int humidity;
    private String icon;
    private boolean polar;
    private double prec_mm;
    private int prec_period;
    private int prec_prob;
    private double prec_strength;
    private int prec_type;
    private int pressure_mm;
    private int pressure_pa;
    @JsonProperty("temp_avg")
    private int temp_avg;
    private int temp_max;
    private int temp_min;
    private int feels_like;
    private int uv_index;
    private int wind_angle;
    private String wind_dir;
    private double wind_gust;
    private double wind_speed;
}
