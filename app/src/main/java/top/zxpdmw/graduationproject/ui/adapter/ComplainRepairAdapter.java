package top.zxpdmw.graduationproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import top.zxpdmw.graduationproject.R;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.bean.ComplainRepair;

@AllArgsConstructor
public class ComplainRepairAdapter extends BaseAdapter {
    private List<ComplainRepair> complainRepairs;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_list_complainrepair, parent, false);
        final TextView viewById = convertView.findViewById(R.id.cr_type);
        final ImageView viewById1 = convertView.findViewById(R.id.cr_icon);
        final TextView viewById2 = convertView.findViewById(R.id.cr_message);

        viewById.setText(complainRepairs.get(position).getCr_type());
        viewById2.setText(complainRepairs.get(position).getMessage());
        viewById1.setBackgroundResource(R.drawable.touxiang);

        return convertView;
    }
}
