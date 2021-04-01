package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.adapter.ComplainRepairAdapter;
import top.zxpdmw.graduationproject.model.ComplainRepair;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ComplainRepairActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Toolbar toolbar;
    private List<ComplainRepair> list;
    private ListView listView;
    private ComplainRepairAdapter complainRepairAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_repair);
        init();
    }

    private void init(){
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("投诉报修");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        toComplainRepairContent(position);
    }

    private void toComplainRepairContent(int position){
       runOnUiThread(()->{
           Intent intent=new Intent(ComplainRepairActivity.this,DetailNoticeActivity.class);
           intent.putExtra("cr",list.get(position));
           startActivity(intent);
       });
    }
}