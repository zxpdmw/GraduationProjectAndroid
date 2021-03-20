package top.zxpdmw.graduationproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.model.HouseRentSale;
import top.zxpdmw.graduationproject.model.Notice;

@AllArgsConstructor
public class HouseRentSaleAdapter extends BaseAdapter {
    private ArrayList<HouseRentSale> houseRentSales;
    private Context context;
    @Override
    public int getCount() {
        return houseRentSales.size();
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
