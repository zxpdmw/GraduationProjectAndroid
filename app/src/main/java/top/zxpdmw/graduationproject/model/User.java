package top.zxpdmw.graduationproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String nickname;
    private String username;
    private String password;
    private String address;
    private String houseId;
    private String phone;
}
