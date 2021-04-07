package top.zxpdmw.graduationproject.model;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import top.zxpdmw.graduationproject.bean.WeatherDay;
import top.zxpdmw.graduationproject.bean.WeatherHour;
import top.zxpdmw.graduationproject.bean.WeatherNow;
import top.zxpdmw.graduationproject.http.HttpManager;

public class WeatherModel {

    public static final String NOW = "https://devapi.qweather.com/v7/weather/now";
    public static final String HOUR = "https://devapi.qweather.com/v7/weather/24h";
    public static final String DAY = "https://devapi.qweather.com/v7/weather/7d";
    public static final String KEY = "77dec0efc65a4c90bca92d5e7826eb43";
    public static final String LOCATION = "101220301";
    final WeatherHttp weatherHttp = HttpManager.retrofit.create(WeatherHttp.class);


    public void Now(Callback<WeatherNow> callback) {
        weatherHttp.Now(KEY, LOCATION).enqueue(callback);
    }

    public void Hour(Callback<WeatherHour> callback) {
        weatherHttp.Hour(KEY, LOCATION).enqueue(callback);
    }

    public void Daty(Callback<WeatherDay> callback) {
        weatherHttp.Day(KEY, LOCATION).enqueue(callback);
    }

    interface WeatherHttp {

        @GET(DAY)
        Call<WeatherDay> Day(@Query("key") String key, @Query("location") String location);

        @GET(NOW)
        Call<WeatherNow> Now(@Query("key") String key, @Query("location") String location);

        @GET(HOUR)
        Call<WeatherHour> Hour(@Query("key") String key, @Query("location") String location);
    }
}
