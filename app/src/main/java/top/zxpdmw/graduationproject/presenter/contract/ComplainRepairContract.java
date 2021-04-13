package top.zxpdmw.graduationproject.presenter.contract;

import java.util.List;

import top.zxpdmw.graduationproject.bean.ComplainRepair;
import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface ComplainRepairContract {
    interface View extends BaseView {
        void showList(List<ComplainRepair> list);

        void cancel();

        void add(ComplainRepair complainRepair);

        void delete(ComplainRepair complainRepair);
    }

    interface Presenter extends BasePresenter {
        void AddComplainRepair(ComplainRepair complainRepair);

        void DeleteComplainRepair(ComplainRepair id);

        void EditComplainRepair(ComplainRepair complainRepair);

        void GetComplainRepair(String username);
    }
}
