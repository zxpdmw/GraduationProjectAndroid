package top.zxpdmw.graduationproject.ui.fragment.system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hjq.toast.ToastUtils;

import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.ui.adapter.HouseRentSaleAdapter;

public class HouseMessageFragment extends Fragment {
    private HouseRentSaleAdapter adapter;
    public HouseMessageFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_house_content,container,false);
        TextView textView=view.findViewById(R.id.house_message_title);
        final Bundle arguments = getArguments();
        if (arguments!=null){
            final HouseRentSale content = (HouseRentSale) arguments.get("content");
            textView.setText(content.getMessage());
        }else{
            ToastUtils.show("没有数据");

        }

        return view;
    }
}
