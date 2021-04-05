package top.zxpdmw.graduationproject.presenter.contract;

import java.util.List;

import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;
import top.zxpdmw.graduationproject.ui.fragment.BaseFragment;

public interface NoticeContract {
    interface View extends BaseFragment {
        void showResult(List<Notice> list);
        void switchFragment();
    }

    interface Presenter extends BasePresenter{
        void RecommendNotice();
    }

}
