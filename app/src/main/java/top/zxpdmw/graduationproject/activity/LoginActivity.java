package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
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
    private String houseId;

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
            getHouseId();
            checkLogin();
        });
    }


    private void newUser() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void loginSuccess() {
        Intent intent = new Intent(LoginActivity.this, SystemMainActivity.class);
        intent.putExtra("houseId",houseId);
        intent.putExtra("username",username.getText().toString());
        startActivity(intent);
    }

    private void checkLogin() {
        new Thread(() -> {
            try {
                String uname = username.getText().toString();
                String upwd = password.getText().toString();
                Response response = HttpUtil.Get(ConstUtil.USER_LOGIN + "?username=" + uname + "&password=" + upwd);
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
                            new ToastUtil(this, message).show(500);
                        });
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    new ToastUtil(this, ConstUtil.SYSTEM_EXCEPTION).show(500);
                });
            }
        }).start();
    }

    private void getHouseId() {
        new Thread(()->{
            Response response = HttpUtil.Get(ConstUtil.USER_GET_HOUSEID + "?username=" + username.getText().toString());
            try {
                JSONObject jsonObject=new JSONObject(response.body().string());
                if (jsonObject.getString("code").equals("666")){
                    houseId=jsonObject.getString("data");
                }else{
                    runOnUiThread(() -> {
                        new ToastUtil(this, ConstUtil.SYSTEM_EXCEPTION).show(500);
                    });
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    new ToastUtil(this, ConstUtil.SYSTEM_EXCEPTION).show(500);
                });
            }

        }).start();
    }
}