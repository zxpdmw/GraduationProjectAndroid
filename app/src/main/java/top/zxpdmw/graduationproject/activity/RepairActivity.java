package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;

import top.zxpdmw.graduationproject.R;
public class RepairActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);
        init();
    }

    private void init(){
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("报修");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}