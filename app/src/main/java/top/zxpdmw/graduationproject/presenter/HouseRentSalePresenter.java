package top.zxpdmw.graduationproject.presenter;

import android.util.Log;

import java.util.List;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.CommonList;
import top.zxpdmw.graduationproject.bean.CommonOne;
import top.zxpdmw.graduationproject.bean.HouseRentSale;
import top.zxpdmw.graduationproject.model.HouseRentSaleModel;
import top.zxpdmw.graduationproject.presenter.contract.HouseRentSaleContract;
import top.zxpdmw.graduationproject.util.ConstUtil;

public class HouseRentSalePresenter implements HouseRentSaleContract.Presenter {
    private static final String TAG = "HouseREPresenter";
    private HouseRentSaleContract.View view;
    private HouseRentSaleModel houseRentSaleModel;
    public HouseRentSalePresenter(HouseRentSaleContract.View view){
        this.view=view;
        houseRentSaleModel =new HouseRentSaleModel();
    }
    @Override
    public void HouseRent() {
        houseRentSaleModel.HouseRent(new Callback<CommonList<HouseRentSale>>() {
            @Override
            public void onResponse(Call<CommonList<HouseRentSale>> call, Response<CommonList<HouseRentSale>> response) {
                    int coed=response.body().getCode();
                if (coed==666){
                    List<HouseRentSale> list=response.body().getData();
                    view.showList(list);
                }
            }
            @Override
            public void onFailure(Call<CommonList<HouseRentSale>> call, Throwable t) {
                view.showError(ConstUtil.SYSTEM_EXCEPTION);
            }
        });
    }

    @Override
    public void HouseSale() {
        houseRentSaleModel.HouseSale(new Callback<CommonList<HouseRentSale>>() {
            @Override
            public void onResponse(Call<CommonList<HouseRentSale>> call, Response<CommonList<HouseRentSale>> response) {
                int coed=response.body().getCode();
                if (coed==666){
                    List<HouseRentSale> list=response.body().getData();
                    view.showList(list);
                }
            }

            @Override
            public void onFailure(Call<CommonList<HouseRentSale>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                view.showError(ConstUtil.SYSTEM_EXCEPTION);
            }
        });
    }

    @Override
    public void HouseByUsername(String username) {
        houseRentSaleModel.HouseByUsername(username, new Callback<CommonList<HouseRentSale>>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<CommonList<HouseRentSale>> call, Response<CommonList<HouseRentSale>> response) {
                int code=response.body().getCode();
                if (code==666){
                    if (response.body().getData()==null){
                        view.showMsg("您还未发布任何房屋租赁信息");
                    }else {
                        final List<HouseRentSale> data = response.body().getData();
                        view.showList(data);
                    }
                }else{
                    view.showNoData();
                }
            }

            @Override
            public void onFailure(Call<CommonList<HouseRentSale>> call, Throwable t) {
                Log.d(TAG, ConstUtil.SYSTEM_EXCEPTION);
                view.showError(ConstUtil.SYSTEM_EXCEPTION);
            }
        });
    }

    @Override
    public void HousePublish(HouseRentSale houseRentSale) {
        houseRentSaleModel.HousePublish(houseRentSale, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void DeleteHouse(HouseRentSale houseRentSale) {
        houseRentSaleModel.DeleteHouse(houseRentSale.getId(), new Callback<CommonOne>() {
            @Override
            public void onResponse(Call<CommonOne> call, Response<CommonOne> response) {
                int code=response.body().getCode();
                if (code==666){
                    view.delete(houseRentSale);
                }
            }

            @Override
            public void onFailure(Call<CommonOne> call, Throwable t) {

            }
        });
    }

    @Override
    public void EditHousePrice(HouseRentSale houseRentSale) {
        view.showMsg("价格修改成功");
        view.cancel();
    }
}
