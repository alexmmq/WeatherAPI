package entity.root.info;

import lombok.Data;

@Data
public class Info {
    private boolean n;
    private String url;
    private double lat;
    private double lon;
    private Tzinfo tzinfo;
    private int def_pressure_mm;
    private int def_pressure_pa;
    private int zoom;
    private boolean nr;
    private boolean ns;
    private boolean nsr;
    private boolean p;
    private boolean f;
    private boolean _h;
}
