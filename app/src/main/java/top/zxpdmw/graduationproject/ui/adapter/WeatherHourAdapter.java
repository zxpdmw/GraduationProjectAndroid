package top.zxpdmw.graduationproject.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.SneakyThrows;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.WeatherHour;


public class WeatherHourAdapter extends RecyclerView.Adapter<WeatherHourAdapter.ViewHolder> {
    List<WeatherHour.Hour> list;

    public WeatherHourAdapter(List<WeatherHour.Hour> list) {
        this.list = list;
    }

    public void addData(List<WeatherHour.Hour> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_weather_hour, null);
        //实例化ViewHolder
        return new ViewHolder(view);
    }

    @Override
    @SneakyThrows
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        WeatherHour.Hour vo = list.get(position);
        //设置
        final String fxTime = vo.getFxTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        final String substring = fxTime.substring(0, fxTime.length() - 6);
        final Date parse = format.parse(substring);
        holder.tv_time.setText(parse.getHours()+"时");
        holder.img_icon.setImageResource(R.drawable.yun);
        holder.tv_rain.setText(vo.getPop() + '%');
        holder.tv_temp.setText(vo.getTemp());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_rain;
        ImageView img_icon;
        TextView tv_temp;

        public ViewHolder(View view) {
            super(view);
            tv_rain = view.findViewById(R.id.rain_probability);
            tv_temp = view.findViewById(R.id.weather_temp);
            tv_time = view.findViewById(R.id.time);
            img_icon = view.findViewById(R.id.weather_icon);
        }
    }
}
