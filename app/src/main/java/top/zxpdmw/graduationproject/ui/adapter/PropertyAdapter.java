package top.zxpdmw.graduationproject.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.bean.Property;

@AllArgsConstructor
public class PropertyAdapter extends BaseAdapter {
    private ArrayList<Property> properties;
    private Context context;

    @Override
    public int getCount() {
        return properties.size();
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
