package top.zxpdmw.graduationproject;

import android.app.Application;
import android.content.Context;

import top.zxpdmw.graduationproject.util.ToastUtils;

public class BaseApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // Toast工具类
        ToastUtils.init(this);
    }
    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        return context;
    }

}
