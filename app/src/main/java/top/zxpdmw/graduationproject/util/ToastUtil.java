package top.zxpdmw.graduationproject.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

public class ToastUtil {

    private String TAG = "ToastUtil";
    private Toast mToast;
    private TimeCount timeCount;
    private String message;
    private boolean canceled = true;

    public ToastUtil(Context context, String msg) {
        message = msg;
        if (mToast == null) {
            mToast =Toast.makeText(context,message,Toast.LENGTH_SHORT);
        }
    }

    /**
     * 自定义居中显示toast
     */
    public void show() {
        mToast.show();
    }

    /**
     * 自定义时长、居中显示toast
     * @param duration
     */
    public void show(int duration) {
        timeCount = new TimeCount(duration, 100);
        if (canceled) {
            timeCount.start();
            show();
            canceled = false;
        }
    }

    /**
     * 隐藏toast
     */
    private void hide() {
        if (mToast != null) {
            mToast.cancel();
        }
        canceled = true;
    }


    /**
     *  自定义计时器
     */
    private class TimeCount extends CountDownTimer {

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