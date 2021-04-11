package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.HouseKeeping;

public class DetailHouseKeepingActivity extends AppCompatActivity {
    Intent intent;
    HouseKeeping houseKeeping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_house_keeping);
        intent=getIntent();
        houseKeeping= (HouseKeeping) intent.getSerializableExtra("hk");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }
}