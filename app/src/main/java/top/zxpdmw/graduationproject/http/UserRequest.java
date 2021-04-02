package top.zxpdmw.graduationproject.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import top.zxpdmw.graduationproject.bean.CommonResult;
import top.zxpdmw.graduationproject.bean.User;

public interface UserRequest {
    @GET("user/login")
    Call<CommonResult> LoginUser(@Query("username")String username, @Query("password")String password);

    @POST("user/register")
    Call<CommonResult> RegisterUser(@Body User user);

    @POST("user/password")
    Call<CommonResult> EditPassword(@Body User user);

    @POST("/user/info")
    Call<CommonResult> EditUserInfo(@Body User user);

    @GET("/user/get")
    Call<CommonResult> GetUserInfo(@Query("username") String username);
}
