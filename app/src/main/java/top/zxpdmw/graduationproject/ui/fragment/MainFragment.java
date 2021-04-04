package top.zxpdmw.graduationproject.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.zxpdmw.graduationproject.R;

public class MainFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    Button register;
    FragmentManager fragmentManager;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fg_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentManager = getActivity().getSupportFragmentManager();
        return view;
    }

    @OnClick(R.id.login)
    void setLogin(){
        fragmentManager.beginTransaction().replace(R.id.fg_main_layout,new LoginFragment()).addToBackStack("login").commit();
    }

    @OnClick(R.id.register)
    void setRegister(){
        fragmentManager.beginTransaction().replace(R.id.fg_main_layout,new RegisterFragment()).addToBackStack("register").commit();
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
