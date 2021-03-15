package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.model.RegisterUser;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.HttpUtil;
import top.zxpdmw.graduationproject.util.JsonUtil;
import top.zxpdmw.graduationproject.util.ToastUtil;

public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private EditText username, password, nickname, houseId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        register.setOnClickListener(v -> {
            register();
        });

    }

    private void initView() {
        register = findViewById(R.id.register_button);
        username = findViewById(R.id.register_username);
        password = findViewById(R.id.register_password);
        nickname = findViewById(R.id.register_nickname);
        houseId = findViewById(R.id.register_houseId);
    }

    private void registerSuccessToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void register() {
        new Thread(()->{
            try {
                RegisterUser user=new RegisterUser(nickname.getText().toString(),username.getText().toString(),password.getText().toString(),houseId.getText().toString());
                Request request =new Request.Builder()
                        .url(HttpUtil.BASE_URL+"user/register")
                        .post(RequestBody.create(MediaType.parse("application/json"), JsonUtil.BeanToJson(user)))
                        .build();
                Response response=HttpUtil.OK_HTTP_CLIENT.newCall(request).execute();
                JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                String code = jsonObject.getString("code");
                String message=jsonObject.getString("message");
                if (code.equals("666")){
                    runOnUiThread(() -> {
//                        Toast.makeText(RegisterActivity.this,message+ConstUtil.TO_LOGIN_ACTIVITY,Toast.LENGTH_LONG).show();
                        ToastUtil toastUtil = new ToastUtil(RegisterActivity.this, message + ConstUtil.TO_LOGIN_ACTIVITY);
                        toastUtil.show(300);
//                        try {
//                            TimeUnit.SECONDS.sleep(1);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        registerSuccessToLogin();
                    });
                }else {
                    if (code.equals("5550"))
                    runOnUiThread(() -> {
                        ToastUtil toastUtil = new ToastUtil(this, message);
                        toastUtil.show(500);
                    });
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    ToastUtil toastUtil = new ToastUtil(this, ConstUtil.SYSTEM_EXCEPTION);
                    toastUtil.show(500);
                });
            }
        }).start();
    }
}