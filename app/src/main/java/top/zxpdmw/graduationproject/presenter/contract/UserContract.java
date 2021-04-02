package top.zxpdmw.graduationproject.presenter.contract;

import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface UserContract {
     interface View extends BaseView{

     }

     interface Presenter extends BasePresenter{
         void LoginUser(String username, String password);
         void RegisterUser(User user);
     }
}
