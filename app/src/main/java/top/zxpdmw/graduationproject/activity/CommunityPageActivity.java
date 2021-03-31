package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import top.zxpdmw.graduationproject.R;

public class CommunityPageActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView title,name,introduce,phone,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_page);
        init();
    }

    private void init(){
        toolbar=findViewById(R.id.toolbar);
        title=findViewById(R.id.community_title);
        name=findViewById(R.id.community_name);
        introduce=findViewById(R.id.community_introduce);
        phone=findViewById(R.id.community_phone);
        address=findViewById(R.id.community_address);
        toolbar.setTitle("社区黄页");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}