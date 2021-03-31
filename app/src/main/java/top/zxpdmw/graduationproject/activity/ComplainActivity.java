package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import top.zxpdmw.graduationproject.R;
import android.os.Bundle;

public class ComplainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        init();
    }

    private void init(){
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("投诉");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}