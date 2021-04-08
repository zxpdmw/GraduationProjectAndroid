package top.zxpdmw.graduationproject.ui.fragment.system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import java.util.List;

import lombok.SneakyThrows;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.presenter.HouseRentSalePresenter;
import top.zxpdmw.graduationproject.presenter.contract.HouseRentSaleContract;
import top.zxpdmw.graduationproject.ui.adapter.HouseRentSaleAdapter;
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.util.ConstUtil;
import top.zxpdmw.graduationproject.util.ToastUtils;


public class HouseListFragment extends Fragment implements HouseRentSaleContract.View, AdapterView.OnItemClickListener {
    ListView listView;
    HouseRentSalePresenter houseRentSalePresenter =new HouseRentSalePresenter(this);
    FragmentManager fManager;
    List<HouseRentSale> list;

    public static HouseListFragment newInstance(int type,FragmentManager fManager){
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
        ToastUtils.show("没有数据",500);

    }

    @SneakyThrows
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_house_list,container,false);
        listView=view.findViewById(R.id.house_list);
        fManager= getActivity().getSupportFragmentManager();
        if (getArguments()!=null){
            final Bundle arguments = getArguments();
            final int type = arguments.getInt("type");
            if (type==1){
                houseRentSalePresenter.HouseRent();
            }else if (type==2){
                houseRentSalePresenter.HouseSale();
            }else if (type==3){
                houseRentSalePresenter.HouseByUsername(arguments.getString("username"));
            }
        }else{
            ToastUtils.show(ConstUtil.SYSTEM_EXCEPTION,500);
        }
        listView.setOnItemClickListener(this);
        return view;
    }

    void initListView(List<HouseRentSale> list){
        HouseRentSaleAdapter houseRentSaleAdapter = new HouseRentSaleAdapter(list,getActivity());
        listView.setAdapter(houseRentSaleAdapter);
    }

    @Override
    public void showList(List<HouseRentSale> list) {
        this.list=list;
        initListView(list);
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoading() {

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



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        HouseMessageFragment houseMessageFragment = new HouseMessageFragment();
        Bundle bd = new Bundle();
        bd.putSerializable("content", list.get(position));
        houseMessageFragment.setArguments(bd);
        //获取Activity的控件
        //加上Fragment替换动画
        fTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
        fTransaction.replace(R.id.ly_content, houseMessageFragment);
        //调用addToBackStack将Fragment添加到栈中
        fTransaction.addToBackStack(null);
        fTransaction.commit();
    }
}
