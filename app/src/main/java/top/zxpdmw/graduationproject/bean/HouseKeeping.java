package top.zxpdmw.graduationproject.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseKeeping implements Serializable {
    private Integer id;
    private String hk_type;
    private String address;
    private String phone;
    private String status;
    private String username;
}
