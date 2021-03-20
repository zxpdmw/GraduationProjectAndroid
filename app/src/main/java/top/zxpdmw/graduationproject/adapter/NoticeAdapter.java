package top.zxpdmw.graduationproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.model.Notice;

@AllArgsConstructor
public class NoticeAdapter extends BaseAdapter {
    private ArrayList<Notice> notices;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_list_notice,parent,false);
        ImageView notice_icon = convertView.findViewById(R.id.notice_icon);
        TextView notice_title = convertView.findViewById(R.id.notice_title);
        TextView notice_publish_time = convertView.findViewById(R.id.notice_publish_time);
        notice_icon.setBackgroundResource(R.drawable.background);
        notice_title.setText(notices.get(position).getTitle());
        notice_publish_time.setText(notices.get(position).getContent());
        return convertView;
    }
}
