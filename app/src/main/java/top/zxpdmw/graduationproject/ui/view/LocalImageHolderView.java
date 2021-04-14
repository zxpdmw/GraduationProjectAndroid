package top.zxpdmw.graduationproject.ui.view;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

import top.zxpdmw.graduationproject.R;

public class LocalImageHolderView extends Holder<Integer> {
    ImageView imageView;
    public LocalImageHolderView(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView=itemView.findViewById(R.id.view_pager_image);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void updateUI(Integer data) {
        imageView.setImageResource(data);
    }

}
