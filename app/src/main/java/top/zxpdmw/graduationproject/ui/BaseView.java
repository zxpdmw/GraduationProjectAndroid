package top.zxpdmw.graduationproject.ui;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.ui.activity.SystemMainActivity;

public interface BaseView {
    void showError(String msg);
    void jumpView(AppCompatActivity activity);
    void showMsg(String msg);
}
