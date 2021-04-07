package top.zxpdmw.graduationproject.presenter;

import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.CommonList;
import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.model.NoticeModel;
import top.zxpdmw.graduationproject.presenter.contract.NoticeContract;

public class NoticePresenter implements NoticeContract.Presenter {
    private static final String TAG = "zwy-----Notice";
    private NoticeModel noticeModel;
    private NoticeContract.View view;

    public NoticePresenter(NoticeContract.View view) {
        this.view=view;
        noticeModel=new NoticeModel();
    }

    @Override
    public void RecommendNotice() {
        noticeModel.RecommendNotice(new Callback<CommonList<Notice>>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<CommonList<Notice>> call, Response<CommonList<Notice>> response) {
                int code = response.body().getCode();
                String msg = response.body().getMessage();
                List<Notice> list = response.body().getData();
                if (code == 666) {
                    view.showResult(list);
                }
            }

            @Override
            public void onFailure(Call<CommonList<Notice>> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }
}
