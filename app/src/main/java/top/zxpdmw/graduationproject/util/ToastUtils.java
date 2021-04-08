package top.zxpdmw.graduationproject.util;

import android.app.Application;
import android.os.CountDownTimer;
import android.widget.Toast;

public class ToastUtils {
    private static Toast toast;
    private static Application sContext;
    public static void init(Application application) {
        sContext = application;
    }
    private static boolean canceled = true;
    private static TimeCount timeCount;

    private static void showShort(CharSequence sequence) {
        if (toast == null) {
            toast = Toast.makeText(sContext, sequence, Toast.LENGTH_SHORT);
        } else {
            toast.cancel();
            toast=Toast.makeText(sContext, sequence, Toast.LENGTH_LONG);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void show(String msg,int duration) {
        timeCount = new TimeCount(duration, 100);
        if (canceled) {
            timeCount.start();
            showShort(msg);
            canceled = false;
        }
    }

    private static void showLong(CharSequence sequence) {
        if (toast == null) {
            toast = Toast.makeText(sContext, sequence, Toast.LENGTH_LONG);
        } else {
            toast.cancel();
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.show();
    }

    private static void hide() {
        if (toast != null) {
            toast.cancel();
        }
        canceled = true;
    }


    private static class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval); //millisInFuture总计时长，countDownInterval时间间隔(一般为1000ms)
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            hide();//记数结束后调用取消Toast的显示
        }
    }

}