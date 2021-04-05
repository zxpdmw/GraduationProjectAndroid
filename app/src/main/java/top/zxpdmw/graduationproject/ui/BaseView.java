package top.zxpdmw.graduationproject.ui;

import androidx.appcompat.app.AppCompatActivity;

public interface BaseView {
    void showError(String msg);

    void jumpView(AppCompatActivity activity);

    void showMsg(String msg);

}

