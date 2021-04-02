package top.zxpdmw.graduationproject.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.bean.ComplainRepair;

@AllArgsConstructor
public class ComplainRepairAdapter extends BaseAdapter {
    private ArrayList<ComplainRepair> complainRepairs;
    private Context context;
    @Override
    public int getCount() {
        return complainRepairs.size();
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
