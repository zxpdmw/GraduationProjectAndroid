package top.zxpdmw.graduationproject.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import top.zxpdmw.graduationproject.bean.CommonList;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.http.HttpManager;
import top.zxpdmw.graduationproject.util.ConstUtil;

public class UserModel {
    private static final String TAG = "zwt-UserModel";
    final UserHttpInterface userRequest = HttpManager.retrofit.create(UserHttpInterface.class);

    public void LoginUser(String username, String password, Callback<ResponseBody> callback){
        userRequest.LoginUser(username,password).enqueue(callback);
    }

    public void RegisterUser(User user,Callback<CommonList> callback){
        userRequest.RegisterUser(user).enqueue(callback);
    }

    public void EditUserInfo(User user,Callback<ResponseBody> callback){
        userRequest.EditUserInfo(user).enqueue(callback);
    }

    public void EditPassword(User user,Callback<ResponseBody> callback){
        userRequest.EditUserInfo(user).enqueue(callback);
    }


    interface UserHttpInterface{
        @GET(ConstUtil.USER_LOGIN)
        Call<ResponseBody> LoginUser(@Query("username")String username, @Query("password")String password);

        @POST(ConstUtil.USER_REGISTER)
        Call<CommonList> RegisterUser(@Body User user);

        @POST(ConstUtil.USER_PASSWORD)
        Call<ResponseBody> EditPassword(@Body User user);

        @POST(ConstUtil.USER_INFO)
        Call<ResponseBody> EditUserInfo(@Body User user);

        @GET(ConstUtil.USER_GET_INFO)
        Call<ResponseBody> GetUserInfo(@Query("username") String username);

    }
}
