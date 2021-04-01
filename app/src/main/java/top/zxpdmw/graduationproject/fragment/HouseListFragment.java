package top.zxpdmw.graduationproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
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
import top.zxpdmw.graduationproject.util.ToastUtil;


public class HouseListFragment extends Fragment {
    private static List<HouseRentSale> rent,sale,my;
    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        getSaleHouseInfo();
        getRentHouseInfo();
        getMyHouseInfo();
    }

    public static HouseListFragment newInstance(String list){
        HouseListFragment houseListFragment=new HouseListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("list",list);
        houseListFragment.setArguments(bundle);
        return houseListFragment;
    }

    @SneakyThrows
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_house_list,container,false);
        ListView listView=view.findViewById(R.id.house_list);
        if (getArguments()!=null){
            final Bundle arguments = getArguments();
            final String list = arguments.getString("list");
            final List<HouseRentSale> houseRentSaleList = JsonUtil.getHouseRentSaleList(new JSONArray(list));
            HouseRentSaleAdapter houseRentSaleAdapter = new HouseRentSaleAdapter(houseRentSaleList,getActivity());
            listView.setAdapter(houseRentSaleAdapter);
        }else{
            new ToastUtil(getActivity(),ConstUtil.SYSTEM_EXCEPTION).show(500);
        }

        return view;
    }

    private  void getSaleHouseInfo() {
        new Thread(() -> {
            Response get = HttpUtil.Get(ConstUtil.HOUSE_SALE);
            try {
                JSONObject jsonObject = new JSONObject(Objects.requireNonNull(get.body()).string());
                if (jsonObject.getString("code").equals("666")) {
                     rent=JsonUtil.getHouseRentSaleList(jsonObject.getJSONArray("data"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private  void getRentHouseInfo() {
        new Thread(() -> {
            Response get = HttpUtil.Get(ConstUtil.HOUSE_RENT);
            try {
                JSONObject jsonObject = new JSONObject(Objects.requireNonNull(get.body()).string());
                if (jsonObject.getString("code").equals("666")) {
                    sale=JsonUtil.getHouseRentSaleList(jsonObject.getJSONArray("data"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    private  void getMyHouseInfo() {
        new Thread(() -> {
            Response get = HttpUtil.Get(ConstUtil.HOUSE_RENT);
            try {
                JSONObject jsonObject = new JSONObject(Objects.requireNonNull(get.body()).string());
                if (jsonObject.getString("code").equals("666")) {
                   my=JsonUtil.getHouseRentSaleList(jsonObject.getJSONArray("data"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
