package top.zxpdmw.graduationproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.fragment.HouseMessageFragment;
import top.zxpdmw.graduationproject.model.HouseRentSale;


public class HouseRentSaleAdapter extends BaseAdapter {
    private List<HouseRentSale> houseRentSales;
    private Context context;

    public HouseRentSaleAdapter(List<HouseRentSale> list,Context context){
        this.houseRentSales=list;
        this.context=context;
    }

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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_list_house,parent,false);
        TextView house_nickname=convertView.findViewById(R.id.house_nickname);
        TextView house_message= convertView.findViewById(R.id.house_message);
        TextView house_phone = convertView.findViewById(R.id.house_phone);
        TextView house_address = convertView.findViewById(R.id.house_address);
        house_nickname.setText("张小培");
        house_message.setText(houseRentSales.get(position).getMessage());
        house_phone.setText(houseRentSales.get(position).getPhone());
        house_address.setText(houseRentSales.get(position).getAddress());
        return convertView;
    }
}
