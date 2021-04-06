package top.zxpdmw.graduationproject.presenter.contract;

import java.util.List;

import top.zxpdmw.graduationproject.bean.Weather;
import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface WeatherContract {
    interface View extends BaseView{
        void showList(List<Weather> list);
        void initTodayWeather();
    }
    interface Presenter extends BasePresenter{
        void getWeather(String city);
    }
}
