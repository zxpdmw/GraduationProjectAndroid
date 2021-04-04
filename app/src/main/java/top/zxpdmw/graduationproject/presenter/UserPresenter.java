package top.zxpdmw.graduationproject.presenter;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.CommonList;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.model.UserModel;
import top.zxpdmw.graduationproject.presenter.contract.UserContract;
import top.zxpdmw.graduationproject.ui.activity.SystemMainActivity;
import top.zxpdmw.graduationproject.ui.fragment.RegisterFragment;
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
        model.RegisterUser(user, new Callback<CommonList>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<CommonList> call, Response<CommonList> response) {
                int code=response.body().getCode();
                String msg=response.body().getMessage();
                if (code==666){
                    view.showMsg(ConstUtil.TO_LOGIN_ACTIVITY);
                    TimeUnit.SECONDS.sleep(1);
                    view.switchFragment();
                }else{
                    view.showMsg(msg);
                }

            }
            @Override
            public void onFailure(Call<CommonList> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }

}
