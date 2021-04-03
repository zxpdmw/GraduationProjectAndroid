package top.zxpdmw.graduationproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.SneakyThrows;
import okhttp3.Response;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.ui.adapter.SystemMainAdapter;
import top.zxpdmw.graduationproject.bean.Module;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.ToastUtil;


public class SystemMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "zwy----SystemActivity";
    private List<Module> moduleList = null;
    private Context context;
    private SystemMainAdapter adapter = null;
    @BindView(R.id.list_main)
    ListView listView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Intent intent;
    private User user;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemmain);
        ButterKnife.bind(this);
        init();
        setSupportActionBar(toolbar);
        toolbar.setTitle("便民社区");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        adapter = new SystemMainAdapter(moduleList, context);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void init() throws InterruptedException {
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        context = SystemMainActivity.this;;
        moduleList = Arrays.asList(Module.NOTICE, Module.PAGE, Module.PROPERTY, Module.COMPLAIN_REPAIR, Module.HOUSE_KEEPING, Module.HOUSE_RENT_SALE, Module.MY_INFO);
        TimeUnit.SECONDS.sleep(1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            final Intent intent = new Intent(SystemMainActivity.this, NoticeActivity.class);
            startActivity(intent);
        } else if (position == 1) {
            Intent intent = new Intent(SystemMainActivity.this, CommunityPageActivity.class);
            startActivity(intent);

        } else if (position == 2) {
                Intent intent = new Intent(SystemMainActivity.this, PropertyActivity.class);
                intent.putExtra("houseId", user.getHouse_id());
                intent.putExtra("nickname", user.getNickname());
                startActivity(intent);
        } else if (position == 3) {
            Intent intent = new Intent(SystemMainActivity.this, ComplainRepairActivity.class);
            startActivity(intent);
        } else if (position == 4) {
            Intent intent = new Intent(SystemMainActivity.this, HouseKeepingActivity.class);
            startActivity(intent);
        } else if (position == 5) {
            Intent intent = new Intent(SystemMainActivity.this, HouseRentSaleActivity.class);
            final Bundle bundle = new Bundle();
            bundle.putString("username",user.getUsername());
            Log.d(TAG, user.toString());
            intent.putExtra("bundle",bundle);
            startActivity(intent);
        } else if (position == 6) {
            Intent intent = new Intent(SystemMainActivity.this, MyInfoActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
    }

//    private void getRecommendNotice() {
//        new Thread(() -> {
//            Response response = HttpUtil.Get(ConstUtil.NOTICE_RECOMMEND);
//            try {
//                JSONObject jsonObject = new JSONObject(response.body().string());
//                code = jsonObject.getString("code");
//                if (code.equals("666")) {
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//                    data = jsonArray.toString();
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//    private void getPropertyBalance() {
//        new Thread(() -> {
//            Response get = HttpUtil.Get(ConstUtil.PROPERTY_GET + "?houseId=" + user.getHouse_id());
//            try {
//                JSONObject jsonObject = new JSONObject(get.body().string());
//                if (jsonObject.getString("code").equals("666")) {
//                    propertyBalance = jsonObject.getString("data");
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//    }

}
