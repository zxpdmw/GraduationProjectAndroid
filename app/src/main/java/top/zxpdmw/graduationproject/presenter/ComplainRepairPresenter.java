package top.zxpdmw.graduationproject.presenter;

import org.json.JSONObject;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
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
        complainRepairModel.AddComplainRepair(complainRepair, new Callback<ResponseBody>() {
            @Override
            @SneakyThrows
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject jsonObject=new JSONObject(response.body().string());
                String code=jsonObject.getString("code");
                if (code.equals("666")){
                    view.showMsg(jsonObject.getString("message"));
                    GetComplainRepair(complainRepair.getUsername());
                    view.cancel();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void DeleteComplainRepair(String id,String username) {
        complainRepairModel.DeleteComplainRepair(id, username, new Callback<CommonList<ComplainRepair>>() {
            @Override
            public void onResponse(Call<CommonList<ComplainRepair>> call, Response<CommonList<ComplainRepair>> response) {

            }

            @Override
            public void onFailure(Call<CommonList<ComplainRepair>> call, Throwable t) {

            }
        });
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
