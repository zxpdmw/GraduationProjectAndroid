package top.zxpdmw.graduationproject.ui.activity.system;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import lombok.SneakyThrows;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.HFWeather;
import top.zxpdmw.graduationproject.bean.Weather;
import top.zxpdmw.graduationproject.presenter.WeatherPresenter;
import top.zxpdmw.graduationproject.presenter.contract.WeatherContract;
import top.zxpdmw.graduationproject.ui.adapter.SystemMainAdapter;
import top.zxpdmw.graduationproject.bean.Module;
import top.zxpdmw.graduationproject.bean.User;


public class SystemMainActivity extends AppCompatActivity implements WeatherContract.View {
    List<Module> moduleList = Arrays.asList(Module.NOTICE, Module.PAGE, Module.PROPERTY, Module.COMPLAIN_REPAIR, Module.HOUSE_KEEPING, Module.HOUSE_RENT_SALE, Module.MY_INFO);
    private SystemMainAdapter adapter = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView textView;
    @BindView(R.id.list_main)
    ListView listView;
    @BindView(R.id.weather)
    RelativeLayout relativeLayout;
    @BindView(R.id.weather_icon)
    ImageView icon;
    @BindView(R.id.weather_address)
    TextView address;
    @BindView(R.id.weather_now)
    TextView now;
    @BindView(R.id.weather_status)
    TextView status;
    @BindView(R.id.time)
    TextView time;

    WeatherPresenter weatherPresenter = new WeatherPresenter(this);

    User user;
    FragmentManager fragmentManage = getSupportFragmentManager();

    private long exitTime = 0;


    @Override
    public void showNow(HFWeather.Now hfWeather) {
        now.setText(hfWeather.getTemp()+ "\u2103");
        status.setText(hfWeather.getText());
        address.setText("芜湖");
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd     HH:mm");
        simpleDateFormat.setTimeZone(timeZone);
        final String format = simpleDateFormat.format(new Date());
        time.setText(format);

    }

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemmain);
        ButterKnife.bind(this);
        weatherPresenter.Now("101220301");


        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        textView.setText("便民社区");
        adapter = new SystemMainAdapter(moduleList, this);
        listView.setAdapter(adapter);
    }

    @OnClick(R.id.weather)
    void setRelativeLayout() {
        final Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnItemClick(R.id.list_main)
    void setListView(int id) {
        final Intent intent = new Intent();
        switch (id) {
            case 0:
                intent.setClass(this, NoticeActivity.class);
                break;
            case 1:
                intent.setClass(this, CommunityPageActivity.class);
                break;
            case 2:
                intent.setClass(this, PropertyActivity.class);
                intent.putExtra("houseId", user.getHouse_id());
                intent.putExtra("nickname", user.getNickname());
                break;
            case 3:
                intent.setClass(this, ComplainRepairActivity.class);
                intent.putExtra("username", user.getUsername());
                break;
            case 4:
                intent.setClass(this, HouseKeepingActivity.class);
                break;
            case 5:
                intent.setClass(this, HouseRentSaleActivity.class);
                final Bundle bundle = new Bundle();
                bundle.putString("username", user.getUsername());
                intent.putExtra("bundle", bundle);
                break;
            case 6:
                intent.setClass(this, MyInfoActivity.class);
                intent.putExtra("user", user);
                break;
        }
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManage.getBackStackEntryCount() == 0) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        } else {
            fragmentManage.popBackStack();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void showList(List<Weather> list) {
//        this.weatherList = list;
//        now.setText(list.get(0).getReal());
//        status.setText(list.get(0).getWeather());
//        range.setText(list.get(0).getLowest() + "~" + list.get(0).getHighest());
//        wind.setText(list.get(0).getWind());
//        address.setText(list.get(0).getArea());
//        final Calendar instance = Calendar.getInstance(Locale.CHINA);
//        final Date time = instance.getTime();
//        int hour=time.getHours()+8;
//        this.time.setText(list.get(0).getDate()+"     "+hour+":"+time.getMinutes());
//        switch (list.get(0).getWeatherimg()) {
//            case "duoyun.png":
//                icon.setBackgroundResource(R.drawable.yun);
//                break;
//            case "yu.png":
//                icon.setBackgroundResource(R.drawable.yu);
//                break;
//            case "yun.png":
//                icon.setBackgroundResource(R.drawable.yun);
//                break;
//            case "qing.png":
//                icon.setBackgroundResource(R.drawable.qing);
//                break;
//            case "yin.png":
//                icon.setBackgroundResource(R.drawable.yin);
//                break;
//            case "leizhenyu.png":
//                icon.setBackgroundResource(R.drawable.lei);
//                break;
//            case "xue.png":
//                icon.setBackgroundResource(R.drawable.xue);
//                break;
//            case "wu.png":
//                icon.setBackgroundResource(R.drawable.wu);
//                break;
//            case "shachenbao.png":
//                icon.setBackgroundResource(R.drawable.shachen);
//                break;
//        }
    }

    @Override
    public void initTodayWeather() {

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
