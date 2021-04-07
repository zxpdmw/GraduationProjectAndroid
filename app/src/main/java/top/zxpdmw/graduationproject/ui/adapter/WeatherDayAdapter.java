package top.zxpdmw.graduationproject.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.SneakyThrows;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.WeatherDay;
import top.zxpdmw.graduationproject.bean.WeatherHour;
import top.zxpdmw.graduationproject.util.TimeUtil;

public class WeatherDayAdapter extends RecyclerView.Adapter<WeatherDayAdapter.ViewHolder> {
    List<WeatherDay.Day> list;

    public WeatherDayAdapter(List<WeatherDay.Day> list) {
        this.list = list;
    }

    public void add(List<WeatherDay.Day> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_weather_day, null);
        return new ViewHolder(view);
    }

    @Override
    @SneakyThrows
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        WeatherDay.Day vo = list.get(position);
        //设置
        final String fxTime = vo.getFxDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Date parse = format.parse(fxTime);
        final String weekOfDate = TimeUtil.getWeekOfDate(parse);
        holder.day.setText(weekOfDate);
        if (vo.getTempMin().length()==1){
            holder.min.setText("  "+vo.getTempMin());
        }
        holder.max.setText(vo.getTempMax());
        holder.icon.setImageResource(R.drawable.yun);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView day;
//        TextView pop;
        TextView max;
        TextView min;
        public ViewHolder(View view) {
            super(view);
            icon=view.findViewById(R.id.weather_icon);
            day=view.findViewById(R.id.weather_week);
//            pop=view.findViewById(R.id.weather_pop);
            max=view.findViewById(R.id.weather_max);
            min=view.findViewById(R.id.weather_min);
        }
    }
}
