package top.zxpdmw.graduationproject.ui.fragment.system;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.hjq.toast.ToastUtils;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.List;

import lombok.SneakyThrows;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.presenter.HouseRentSalePresenter;
import top.zxpdmw.graduationproject.presenter.contract.HouseRentSaleContract;
import top.zxpdmw.graduationproject.ui.activity.system.DetailHouseActivity;
import top.zxpdmw.graduationproject.ui.adapter.HouseRentSaleAdapter;
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.util.ConstUtil;


public class HouseListFragment extends Fragment implements HouseRentSaleContract.View{
    SwipeRecyclerView listView;
    HouseRentSalePresenter houseRentSalePresenter =new HouseRentSalePresenter(this);
    FragmentManager fManager;
    List<HouseRentSale> list;
    HouseRentSaleAdapter houseRentSaleAdapter;
    MaterialDialog.Builder builder;
    MaterialDialog show;

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
        ToastUtils.show("没有数据");
    }

    @Override
    public void add(HouseRentSale houseRentSale) {
        houseRentSaleAdapter.add(houseRentSale);
    }

    @Override
    public void delete(HouseRentSale houseRentSale) {
        houseRentSaleAdapter.delete(houseRentSale);
    }

    @Override
    public void cancel() {
        show.cancel();
    }

    @Override
    public void back() {

    }

    @Override
    public void updateData() {
        houseRentSaleAdapter.notifyDataSetChanged();
    }

    @SneakyThrows
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_house_list,container,false);
        listView=view.findViewById(R.id.house_list);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
                    @Override
                    public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
                        SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity()); // 各种文字和图标属性设置。
                        SwipeMenuItem editItem = new SwipeMenuItem(getActivity()); // 各种文字和图标属性设置。
                        editItem.setText("修改");
                        editItem.setTextSize(25);
                        editItem.setTextColor(getResources().getColor(R.color.white));
                        editItem.setHeight(MATCH_PARENT);
                        deleteItem.setText("删除");
                        deleteItem.setTextSize(25);
                        deleteItem.setTextColor(getResources().getColor(R.color.white));
                        deleteItem.setHeight(MATCH_PARENT);
                        deleteItem.setBackgroundColor(getResources().getColor(R.color.red));
                        editItem.setBackgroundColor(getResources().getColor(R.color.red));
                        deleteItem.setWidth(350);
                        editItem.setWidth(350);
                        rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。
                        leftMenu.addMenuItem(editItem); // 在Item左侧添加一个菜单。
                    }
                };
                listView.setSwipeMenuCreator(mSwipeMenuCreator);

                // 菜单点击监听。
                OnItemMenuClickListener mItemMenuClickListener = new OnItemMenuClickListener() {
                    @Override
                    public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                        // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                        menuBridge.closeMenu();
                        final int direction = menuBridge.getDirection();
                        if (direction==-1){
                                houseRentSalePresenter.DeleteHouse(list.get(position));
                        }else{
                            final View view1=View.inflate(getActivity(),R.layout.edit_house_price,null);
                            builder=new MaterialDialog.Builder(getActivity());
                            show=builder.customView(view1,false).show();
                            Button edit=view1.findViewById(R.id.edit);
                            Button cancel=view1.findViewById(R.id.cancel);
                            EditText new_price=view1.findViewById(R.id.new_price);
                            edit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    HouseRentSale houseRentSale=list.get(position);
                                    houseRentSale.setPrice(new_price.getText().toString());
                                    houseRentSalePresenter.EditHousePrice(houseRentSale);
                                }
                            });

                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    show.cancel();
                                }
                            });


                        }

                    }
                };
                listView.setOnItemMenuClickListener(mItemMenuClickListener);
            }
        }else{
            ToastUtils.show(ConstUtil.SYSTEM_EXCEPTION);
        }

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int adapterPosition) {
                final Intent intent = new Intent(getActivity(), DetailHouseActivity.class);
                intent.putExtra("house",list.get(adapterPosition));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_of_left);
            }
        });
        return view;
    }

    void initListView(List<HouseRentSale> list){
        houseRentSaleAdapter = new HouseRentSaleAdapter(list);
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
        ToastUtils.show(msg);
    }

    @Override
    public void jumpView(AppCompatActivity activity) {

    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.show(msg);
    }
}
