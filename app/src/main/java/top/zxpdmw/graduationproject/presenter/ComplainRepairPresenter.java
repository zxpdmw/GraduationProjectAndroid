package top.zxpdmw.graduationproject.presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.bean.CommonList;
import top.zxpdmw.graduationproject.bean.ComplainRepair;
import top.zxpdmw.graduationproject.model.ComplainRepairModel;
import top.zxpdmw.graduationproject.presenter.contract.ComplainRepairContract;

public class ComplainRepairPresenter implements ComplainRepairContract.Presenter {
    private ComplainRepairModel complainRepairModel;
    private ComplainRepairContract.View view;
    public ComplainRepairPresenter(ComplainRepairContract.View view){
        this.view=view;
        complainRepairModel=new ComplainRepairModel();
    }
    @Override
    public void AddComplainRepair(ComplainRepair complainRepair) {

    }

    @Override
    public void DeleteComplainRepair(Integer id) {

    }

    @Override
    public void EditComplainRepair(ComplainRepair complainRepair) {

    }

    @Override
    public void GetComplainRepair(String username) {
        complainRepairModel.GetComplainRepair(username, new Callback<CommonList<ComplainRepair>>() {
            @Override
            public void onResponse(Call<CommonList<ComplainRepair>> call, Response<CommonList<ComplainRepair>> response) {
                int code=response.body().getCode();
                String message=response.body().getMessage();
                if (code==666){
                    view.showList(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CommonList<ComplainRepair>> call, Throwable t) {

            }
        });
    }
}
