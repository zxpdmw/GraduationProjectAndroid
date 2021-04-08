package top.zxpdmw.graduationproject.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherAir {
    String code;
    String updateTime;
    String fxLink;
    Now now;
    List<Station> station;
    Object object;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Now {
        String pubTime;
        String aqi;
        String level;
        String category;
        String primary;
        String pm10;
        String pm2p5;
        String no2;
        String so2;
        String co;
        String o3;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Station {
        String name;
        String id;
        String pubTime;
        String aqi;
        String level;
        String category;
        String primary;
        String pm10;
        String pm2p5;
        String no2;
        String so2;
        String co;
        String o3;
    }
}
