package top.zxpdmw.graduationproject.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice implements Serializable {
    private String id;
    private String title;
    private String content;
    private String publisher;
    private String publish_time;
}
