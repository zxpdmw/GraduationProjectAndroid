package top.zxpdmw.graduationproject.model;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import top.zxpdmw.graduationproject.http.HttpManager;
import top.zxpdmw.graduationproject.util.ConstUtil;

public class PropertyModel {
    final PropertyHttp propertyHttp = HttpManager.retrofit.create(PropertyHttp.class);

    public void GetProperty(String houseId, Callback<ResponseBody> callback) {
        propertyHttp.GetProperty(houseId).enqueue(callback);
    }

    public void AddProperty(String houseId, String property, Callback<ResponseBody> callback) {
        propertyHttp.AddProperty(houseId, property).enqueue(callback);
    }

    interface PropertyHttp {
        @GET(ConstUtil.PROPERTY_GET)
        Call<ResponseBody> GetProperty(@Query("houseId") String houseId);

        @GET(ConstUtil.PROPERTY_ADD)
        Call<ResponseBody> AddProperty(@Query("houseId") String houseid, @Query("property") String property);
    }
}
