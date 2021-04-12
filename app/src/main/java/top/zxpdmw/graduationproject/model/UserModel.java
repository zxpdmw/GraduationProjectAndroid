package top.zxpdmw.graduationproject.model;

import okhttp3.Cache;
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
    private static final String TAG = "zwy-UserModel";
    public static final String USER_LOGIN = "user/login";
    public static final String USER_REGISTER = "user/register";
    public static final String USER_EDIT_PASSWORD = "user/editpassword";
    public static final String USER_EDIT_ADDRESS = "user/editaddress";
    public static final String USER_EDIT_NICKNAME = "user/editnickname";
    public static final String USER_EDIT_PHONE = "user/editphone";
    public static final String USER_EDIT_HOUSE = "user/edithouse";
    final UserHttpInterface userRequest = HttpManager.retrofit.create(UserHttpInterface.class);

    public void LoginUser(String username, String password, Callback<ResponseBody> callback) {
        userRequest.LoginUser(username, password).enqueue(callback);
    }

    public void RegisterUser(User user, Callback<CommonList> callback) {
        userRequest.RegisterUser(user).enqueue(callback);
    }

    public void EditPassword(String username, String password, Callback<ResponseBody> callback) {
        userRequest.EditPassword(username, password).enqueue(callback);
    }

    public void EditAddress(String username, String address, Callback<ResponseBody> callback) {
        userRequest.EditAddress(username, address).enqueue(callback);
    }

    public void EditPhone(String username, String phone, Callback<ResponseBody> callback) {
        userRequest.EditPhone(username, phone).enqueue(callback);
    }

    public void EditHouse(String username, String house, Callback<ResponseBody> callback) {
        userRequest.EditHouse(username, house).enqueue(callback);
    }

    public void EditNickname(String username, String nickname, Callback<ResponseBody> callback) {
        userRequest.EditNickname(username, nickname).enqueue(callback);
    }

    interface UserHttpInterface {
        @GET(USER_LOGIN)
        Call<ResponseBody> LoginUser(@Query("username") String username, @Query("password") String password);

        @POST(USER_REGISTER)
        Call<CommonList> RegisterUser(@Body User user);

        @POST(USER_EDIT_PASSWORD)
        Call<ResponseBody> EditPassword(@Query("username") String username, @Query("password") String password);

        @GET(USER_EDIT_ADDRESS)
        Call<ResponseBody> EditAddress(@Query("username") String username, @Query("address") String address);

        @GET(USER_EDIT_NICKNAME)
        Call<ResponseBody> EditNickname(@Query("username") String username, @Query("nickname") String nickname);

        @GET(USER_EDIT_PHONE)
        Call<ResponseBody> EditPhone(@Query("username") String username, @Query("phone") String phone);

        @GET(USER_EDIT_HOUSE)
        Call<ResponseBody> EditHouse(@Query("username") String username, @Query("house") String house);


    }
}
