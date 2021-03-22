package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kotlin.collections.IntIterator;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.HttpUtil;
import top.zxpdmw.graduationproject.util.ToastUtil;

public class PropertyActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button button;
    private TextView nickname, balance;
    private EditText editText;
    private Intent intent;
    private String beforeBalance;
    private String houseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        init();
        button.setOnClickListener(v -> {
            String add = editText.getText().toString();
            AddProperty(add);
        });
    }

    private void init() {
        intent = getIntent();
        beforeBalance = intent.getStringExtra("property");
        houseId = intent.getStringExtra("houseId");
        toolbar = findViewById(R.id.toolbar);
        button = findViewById(R.id.addProperty);
        nickname = findViewById(R.id.property_nickname);
        balance = findViewById(R.id.property_balance);
        editText = findViewById(R.id.property_add);
        balance.setText(beforeBalance);
        toolbar.setTitle("物 业 费");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        nickname.setText(intent.getStringExtra("nickname"));
    }

    private void AddProperty(String add) {
        new Thread(() -> {
            Request build = new Request.Builder()
                    .url(ConstUtil.BASE_URL + ConstUtil.PROPERTY_ADD + "?houseId=" + houseId + "&property=" + add)
                    .get().build();
            try {
                Response execute = HttpUtil.OK_HTTP_CLIENT.newCall(build).execute();
                JSONObject jsonObject = new JSONObject(execute.body().string());
                String code = jsonObject.getString("code");
                if (code.equals("666")) {
                    runOnUiThread(() -> {
                        balance.setText(String.valueOf(Integer.parseInt(add) + Integer.parseInt(balance.getText().toString())));
                        balance.clearComposingText();
                        new ToastUtil(this, "缴费成功").show(500);
                    });

                } else {
                    runOnUiThread(() -> new ToastUtil(this, ConstUtil.SYSTEM_EXCEPTION).show(500));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void getNickName(){
        new Thread(()->{

        }).start();
    }

}