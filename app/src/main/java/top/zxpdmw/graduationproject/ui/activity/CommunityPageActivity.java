package top.zxpdmw.graduationproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.zxpdmw.graduationproject.R;

public class CommunityPageActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.community_title)
    TextView title;
    @BindView(R.id.community_name)
    TextView name;
    @BindView(R.id.community_introduce)
    TextView introduce;
    @BindView(R.id.community_phone)
    TextView phone;
    @BindView(R.id.community_address)
    TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_page);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        toolbar.setTitle("社区黄页");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}