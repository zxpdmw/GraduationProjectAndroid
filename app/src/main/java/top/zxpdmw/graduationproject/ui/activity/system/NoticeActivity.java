package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.presenter.NoticePresenter;
import top.zxpdmw.graduationproject.presenter.contract.NoticeContract;
import top.zxpdmw.graduationproject.ui.adapter.NoticeAdapter;
import top.zxpdmw.graduationproject.bean.Notice;

public class NoticeActivity extends AppCompatActivity implements NoticeContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.notice_list)
            ListView listView;
    List<Notice> noticeList;

    NoticePresenter noticePresenter=new NoticePresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        initToolBar("");
        noticePresenter.RecommendNotice();
    }
    @Override
    public void showResult(List<Notice> list) {
        this.noticeList=list;
        final NoticeAdapter noticeAdapter = new NoticeAdapter(list, this);
        listView.setAdapter(noticeAdapter);
    }

    @OnItemClick(R.id.notice_list)
    void setListView(int id){
        Intent intent=new Intent(NoticeActivity.this,DetailNoticeActivity.class);
        intent.putExtra("notice",noticeList.get(id));
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
    }

    @Override
    public void switchFragment() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void jumpView(AppCompatActivity activity) {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void initToolBar(String title) {
        toolbar.setTitle("社区公告");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
        });
    }

    @Override
    public void destroyToolBar(String title) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_of_right);
    }
}
