package top.zxpdmw.graduationproject.model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import top.zxpdmw.graduationproject.bean.CommonList;
import top.zxpdmw.graduationproject.bean.CommonOne;
import top.zxpdmw.graduationproject.bean.Notice;
import top.zxpdmw.graduationproject.http.HttpManager;
import top.zxpdmw.graduationproject.util.ConstUtil;

public class NoticeModel {
    final NoticeHttp noticeHttp = HttpManager.retrofit.create(NoticeHttp.class);

    public void RecommendNotice(Callback<CommonList<Notice>> callback){
        noticeHttp.RecommendNotice().enqueue(callback);
    }

    public void DetailNotice(String title,Callback<CommonOne<Notice>> callback){
        noticeHttp.DetailNotice(title).enqueue(callback);
    }

    interface NoticeHttp{
        @GET(ConstUtil.NOTICE_RECOMMEND)
        Call<CommonList<Notice>> RecommendNotice();

        @GET(ConstUtil.NOTICE_DETAIL)
        Call<CommonOne<Notice>> DetailNotice(@Query("title")String title);
    }
}
