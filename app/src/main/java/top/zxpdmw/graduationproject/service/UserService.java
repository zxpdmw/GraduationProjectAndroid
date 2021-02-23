package top.zxpdmw.graduationproject.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @GET("login")
    Call<ResponseBody> checkLogin(@Query("username")String username,@Query("password")String password);

    @GET("register")
    Call<ResponseBody> register(@Query("username")String username,@Query("password")String password);

    @POST
    Call<ResponseBody> checkRegister(@Field("username")String username,@Field("password")String password);
}
