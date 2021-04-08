package top.zxpdmw.graduationproject.ui.activity.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.presenter.NoticePresenter;
import top.zxpdmw.graduationproject.presenter.contract.NoticeContract;
import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.ui.adapter.NoticeAdapter;
import top.zxpdmw.graduationproject.ui.adapter.ItemClickListener;

public class NoticeActivity extends AppCompatActivity implements NoticeContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.notice_list)
    RecyclerView recyclerView;
    List<Notice> noticeList;
    ZLoadingDialog dialog = new ZLoadingDialog(this);

    NoticePresenter noticePresenter = new NoticePresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        showLoading();
        initToolBar("");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noticePresenter.RecommendNotice();

    }

    @Override
    public void showResult(List<Notice> list) {
        this.noticeList = list;
        final NoticeAdapter noticeAdapter = new NoticeAdapter(list);
        noticeAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Intent intent = new Intent(NoticeActivity.this, DetailNoticeActivity.class);
                intent.putExtra("notice", noticeList.get(position));
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
            }
        });

        recyclerView.setAdapter(noticeAdapter);
    }


    @Override
    public void dismissLoading() {
        dialog.dismiss();
    }

    @Override
    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.WHITE)
                .setDialogBackgroundColor(getResources().getColor(R.color.loading_background))
                .setDurationTime(1)
                .setHintText("加载中...").show();
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
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
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
