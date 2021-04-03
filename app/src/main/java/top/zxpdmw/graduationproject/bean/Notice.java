package top.zxpdmw.graduationproject.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private String publisher;
    private String publish_time;
}
