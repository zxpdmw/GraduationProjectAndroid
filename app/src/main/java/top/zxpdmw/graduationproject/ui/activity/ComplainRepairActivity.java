package top.zxpdmw.graduationproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.ui.adapter.ComplainRepairAdapter;
import top.zxpdmw.graduationproject.bean.ComplainRepair;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ComplainRepairActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    List<ComplainRepair> list;
    @BindView(R.id.list_complain_repair)
    ListView listView;
    ComplainRepairAdapter complainRepairAdapter;
    Context context = ComplainRepairActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_repair);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("投诉报修");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(ComplainRepairActivity.this, DetailNoticeActivity.class);
        intent.putExtra("cr", list.get(position));
        startActivity(intent);
    }
}