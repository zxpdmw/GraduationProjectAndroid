package top.zxpdmw.graduationproject.presenter;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.CommonResult;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.model.UserModel;
import top.zxpdmw.graduationproject.presenter.contract.UserContract;
import top.zxpdmw.graduationproject.ui.activity.LoginActivity;
import top.zxpdmw.graduationproject.ui.activity.SystemMainActivity;
import top.zxpdmw.graduationproject.util.ConstUtil;


public class UserPresenter implements UserContract.Presenter {

    private static final String TAG = "zwy-----UserPresenter";
    private UserContract.View view;
    private UserModel model;

    public UserPresenter(UserContract.View view){
        this.view=view;
        model=new UserModel();
    }

    @Override
    public void LoginUser(String username, String password) {
        model.LoginUser(username,password,new Callback<ResponseBody>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject jsonObject=new JSONObject(response.body().string());
                String code=jsonObject.getString("code");
                String message=jsonObject.getString("message");
                final User data = new Gson().fromJson(jsonObject.getJSONObject("data").toString(), User.class);
                Log.d(TAG, "onResponse: "+code);
                Log.d(TAG, "onResponse: "+message);
                Log.d(TAG, "onResponse: "+data);
                switch (code) {
                    case "666":
                        view.showMsg(message);
                        view.LoadUser(data);
                        view.jumpView(new SystemMainActivity());
                        break;
                    case "5551":
                    case "5552":
                        view.showMsg(message);
                        break;
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showError(ConstUtil.SYSTEM_EXCEPTION);
            }
        });
    }

    @Override
    public void RegisterUser(User user) {
        model.RegisterUser(user, new Callback<CommonResult>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<CommonResult> call, Response<CommonResult> response) {
                int code=response.body().getCode();
                String msg=response.body().getMessage();
                Log.d(TAG, "onResponse: "+response);
                if (code==666){
                    view.showMsg(ConstUtil.TO_LOGIN_ACTIVITY);
                    view.jumpView(new LoginActivity());
                }else{
                    view.showMsg(msg);
                }

            }
            @Override
            public void onFailure(Call<CommonResult> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }
}
