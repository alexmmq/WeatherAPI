package entity.root.forecasts;

import lombok.Data;

@Data
public class Parts {
    private Day day;
    private DayShort day_short;
    private Evening evening;
    private Morning morning;
    private Night night;
    private NightShort night_short;
}
