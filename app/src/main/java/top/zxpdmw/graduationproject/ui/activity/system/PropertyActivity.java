package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.presenter.PropertyPresenter;
import top.zxpdmw.graduationproject.presenter.contract.PropertyContract;

public class PropertyActivity extends AppCompatActivity implements PropertyContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.addProperty)
    Button button;
    @BindView(R.id.huhao_xinxi)
    TextView huhao;
    @BindView(R.id.huming_xinxi)
    TextView nickname;
    @BindView(R.id.property_yue)
    TextView balance;
    @BindView(R.id.edit)
    EditText editText;
    @BindView(R.id.property_100)
    Button p100;
    @BindView(R.id.property_200)
    Button p200;
    @BindView(R.id.property_300)
    Button p300;
    @BindView(R.id.textView)
    TextView qianfei;

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
            ToastUtils.show("请输入缴纳金额");
        } else {
            presenter.AddProperty(houseId,editText.getText().toString());
        }
    }

    @OnClick(R.id.property_100)
    void set100(){
        editText.setText("100");
    }

    @OnClick(R.id.property_300)
    void setP300(){
        editText.setText("300");
    }

    @OnClick(R.id.property_200)
    void setP200(){
        editText.setText("200");
    }



    private void init() {
        intent = getIntent();
        houseId = intent.getStringExtra("houseId");
        nickname.setText(intent.getStringExtra("nickname"));
        presenter.GetProperty(houseId);
        toolbar_title.setText("物业费");
        toolbar.setTitle("");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });
    }

    @Override
    public void LoadData(String value) {
        if (Integer.parseInt(value)<0){
            qianfei.setText("已欠费!请及时缴费");
            qianfei.setTextColor(getResources().getColor(R.color.red));
        }
        balance.setText(value);
    }

    @Override
    public void checkProperty() {
        if (Integer.parseInt(balance.getText().toString())>0){
            qianfei.setTextColor(getResources().getColor(R.color.black));
            qianfei.setText("暂未查询到欠费");
       }
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void jumpView(AppCompatActivity activity) {

    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }
}