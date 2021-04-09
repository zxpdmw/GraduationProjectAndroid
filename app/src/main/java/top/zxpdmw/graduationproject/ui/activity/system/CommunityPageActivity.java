package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.ui.BaseView;

public class CommunityPageActivity extends AppCompatActivity implements BaseView {
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
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_page);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        toolbar.setTitle("");
        toolbar_title.setText("社区黄页");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void jumpView(AppCompatActivity activity) {

    }

    @Override
    public void showMsg(String msg) {

    }
}