package top.zxpdmw.graduationproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private Integer id;
    private String house_id;
    private String amount;
    private String address;
}
