package top.zxpdmw.graduationproject.presenter.contract;

import java.util.List;

import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface MainContract {
    interface View extends BaseView{
        void showData(String list);
    }

    interface Presenter extends BasePresenter{
        void RecommendNotice();
    }
}
