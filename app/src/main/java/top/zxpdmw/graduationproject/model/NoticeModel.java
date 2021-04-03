package top.zxpdmw.graduationproject.model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import top.zxpdmw.graduationproject.bean.CommonResult;
import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.http.HttpManager;
import top.zxpdmw.graduationproject.util.ConstUtil;

public class NoticeModel {
    final NoticeHttp noticeHttp = HttpManager.retrofit.create(NoticeHttp.class);

    public void RecommendNotice(Callback<CommonResult<Notice>> callback){
        noticeHttp.RecommendNotice().enqueue(callback);
    }

    interface NoticeHttp{
        @GET(ConstUtil.NOTICE_RECOMMEND)
        Call<CommonResult<Notice>> RecommendNotice();
    }
}
