package top.zxpdmw.graduationproject.presenter;

import android.util.Log;

import org.json.JSONObject;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.zxpdmw.graduationproject.model.PropertyModel;
import top.zxpdmw.graduationproject.presenter.contract.PropertyContract;
import top.zxpdmw.graduationproject.util.ConstUtil;

public class PropertyPresenter implements PropertyContract.Presenter {
    private static final String TAG = "zwy-Property***";
    PropertyModel model;
    PropertyContract.View view;

    public PropertyPresenter(PropertyContract.View view) {
        this.view = view;
        model = new PropertyModel();
    }

    @Override
    public void GetProperty(String houseId) {
        model.GetProperty(houseId, new Callback<ResponseBody>() {


            @SneakyThrows
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject jsonObject = new JSONObject(response.body().string());
                String code = jsonObject.getString("code");
                if (code.equals("666")){
                    String data = jsonObject.getString("data");
                    view.LoadData(data);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                view.showError(ConstUtil.SYSTEM_EXCEPTION);
            }
        });
    }

    @Override
    public void AddProperty(String houseId, String property) {
        model.AddProperty(houseId, property, new Callback<ResponseBody>() {
            @Override
            @SneakyThrows
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject jsonObject=new JSONObject(response.body().string());
                String code=jsonObject.getString("code");
                String message=jsonObject.getString("message");
                if (code.equals("666")) {
                    String data = jsonObject.getString("data");
                    Log.d(TAG, data);
                    view.LoadData(data);
                }
                Log.d(TAG, message);
                view.showMsg(message);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showError(ConstUtil.SYSTEM_EXCEPTION);
            }
        });
    }
}
