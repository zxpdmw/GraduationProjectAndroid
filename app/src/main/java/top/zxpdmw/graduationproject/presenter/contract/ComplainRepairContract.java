package top.zxpdmw.graduationproject.presenter.contract;

import java.util.List;

import top.zxpdmw.graduationproject.bean.ComplainRepair;
import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface ComplainRepairContract {
    interface View extends BaseView{
        void showList(List<ComplainRepair> list);
        void cancel();
    }

    interface Presenter extends BasePresenter{
        void AddComplainRepair(ComplainRepair complainRepair);
        void DeleteComplainRepair(Integer id);
        void EditComplainRepair(ComplainRepair complainRepair);
        void GetComplainRepair(String username);
    }
}
