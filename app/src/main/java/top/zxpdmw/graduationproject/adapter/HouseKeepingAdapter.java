package top.zxpdmw.graduationproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.model.HouseKeeping;
import top.zxpdmw.graduationproject.model.Notice;

@AllArgsConstructor
public class HouseKeepingAdapter extends BaseAdapter {
    private ArrayList<HouseKeeping> houseKeepings;
    private Context context;
    @Override
    public int getCount() {
        return houseKeepings.size();
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
        return null;
    }
}
