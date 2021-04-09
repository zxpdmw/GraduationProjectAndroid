package top.zxpdmw.graduationproject.ui.activity.system;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.HouseKeeping;
import top.zxpdmw.graduationproject.presenter.ComplainRepairPresenter;
import top.zxpdmw.graduationproject.presenter.contract.ComplainRepairContract;
import top.zxpdmw.graduationproject.ui.adapter.ComplainRepairAdapter;
import top.zxpdmw.graduationproject.bean.ComplainRepair;
import top.zxpdmw.graduationproject.ui.adapter.ItemClickListener;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;

import java.util.List;

public class ComplainRepairActivity extends AppCompatActivity implements ComplainRepairContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.list_complain_repair)
    RecyclerView recyclerView;
    ComplainRepairPresenter complainRepairPresenter=new ComplainRepairPresenter(this);
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_repair);
        ButterKnife.bind(this);
        init();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        complainRepairPresenter.GetComplainRepair(intent.getStringExtra("username"));
    }

    private void init() {
        intent=getIntent();
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

    private void initListView(List<ComplainRepair> complainRepairs){
        final ComplainRepairAdapter complainRepairAdapter = new ComplainRepairAdapter(complainRepairs);
        complainRepairAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                ToastUtils.show("zzz");
            }
        });
        recyclerView.setAdapter(complainRepairAdapter);
    }

    @OnClick(R.id.add_complain_repair)
    void addComplainRepair(){
        ContextThemeWrapper contextThemeWrapper =
                new ContextThemeWrapper(ComplainRepairActivity.this, R.style.dialog);
        //实例化布局
        View view = LayoutInflater.from(this).inflate(R.layout.activity_add_complain_repair,null);
        EditText address = view.findViewById(R.id.address);
        EditText phone=view.findViewById(R.id.phone);
        EditText type=view.findViewById(R.id.message);
        final HouseKeeping houseKeeping = new HouseKeeping();
        houseKeeping.setAddress(address.getText().toString());
        houseKeeping.setPhone(phone.getText().toString());
        houseKeeping.setHk_type(type.getText().toString());
        //找到并对自定义布局中的控件进行操作的示例

        //创建对话框
        AlertDialog dialog = new AlertDialog.Builder(contextThemeWrapper).create();
//        dialog.setIcon(R.drawable.touxiang);//设置图标
//        dialog.setTitle("添加投诉报修");//设置标题
        dialog.setView(view);//添加布局
        //设置按键
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                complainRepairPresenter.AddComplainRepair(null);
            }
        });
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void showList(List<ComplainRepair> list) {
        initListView(list);
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