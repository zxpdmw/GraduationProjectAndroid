package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.model.Notice;

public class DetailNoticeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView title,publisher,content,publishTime;
    private Notice notice;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notice);
        init();

    }

    private void init(){
        toolbar=findViewById(R.id.toolbar);
        title=findViewById(R.id.detail_notice_title);
        content=findViewById(R.id.detail_notice_content);
        publisher=findViewById(R.id.detail_notice_publisher);
        publishTime=findViewById(R.id.detail_notice_publish_time);
        toolbar.setTitle("公告详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        intent=getIntent();
        notice=(Notice) intent.getSerializableExtra("notice");
        title.setText(notice.getTitle());
        publisher.setText(notice.getPublisher());
        publishTime.setText(notice.getPublish_time());
        content.setText(notice.getContent());
    }
}