package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.service.UserService;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.HttpUtil;
import top.zxpdmw.graduationproject.util.UrlUtil;


public class LoginActivity extends AppCompatActivity {
    private TextView newUser;
    private Button login;
    private EditText username;
    private EditText password;

    private void initView() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
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
            new Thread(()->{
                UserService userService = HttpUtil.builder.create(UserService.class);
                Call<ResponseBody> responseBodyCall = userService.checkLogin(username.getText().toString(), password.getText().toString());
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                if (response.body() != null) {
                                    if (response.body().string().equals("true")) {
                                        Log.println(Log.DEBUG, "success", "请求成功");
                                        loginSuccess();
                                    } else {
                                        Toast.makeText(LoginActivity.this, ConstUtil.USERNAME_PASSWORD_ERROR, Toast.LENGTH_LONG).show();
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, ConstUtil.SYSTEM_EXCEPTION, Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.println(Log.DEBUG, "http", t.toString());
                        Log.println(Log.DEBUG, "http", call.toString());
                    }
                });
            }).start();

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

}