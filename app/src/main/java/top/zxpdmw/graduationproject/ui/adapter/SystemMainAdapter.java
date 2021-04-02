package top.zxpdmw.graduationproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.Module;

@AllArgsConstructor
public class SystemMainAdapter extends BaseAdapter {
    private List<Module> list;
    private Context context;

    @Override
    public int getCount() {
        return list.size();
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_list_main,parent,false);
        ImageView notice_icon = convertView.findViewById(R.id.main_icon);
        TextView notice_title = convertView.findViewById(R.id.main_title);
        notice_icon.setBackgroundResource(list.get(position).getImg_icon());
        notice_title.setText(list.get(position).getTitle());
        return convertView;
    }
}
