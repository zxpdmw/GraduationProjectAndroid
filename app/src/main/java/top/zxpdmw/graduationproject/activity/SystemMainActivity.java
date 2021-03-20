package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.Response;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.adapter.NoticeAdapter;
import top.zxpdmw.graduationproject.model.Notice;
import top.zxpdmw.graduationproject.util.HttpUtil;
import top.zxpdmw.graduationproject.util.JsonUtil;


public class SystemMainActivity extends AppCompatActivity {
    private List<Notice> data = null;
    private Context context;
    private NoticeAdapter noticeAdapter = null;
    private ListView list_notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemmain);
        getData();
//        context = SystemMainActivity.this;
//        list_notice = findViewById(R.id.list_item);
//        noticeAdapter = new NoticeAdapter((ArrayList<Notice>) data, context);
//        list_notice.setAdapter(noticeAdapter);
    }

    private void getData(){
        new Thread(()->{
            try {
                Response response = HttpUtil.GetNoParam("notice/recommend");
                JSONObject jsonObject=new JSONObject(Objects.requireNonNull(response.body()).string());
                if (jsonObject.getString("code").equals("666")){
                    data = JsonUtil.getNoticeList(jsonObject.getJSONArray("data"));
                    System.out.println(data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
