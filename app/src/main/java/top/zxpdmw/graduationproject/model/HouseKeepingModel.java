package top.zxpdmw.graduationproject.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import top.zxpdmw.graduationproject.bean.CommonList;
import top.zxpdmw.graduationproject.bean.CommonOne;
import top.zxpdmw.graduationproject.bean.HouseKeeping;
import top.zxpdmw.graduationproject.http.HttpManager;
import top.zxpdmw.graduationproject.util.ConstUtil;

public class HouseKeepingModel {
    final HouseKeepingHttp houseKeepingHttp= HttpManager.retrofit.create(HouseKeepingHttp.class);

    public void DeleteHouseKeeping(Integer id, Callback<CommonOne> callback){
        houseKeepingHttp.DeleteHouseKeeping(id).enqueue(callback);
    }

    public void AddHouseKeeping(HouseKeeping houseKeeping,Callback<CommonOne> callback){
        houseKeepingHttp.AddHouseKeeping(houseKeeping).enqueue(callback);
    }

    public void EditHouseKeeping(HouseKeeping houseKeeping,Callback<CommonOne> callback){
        houseKeepingHttp.EditHouseKeeping(houseKeeping).enqueue(callback);
    }

    public void GetHouseKeeping(String username,Callback<CommonList<HouseKeeping>> callback){
        houseKeepingHttp.GetHouseKeeping(username).enqueue(callback);
    }


    interface HouseKeepingHttp{

        @GET(ConstUtil.HOUSE_KEEPING_DELETE)
        Call<CommonOne> DeleteHouseKeeping(@Query("id")Integer id);
        @POST(ConstUtil.HOUSE_KEEPING_ADD)
        Call<CommonOne> AddHouseKeeping(@Body HouseKeeping houseKeeping);
        @GET(ConstUtil.HOUSE_KEEPING_GET_BY_USERNAME)
        Call<CommonList<HouseKeeping>> GetHouseKeeping(@Query("username") String username);
        @POST(ConstUtil.HOUSE_KEEPING_EDIT)
        Call<CommonOne> EditHouseKeeping(@Body HouseKeeping houseKeeping);
    }

}
