package top.zxpdmw.graduationproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain =true)
public class HFWeather {
    String code;
    String updateTime;
    String fxLink;
    Now now;
    Object object;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain =true)
    public class Now{
        String obsTime;
        String temp;
        String feelslike;
        String icon;
        String text;
        String wind360;
        String windDir;
        String windScale;
        String windSpeed;
        String humidity;
        String precip;
        String pressure;
        String vis;
        String cloud;
        String dew;
    }

    class Refer{

    }

}

