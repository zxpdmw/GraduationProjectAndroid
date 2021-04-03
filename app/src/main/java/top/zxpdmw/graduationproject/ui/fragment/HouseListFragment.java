package top.zxpdmw.graduationproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import lombok.SneakyThrows;
import okhttp3.Response;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.presenter.HouseRSPresenter;
import top.zxpdmw.graduationproject.presenter.contract.HouseRSContract;
import top.zxpdmw.graduationproject.ui.adapter.HouseRentSaleAdapter;
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.JsonUtil;
import top.zxpdmw.graduationproject.util.ToastUtil;


public class HouseListFragment extends Fragment implements HouseRSContract.View {
    ListView listView;
    HouseRSPresenter houseRSPresenter=new HouseRSPresenter(this);

    public static HouseListFragment newInstance(int type){
        HouseListFragment houseListFragment=new HouseListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        houseListFragment.setArguments(bundle);
        return houseListFragment;
    }

    public static HouseListFragment newInstance(int type,String username){
        HouseListFragment houseListFragment=new HouseListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        bundle.putString("username",username);
        houseListFragment.setArguments(bundle);
        return houseListFragment;
    }

    @Override
    public void showNoData() {
        new ToastUtil(getActivity(),"没有数据").show(500);
    }

    @SneakyThrows
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_house_list,container,false);
        listView=view.findViewById(R.id.house_list);
        if (getArguments()!=null){
            final Bundle arguments = getArguments();
            final int type = arguments.getInt("type");
            if (type==1){
                houseRSPresenter.HouseRent();
            }else if (type==2){
                houseRSPresenter.HouseSale();
            }else if (type==3){
                houseRSPresenter.HouseByUsername(arguments.getString("username"));
            }
        }else{
            new ToastUtil(getActivity(),ConstUtil.SYSTEM_EXCEPTION).show(500);
        }
        return view;
    }

    void initListView(List<HouseRentSale> list){
        HouseRentSaleAdapter houseRentSaleAdapter = new HouseRentSaleAdapter(list,getActivity());
        listView.setAdapter(houseRentSaleAdapter);
    }

    @Override
    public void showResult(List<HouseRentSale> list) {
        initListView(list);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void jumpView(AppCompatActivity activity) {

    }

    @Override
    public void showMsg(String msg) {

    }
}
