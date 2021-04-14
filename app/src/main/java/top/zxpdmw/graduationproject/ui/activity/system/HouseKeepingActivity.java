package top.zxpdmw.graduationproject.ui.activity.system;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.ComplainRepair;
import top.zxpdmw.graduationproject.bean.HouseKeeping;
import top.zxpdmw.graduationproject.presenter.HouseKeepingPresenter;
import top.zxpdmw.graduationproject.presenter.contract.HouseKeepingContract;
import top.zxpdmw.graduationproject.ui.adapter.ComplainRepairAdapter;
import top.zxpdmw.graduationproject.ui.adapter.HouseKeepingAdapter;

public class HouseKeepingActivity extends AppCompatActivity implements HouseKeepingContract.View {
    @BindView(R.id.toolbar)
     Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.add_house_keeping) ImageView imageView;
    @BindView(R.id.list_house_keeping)
    SwipeRecyclerView swipeRecyclerView;
    HouseKeepingAdapter houseKeepingAdapter;

    @Override
    public void delete(HouseKeeping houseKeeping) {
        houseKeepingAdapter.delete(houseKeeping);
    }

    @Override
    public void add(HouseKeeping houseKeeping) {
        houseKeepingAdapter.add(houseKeeping);
    }

    @Override
    public void cancel() {
        show.cancel();
    }

    HouseKeepingPresenter houseKeepingPresenter=new HouseKeepingPresenter(this);
    MaterialDialog.Builder builder;
    MaterialDialog show;
    List<HouseKeeping> list;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_keeping);
        ButterKnife.bind(this);
        swipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        intent=getIntent();
        houseKeepingPresenter.GetHouseKeeping(intent.getStringExtra("username"));
        builder=new MaterialDialog.Builder(this);
        initToolbar();
    }

    @OnClick(R.id.add_house_keeping)
    void addHouseKeeping(){
        final View view = View.inflate(this, R.layout.add_house_keeping, null);
        Button cancel = view.findViewById(R.id.cancel);
        Button add = view.findViewById(R.id.add);
        show = builder.customView(view, false)
                .show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.cancel();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText address = view.findViewById(R.id.address);
                EditText phone = view.findViewById(R.id.phone);
                EditText message = view.findViewById(R.id.message);
                final HouseKeeping houseKeeping = new HouseKeeping();
                houseKeeping.setHk_type(message.getText().toString());
                houseKeeping.setAddress(address.getText().toString());
                houseKeeping.setPhone(phone.getText().toString());
                houseKeeping.setUsername(intent.getStringExtra("username"));
                houseKeepingPresenter.AddHouseKeeping(houseKeeping);
            }
        });


    }

    private void initToolbar(){
        toolbar.setTitle("");
        toolbar_title.setText("家政服务");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });
    }

    @Override
    public void showList(List<HouseKeeping> list) {
        this.list=list;
        // 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext()); // 各种文字和图标属性设置。
                deleteItem.setText("删除");
                deleteItem.setTextSize(25);
                deleteItem.setTextColor(getResources().getColor(R.color.white));
                deleteItem.setHeight(MATCH_PARENT);
                deleteItem.setBackgroundColor(getResources().getColor(R.color.red));
                deleteItem.setWidth(350);
                rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。
            }
        };
        swipeRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator);

        // 菜单点击监听。
        OnItemMenuClickListener mItemMenuClickListener = new OnItemMenuClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();
                houseKeepingPresenter.DeleteHouseKeeping(list.get(position));
            }
        };

        swipeRecyclerView.setOnItemMenuClickListener(mItemMenuClickListener);

        swipeRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int adapterPosition) {
                final View detail = View.inflate(getApplicationContext(), R.layout.detail_house_keeping, null);
                TextView hk_type=detail.findViewById(R.id.hk_type);
                TextView hk_address=detail.findViewById(R.id.hk_address);
                TextView hk_phone=detail.findViewById(R.id.hk_phone);
                TextView hk_status=detail.findViewById(R.id.hk_status);
                hk_type.setText(list.get(adapterPosition).getHk_type());
                hk_address.setText(list.get(adapterPosition).getAddress());
                hk_phone.setText(list.get(adapterPosition).getPhone());
                if (list.get(adapterPosition).getStatus().equals("已处理")){
                    hk_status.setTextColor(getResources().getColor(R.color.green));
                    hk_status.setText("已处理");
                }
                show=builder.customView(detail,false).show();
            }
        });

         houseKeepingAdapter= new HouseKeepingAdapter(list);
        swipeRecyclerView.setAdapter(houseKeepingAdapter);
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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }
}