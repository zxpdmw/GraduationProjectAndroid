package top.zxpdmw.graduationproject.util;

import retrofit2.Retrofit;

public class HttpUtil {
    public static final Retrofit builder=new Retrofit.Builder().baseUrl("http://127.0.0.1:80/").build();
}
