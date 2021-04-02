package top.zxpdmw.graduationproject.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpManager {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://39.96.113.190:8080/") // 设置网络请求的Url地址
            .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
            .build();
}
