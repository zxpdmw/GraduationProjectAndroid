package top.zxpdmw.graduationproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.SneakyThrows;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.presenter.NoticePresenter;
import top.zxpdmw.graduationproject.presenter.contract.NoticeContract;
import top.zxpdmw.graduationproject.ui.adapter.NoticeAdapter;
import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.util.ToastUtil;

public class NoticeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, NoticeContract.View {
    private static final String TAG = "zwy---------";
    List<Notice> noticeList;
    Context context=NoticeActivity.this;
    NoticeAdapter noticeAdapter;
    @BindView(R.id.list_notice)
    ListView listView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    NoticePresenter noticePresenter=new NoticePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        noticePresenter.RecommendNotice();
        init();
        listView.setOnItemClickListener(this);
    }

    @SneakyThrows
    private void init() {
        toolbar.setTitle("社区公告");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        context = NoticeActivity.this;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(NoticeActivity.this,DetailNoticeActivity.class);
        intent.putExtra("notice",noticeList.get(position));
        startActivity(intent);
    }

    private void initListView(List<Notice> list){
        noticeAdapter = new NoticeAdapter(list, context);
        listView.setAdapter(noticeAdapter);
    }

    @Override
    public void showError(String msg) {
        new ToastUtil(this, msg).show(500);
    }

    @Override
    public void jumpView(AppCompatActivity activity) {

    }

    @Override
    public void showMsg(String msg) {
            new ToastUtil(this,msg).show(500);
    }

    @Override
    public void showResult(List<Notice> list) {
        noticeList=list;
        initListView(list);
    }
}

