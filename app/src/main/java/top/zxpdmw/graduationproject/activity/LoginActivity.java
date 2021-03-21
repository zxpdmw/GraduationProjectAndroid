package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.Time;
import java.time.chrono.ChronoLocalDate;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.HttpUtil;
import top.zxpdmw.graduationproject.util.ToastUtil;


public class LoginActivity extends AppCompatActivity {
    private TextView newUser;
    private Button login;
    private EditText username;
    private EditText password;

    private void initView() {
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        newUser = findViewById(R.id.new_user);
        login = findViewById(R.id.login_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        newUser.setOnClickListener(v -> {
            newUser();
        });

        login.setOnClickListener(v -> {
            checkLogin();
        });
    }


    private void newUser() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void loginSuccess() {
        Intent intent = new Intent(LoginActivity.this, SystemMainActivity.class);
        startActivity(intent);
    }

    private void checkLogin() {
        new Thread(() -> {
            try {
                String uname = username.getText().toString();
                String upwd = password.getText().toString();
                Request request = new Request.Builder()
                        .url(ConstUtil.BASE_URL + ConstUtil.USER_LOGIN + "?username=" + uname + "&password=" + upwd)
                        .build();
                Response response = HttpUtil.OK_HTTP_CLIENT.newCall(request).execute();
                JSONObject jsonObject = new JSONObject(response.body().string());
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                switch (code) {
                    case "666":
                        runOnUiThread(() -> {
                            ToastUtil toastUtil = new ToastUtil(this, message);
                            toastUtil.show(500);
                            loginSuccess();
                        });
                        break;
                    case "5551":
                    case "5552":
                        runOnUiThread(() -> {
                            ToastUtil toastUtil = new ToastUtil(this, message);
                            toastUtil.show(500);
                        });
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    ToastUtil toastUtil = new ToastUtil(this, ConstUtil.SYSTEM_EXCEPTION);
                    toastUtil.show(500);
                });
            }
        }).start();
    }
}