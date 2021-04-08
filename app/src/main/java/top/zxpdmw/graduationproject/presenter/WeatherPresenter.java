package top.zxpdmw.graduationproject.presenter;

import android.util.Log;

import org.w3c.dom.NodeList;

import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.WeatherAir;
import top.zxpdmw.graduationproject.bean.WeatherDay;
import top.zxpdmw.graduationproject.bean.WeatherHour;
import top.zxpdmw.graduationproject.bean.WeatherLife;
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
            @SneakyThrows
            @Override
            public void onResponse(Call<WeatherNow> call, Response<WeatherNow> response) {
                String code=response.body().getCode();
                if (code.equals("200")){
                    view.showNow(response.body().getNow());
                }
                view.dismissLoading();
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
                    view.showHour(response.body().getHourly());
                }

            }

            @Override
            public void onFailure(Call<WeatherHour> call, Throwable t) {

            }
        });
    }

    @Override
    public void Day() {
        model.Day(new Callback<WeatherDay>() {
            @Override
            public void onResponse(Call<WeatherDay> call, Response<WeatherDay> response) {
                String code=response.body().getCode();
                if (code.equals("200")){
                    view.showDay(response.body().getDaily());
                }
            }

            @Override
            public void onFailure(Call<WeatherDay> call, Throwable t) {

            }
        });
    }

    @Override
    public void Life() {
        model.Life(new Callback<WeatherLife>() {
            @Override
            public void onResponse(Call<WeatherLife> call, Response<WeatherLife> response) {
                String code=response.body().getCode();
                if (code.equals("200")){
                    view.showLife(response.body().getDaily().get(0));
                }
            }

            @Override
            public void onFailure(Call<WeatherLife> call, Throwable t) {

            }
        });
    }

    @Override
    public void Air() {
        model.Air(new Callback<WeatherAir>() {
            @Override
            public void onResponse(Call<WeatherAir> call, Response<WeatherAir> response) {
                String code=response.body().getCode();
                if (code.equals("200")) {
                    view.showAir(response.body());
                    Log.d("zwy", "onResponse: " + response.body());
                }else{
                    Log.d("zwy", "onResponse: fail");
                }

            }

            @Override
            public void onFailure(Call<WeatherAir> call, Throwable t) {

            }
        });
    }
}
