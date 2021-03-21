package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Window;

import top.zxpdmw.graduationproject.R;

public class TestActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("便民社区");
        toolbar.setSubtitle("社区公告");
        toolbar.setNavigationIcon(R.drawable.logo);
    }
}