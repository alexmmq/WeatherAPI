package entity.root.forecasts;

import lombok.Data;

@Data
public class Hour {

    private String hour;
    private int hour_ts;
    private double cloudness;
    private String condition;
    private int feels_like;
    private int humidity;
    private String icon;
    private double prec_mm;
    private int prec_period;
    private int prec_prob;
    private double prec_strength;
    private int prec_type;
    private int pressure_mm;
    private int pressure_pa;
    private int temp;
    private int uv_index;
    private int wind_angle;
    private String wind_dir;
    private double wind_gust;
    private double wind_speed;
}
