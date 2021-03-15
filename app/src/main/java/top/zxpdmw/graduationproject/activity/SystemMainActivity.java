package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Request;
import okhttp3.Response;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.util.HttpUtil;

public class SystemMainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemmain);
        initView();
        button.setOnClickListener(v -> getData());
    }

    private void initView() {
        button = findViewById(R.id.testButton);
    }

    @SuppressLint("SetTextI18n")
    private void getData() {
        new Thread(() -> {
            Request request = new Request.Builder().url(HttpUtil.BASE_URL + "notice/recommend").build();
            try {
                Response execute = HttpUtil.OK_HTTP_CLIENT.newCall(request).execute();
                JSONObject jsonObject = new JSONObject(Objects.requireNonNull(execute.body()).string());

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }
}