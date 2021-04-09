package top.zxpdmw.graduationproject.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.presenter.UserPresenter;
import top.zxpdmw.graduationproject.presenter.contract.UserContract;
import top.zxpdmw.graduationproject.util.ToastUtils;

public class RegisterFragment extends Fragment implements UserContract.View {
    UserPresenter userPresenter = new UserPresenter(this);
    Unbinder unbinder;
    @BindView(R.id.register_houseId)
    EditText houseId;
    @BindView(R.id.register_nickname)
    EditText nickname;
    @BindView(R.id.register_username)
    EditText username;
    @BindView(R.id.register_password)
    EditText password;
    @BindView(R.id.register_button)
    Button register;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fg_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.register_button)
    void setRegister() {
        User user = new User();
        if (nickname.getText().toString().equals("")) {
            ToastUtils.show("请输入昵称!",500);

            return;
        }
        user.setNickname(nickname.getText().toString());
        if (username.getText().toString().equals("")) {
            ToastUtils.show("请输入账号!",500);

            return;
        }
        user.setUsername(username.getText().toString());
        if (password.getText().toString().equals("")) {
            ToastUtils.show("请输入密码!",500);

            return;
        }
        user.setPassword(password.getText().toString());
        if (houseId.getText().toString().equals("")) {
                        ToastUtils.show("请输入房屋号!",500);
            return;
        }
        user.setHouse_id(houseId.getText().toString());
        userPresenter.RegisterUser(user);
    }

    @OnTextChanged(value = R.id.register_username, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void setUsername() {
        if (username.getText().toString().length()<=5||username.getText().toString().length()>10){
            username.setError("账号长度不合法!");
        }
    }

    @OnTextChanged(value = R.id.register_password,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void setPassword(){
        if (password.getText().toString().length()<=5||password.getText().toString().length()>10){
            password.setError("密码长度不合法!");
        }
    }


    @Override
    public void switchFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fg_main_layout, new LoginFragment()).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void LoadUser(User user) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {
//        new ToastUtil(getActivity(), msg).show(500);
        ToastUtils.show(msg,500);

    }

    @Override
    public void jumpView(AppCompatActivity activity) {

    }

    @Override
    public void showMsg(String msg) {
//        new ToastUtil(getActivity(), msg).show(500);
        ToastUtils.show(msg,500);

    }
}
