package top.zxpdmw.graduationproject.fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.Response;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.adapter.HouseRentSaleAdapter;
import top.zxpdmw.graduationproject.model.HouseRentSale;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.HttpUtil;
import top.zxpdmw.graduationproject.util.JsonUtil;


public class HouseListFragment extends Fragment {
    private List<HouseRentSale> list;
    public HouseListFragment(List<HouseRentSale> list){
        this.list=list;
        System.out.println(list+"构造方法");
    }

    public void setList(List<HouseRentSale> list) {
        this.list = list;
    }

    public List<HouseRentSale> getList() {
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_house_list,container,false);
        ListView listView=view.findViewById(R.id.house_list);
        HouseRentSaleAdapter houseRentSaleAdapter = new HouseRentSaleAdapter(list,getActivity());
        listView.setAdapter(houseRentSaleAdapter);
        return view;
    }

}
