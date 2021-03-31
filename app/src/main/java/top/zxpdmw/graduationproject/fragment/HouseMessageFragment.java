package top.zxpdmw.graduationproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.adapter.HouseRentSaleAdapter;
import top.zxpdmw.graduationproject.model.HouseRentSale;

public class HouseMessageFragment extends Fragment {
    private HouseRentSaleAdapter adapter;
    public HouseMessageFragment(HouseRentSaleAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_house_list,container,false);
        ListView listView = view.findViewById(R.id.house_list);
        listView.setAdapter(adapter);
        return view;
    }
}
