package top.zxpdmw.graduationproject.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String nickname;
    private String username;
    private String password;
    private String address;
    private String house_id;
    private String phone;
}
