package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.xuexiang.xui.XUI;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import top.zxpdmw.graduationproject.R;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button register;


    private void initView(){
        login= findViewById(R.id.login);
        register=findViewById(R.id.register);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }
}