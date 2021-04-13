package top.zxpdmw.graduationproject.presenter.contract;

import java.util.List;

import top.zxpdmw.graduationproject.bean.HouseKeeping;
import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface HouseKeepingContract {
    interface View  extends BaseView{
        void showList(List<HouseKeeping> list);
        void delete(HouseKeeping houseKeeping);
        void add(HouseKeeping houseKeeping);
        void cancel();
    }

    interface Presenter extends BasePresenter{
        void GetHouseKeeping(String username);
        void AddHouseKeeping(HouseKeeping houseKeeping);
        void DeleteHouseKeeping(HouseKeeping id);
        void EditHouseKeeping(HouseKeeping houseKeeping);
    }
}
