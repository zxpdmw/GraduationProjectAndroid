package top.zxpdmw.graduationproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.model.Notice;

@AllArgsConstructor
public class NoticeAdapter extends BaseAdapter {
    private List<Notice> notices;
    private Context context;

    @Override
    public int getCount() {
        return notices.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_notice, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.notice_icon = convertView.findViewById(R.id.notice_icon);
            viewHolder.notice_title = convertView.findViewById(R.id.notice_title);
            viewHolder.notice_publish_time = convertView.findViewById(R.id.notice_publish_time);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.notice_icon.setBackgroundResource(R.drawable.touxiang);
        viewHolder.notice_title.setText(notices.get(position).getTitle());
        viewHolder.notice_publish_time.setText(notices.get(position).getContent());
        return convertView;
    }

    static class ViewHolder {
        ImageView notice_icon;
        TextView notice_title;
        TextView notice_publish_time;
    }

    public void add(Notice data) {
        if (notices == null) {
            notices = new ArrayList<>();
        }
        notices.add(data);
        notifyDataSetChanged();
    }

    public void add(int position,Notice data){
        if (notices == null) {
            notices = new ArrayList<>();
        }
        notices.add(position,data);
        notifyDataSetChanged();
    }

    public void clear(){
        if (notices != null) {
            notices.clear();
        }
        notifyDataSetChanged();
    }

    public void remove(Notice data) {
        if(notices != null) {
            notices.remove(data);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if(notices != null) {
            notices.remove(position);
        }
        notifyDataSetChanged();
    }
}
