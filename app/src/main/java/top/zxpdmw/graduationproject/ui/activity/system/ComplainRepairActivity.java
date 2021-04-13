package top.zxpdmw.graduationproject.ui.activity.system;


import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.presenter.ComplainRepairPresenter;
import top.zxpdmw.graduationproject.presenter.contract.ComplainRepairContract;
import top.zxpdmw.graduationproject.ui.adapter.ComplainRepairAdapter;
import top.zxpdmw.graduationproject.bean.ComplainRepair;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ComplainRepairActivity extends AppCompatActivity implements ComplainRepairContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.list_complain_repair)
    SwipeRecyclerView recyclerView;
    ComplainRepairPresenter complainRepairPresenter = new ComplainRepairPresenter(this);
    Intent intent;
    MaterialDialog.Builder builder;
    MaterialDialog show;
    List<ComplainRepair> list;
    ComplainRepairAdapter complainRepairAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_repair);
        ButterKnife.bind(this);
        builder = new MaterialDialog.Builder(this);
        init();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        complainRepairPresenter.GetComplainRepair(intent.getStringExtra("username"));
    }

    private void init() {
        intent = getIntent();
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        toolbar.setTitle("");
        toolbar_title.setText("投诉保修");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });
    }

    private void initListView(List<ComplainRepair> complainRepairs) {

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

        recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);

        // 菜单点击监听。
        OnItemMenuClickListener mItemMenuClickListener = new OnItemMenuClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();
               complainRepairPresenter.DeleteComplainRepair(list.get(position));
            }
        };

        recyclerView.setOnItemMenuClickListener(mItemMenuClickListener);

        recyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int adapterPosition) {
                Intent intent = new Intent(ComplainRepairActivity.this, DetailComplainRepairActivity.class);
                intent.putExtra("cr", list.get(adapterPosition));
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
            }
        });

        complainRepairAdapter = new ComplainRepairAdapter(complainRepairs);
        recyclerView.setAdapter(complainRepairAdapter);
    }

    @OnClick(R.id.add_complain_repair)
    void addComplainRepair() {
        final View view = View.inflate(this, R.layout.add_complain_repair, null);

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
                RadioGroup radioGroup = view.findViewById(R.id.radio_group);
                RadioButton radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
                EditText address = view.findViewById(R.id.address);
                EditText phone = view.findViewById(R.id.phone);
                EditText message = view.findViewById(R.id.message);
                final ComplainRepair complainRepair = new ComplainRepair();
                complainRepair.setAddress(address.getText().toString());
                complainRepair.setPhone(phone.getText().toString());
                complainRepair.setCr_type(radioButton.getText().toString());
                complainRepair.setMessage(message.getText().toString());
                complainRepair.setUsername(intent.getStringExtra("username"));
                complainRepairPresenter.AddComplainRepair(complainRepair);
            }
        });
    }

    @Override
    public void showList(List<ComplainRepair> list) {
        this.list = list;
        initListView(list);
    }

    @Override
    public void cancel() {
        show.cancel();
    }

    @Override
    public void add(ComplainRepair complainRepair) {
        complainRepairAdapter.add(complainRepair);
        complainRepairAdapter.notifyItemChanged(0);
    }

    @Override
    public void delete(ComplainRepair complainRepair) {
        complainRepairAdapter.delete(complainRepair);
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
}