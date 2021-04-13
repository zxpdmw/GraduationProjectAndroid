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
@Entity
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String nickname;
    private String username;
    private String password;
    private String address;
    private String house_id;
    private String phone;
}
