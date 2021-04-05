package top.zxpdmw.graduationproject.ui.activity.system;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import lombok.SneakyThrows;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.ui.adapter.SystemMainAdapter;
import top.zxpdmw.graduationproject.bean.Module;
import top.zxpdmw.graduationproject.bean.User;


public class SystemMainActivity extends AppCompatActivity {
    List<Module> moduleList = Arrays.asList(Module.NOTICE, Module.PAGE, Module.PROPERTY, Module.COMPLAIN_REPAIR, Module.HOUSE_KEEPING, Module.HOUSE_RENT_SALE, Module.MY_INFO);
    private SystemMainAdapter adapter = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView textView;
    @BindView(R.id.list_main)
    ListView listView;
    User user;
    FragmentManager fragmentManage = getSupportFragmentManager();

    private long exitTime = 0;


    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemmain);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        textView.setText("便民社区");
        adapter=new SystemMainAdapter(moduleList,this);
        listView.setAdapter(adapter);
    }

    @OnItemClick(R.id.list_main)
    void setListView(int id){
        final Intent intent=new Intent();
        switch (id){
            case 0:
                intent.setClass(this,NoticeActivity.class);
                break;
            case 1:
                intent.setClass(this,CommunityPageActivity.class);
                break;
            case 2:
                intent.setClass(this,PropertyActivity.class);
                intent.putExtra("houseId", user.getHouse_id());
                intent.putExtra("nickname", user.getNickname());
                break;
            case 3:
                intent.setClass(this, ComplainRepairActivity.class);
                intent.putExtra("username",user.getUsername());
                break;
            case 4:
                intent.setClass(this,HouseKeepingActivity.class);
                break;
            case 5:
                intent.setClass(this,HouseRentSaleActivity.class);
                final Bundle bundle = new Bundle();
                bundle.putString("username",user.getUsername());
                intent.putExtra("bundle",bundle);
                break;
            case 6:
                intent.setClass(this,MyInfoActivity.class);
                intent.putExtra("user", user);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManage.getBackStackEntryCount() == 0) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        } else {
            fragmentManage.popBackStack();
        }
    }
}
