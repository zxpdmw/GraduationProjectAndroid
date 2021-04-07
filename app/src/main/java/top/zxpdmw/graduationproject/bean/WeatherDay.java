package top.zxpdmw.graduationproject.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WeatherDay {
    String code;
    String updateTime;
    String fxLink;
    List<Day> daily;
    Object object;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Day {
        String fxDate;
        String sunrise;
        String sunset;
        String moonrise;
        String moonset;
        String moonPhase;
        String tempMax;
        String tempMin;
        String iconDay;
        String textDay;
        String iconNight;
        String textNight;
        String wind360Day;
        String windDirDay;
        String windScaleDay;
        String windSpeedDay;
        String wind360Night;
        String windDirNight;
        String windScaleNight;
        String windSpeedNight;
        String humidity;
        String precip;
        String pressure;
        String vis;
        String cloud;
        String uvIndex;
    }
}
