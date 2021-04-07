package top.zxpdmw.graduationproject.presenter;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.WeatherDay;
import top.zxpdmw.graduationproject.bean.WeatherHour;
import top.zxpdmw.graduationproject.bean.WeatherNow;
import top.zxpdmw.graduationproject.model.WeatherModel;
import top.zxpdmw.graduationproject.presenter.contract.WeatherContract;

public class WeatherPresenter implements WeatherContract.Presenter {

    WeatherModel model;
    WeatherContract.View view;
    public WeatherPresenter(WeatherContract.View view){
        this.view=view;
        model=new WeatherModel();
    }


    @Override
    public void Now() {
        model.Now(new Callback<WeatherNow>() {
            @Override
            public void onResponse(Call<WeatherNow> call, Response<WeatherNow> response) {
                String code=response.body().getCode();
                if (code.equals("200")){
                    view.showNow(response.body().getNow());
                    Log.d("zwy", "onResponse: sccess"+response.body().getNow());
                }else{
                    Log.d("zwy", "onResponse: false");
                }
            }

            @Override
            public void onFailure(Call<WeatherNow> call, Throwable t) {

            }
        });
    }

    @Override
    public void Hour() {
        model.Hour(new Callback<WeatherHour>() {
            @Override
            public void onResponse(Call<WeatherHour> call, Response<WeatherHour> response) {
                String code=response.body().getCode();
                if (code.equals("200")){
                    Log.d("zwy", "onResponse: "+response.body().toString());
                    view.showHour(response.body().getHourly());
                }else {
                    Log.d("zwy", "onResponse: false");
                }

            }

            @Override
            public void onFailure(Call<WeatherHour> call, Throwable t) {

            }
        });
    }

    @Override
    public void Day() {
        model.Daty(new Callback<WeatherDay>() {
            @Override
            public void onResponse(Call<WeatherDay> call, Response<WeatherDay> response) {
                String code=response.body().getCode();
                if (code.equals("200")){
                    Log.d("zwy", "onResponse: "+response.body().toString());
                    view.showDay(response.body().getDaily());
                }else{
                    Log.d("zwy", "onResponse: false");

                }
            }

            @Override
            public void onFailure(Call<WeatherDay> call, Throwable t) {

            }
        });
    }
}
