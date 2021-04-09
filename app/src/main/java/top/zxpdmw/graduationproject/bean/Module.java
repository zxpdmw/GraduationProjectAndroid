package top.zxpdmw.graduationproject.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.zxpdmw.graduationproject.R;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module implements Serializable {
    private int img_icon;
    private int title;

    public static final Module MY_INFO = new Module(R.drawable.me, R.string.me);
    public static final Module PROPERTY =new Module(R.drawable.property, R.string.property) ;
    public static final Module NOTICE=new Module(R.drawable.notice,R.string.notice);
    public static final Module PAGE=new Module(R.drawable.page, R.string.page );
    public static final Module COMPLAIN_REPAIR=new Module(R.drawable.complain, R.string.complain);
    public static final Module REPAIR=new Module(R.drawable.repair, R.string.repair);
    public static final Module HOUSE_KEEPING=new Module(R.drawable.housekeeping,R.string.houseKeeping);
    public static final Module HOUSE_RENT_SALE=new Module(R.drawable.houserentsale, R.string.houseRentSale);

    public static final Module HELP=new Module(R.drawable.help,R.string.help);
    public static final Module ABOUT=new Module(R.drawable.about,R.string.about);
    public static final Module HUANCUN=new Module(R.drawable.huancun,R.string.huancun);
    public static final Module HAOPING=new Module(R.drawable.haoping,R.string.haoping);


}
