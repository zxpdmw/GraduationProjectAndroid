package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.ui.view.LocalImageHolderView;

public class DetailHouseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    List<Integer> list = new ArrayList<>();
    @BindView(R.id.house_price)
    TextView price;
    @BindView(R.id.house_message)
    TextView message;
    @BindView(R.id.chaoxiang_xinxi)
    TextView chaoxiang;
    @BindView(R.id.louceng_xinxi)
    TextView louceng;
    @BindView(R.id.ruzhu_xinxi)
    TextView ruzhu;
    @BindView(R.id.mianji_xinxi)
    TextView mianji;
    @BindView(R.id.xiaoqu_xinxi)
    TextView xiaoqu;
    @BindView(R.id.dianhua_xinxi)
    TextView dianhua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_house);
        ButterKnife.bind(this);
        initToolbar();
        list.add(R.drawable.one);
        list.add(R.drawable.two);
        list.add(R.drawable.three);
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LocalImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.view_pager;
            }
        }, list) //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(2000)
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        HouseRentSale house= (HouseRentSale) getIntent().getSerializableExtra("house");
        xiaoqu.setText(house.getAddress());
        dianhua.setText(house.getPhone());
        if (house.getT().equals("sale")){
            price.setText(house.getPrice() + "元/平");
        }else{
            price.setText(house.getPrice()+"元/月");
        }
        chaoxiang.setText(house.getOrientation());
        louceng.setText(house.getFloor());
        mianji.setText(house.getArea()+"㎡");
        ruzhu.setText(house.getRuzhu());
        message.setText(house.getMessage());
    }

    private void initToolbar(){
        toolbar.setTitle("");
        toolbar_title.setText("房屋信息");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }
}