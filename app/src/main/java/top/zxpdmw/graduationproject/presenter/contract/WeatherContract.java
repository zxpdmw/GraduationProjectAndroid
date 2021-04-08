package top.zxpdmw.graduationproject.presenter.contract;

import com.zyao89.view.zloading.ZLoadingDialog;

import java.util.List;

import top.zxpdmw.graduationproject.bean.WeatherAir;
import top.zxpdmw.graduationproject.bean.WeatherDay;
import top.zxpdmw.graduationproject.bean.WeatherHour;
import top.zxpdmw.graduationproject.bean.WeatherLife;
import top.zxpdmw.graduationproject.bean.WeatherNow;
import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface WeatherContract {
    interface View extends BaseView{
        void showNow(WeatherNow.Now hfWeather);
        void showHour(List<WeatherHour.Hour> list);
        void showDay(List<WeatherDay.Day> list);
        void showLife(WeatherLife.Day life);
        void showAir(WeatherAir air);
    }
    interface Presenter extends BasePresenter{
        void Now();
        void Hour();
        void Day();
        void Life();
        void Air();
    }
}
