package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.presenter.HouseRentSalePresenter;
import top.zxpdmw.graduationproject.presenter.contract.HouseRentSaleContract;
import top.zxpdmw.graduationproject.ui.view.HouseTypePopupWindow;
import top.zxpdmw.graduationproject.ui.view.PhotoPopupWindow;

public class PublishHouseActivity extends AppCompatActivity implements HouseRentSaleContract.View {
    @BindView(R.id.publish)
    Button publish;
    @BindView(R.id.city_xinxi)
    EditText city;
    @BindView(R.id.juti_xinxi)
    EditText address;
    @BindView(R.id.price)
    EditText price;
    @BindView(R.id.phone_xinxi)
    EditText phone;
    @BindView(R.id.xiaoqu_xinxi)
    EditText xiaoqu;
    @BindView(R.id.zufangleixing)
    TextView type;
    HouseTypePopupWindow houseTypePopupWindow;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.xiangqing_xinxi)
    TextView message;
    @BindView(R.id.chaoxiang_xinxi)
    EditText chaoxiang;
    @BindView(R.id.louceng_xinxi)
    EditText louceng;
    @BindView(R.id.ruzhu_xinxi)
    EditText ruzhu;
    HouseRentSalePresenter houseRentSalePresenter = new HouseRentSalePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_house);
        ButterKnife.bind(this);
        initToolBar();
    }

    void initToolBar() {
        toolbar.setTitle("");
        toolbar_title.setText("房源发布");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });
    }

    @OnClick(R.id.zufangleixing)
    void setType() {
        houseTypePopupWindow = new HouseTypePopupWindow(PublishHouseActivity.this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.setText("合租");
                houseTypePopupWindow.dismiss();

            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.setText("整租");
                houseTypePopupWindow.dismiss();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.setText("卖房");
                houseTypePopupWindow.dismiss();
            }
        });
        View rootView = LayoutInflater.from(PublishHouseActivity.this).inflate(R.layout.activity_publish_house, null);
        houseTypePopupWindow.showAtLocation(rootView,
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void back() {
        onBackPressed();
    }

    @Override
    public void updateData() {

    }

    @OnClick(R.id.publish)
    void setPublish() {
        HouseRentSale houseRentSale = new HouseRentSale();
        if (city.getText().toString().equals("")) {
            ToastUtils.show("请输入城市");
            return;
        }

        if (xiaoqu.getText().toString().equals("")) {
            ToastUtils.show("请输入小区");
            return;
        }
        if (address.getText().toString().equals("")) {
            ToastUtils.show("请输入地址");
            return;
        }
        houseRentSale.setAddress(xiaoqu.getText().toString() + "·" + address.getText().toString());
        if (price.getText().toString().equals("")) {
            ToastUtils.show("请输入租金");
            return;
        }
        houseRentSale.setPrice(price.getText().toString());
        if (type.getText().toString().equals("卖房")) {
            houseRentSale.setT("sale");
        } else {
            houseRentSale.setT("rent");
        }
        if (message.getText().toString().equals("")) {
            ToastUtils.show("请输入简介");
            return;
        }
        houseRentSale.setMessage(message.getText().toString());
        if (chaoxiang.getText().toString().equals("")) {
            ToastUtils.show("请输入房屋朝向");
            return;
        }
        houseRentSale.setOrientation(chaoxiang.getText().toString());
        if (louceng.getText().toString().equals("")) {
            ToastUtils.show("请输入楼层");
            return;
        }
        houseRentSale.setFloor(louceng.getText().toString());
        if (ruzhu.getText().toString().equals("")) {
            ToastUtils.show("请输入入住信息");
            return;
        }
        houseRentSale.setRuzhu(ruzhu.getText().toString());
        if (phone.getText().toString().equals("")) {
            ToastUtils.show("请输入电话");
            return;
        }
        houseRentSale.setPhone(phone.getText().toString());
        houseRentSale.setUsername(getIntent().getStringExtra("username"));
        houseRentSalePresenter.HousePublish(houseRentSale);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }

    @Override
    public void showList(List<HouseRentSale> list) {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void add(HouseRentSale houseRentSale) {

    }

    @Override
    public void delete(HouseRentSale houseRentSale) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void jumpView(AppCompatActivity activity) {

    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.show(msg);
    }
}