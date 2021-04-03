package top.zxpdmw.graduationproject.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import top.zxpdmw.graduationproject.bean.CommonList;
import top.zxpdmw.graduationproject.bean.ComplainRepair;
import top.zxpdmw.graduationproject.http.HttpManager;
import top.zxpdmw.graduationproject.util.ConstUtil;

public class ComplainRepairModel {
    final ComplainRepairHttp complainRepairHttp= HttpManager.retrofit.create(ComplainRepairHttp.class);

    public void AddComplainRepair(ComplainRepair complainRepair, Callback<ResponseBody> callback){
        complainRepairHttp.AddComplainRepair(complainRepair).enqueue(callback);
    }

    public void GetComplainRepair(String username,Callback<CommonList<ComplainRepair>> callback){
        complainRepairHttp.GetComplainRepair(username).enqueue(callback);
    }

    interface ComplainRepairHttp{
        @POST(ConstUtil.COMPLAIN_REPAIR_ADD)
        Call<ResponseBody> AddComplainRepair(@Body ComplainRepair complainRepair);

        @GET(ConstUtil.COMPLAIN_REPAIR_GET)
        Call<CommonList<ComplainRepair>> GetComplainRepair(@Query("username")String username);



    }
}
