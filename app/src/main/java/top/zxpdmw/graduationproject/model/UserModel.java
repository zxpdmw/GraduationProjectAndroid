package top.zxpdmw.graduationproject.model;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import top.zxpdmw.graduationproject.bean.CommonResult;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.http.HttpManager;
import top.zxpdmw.graduationproject.http.UserRequest;

public class UserModel {
    final UserRequest userRequest = HttpManager.retrofit.create(UserRequest.class);

    public void LoginUser(String username,String password,Callback<CommonResult> callback){
        userRequest.LoginUser(username,password).enqueue(callback);
    }

    public void RegisterUser(User user,Callback<CommonResult> callback){
        userRequest.RegisterUser(user).enqueue(callback);
    }
}
