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
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.http.HttpManager;
import top.zxpdmw.graduationproject.util.ConstUtil;

public class HouseRentSaleModel {
    public static final String HOUSE_RENT_SALE="houserentsale/all";
    public static final String HOUSE_RENT="houserentsale/rent";
    public static final String HOUSE_SALE="houserentsale/sale";
    public static final String HOUSE_RENT_SALE_PUBLISH="houserentsale/publishe";
    public static final String HOUSE_RENT_SALE_DELETE="houserentsale/delete";
    public static final String HOUSE_RENT_SALE_EDIT_HOUSE="houserentsale/edit";
    public static final String HOUSE_RENT_SALE_USERNAME="houserentsale/get";
    final HouseRentSaleHttp houseRentSaleHttp = HttpManager.retrofit.create(HouseRentSaleHttp.class);

    public void HouseRent(Callback<CommonList<HouseRentSale>> callback){
        houseRentSaleHttp.HouseRent().enqueue(callback);
    }

    public void HouseSale(Callback<CommonList<HouseRentSale>> callback){
        houseRentSaleHttp.HouseSale().enqueue(callback);
    }

    public void HousePublish(HouseRentSale houseRentSale,Callback<ResponseBody> callback){
        houseRentSaleHttp.HousePublish(houseRentSale).enqueue(callback);
    }

    public void HouseByUsername(String username,Callback<CommonList<HouseRentSale>> callback){
        houseRentSaleHttp.HouseByUsername(username).enqueue(callback);
    }

    public void EditHouseRentSale(HouseRentSale houseRentSale, Callback<CommonOne<HouseRentSale>> callback){
        houseRentSaleHttp.HouseEdit(houseRentSale).enqueue(callback);
    }

    interface HouseRentSaleHttp {
        @GET(HOUSE_RENT)
        Call<CommonList<HouseRentSale>> HouseRent();

        @GET(HOUSE_SALE)
        Call<CommonList<HouseRentSale>> HouseSale();

        @GET(HOUSE_RENT_SALE)
        Call<CommonList<HouseRentSale>> HouseRentSale();

        @POST(HOUSE_RENT_SALE_PUBLISH)
        Call<ResponseBody> HousePublish(@Body HouseRentSale houseRentSale);

        @POST(HOUSE_RENT_SALE_EDIT_HOUSE)
        Call<CommonOne<HouseRentSale>> HouseEdit(@Body HouseRentSale houseRentSale);

        @GET(HOUSE_RENT_SALE_USERNAME)
        Call<CommonList<HouseRentSale>> HouseByUsername(@Query("username")String username);
    }

}
