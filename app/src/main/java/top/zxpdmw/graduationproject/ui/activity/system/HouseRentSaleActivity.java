package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.SneakyThrows;
import top.zxpdmw.graduationproject.R;

import top.zxpdmw.graduationproject.ui.fragment.system.HouseListFragment;

public class HouseRentSaleActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
     Toolbar toolbar;
    @BindView(R.id.house_rent)
     TextView house_rent;
    @BindView(R.id.house_sale)
     TextView house_sale;
    @BindView(R.id.house_my)
     TextView house_my;
    @BindView(R.id.ly_content)
     FrameLayout ly_content;

    Intent intent;
    Bundle bundle;

    private HouseListFragment rent, sale, my;
    private FragmentManager fManager;


    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_rent_sale);
        ButterKnife.bind(this);
        init();
        fManager = getSupportFragmentManager();
        house_rent.performClick();   //模拟一次点击，既进去后选择第一项
    }

    @SneakyThrows
    private void init() {
        intent=getIntent();
        bundle=intent.getBundleExtra("bundle");
        toolbar.setTitle("房 屋 租 售");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });

        house_sale.setOnClickListener(this);
        house_rent.setOnClickListener(this);
        house_my.setOnClickListener(this);
    }


    //重置所有文本的选中状态
    private void setSelected() {
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
                    rent = HouseListFragment.newInstance(1,fManager);
                    fTransaction.add(R.id.ly_content, rent);
                } else {
                    fTransaction.show(rent);
                }
                break;
            case R.id.house_sale:
                setSelected();
                house_sale.setSelected(true);
                if (sale == null) {
                    sale = HouseListFragment.newInstance(2,fManager);
                    fTransaction.add(R.id.ly_content, sale);
                } else {
                    fTransaction.show(sale);
                }
                break;
            case R.id.house_my:
                setSelected();
                house_my.setSelected(true);
                if (my == null) {
                    my = HouseListFragment.newInstance(3,bundle.getString("username"));
                    fTransaction.add(R.id.ly_content, my);
                } else {
                    fTransaction.show(my);
                }
                break;
        }
        fTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }
}