package top.zxpdmw.graduationproject.model;

import retrofit2.Callback;
import top.zxpdmw.graduationproject.bean.CommonResult;
import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.http.HttpManager;
import top.zxpdmw.graduationproject.http.NoticeRequest;

public class NoticeModel {
    final NoticeRequest userRequest = HttpManager.retrofit.create(NoticeRequest.class);


    public void DetailNotice(String title,Callback<CommonResult<Notice>> callback){
        userRequest.DetailNotice(title).enqueue(callback);
    }

    public void RecommendNotice(Callback<CommonResult<Notice>> callback){
        userRequest.RecommendNotice().enqueue(callback);
    }
}
