package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.Module;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.ui.activity.MainActivity;
import top.zxpdmw.graduationproject.ui.adapter.MyInfoAdapter;

public class MyActivity extends AppCompatActivity {
    private Intent intent;
    @BindView(R.id.my_nickename)
    TextView nickname;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    private User user;

    @BindView(R.id.rv_setting)
    RecyclerView recyclerView;
    @BindView(R.id.my_touxiang)
    ImageView imageView;
    @BindView(R.id.unLogin)
    Button unlogin;
    @BindView(R.id.edit_password)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        List<Module> list= Arrays.asList(Module.HELP,Module.HAOPING,Module.HUANCUN,Module.ABOUT);
        recyclerView.setAdapter(new MyInfoAdapter(list));
        init();
    }

    private void init(){
//        toolbar.setTitle("我的");
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(v -> {
//            finish();
//            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
//        });
        intent=getIntent();
        user = (User) intent.getSerializableExtra("user");
        nickname.setText(user.getNickname());
    }

    @OnClick(R.id.edit_password)
    void setPassword(){
        final Intent intent = new Intent(this, EditInfoActivity.class);
        intent.putExtra("username", user.getUsername());
        intent.putExtra("data", "");
        intent.putExtra("title", "修改密码");
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
    }


    @OnClick({R.id.my_touxiang})
    void setImageView(){
        final Intent intent = new Intent(this, InfoActivity.class);
        final Bundle bundle = new Bundle();
        bundle.putSerializable("user",user);
        intent.putExtra("bundle",bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);

    }
    @OnClick(R.id.unLogin)
    void setUnLogin(){
        final Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }
}