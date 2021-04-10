package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.presenter.UserPresenter;
import top.zxpdmw.graduationproject.presenter.contract.UserContract;

public class EditInfoActivity extends AppCompatActivity implements UserContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.toolbar_save)
    TextView save;
    @BindView(R.id.edit)
    EditText editText;
    String data, title;
    String username;
    UserPresenter userPresenter = new UserPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        data = intent.getStringExtra("data");
        if (data.equals("")){
            editText.setHint("请输入新密码");
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editText.setHintTextColor(getResources().getColor(R.color.hui));
        }
        title = intent.getStringExtra("title");
        username = intent.getStringExtra("username");
        toolbar_title.setText(title);
        toolbar.setTitle("");
        save.setClickable(false);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });
        editText.setText(data);
    }

    @OnTextChanged(value = R.id.edit, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void setSave() {
        if (!editText.getText().toString().equals(data)) {
            save.setTextColor(Color.BLACK);
            save.setClickable(true);
        }
        if (editText.getText().toString().equals("")) {
            save.setClickable(false);
            save.setTextColor(getResources().getColor(R.color.hui));
        }
    }

    @OnClick({R.id.toolbar_save})
    void Save() {
        if (toolbar_title.getText().toString().equals("昵称")) {
            userPresenter.EditNickname(username, editText.getText().toString());
        } else if (toolbar_title.getText().toString().equals("地址")) {
            userPresenter.EditAddress(username, editText.getText().toString());

        } else if (toolbar_title.getText().toString().equals("电话")) {
            userPresenter.EditPhone(username, editText.getText().toString());

        } else if (toolbar_title.getText().toString().equals("房屋号")) {
            userPresenter.EditHouse(username, editText.getText().toString());

        }else if (toolbar_title.getText().toString().equals("修改密码")){
            userPresenter.EditPassword(username,editText.getText().toString());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }

    @Override
    public void LoadUser(User user) {

    }

    @Override
    public void switchFragment() {

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
}