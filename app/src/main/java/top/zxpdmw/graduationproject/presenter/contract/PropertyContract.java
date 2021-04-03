package top.zxpdmw.graduationproject.presenter.contract;

import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface PropertyContract {

    interface View extends BaseView{
        void LoadData(String property);
    }

    interface Presenter extends BasePresenter{
        void GetProperty(String houseId);
        void AddProperty(String houseId,String property);
    }
}
