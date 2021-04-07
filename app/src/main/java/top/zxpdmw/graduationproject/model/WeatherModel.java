package top.zxpdmw.graduationproject.model;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import top.zxpdmw.graduationproject.bean.CommonOne;
import top.zxpdmw.graduationproject.bean.HFWeather;
import top.zxpdmw.graduationproject.bean.ResponseWeather;
import top.zxpdmw.graduationproject.bean.Weather;
import top.zxpdmw.graduationproject.http.HttpManager;

public class WeatherModel {

    public static final String GET_WEATHER="http://api.tianapi.com/txapi/tianqi/index";
    public static final String NOW="https://devapi.qweather.com/v7/weather/now";
    public static final String KEY="e7a558928691361882a5962cd136c3eb";
    public static final String key="77dec0efc65a4c90bca92d5e7826eb43";
    final WeatherHttp weatherHttp= HttpManager.retrofit.create(WeatherHttp.class);

    public void getWeather(String id, Callback<ResponseWeather> callback){
        weatherHttp.getWeather(id,KEY).enqueue(callback);
    }

    public void Now(String location,Callback<HFWeather> callback){
        weatherHttp.Now(key,location).enqueue(callback);
    }

    interface WeatherHttp{
        @GET(GET_WEATHER)
        Call<ResponseWeather> getWeather(@Query("city") String city,@Query("key") String key);

        @GET(NOW)
        Call<HFWeather> Now(@Query("key")String key,@Query("location")String location);
    }
}
