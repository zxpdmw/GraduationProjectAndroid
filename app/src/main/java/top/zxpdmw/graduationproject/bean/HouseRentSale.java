package top.zxpdmw.graduationproject.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRentSale implements Serializable {
    private Integer id;
    private String price;
    private String t;
    private String username;
    private String message;
    private String phone;
    private String address;
    private String floor;
    private String ruzhu;
    private String area;
    private String orientation;
}
