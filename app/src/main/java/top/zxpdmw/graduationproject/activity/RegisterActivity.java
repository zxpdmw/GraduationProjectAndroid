package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private EditText username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        register.setOnClickListener(v -> {
            register();
        });

    }

    private void initView(){
        register= findViewById(R.id.register_button);
        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
    }

    private void  registerSuccessToLogin(){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void register(){
        new Thread(()->{
            UserService userService = HttpUtil.builder.create(UserService.class);
            retrofit2.Call<ResponseBody> responseBodyCall = userService.register(username.getText().toString(), password.getText().toString());
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            if (response.body() != null) {
                                if (response.body().string().equals("true")) {
                                    Log.println(Log.DEBUG, "success", "请求成功");
                                    registerSuccessToLogin();
                                } else {
                                    Toast.makeText(RegisterActivity.this, ConstUtil.USERNAME_PASSWORD_ERROR, Toast.LENGTH_LONG).show();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, ConstUtil.SYSTEM_EXCEPTION, Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.println(Log.DEBUG, "http", t.toString());
                    Log.println(Log.DEBUG, "http", t.toString());
                }
            });
        }).start();
    }
}