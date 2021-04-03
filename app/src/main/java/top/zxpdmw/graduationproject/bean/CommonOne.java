package top.zxpdmw.graduationproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonOne<T> {
    private int code;
    private String message;
    private T data;
}
