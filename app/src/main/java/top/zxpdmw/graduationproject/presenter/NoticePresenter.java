package top.zxpdmw.graduationproject.presenter;

import android.util.Log;

import androidx.annotation.LongDef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.CommonResult;
import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.model.NoticeModel;
import top.zxpdmw.graduationproject.presenter.contract.NoticeContract;
import top.zxpdmw.graduationproject.ui.activity.DetailNoticeActivity;
import top.zxpdmw.graduationproject.ui.activity.NoticeActivity;

public class NoticePresenter implements NoticeContract.Presenter {
    private static final String TAG = "zwy-----Notice";
    private NoticeModel noticeModel;
    private NoticeContract.View view;

    public NoticePresenter(NoticeContract.View view) {
        this.view=view;
        noticeModel=new NoticeModel();
    }



    @Override
    public void DetailNotice(String title) {
        noticeModel.DetailNotice(title,new Callback<CommonResult<Notice>>() {
            @Override
            public void onResponse(Call<CommonResult<Notice>> call, Response<CommonResult<Notice>> response) {
                int code=response.body().getCode();
                final List<Notice> data = response.body().getData();
                if (code==666){
                    view.showResult(data);
                    view.jumpView(new DetailNoticeActivity());
                }
            }

            @Override
            public void onFailure(Call<CommonResult<Notice>> call, Throwable t) {

            }
        });
    }

    @Override
    public void RecommendNotice() {
        noticeModel.RecommendNotice(new Callback<CommonResult<Notice>>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<CommonResult<Notice>> call, Response<CommonResult<Notice>> response) {
                int code = response.body().getCode();
                String msg = response.body().getMessage();
                List<Notice> list = response.body().getData();
                if (code == 666) {
                    view.showResult(list);
                    view.jumpView(new NoticeActivity());
                }
            }

            @Override
            public void onFailure(Call<CommonResult<Notice>> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }
}
