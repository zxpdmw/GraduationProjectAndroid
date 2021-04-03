package top.zxpdmw.graduationproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.presenter.UserPresenter;
import top.zxpdmw.graduationproject.presenter.contract.UserContract;
import top.zxpdmw.graduationproject.util.ToastUtil;


public class LoginActivity extends AppCompatActivity implements UserContract.View {
    private static final String TAG = "zwy-LoginActivity";
    @BindView(R.id.new_user)
    TextView newUser;
    @BindView(R.id.login_button)
    Button login;
    @BindView(R.id.login_username)
    EditText username;
    @BindView(R.id.login_password)
    EditText password;

    private User user;
    private UserPresenter presenter = new UserPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.new_user)
    void newUser(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_button)
    void loginUser(){
        presenter.LoginUser(username.getText().toString(), password.getText().toString());
    }

    @Override
    public void showError(String msg) {
        new ToastUtil(this, msg).show(500);
    }

    @Override
    public void LoadUser(User user) {
        this.user=user;
    }

    @Override
    public void jumpView(AppCompatActivity activity) {
        final Intent intent = new Intent(LoginActivity.this, SystemMainActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    @Override
    public void showMsg(String message) {
        new ToastUtil(this, message).show(500);
    }
}