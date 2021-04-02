package top.zxpdmw.graduationproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import okhttp3.Response;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.presenter.UserPresenter;
import top.zxpdmw.graduationproject.presenter.contract.UserContract;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.HttpUtil;
import top.zxpdmw.graduationproject.util.ToastUtil;

public class RegisterActivity extends AppCompatActivity implements UserContract.View {
    private static final String TAG = "zwy---------Register";
    @BindView(R.id.register_button)
    Button register;
    @BindView(R.id.register_username)
    EditText username;
    @BindView(R.id.register_password)
    EditText password;
    @BindView(R.id.register_nickname)
    EditText nickname;
    @BindView(R.id.register_houseId)
    EditText houseId;

    private UserPresenter presenter=new UserPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        register.setOnClickListener(v -> {
            final User user = new User();
            user.setHouse_id(houseId.getText().toString());
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.setNickname(nickname.getText().toString());
            presenter.RegisterUser(user);
        });
    }


    @Override
    public void showError(String msg) {
        new ToastUtil(this,msg).show(500);
    }

    @Override
    public void jumpView(AppCompatActivity activity) {
        final Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMsg(String message) {
        new ToastUtil(this,message).show(500);
    }
}