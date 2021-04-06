package top.zxpdmw.graduationproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Weather {
    String area;//地区
    String date;//日期
    String week;//星期
    String weather;//早晚天气变化
    String weatherimg;//天气图标
    String real;//实时天气
    String lowest;//最低温
    String highest;//最高温
    String wind;//风向
    String winddeg;//风向360角度
    String windspeed;//风速，km/h
    String windsc;//风力
    String sunrise;//日出时间
    String sunset;//日落时间
    String moonrise;//月升时间
    String moondown;//月落时间
    String pcpn;//降雨量
    String pop;//降雨概率
    String uv_index;//紫外线强度指数
    String vis;//能见度 单位 公里
    String humidity;//相对湿度
    String  tips;//生活指数提示
}
