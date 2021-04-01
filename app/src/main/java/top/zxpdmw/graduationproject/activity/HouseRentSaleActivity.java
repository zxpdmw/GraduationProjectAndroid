package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;
import okhttp3.Response;
import top.zxpdmw.graduationproject.R;

import top.zxpdmw.graduationproject.adapter.HouseRentSaleAdapter;
import top.zxpdmw.graduationproject.fragment.HouseListFragment;
import top.zxpdmw.graduationproject.model.HouseRentSale;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.HttpUtil;
import top.zxpdmw.graduationproject.util.JsonUtil;

public class HouseRentSaleActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    //UI Object
    private TextView house_rent;
    private TextView house_sale;
    private TextView house_my;
    private FrameLayout ly_content;
    private String listRent;
    private String listSale;
    private String listMy;

    private Intent intent;

    //Fragment Object
    private HouseListFragment rent, sale, my;
    private FragmentManager fManager;


    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_rent_sale);
        init();
        fManager = getSupportFragmentManager();
        TimeUnit.SECONDS.sleep(1);
        house_rent.performClick();   //模拟一次点击，既进去后选择第一项
    }

    @SneakyThrows
    private void init() {
        getMyHouseInfo();
        getSaleHouseInfo();
        getRentHouseInfo();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("房 屋 租 售");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        house_rent = findViewById(R.id.house_rent);
        house_sale = findViewById(R.id.house_sale);
        house_my = findViewById(R.id.house_my);
        ly_content = findViewById(R.id.ly_content);

        house_sale.setOnClickListener(this);
        house_rent.setOnClickListener(this);
        house_my.setOnClickListener(this);
    }


    //重置所有文本的选中状态
    private void setSelected() {
        getMyHouseInfo();
        getSaleHouseInfo();
        getRentHouseInfo();
        house_rent.setSelected(false);
        house_my.setSelected(false);
        house_sale.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (rent != null) fragmentTransaction.hide(rent);
        if (sale != null) fragmentTransaction.hide(sale);
        if (my != null) fragmentTransaction.hide(my);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()) {
            case R.id.house_rent:
                setSelected();
                house_rent.setSelected(true);
                if (rent == null) {
                    rent = HouseListFragment.newInstance(listRent);
                    fTransaction.add(R.id.ly_content, rent);
                } else {
                    getRentHouseInfo();
                    sale.setListRent(listRent);
                    fTransaction.show(rent);
                }
                break;
            case R.id.house_sale:
                setSelected();
                house_sale.setSelected(true);
                if (sale == null) {
                    sale = HouseListFragment.newInstance(listSale);
                    fTransaction.add(R.id.ly_content, sale);
                } else {
                    getSaleHouseInfo();
                    sale.setListSale(listSale);
                    fTransaction.show(sale);
                }
                break;
            case R.id.house_my:
                setSelected();
                house_my.setSelected(true);
                if (my == null) {
                    my = HouseListFragment.newInstance(listMy);
                    fTransaction.add(R.id.ly_content, my);
                } else {
                    getRentHouseInfo();
                    my.setListMy(listMy);
                    fTransaction.show(my);
                }
                break;
        }
        fTransaction.commit();
    }

    private void getSaleHouseInfo() {
        new Thread(() -> {
            Response get = HttpUtil.Get(ConstUtil.HOUSE_SALE);
            try {
                JSONObject jsonObject = new JSONObject(Objects.requireNonNull(get.body()).string());
                if (jsonObject.getString("code").equals("666")) {
                    listSale =jsonObject.getJSONArray("data").toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void getRentHouseInfo() {
        new Thread(() -> {
            Response get = HttpUtil.Get(ConstUtil.HOUSE_RENT);
            try {
                JSONObject jsonObject = new JSONObject(Objects.requireNonNull(get.body()).string());
                if (jsonObject.getString("code").equals("666")) {
                    listRent = jsonObject.getJSONArray("data").toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    private void getMyHouseInfo() {
        new Thread(() -> {
            Response get = HttpUtil.Get(ConstUtil.HOUSE_RENT);
            try {
                JSONObject jsonObject = new JSONObject(Objects.requireNonNull(get.body()).string());
                if (jsonObject.getString("code").equals("666")) {
                    listMy=jsonObject.getJSONArray("data").toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}