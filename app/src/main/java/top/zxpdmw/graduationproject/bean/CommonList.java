package top.zxpdmw.graduationproject.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonList<T>{
    private int code;
    private String message;
    private List<T> data;
}
