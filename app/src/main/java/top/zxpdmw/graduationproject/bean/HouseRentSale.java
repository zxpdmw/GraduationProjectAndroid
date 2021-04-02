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
    private String status;
    private String hr_type;
    private String username;
    private String message;
    private String phone;
    private String address;
}
