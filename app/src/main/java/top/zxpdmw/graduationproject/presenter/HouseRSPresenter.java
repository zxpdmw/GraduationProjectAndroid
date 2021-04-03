package top.zxpdmw.graduationproject.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.CommonResult;
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.model.HouseRSModel;
import top.zxpdmw.graduationproject.presenter.contract.HouseRSContract;
import top.zxpdmw.graduationproject.util.ConstUtil;

public class HouseRSPresenter implements HouseRSContract.Presenter {
    private static final String TAG = "HouseREPresenter";
    private HouseRSContract.View view;
    private HouseRSModel houseRSModel;
    public HouseRSPresenter(HouseRSContract.View view){
        this.view=view;
        houseRSModel =new HouseRSModel();
    }
    @Override
    public void HouseRent() {
        houseRSModel.HouseRent(new Callback<CommonResult<HouseRentSale>>() {
            @Override
            public void onResponse(Call<CommonResult<HouseRentSale>> call, Response<CommonResult<HouseRentSale>> response) {
                    int coed=response.body().getCode();
                if (coed==666){
                    List<HouseRentSale> list=response.body().getData();
                    view.showResult(list);
                }else{
                    view.showNoData();
                }
            }
            @Override
            public void onFailure(Call<CommonResult<HouseRentSale>> call, Throwable t) {
                view.showError(ConstUtil.SYSTEM_EXCEPTION);
            }
        });
    }

    @Override
    public void HouseSale() {
        houseRSModel.HouseSale(new Callback<CommonResult<HouseRentSale>>() {
            @Override
            public void onResponse(Call<CommonResult<HouseRentSale>> call, Response<CommonResult<HouseRentSale>> response) {
                int coed=response.body().getCode();
                if (coed==666){
                    List<HouseRentSale> list=response.body().getData();
                    view.showResult(list);
                }else{
                    view.showNoData();
                }
            }

            @Override
            public void onFailure(Call<CommonResult<HouseRentSale>> call, Throwable t) {

            }
        });
    }

    @Override
    public void HouseByUsername(String username) {
        houseRSModel.HouseByUsername(username, new Callback<ResponseBody>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject jsonObject=new JSONObject(response.body().string());
                String code=jsonObject.getString("code");
                if (code.equals("666")){
                    if (jsonObject.getString("data").equals("")){
                        view.showMsg("您还未发布任何房屋租赁信息");
                    }else {
                        Type type = new TypeToken<List<HouseRentSale>>() {}.getType();
                        List<HouseRentSale> list = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), type);
                        view.showResult(list);
                    }
                }else{
                    view.showNoData();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, ConstUtil.SYSTEM_EXCEPTION);
                view.showError(ConstUtil.SYSTEM_EXCEPTION);
            }
        });
    }

    @Override
    public void HousePublish(HouseRentSale houseRentSale) {
        houseRSModel.HousePublish(houseRentSale, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
