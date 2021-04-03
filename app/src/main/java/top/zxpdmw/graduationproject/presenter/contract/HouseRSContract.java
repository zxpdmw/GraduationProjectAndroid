package top.zxpdmw.graduationproject.presenter.contract;

import java.util.List;

import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface HouseRSContract {

    interface View extends BaseView{
        void showResult(List<HouseRentSale> list);
        void showNoData();
    }

    interface Presenter extends BasePresenter{
        void HouseRent();
        void HouseSale();
        void HouseByUsername(String username);
        void HousePublish(HouseRentSale houseRentSale);
    }
}
