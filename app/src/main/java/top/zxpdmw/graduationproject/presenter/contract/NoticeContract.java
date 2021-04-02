package top.zxpdmw.graduationproject.presenter.contract;

import java.util.List;

import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface NoticeContract {
    interface View extends BaseView{
        void showResult(List<Notice> list);
    }

    interface Presenter extends BasePresenter{
        void DetailNotice(String title);
        void RecommendNotice();
    }

}
