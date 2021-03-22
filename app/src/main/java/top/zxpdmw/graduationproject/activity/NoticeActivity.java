package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.List;

import lombok.SneakyThrows;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.adapter.NoticeAdapter;
import top.zxpdmw.graduationproject.model.Notice;
import top.zxpdmw.graduationproject.util.JsonUtil;

public class NoticeActivity extends AppCompatActivity {
    private List<Notice> noticeList;
    private Context context;
    private NoticeAdapter noticeAdapter;
    private ListView listView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        init();
        noticeAdapter = new NoticeAdapter(noticeList, context);
        listView.setAdapter(noticeAdapter);
    }

    @SneakyThrows
    private void init(){
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("社区公告");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        listView=findViewById(R.id.list_notice);
        context = NoticeActivity.this;
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        noticeList = JsonUtil.getNoticeList(new JSONArray(data));
    }

}

