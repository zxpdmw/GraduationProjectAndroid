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
public class WeatherLife {
    String code;
    String updateTime;
    String fxLink;
    List<Day> daily;
    Object object;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Day {
        String date;
        String type;
        String name;
        String level;
        String category;
        String text;
    }
}
