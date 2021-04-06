package top.zxpdmw.graduationproject.model;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import top.zxpdmw.graduationproject.bean.CommonOne;
import top.zxpdmw.graduationproject.bean.ResponseWeather;
import top.zxpdmw.graduationproject.bean.Weather;
import top.zxpdmw.graduationproject.http.HttpManager;

public class WeatherModel {

    public static final String GET_WEATHER="http://api.tianapi.com/txapi/tianqi/index";
    public static final String KEY="e7a558928691361882a5962cd136c3eb";
    final WeatherHttp weatherHttp= HttpManager.retrofit.create(WeatherHttp.class);

    public void getWeather(String id, Callback<ResponseWeather> callback){
        weatherHttp.getWeather(id,KEY).enqueue(callback);
    }

    interface WeatherHttp{
        @GET(GET_WEATHER)
        Call<ResponseWeather> getWeather(@Query("city") String city,@Query("key") String key);
    }
}
