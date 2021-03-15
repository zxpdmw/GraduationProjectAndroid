package top.zxpdmw.graduationproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {
    public String nickname;
    public String username;
    public String password;
    public String house_Id;
}
