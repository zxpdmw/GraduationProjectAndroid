package top.zxpdmw.graduationproject.presenter;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.ResponseWeather;
import top.zxpdmw.graduationproject.model.WeatherModel;
import top.zxpdmw.graduationproject.presenter.contract.WeatherContract;
import top.zxpdmw.graduationproject.ui.fragment.LoginFragment;

public class WeatherPresenter implements WeatherContract.Presenter {

    WeatherModel model;
    WeatherContract.View view;
    public WeatherPresenter(WeatherContract.View view){
        this.view=view;
        model=new WeatherModel();
    }

    @Override
    public void getWeather(String city) {
        model.getWeather(city, new Callback<ResponseWeather>() {
            @Override
            public void onResponse(Call<ResponseWeather> call, Response<ResponseWeather> response) {
                    int code=response.body().getCode();
                    if (code==200){
                        view.showList(response.body().getNewslist());
                        Log.d("zwy", "onResponse: "+response.body().getNewslist());
                    }else{
                        view.showMsg(response.body().getMsg());
                    }
            }

            @Override
            public void onFailure(Call<ResponseWeather> call, Throwable t) {

            }
        });
    }
}
