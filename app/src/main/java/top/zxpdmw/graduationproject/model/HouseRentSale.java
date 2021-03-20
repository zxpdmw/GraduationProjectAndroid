package top.zxpdmw.graduationproject.model;

import java.util.PriorityQueue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRentSale {
    private Integer id;
    private String status;
    private String hr_type;
    private String username;
    private String message;
    private String phone;
    private String address;
}
