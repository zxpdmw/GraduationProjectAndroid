package top.zxpdmw.graduationproject.model;

import androidx.lifecycle.LiveData;

import java.util.List;

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
    public static final String HOUSE_RENT="rent/all";
    public static final String HOUSE_SALE="sale/all";
    public static final String HOUSE_RENT_PUBLISH="rent/publish";
    public static final String HOUSE_SALE_PUBLISH="sale/publish";
    public static final String HOUSE_RENT_DELETE="rent/delete";
    public static final String HOUSE_SALE_DELETE="sale/delete";
    public static final String HOUSE_RENT_EDIT_HOUSE="rent/editprice";
    public static final String HOUSE_SALE_EDIT_HOUSE="sale/editprice";
    public static final String USER_ALL_HOUSE="user/allhouse";
    final HouseRentSaleHttp houseRentSaleHttp = HttpManager.retrofit.create(HouseRentSaleHttp.class);

    public void HouseRent(Callback<CommonList<HouseRentSale>> callback){
        houseRentSaleHttp.HouseRent().enqueue(callback);
    }

    public void HouseSale(Callback<CommonList<HouseRentSale>> callback){
        houseRentSaleHttp.HouseSale().enqueue(callback);
    }

    public void HouseSalePublish(HouseRentSale houseRentSale,Callback<CommonOne> callback){
        houseRentSaleHttp.SalePublish(houseRentSale).enqueue(callback);
    }
    public void HouseRentPublish(HouseRentSale houseRentSale,Callback<CommonOne> callback){
        houseRentSaleHttp.RentPublish(houseRentSale).enqueue(callback);
    }

    public void HouseByUsername(String username,Callback<CommonList<HouseRentSale>> callback){
        houseRentSaleHttp.HouseByUsername(username).enqueue(callback);
    }


    public void DeleteSaleHouse(Integer id, Callback<CommonOne> callback){
        houseRentSaleHttp.SaleHouseDelete(id).enqueue(callback);
    }

    public void DeleteRentHouse(Integer id, Callback<CommonOne> callback){
        houseRentSaleHttp.RentHouseDelete(id).enqueue(callback);
    }

    public void EditHouseRentPrice(Integer id,String price,Callback<CommonOne> callback){
        houseRentSaleHttp.EditRentHousePrice(id,price).enqueue(callback);
    }
    public void EditHouseSalePrice(Integer id,String price,Callback<CommonOne> callback){
        houseRentSaleHttp.EditSaleHousePrice(id,price).enqueue(callback);
    }

    interface HouseRentSaleHttp {
        @GET(HOUSE_RENT)
        Call<CommonList<HouseRentSale>> HouseRent();

        @GET(HOUSE_SALE)
        Call<CommonList<HouseRentSale>> HouseSale();

        @POST(HOUSE_SALE_PUBLISH)
        Call<CommonOne> SalePublish(@Body HouseRentSale houseRentSale);

        @POST(HOUSE_RENT_PUBLISH)
        Call<CommonOne> RentPublish(@Body HouseRentSale houseRentSale);

        @GET(USER_ALL_HOUSE)
        Call<CommonList<HouseRentSale>> HouseByUsername(@Query("username")String username);

        @GET(HOUSE_SALE_DELETE)
        Call<CommonOne> SaleHouseDelete(@Query("id")Integer id);

        @GET(HOUSE_RENT_DELETE)
        Call<CommonOne> RentHouseDelete(@Query("id")Integer id);

        @GET(HOUSE_RENT_EDIT_HOUSE)
        Call<CommonOne> EditRentHousePrice(@Query("id")Integer id,@Query("price")String price);

        @GET(HOUSE_SALE_EDIT_HOUSE)
        Call<CommonOne> EditSaleHousePrice(@Query("id")Integer id,@Query("price")String price);
    }

}
