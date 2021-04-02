package top.zxpdmw.graduationproject.model;

import retrofit2.Callback;
import top.zxpdmw.graduationproject.bean.CommonResult;
import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.http.HttpManager;
import top.zxpdmw.graduationproject.http.NoticeRequest;

public class MainModel {
    final NoticeRequest noticeRequest=HttpManager.retrofit.create(NoticeRequest.class);
    public void RecommendNotice(Callback<CommonResult<Notice>> callback){
        noticeRequest.RecommendNotice().enqueue(callback);
    }

}
