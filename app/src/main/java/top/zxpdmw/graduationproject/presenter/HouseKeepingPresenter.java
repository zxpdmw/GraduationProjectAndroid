package top.zxpdmw.graduationproject.presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.CommonList;
import top.zxpdmw.graduationproject.bean.CommonOne;
import top.zxpdmw.graduationproject.bean.HouseKeeping;
import top.zxpdmw.graduationproject.model.HouseKeepingModel;
import top.zxpdmw.graduationproject.presenter.contract.HouseKeepingContract;
import top.zxpdmw.graduationproject.ui.BaseView;

public class HouseKeepingPresenter implements HouseKeepingContract.Presenter {

    private HouseKeepingContract.View view;
    private HouseKeepingModel houseKeepingModel;
    public HouseKeepingPresenter(HouseKeepingContract.View view){
        this.view=view;
        houseKeepingModel=new HouseKeepingModel();
    }

    @Override
    public void GetHouseKeeping(String username) {
        houseKeepingModel.GetHouseKeeping(username, new Callback<CommonList<HouseKeeping>>() {
            @Override
            public void onResponse(Call<CommonList<HouseKeeping>> call, Response<CommonList<HouseKeeping>> response) {
                int code=response.body().getCode();
                if (code==666){
                    view.showList(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CommonList<HouseKeeping>> call, Throwable t) {

            }
        });
    }

    @Override
    public void AddHouseKeeping(HouseKeeping houseKeeping) {
        houseKeepingModel.AddHouseKeeping(houseKeeping, new Callback<CommonOne>() {
            @Override
            public void onResponse(Call<CommonOne> call, Response<CommonOne> response) {

            }

            @Override
            public void onFailure(Call<CommonOne> call, Throwable t) {

            }
        });
    }

    @Override
    public void DeleteHouseKeeping(Integer id) {
        houseKeepingModel.DeleteHouseKeeping(id, new Callback<CommonOne>() {
            @Override
            public void onResponse(Call<CommonOne> call, Response<CommonOne> response) {

            }

            @Override
            public void onFailure(Call<CommonOne> call, Throwable t) {

            }
        });
    }

    @Override
    public void EditHouseKeeping(HouseKeeping houseKeeping) {

    }
}
