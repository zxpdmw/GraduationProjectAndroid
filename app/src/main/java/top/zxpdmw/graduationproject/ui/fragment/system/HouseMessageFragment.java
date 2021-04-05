package top.zxpdmw.graduationproject.ui.fragment.system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.ui.adapter.HouseRentSaleAdapter;
import top.zxpdmw.graduationproject.util.ToastUtil;

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
            new ToastUtil(getActivity(),"now is no data").show(500);
        }

        return view;
    }
}
