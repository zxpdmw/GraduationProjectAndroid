package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.util.TimeUtil;

public class DetailNoticeActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.notice_title)
    TextView title;
    @BindView(R.id.notice_publisher)
    TextView publisher;
    @BindView(R.id.notice_content)
    TextView content;
    @BindView(R.id.notice_publish_time)
    TextView publishTime;
    private Notice notice;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notice);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        toolbar.setTitle("公告详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        intent = getIntent();
        notice = (Notice) intent.getSerializableExtra("notice");
        title.setText(notice.getTitle());
        publisher.setText(notice.getPublisher());
        final String s = TimeUtil.descriptiveData(notice.getPublish_time());
        publishTime.setText(s);
        content.setText(notice.getContent());
    }
}