package top.zxpdmw.graduationproject.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.hjq.toast.ToastUtils;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.AppDataBase;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.dao.UserDao;
import top.zxpdmw.graduationproject.presenter.UserPresenter;
import top.zxpdmw.graduationproject.presenter.contract.UserContract;
import top.zxpdmw.graduationproject.ui.activity.system.SystemMainActivity;
import top.zxpdmw.graduationproject.util.DbUtil;

public class LoginFragment extends Fragment implements UserContract.View {
    private Unbinder unbinder;
    @BindView(R.id.new_user)
    TextView newUser;
    @BindView(R.id.login_button)
    Button login;
    @BindView(R.id.login_username)
    EditText username;
    @BindView(R.id.login_password)
    EditText password;
    User user;
    ZLoadingDialog dialog;


    UserPresenter userPresenter = new UserPresenter(this);

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fg_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void dismissLoading() {
        dialog.dismiss();
    }

    @Override
    public void showLoading() {

    }

    @OnClick(R.id.new_user)
    public void setNewUser() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fg_main_layout, new RegisterFragment()).addToBackStack(null).commit();
    }

    @OnClick(R.id.login_button)
    public void loginUser() {
        if (username.getText().toString().equals("")){
           ToastUtils.show("请输入账号!");
            return;
        }

        if (password.getText().toString().equals("")){
            ToastUtils.show("请输入密码!");
            return;
        }

        userPresenter.LoginUser(username.getText().toString(), password.getText().toString());

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void LoadUser(User user) {
        this.user = user;
    }

    @Override
    public void switchFragment() {

    }

    @Override
    public void showError(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void jumpView(AppCompatActivity activity) {
        final Intent intent = new Intent(getActivity(), SystemMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user", user);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.show(msg);
    }

}
