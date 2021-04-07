package top.zxpdmw.graduationproject.ui.activity.system;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.SneakyThrows;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.WeatherDay;
import top.zxpdmw.graduationproject.bean.WeatherHour;
import top.zxpdmw.graduationproject.bean.WeatherNow;
import top.zxpdmw.graduationproject.presenter.WeatherPresenter;
import top.zxpdmw.graduationproject.presenter.contract.WeatherContract;
import top.zxpdmw.graduationproject.ui.adapter.ItemClickListener;
import top.zxpdmw.graduationproject.ui.adapter.MainAdapter;
import top.zxpdmw.graduationproject.bean.Module;
import top.zxpdmw.graduationproject.bean.User;


public class SystemMainActivity extends AppCompatActivity implements WeatherContract.View {
    List<Module> moduleList = Arrays.asList(Module.NOTICE, Module.PAGE, Module.PROPERTY, Module.COMPLAIN_REPAIR, Module.HOUSE_KEEPING, Module.HOUSE_RENT_SALE, Module.MY_INFO);

    @Override
    public void showHour(List<WeatherHour.Hour> list) {

    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView textView;
    @BindView(R.id.list_main)
    RecyclerView recyclerView;
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
    public void showDay(List<WeatherDay.Day> list) {

    }

    @Override
    public void showNow(WeatherNow.Now hfWeather) {
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
        weatherPresenter.Now();


        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        textView.setText("便民社区");
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        final MainAdapter mainAdapter = new MainAdapter(moduleList);
        mainAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                final Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(SystemMainActivity.this, NoticeActivity.class);
                        break;
                    case 1:
                        intent.setClass(SystemMainActivity.this, CommunityPageActivity.class);
                        break;
                    case 2:
                        intent.setClass(SystemMainActivity.this, PropertyActivity.class);
                        intent.putExtra("houseId", user.getHouse_id());
                        intent.putExtra("nickname", user.getNickname());
                        break;
                    case 3:
                        intent.setClass(SystemMainActivity.this, ComplainRepairActivity.class);
                        intent.putExtra("username", user.getUsername());
                        break;
                    case 4:
                        intent.setClass(SystemMainActivity.this, HouseKeepingActivity.class);
                        break;
                    case 5:
                        intent.setClass(SystemMainActivity.this, HouseRentSaleActivity.class);
                        final Bundle bundle = new Bundle();
                        bundle.putString("username", user.getUsername());
                        intent.putExtra("bundle", bundle);
                        break;
                    case 6:
                        intent.setClass(SystemMainActivity.this, MyInfoActivity.class);
                        intent.putExtra("user", user);
                        break;
                }
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
            }
        });
        recyclerView.setAdapter(mainAdapter);
    }

    @OnClick(R.id.weather)
    void setRelativeLayout() {
        final Intent intent = new Intent(this, WeatherActivity.class);
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
