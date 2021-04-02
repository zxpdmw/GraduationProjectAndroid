package top.zxpdmw.graduationproject.presenter;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.CommonResult;
import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.model.MainModel;
import top.zxpdmw.graduationproject.presenter.contract.MainContract;
import top.zxpdmw.graduationproject.ui.activity.NoticeActivity;

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = "zwy-Recommend";

    private MainContract.View view;
    private MainModel mainModel;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        mainModel = new MainModel();
    }

    @Override
    public void RecommendNotice() {
        mainModel.RecommendNotice(new Callback<CommonResult<Notice>>() {
            @Override
            public void onResponse(Call<CommonResult<Notice>> call, Response<CommonResult<Notice>> response) {
                int code = response.body().getCode();
                String msg = response.body().getMessage();
                List<Notice> list = response.body().getData();
                view.showMsg(msg);
                if (code == 666) {
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
