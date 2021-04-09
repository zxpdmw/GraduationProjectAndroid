package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.ui.activity.MainActivity;
import top.zxpdmw.graduationproject.ui.view.PhotoPopupWindow;

public class InfoActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.oner)
    RelativeLayout oner;
    @BindView(R.id.twor)
    RelativeLayout twoer;
    @BindView(R.id.threer)
    RelativeLayout threer;
    @BindView(R.id.fourr)
    RelativeLayout fourr;
    @BindView(R.id.fiver)
    RelativeLayout fiver;
    @BindView(R.id.my_nickanem_text)
    TextView nickname;
    @BindView(R.id.my_address_text)
    TextView address;
    @BindView(R.id.my_house_text)
    TextView house;
    @BindView(R.id.my_phone_text)
    TextView phone;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });
        title.setText("个人资料");
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        user = (User) bundle.getSerializable("user");
        nickname.setText(user.getNickname());
        house.setText(user.getHouse_id());
        address.setText(user.getAddress());
        phone.setText(user.getPhone());
    }

    @OnClick(R.id.oner)
    void setOner() {
        PhotoPopupWindow mPhotoPopupWindow = new PhotoPopupWindow(InfoActivity.this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进入相册选择
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 拍照
            }
        });
        View rootView = LayoutInflater.from(InfoActivity.this).inflate(R.layout.activity_info, null);
        mPhotoPopupWindow.showAtLocation(rootView,
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    @OnClick(R.id.twor)
    void setNickname() {
        final Intent intent = new Intent(this, EditInfoActivity.class);
        intent.putExtra("username", user.getUsername());
        intent.putExtra("data", nickname.getText().toString());
        intent.putExtra("title", "昵称");
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
    }

    @OnClick(R.id.threer)
    void setAddress() {
        final Intent intent = new Intent(this, EditInfoActivity.class);
        intent.putExtra("username", user.getUsername());
        intent.putExtra("data", address.getText().toString());
        intent.putExtra("title", "地址");
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
    }

    @OnClick(R.id.fourr)
    void setPhone() {
        final Intent intent = new Intent(this, EditInfoActivity.class);
        intent.putExtra("username", user.getUsername());
        intent.putExtra("data", phone.getText().toString());
        intent.putExtra("title", "电话");
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
    }

    @OnClick(R.id.fiver)
    void setHouse() {
        final Intent intent = new Intent(this, EditInfoActivity.class);
        intent.putExtra("username", user.getUsername());

        intent.putExtra("data", house.getText().toString());
        intent.putExtra("title", "房屋号");
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }
}