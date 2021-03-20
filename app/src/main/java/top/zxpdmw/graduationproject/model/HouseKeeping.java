package top.zxpdmw.graduationproject.model;

import com.google.android.material.appbar.AppBarLayout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseKeeping {
    private Integer id;
    private String hk_type;
    private String address;
    private String phone;
    private String status;
    private String username;
}
