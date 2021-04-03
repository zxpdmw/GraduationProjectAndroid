package top.zxpdmw.graduationproject.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import top.zxpdmw.graduationproject.bean.CommonResult;
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.http.HttpManager;
import top.zxpdmw.graduationproject.util.ConstUtil;

public class HouseRSModel {
    final HouseRSHttp houseRSHttp= HttpManager.retrofit.create(HouseRSHttp.class);

    public void HouseRent(Callback<CommonResult<HouseRentSale>> callback){
        houseRSHttp.HouseRent().enqueue(callback);
    }

    public void HouseSale(Callback<CommonResult<HouseRentSale>> callback){
        houseRSHttp.HouseSale().enqueue(callback);
    }

    public void HousePublish(HouseRentSale houseRentSale,Callback<ResponseBody> callback){
        houseRSHttp.HousePublish(houseRentSale).enqueue(callback);
    }

    public void HouseByUsername(String username,Callback<ResponseBody> callback){
        houseRSHttp.HouseByUsername(username).enqueue(callback);
    }

    interface HouseRSHttp{
        @GET(ConstUtil.HOUSE_RENT)
        Call<CommonResult<HouseRentSale>> HouseRent();
        @GET(ConstUtil.HOUSE_SALE)
        Call<CommonResult<HouseRentSale>> HouseSale();
        @GET(ConstUtil.HOUSE_RENT_SALE)
        Call<CommonResult<HouseRentSale>> HouseRentSale();

        @POST(ConstUtil.HOUSE_RENT_SALE_PUBLISH)
        Call<ResponseBody> HousePublish(@Body HouseRentSale houseRentSale);

        @POST(ConstUtil.HOUSE_RENT_SALE_EDIT_HOUSE)
        Call<ResponseBody> HouseEdit(@Body HouseRentSale houseRentSale);

        @GET(ConstUtil.HOUSE_RENT_SALE_USERNAME)
        Call<ResponseBody> HouseByUsername(@Query("username")String username);
    }

}
