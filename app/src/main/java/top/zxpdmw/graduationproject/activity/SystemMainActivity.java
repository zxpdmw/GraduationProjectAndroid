package top.zxpdmw.graduationproject.activity;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.adapter.SystemMainAdapter;
import top.zxpdmw.graduationproject.model.Module;
import top.zxpdmw.graduationproject.model.User;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.HttpUtil;
import top.zxpdmw.graduationproject.util.JsonUtil;
import top.zxpdmw.graduationproject.util.ToastUtil;


public class SystemMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<Module> moduleList = null;
    private String code;
    private String data;
    private Context context;
    private SystemMainAdapter adapter = null;
    private ListView listView;
    private Toolbar toolbar;
    private Intent intent;
    private String houseId;
    private String username;
    private String propertyBalance;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemmain);
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

    public void init() {
        intent=getIntent();
        houseId=intent.getStringExtra("houseId");
        username=intent.getStringExtra("username");
        toolbar=findViewById(R.id.toolbar);
        context = SystemMainActivity.this;
        listView = findViewById(R.id.list_main);
        moduleList=Arrays.asList(Module.NOTICE,Module.PAGE,Module.PROPERTY,Module.COMPLAIN,Module.REPAIR,Module.HOUSE_KEEPING,Module.HOUSE_RENT_SALE,Module.MY_INFO);
        getUserInfo();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position==0){
                new Thread(()->{
                    getRecommendNotice();
                    if (data!=""){
                        Intent intent = new Intent(SystemMainActivity.this, NoticeActivity.class);
                        intent.putExtra("data",data);
                        startActivity(intent);
                    }else{
                        runOnUiThread(() -> {
                            new ToastUtil(this,ConstUtil.SYSTEM_EXCEPTION).show(500);
                        });
                    }
                }).start();

        }else if (position==2){
            new Thread(()->{
                getPropertyBalance();
                if (propertyBalance!=""){
                    System.out.println(propertyBalance);
                    Intent intent = new Intent(SystemMainActivity.this,PropertyActivity.class);
                    intent.putExtra("property",propertyBalance);
                    intent.putExtra("houseId",houseId);
                    intent.putExtra("nickname",user.getNickname());
                    startActivity(intent);
                }else{
                    runOnUiThread(() -> {
                        new ToastUtil(this,ConstUtil.SYSTEM_EXCEPTION).show(500);
                    });
                }
            }).start();

        }
    }

    private void getRecommendNotice() {
            Response response = HttpUtil.Get(ConstUtil.NOTICE_RECOMMEND);
            try {
                JSONObject jsonObject = new JSONObject(response.body().string());
                code = jsonObject.getString("code");
                if (code.equals("666")){
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    data=jsonArray.toString();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void getPropertyBalance(){
        Response get = HttpUtil.Get(ConstUtil.PROPERTY_GET + "?houseId=" + houseId);
        try {
            JSONObject jsonObject=new JSONObject(get.body().string());
            if (jsonObject.getString("code").equals("666")){
                propertyBalance=jsonObject.getString("data");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo(){
        new Thread(()->{
            Response get = HttpUtil.Get(ConstUtil.USER_GET_INFO + "?username=" + username);
            try {
                JSONObject jsonObject=new JSONObject(get.body().string());
                if (jsonObject.getString("code").equals("666")){
                    user=JsonUtil.GSON.fromJson(jsonObject.getString("data"), User.class);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
