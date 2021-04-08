package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.SneakyThrows;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.bean.WeatherAir;
import top.zxpdmw.graduationproject.bean.WeatherDay;
import top.zxpdmw.graduationproject.bean.WeatherHour;
import top.zxpdmw.graduationproject.bean.WeatherLife;
import top.zxpdmw.graduationproject.bean.WeatherNow;
import top.zxpdmw.graduationproject.presenter.WeatherPresenter;
import top.zxpdmw.graduationproject.presenter.contract.WeatherContract;
import top.zxpdmw.graduationproject.ui.adapter.HorizontalItemDecoration;
import top.zxpdmw.graduationproject.ui.adapter.WeatherDayAdapter;
import top.zxpdmw.graduationproject.ui.adapter.WeatherHourAdapter;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {
    WeatherPresenter weatherPresenter = new WeatherPresenter(this);
    @BindView(R.id.weather_address)
    TextView tv_address;
    @BindView(R.id.weather_status)
    TextView tv_status;
    @BindView(R.id.weather_now)
    TextView tv_now;
    @BindView(R.id.weather_range)
    TextView tv_range;
    @BindView(R.id.weather_hour)
    RecyclerView rv_hour;
    @BindView(R.id.weather_day)
    RecyclerView rv_day;
    @BindView(R.id.sunrise_time)
    TextView sunrise;
    @BindView(R.id.sunset_time1)
    TextView sunset;
    @BindView(R.id.ziwaixian_zhishu)
    TextView ziwaixian;
    @BindView(R.id.yaqiang_zhishu)
    TextView yaqiang;
    @BindView(R.id.jiangshuiliang_zhishu)
    TextView jiangshuiliang;
    @BindView(R.id.nengjiandu_zhishu)
    TextView nengjiandu;
    @BindView(R.id.wind_zhishu)
    TextView wind;
    @BindView(R.id.shidu_zhishu)
    TextView shidu;
    @BindView(R.id.air)
    TextView air;
    @BindView(R.id.tip)
    TextView tip;
    ZLoadingDialog zLoadingDialog = new ZLoadingDialog(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        showLoading();
        rv_day.setLayoutManager(new LinearLayoutManager(this));
        rv_hour.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_hour.addItemDecoration(new HorizontalItemDecoration(10, this));//10表示10dp
        weatherPresenter.Now();
        weatherPresenter.Air();
        weatherPresenter.Life();
        weatherPresenter.Hour();
        weatherPresenter.Day();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }

    @Override
    @SneakyThrows
    public void dismissLoading() {
        zLoadingDialog.dismiss();
    }

    @Override
    public void showDay(List<WeatherDay.Day> list) {
        WeatherDay.Day day = list.get(0);
        String range = "最高 " + day.getTempMin() + "  最低 " + day.getTempMax();
        tv_range.setText(range);
        ziwaixian.setText(day.getUvIndex());
        jiangshuiliang.setText(day.getPrecip() + "毫米");
        nengjiandu.setText(day.getVis() + "公里");
        yaqiang.setText(day.getPressure() + "百帕");
        sunrise.setText(day.getSunrise());
        sunset.setText(day.getSunset());
        shidu.setText(day.getHumidity() + "%");
        wind.setText(day.getWindDirDay() + " " + day.getWindSpeedDay() + "km/h");
        final WeatherDayAdapter weatherDayAdapter = new WeatherDayAdapter(list);
        rv_day.setAdapter(weatherDayAdapter);
    }

    @Override
    public void showLife(WeatherLife.Day life) {
        tip.setText("今天"+life.getCategory()+":"+life.getText());
    }

    @Override
    public void showAir(WeatherAir air) {
        this.air.setText(air.getNow().getAqi()+" - "+air.getNow().getCategory());
    }

    @Override
    public void showLoading() {
        zLoadingDialog.setLoadingBuilder(Z_TYPE.LEAF_ROTATE)
                .setLoadingColor(Color.WHITE)
                .setDialogBackgroundColor(getResources().getColor(R.color.loading_background))
                .setDurationTime(1.0).show();
    }


    @Override
    public void showNow(WeatherNow.Now hfWeather) {
        tv_address.setText("芜湖市");
        tv_status.setText(hfWeather.getText());
        tv_now.setText(hfWeather.getTemp());
    }

    @Override
    public void showHour(List<WeatherHour.Hour> list) {
        final WeatherHourAdapter weatherHourAdapter = new WeatherHourAdapter(list);
        rv_hour.setAdapter(weatherHourAdapter);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void jumpView(AppCompatActivity activity) {

    }

    @Override
    public void showMsg(String msg) {

    }


}