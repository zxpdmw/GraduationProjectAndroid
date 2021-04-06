package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.User;

public class MyInfoActivity extends AppCompatActivity {
    private Intent intent;
    @BindView(R.id.my_house)
    TextView nickname;
    @BindView(R.id.my_nickename)
    TextView houseId;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        toolbar.setTitle("我的");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });
        intent=getIntent();
        user = (User) intent.getSerializableExtra("user");
        nickname.setText(user.getNickname());
        houseId.setText(user.getHouse_id());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }
}