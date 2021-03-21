package top.zxpdmw.graduationproject.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import top.zxpdmw.graduationproject.adapter.NoticeAdapter;
import top.zxpdmw.graduationproject.model.ComplainRepair;
import top.zxpdmw.graduationproject.model.HouseKeeping;
import top.zxpdmw.graduationproject.model.HouseRentSale;
import top.zxpdmw.graduationproject.model.Notice;

public class JsonUtil{
    public static List<Notice> getNoticeList(@NotNull JSONArray jsonArray) throws JSONException {
        Type type = new TypeToken<List<Notice>>() {}.getType();
        List<Notice> list = new Gson().fromJson(jsonArray.toString(), type);
        return list;
    }

    public static List<HouseRentSale> getHouseRentSaleList(@NotNull JSONArray jsonArray) throws JSONException {
        Type type = new TypeToken<List<HouseRentSale>>() {}.getType();
        List<HouseRentSale> list = new Gson().fromJson(jsonArray.toString(), type);
        return list;
    }

    public static List<HouseRentSale> getHouseRentList(@NotNull JSONArray jsonArray) throws JSONException {
        Type type = new TypeToken<List<HouseRentSale>>() {}.getType();
        List<HouseRentSale> list = new Gson().fromJson(jsonArray.toString(), type);
        return list;
    }

    public static List<HouseRentSale> getHouseSaleList(@NotNull JSONArray jsonArray) throws JSONException {
        Type type = new TypeToken<List<HouseRentSale>>() {}.getType();
        List<HouseRentSale> list = new Gson().fromJson(jsonArray.toString(), type);
        return list;
    }

    public static List<HouseKeeping> getHouseKeepingList(@NotNull JSONArray jsonArray) throws JSONException {
        Type type = new TypeToken<List<HouseKeeping>>() {}.getType();
        List<HouseKeeping> list = new Gson().fromJson(jsonArray.toString(), type);
        return list;
    }

    public static List<ComplainRepair> getComplainRepairList(@NotNull JSONArray jsonArray) throws JSONException {
        Type type = new TypeToken<List<ComplainRepair>>() {}.getType();
        List<ComplainRepair> list = new Gson().fromJson(jsonArray.toString(), type);
        return list;
    }
}
