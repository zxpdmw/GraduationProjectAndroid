package top.zxpdmw.graduationproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.ui.fragment.MainFragment;


public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.login)
//    Button login;
//    @BindView(R.id.register)
//    Button register;
    @BindView(R.id.icon)
    ImageView image;
    @BindView(R.id.fg_main_layout)
    FrameLayout frameLayout;

    private FragmentManager fragmentManager=getSupportFragmentManager();
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RotateAnimation rotate  = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(2000);//设置动画持续周期
        rotate.setRepeatCount(-1);//设置重复次数
        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        rotate.setStartOffset(10);//执行前的等待时间
        image.setAnimation(rotate);

        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (mainFragment==null){
            mainFragment=new MainFragment();
            fragmentTransaction.add(R.id.fg_main_layout,mainFragment);
        }else{
            fragmentTransaction.show(mainFragment);
        }
        fragmentTransaction.commit();


//        login.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//        });
//
//        register.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//            startActivity(intent);
//        });
    }
}
