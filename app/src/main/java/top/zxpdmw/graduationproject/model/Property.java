package top.zxpdmw.graduationproject.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property implements Serializable {
    private Integer id;
    private String house_id;
    private String amount;
    private String address;
}
