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

import okhttp3.Response;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.adapter.SystemMainAdapter;
import top.zxpdmw.graduationproject.model.Module;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.HttpUtil;
import top.zxpdmw.graduationproject.util.ToastUtil;


public class SystemMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<Module> moduleList = null;
    private String code;
    private String data;
    private Context context;
    private SystemMainAdapter adapter = null;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemmain);
        init();
        adapter = new SystemMainAdapter(moduleList, context);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void init() {
        context = SystemMainActivity.this;
        listView = findViewById(R.id.list_main);
        moduleList=Arrays.asList(Module.NOTICE,Module.PAGE,Module.PROPERTY,Module.COMPLAIN,Module.REPAIR,Module.HOUSE_KEEPING,Module.HOUSE_RENT_SALE,Module.MY_INFO);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        new ToastUtil(this,""+position).show(1000);
        switch (position){
            case 0:{
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
            }
            case 1:{
                Intent intent = new Intent(SystemMainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        }
//        if (position==0){
//                new Thread(()->{
//                    getRecommendNotice();
//                    if (data!=""){
//                        Intent intent = new Intent(SystemMainActivity.this, NoticeActivity.class);
//                        intent.putExtra("data",data);
//                        startActivity(intent);
//                    }else{
//                        runOnUiThread(() -> {
//                            new ToastUtil(this,ConstUtil.SYSTEM_EXCEPTION).show(500);
//                        });
//                    }
//                }).start();
//
//        }else{
//
//        }
    }

    private void getRecommendNotice() {
            Response response = HttpUtil.GetNoParam(ConstUtil.NOTICE_RECOMMEND);
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
}
