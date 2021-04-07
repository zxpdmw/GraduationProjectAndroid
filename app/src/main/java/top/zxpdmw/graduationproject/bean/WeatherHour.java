package top.zxpdmw.graduationproject.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class WeatherHour {
    String code;
    String updateTime;
    String fxLink;
    List<Hour> hourly;
    Object object;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain=true)
    public static class Hour{
        String fxTime;
        String temp;
        String icon;
        String text;
        String wind360;
        String windDir;
        String windScale;
        String windSpeed;
        String humidity;
        String pop;
        String precip;
        String preesure;
        String cloud;
        String dew;
    }
}
