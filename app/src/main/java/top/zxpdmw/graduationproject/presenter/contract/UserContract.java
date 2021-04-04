package top.zxpdmw.graduationproject.presenter.contract;

import androidx.fragment.app.Fragment;

import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.presenter.BasePresenter;
import top.zxpdmw.graduationproject.ui.BaseView;

public interface UserContract {
     interface View extends BaseView{
         void LoadUser(User user);
         void switchFragment();
     }

     interface Presenter extends BasePresenter{
         void LoginUser(String username, String password);
         void RegisterUser(User user);

     }
}
