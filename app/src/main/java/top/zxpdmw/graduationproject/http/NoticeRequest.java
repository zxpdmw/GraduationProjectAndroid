package top.zxpdmw.graduationproject.http;

import retrofit2.Call;
import retrofit2.http.GET;
import top.zxpdmw.graduationproject.bean.CommonResult;
import top.zxpdmw.graduationproject.bean.Notice;

public interface NoticeRequest {
    @GET("notice/recommend")
    Call<CommonResult<Notice>> RecommendNotice();

    @GET("notice/detail")
    Call<CommonResult<Notice>> DetailNotice(String title);
}
