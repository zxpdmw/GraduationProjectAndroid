package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;
import java.util.PrimitiveIterator;

import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.model.User;

public class MyInfoActivity extends AppCompatActivity {
    private Intent intent;
    private TextView nickname,houseId;
    private Toolbar toolbar;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        init();
        nickname.setText(user.getNickname());
        houseId.setText(user.getHouse_id());
    }

    private void init(){
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("我的");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        intent=getIntent();
        nickname=findViewById(R.id.my_nickename);
        houseId=findViewById(R.id.my_house);
        user = (User) intent.getSerializableExtra("user");
    }
}