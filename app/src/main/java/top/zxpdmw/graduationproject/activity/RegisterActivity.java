package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.HttpUtil;
import top.zxpdmw.graduationproject.util.ToastUtil;

public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private EditText username, password, nickname, houseId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        register.setOnClickListener(v -> register());

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
                JSONObject user=new JSONObject();
                user.put("nickname",nickname.getText().toString());
                user.put("username",username.getText().toString());
                user.put("password",password.getText().toString());
                user.put("house_id",houseId.getText().toString());
                Response response = HttpUtil.PostJson(user);
                JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                String code = jsonObject.getString("code");
                String message=jsonObject.getString("message");
                if (code.equals("666")){
                    runOnUiThread(() -> {
                        ToastUtil toastUtil = new ToastUtil(RegisterActivity.this, message + ConstUtil.TO_LOGIN_ACTIVITY);
                        toastUtil.show(300);
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