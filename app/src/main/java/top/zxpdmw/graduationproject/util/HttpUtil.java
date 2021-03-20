package top.zxpdmw.graduationproject.util;

import com.google.android.material.internal.NavigationMenuItemView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    public static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    public static final String BASE_URL = "http://39.96.113.190:8080/";

    public static Response GetNoParam(String requestPath){
        Response response= null;
        try {
            Request request=new Request.Builder().url(BASE_URL+requestPath).build();
            response = OK_HTTP_CLIENT.newCall(request).execute();

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response PostJson(JSONObject jsonObject){
        Response response=null;
        try {
            Request request =new Request.Builder()
                    .url(HttpUtil.BASE_URL+"user/register")
                    .post(RequestBody.create(jsonObject.toString(), MediaType.parse("application/json")))
                    .build();
             response=HttpUtil.OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
