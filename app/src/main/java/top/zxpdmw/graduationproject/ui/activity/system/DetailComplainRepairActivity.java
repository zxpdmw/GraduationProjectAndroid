package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.ComplainRepair;
import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.util.TimeUtil;

public class DetailComplainRepairActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.notice_title)
    TextView title;
    @BindView(R.id.notice_publisher)
    TextView publisher;
    @BindView(R.id.notice_content)
    TextView content;
    @BindView(R.id.notice_publish_time)
    TextView publishTime;
    private ComplainRepair complainRepair;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_complain_repair);
        ButterKnife.bind(this);
        init();
    }

    void init(){
        toolbar.setTitle("");
        toolbar_title.setText("投诉报修");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });
        intent = getIntent();
        complainRepair = (ComplainRepair) intent.getSerializableExtra("cr");
        title.setText(complainRepair.getCr_type());
        publisher.setText(complainRepair.getUsername());
//        final String s = TimeUtil.descriptiveData(complainRepair.getCreate_time());
//        publishTime.setText(s);
        content.setText(complainRepair.getMessage());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }
}