package top.zxpdmw.graduationproject.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.HouseKeeping;
import top.zxpdmw.graduationproject.presenter.HouseKeepingPresenter;
import top.zxpdmw.graduationproject.presenter.contract.HouseKeepingContract;

public class HouseKeepingActivity extends AppCompatActivity implements HouseKeepingContract.View {
    @BindView(R.id.toolbar)
     Toolbar toolbar;
    @BindView(R.id.add_house_keeping) ImageView imageView;
    HouseKeepingPresenter houseKeepingPresenter=new HouseKeepingPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_keeping);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.add_house_keeping)
    void addHouseKeeping(){
        //实例化布局
        View view = LayoutInflater.from(this).inflate(R.layout.add_house_keeping,null);
        EditText address = view.findViewById(R.id.edit_address);
        EditText phone=view.findViewById(R.id.edit_phone);
        EditText type=view.findViewById(R.id.edit_type);
        final HouseKeeping houseKeeping = new HouseKeeping();
        houseKeeping.setAddress(address.getText().toString());
        houseKeeping.setPhone(phone.getText().toString());
        houseKeeping.setHk_type(type.getText().toString());
        //找到并对自定义布局中的控件进行操作的示例

        //创建对话框
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setIcon(R.drawable.touxiang);//设置图标
        dialog.setTitle("添加投诉报修");//设置标题
        dialog.setView(view);//添加布局
        //设置按键
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                houseKeepingPresenter.AddHouseKeeping(houseKeeping);
            }
        });
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void init(){
        toolbar.setTitle("家政服务");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void showList(List<HouseKeeping> list) {

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