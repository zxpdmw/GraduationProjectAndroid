package top.zxpdmw.graduationproject.presenter.contract;

import java.util.List;

import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface HouseRentSaleContract {

    interface View extends BaseView{
        void showList(List<HouseRentSale> list);
        void showNoData();
        void add(HouseRentSale houseRentSale);
        void delete(HouseRentSale houseRentSale);
        void cancel();
    }

    interface Presenter extends BasePresenter{
        void HouseRent();
        void HouseSale();
        void HouseByUsername(String username);
        void HousePublish(HouseRentSale houseRentSale);
        void DeleteHouse(HouseRentSale houseRentSale);
        void EditHousePrice(HouseRentSale houseRentSale);
    }
}
