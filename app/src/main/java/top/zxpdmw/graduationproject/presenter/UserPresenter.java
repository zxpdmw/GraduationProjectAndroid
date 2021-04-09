package top.zxpdmw.graduationproject.presenter;

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
import top.zxpdmw.graduationproject.ui.activity.system.SystemMainActivity;
import top.zxpdmw.graduationproject.util.ConstUtil;


public class UserPresenter implements UserContract.Presenter {

    private static final String TAG = "zwy-----UserPresenter";
    private UserContract.View view;
    private UserModel model;

    public UserPresenter(UserContract.View view) {
        this.view = view;
        model = new UserModel();
    }

    @Override
    public void LoginUser(String username, String password) {
        model.LoginUser(username, password, new Callback<ResponseBody>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject jsonObject = new JSONObject(response.body().string());
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                switch (code) {
                    case "666":
                        view.showMsg(message);
                        final User data = new Gson().fromJson(jsonObject.getJSONObject("data").toString(), User.class);
                        view.LoadUser(data);
                        view.jumpView(new SystemMainActivity());
                        break;
                    default:
                        view.showMsg(message);
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
                int code = response.body().getCode();
                String msg = response.body().getMessage();
                if (code == 666) {
                    view.showMsg(ConstUtil.TO_LOGIN_ACTIVITY);
                    TimeUnit.SECONDS.sleep(1);
                    view.switchFragment();
                } else {
                    view.showMsg(msg);
                }

            }

            @Override
            public void onFailure(Call<CommonList> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }

    @Override
    public void EditPassword(String username, String password) {
        model.EditPassword(username, password, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void EditAddress(String username, String address) {
        model.EditAddress(username, address, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void EditHouse(String username, String house) {
        model.EditHouse(username, house, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void EditPhone(String username, String phone) {
        model.EditPhone(username, phone, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void EditNickname(String username, String nickname) {
        model.EditNickname(username, nickname, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
