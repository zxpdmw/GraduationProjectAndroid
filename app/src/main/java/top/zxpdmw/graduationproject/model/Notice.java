package top.zxpdmw.graduationproject.model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private String id;
    private String title;
    private String content;
    private String publisher;
    private String publish_time;
}
