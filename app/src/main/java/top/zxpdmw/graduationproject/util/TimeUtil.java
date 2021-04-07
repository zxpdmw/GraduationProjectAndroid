package top.zxpdmw.graduationproject.util;

import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import lombok.SneakyThrows;

public class TimeUtil {
    public static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @SneakyThrows
    public static String descriptiveData(String date) {
        final Date parse = simpleDateFormat.parse(date);
        final long timestamp = parse.getTime();
        String descriptiveText = null;
        String format = "yyyy-MM-dd HH:mm:ss";
        //当前时间
        Calendar currentTime = Calendar.getInstance();
        //要转换的时间
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timestamp);
        //年相同
        if (currentTime.get(Calendar.YEAR) == time.get(Calendar.YEAR)) {
            //获取一年中的第几天并相减，取差值
            switch (currentTime.get(Calendar.DAY_OF_YEAR) - time.get(Calendar.DAY_OF_YEAR)) {
                case 1://当前比目标多一天，那么目标就是昨天
                    descriptiveText = "昨天";
                    format = "HH:mm";
                    break;
                case 0://当前和目标是同一天，就是今天
                    descriptiveText = "今天";
                    format = "HH:mm";
                    break;
                case -1://当前比目标少一天，就是明天
                    descriptiveText = "明天";
                    format = "HH:mm:ss";
                    break;
                default:
                    descriptiveText = null;
                    format = "MM-dd HH:mm";
                    break;
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        String formatDate = simpleDateFormat.format(time.getTime());
        if (!TextUtils.isEmpty(descriptiveText)) {
            return descriptiveText + " " + formatDate;
        }
        return formatDate;
    }

    @SneakyThrows
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
}
