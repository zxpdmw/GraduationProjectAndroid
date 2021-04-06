package top.zxpdmw.graduationproject.bean;

import java.util.Date;
import java.util.IdentityHashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class News {
    String id;//新闻唯一ID
    Date ctime;//发布时间
    String title;//文章标题
    String description;//文章描述
    String source;//文章来源
    String picUrl;//封面图片
    String url;//文章地址
}
