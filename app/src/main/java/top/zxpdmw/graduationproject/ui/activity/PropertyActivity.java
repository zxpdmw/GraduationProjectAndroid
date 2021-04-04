package top.zxpdmw.graduationproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.presenter.PropertyPresenter;
import top.zxpdmw.graduationproject.presenter.contract.PropertyContract;
import top.zxpdmw.graduationproject.util.ToastUtil;

public class PropertyActivity extends AppCompatActivity implements PropertyContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.addProperty)
    Button button;
    @BindView(R.id.property_nickname)
    TextView nickname;
    @BindView(R.id.property_balance)
    TextView balance;
    @BindView(R.id.property_add)
    EditText editText;
    private Intent intent;
    private String houseId;
    PropertyPresenter presenter=new PropertyPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.addProperty)
    public void addProperty(){
        String add = editText.getText().toString();
        if (add.equals("")) {
            new ToastUtil(this, "请输入缴纳金额").show(500);
        } else {
            presenter.AddProperty(houseId,editText.getText().toString());
        }
    }


    private void init() {
        intent = getIntent();
        houseId = intent.getStringExtra("houseId");
        nickname.setText(intent.getStringExtra("nickname"));
        presenter.GetProperty(houseId);
        toolbar.setTitle("物 业 费");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void LoadData(String value) {
        balance.setText(value);
    }

    @Override
    public void showError(String msg) {
        new ToastUtil(this,msg).show(500);
    }

    @Override
    public void jumpView(AppCompatActivity activity) {

    }

    @Override
    public void showMsg(String msg) {
        new ToastUtil(this,msg).show(500);
    }
}
