package top.zxpdmw.graduationproject;

import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.hjq.toast.IToastStyle;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.ToastBlackStyle;
import com.hjq.toast.style.ToastWhiteStyle;
import com.xuexiang.xui.XUI;

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        // Toast工具类
        ToastUtils.init(this);
        ToastUtils.initStyle(new IToastStyle() {
            @Override
            public int getGravity() {
                return Gravity.BOTTOM;
            }

            @Override
            public int getXOffset() {
                return 0;
            }

            @Override
            public int getYOffset() {
                return 0;
            }

            @Override
            public int getZ() {
                return 0;
            }

            @Override
            public int getCornerRadius() {
                return 10;
            }

            @Override
            public int getBackgroundColor() {
                return 0XFFEAEAEA;
            }

            @Override
            public int getTextColor() {
                return 0XBB000000;
            }

            @Override
            public float getTextSize() {
                return 25;
            }

            @Override
            public int getMaxLines() {
                return 0;
            }

            @Override
            public int getPaddingLeft() {
                return 5;
            }

            @Override
            public int getPaddingTop() {
                return 5;
            }

            @Override
            public int getPaddingRight() {
                return 5;
            }

            @Override
            public int getPaddingBottom() {
                return 5;
            }
        });
    }
}
