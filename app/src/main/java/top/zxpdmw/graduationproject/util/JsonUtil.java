package top.zxpdmw.graduationproject.util;

import com.google.gson.Gson;

public class JsonUtil {
    public static final Gson GSON=new Gson();
    public static String BeanToJson(Object o){
        return GSON.toJson(o);
    }

}
