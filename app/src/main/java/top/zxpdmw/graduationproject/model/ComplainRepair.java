package top.zxpdmw.graduationproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplainRepair {
    private Integer id;
    private String cr_type;
    private String phone;
    private String address;
    private String status;
    private String message;
    private String username;
}
