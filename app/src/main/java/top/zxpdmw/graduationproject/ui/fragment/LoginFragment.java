package top.zxpdmw.graduationproject.ui.fragment;

import android.content.Intent;
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

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.presenter.UserPresenter;
import top.zxpdmw.graduationproject.presenter.contract.UserContract;
import top.zxpdmw.graduationproject.ui.activity.SystemMainActivity;
import top.zxpdmw.graduationproject.util.ToastUtil;

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

    UserPresenter userPresenter = new UserPresenter(this);

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fg_login, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.new_user)
    public void setNewUser() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fg_main_layout, new RegisterFragment()).addToBackStack(null).commit();
    }

    @OnClick(R.id.login_button)
    public void loginUser() {
        userPresenter.LoginUser(username.getText().toString(), password.getText().toString());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void LoadUser(User user) {
        this.user=user;
    }

    @Override
    public void switchFragment() {

    }

    @Override
    public void showError(String msg) {
        new ToastUtil(getActivity(),msg).show(500);
    }

    @Override
    public void jumpView(AppCompatActivity activity) {
        final Intent intent = new Intent(getActivity(), SystemMainActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    @Override
    public void showMsg(String msg) {
        new ToastUtil(getActivity(),msg).show(500);
    }

}
