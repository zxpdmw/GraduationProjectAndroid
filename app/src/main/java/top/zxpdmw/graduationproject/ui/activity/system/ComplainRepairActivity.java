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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.strategy.impl.MaterialDialogStrategy;

import java.time.temporal.ValueRange;
import java.util.List;

public class ComplainRepairActivity extends AppCompatActivity implements ComplainRepairContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.list_complain_repair)
    RecyclerView recyclerView;
    ComplainRepairPresenter complainRepairPresenter = new ComplainRepairPresenter(this);
    Intent intent;
    MaterialDialog.Builder builder;
    MaterialDialog show;
    List<ComplainRepair> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_repair);
        ButterKnife.bind(this);
        builder=new MaterialDialog.Builder(this);
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
        final ComplainRepairAdapter complainRepairAdapter = new ComplainRepairAdapter(complainRepairs);
        complainRepairAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Intent intent = new Intent(ComplainRepairActivity.this, DetailComplainRepairActivity.class);
                intent.putExtra("cr", list.get(position));
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
            }
        });
        recyclerView.setAdapter(complainRepairAdapter);
    }

    @OnClick(R.id.add_complain_repair)
    void addComplainRepair() {
        final View view = View.inflate(this, R.layout.activity_add_complain_repair, null);

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
                complainRepairPresenter.GetComplainRepair(intent.getStringExtra("username"));
            }
        });
    }

    @Override
    public void showList(List<ComplainRepair> list) {
        this.list=list;
        initListView(list);
    }

    @Override
    public void cancel() {
        show.cancel();
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