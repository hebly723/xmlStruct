package tool;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import po.CustomUser;

/**
 * Created by JenKin on 2017/6/10.
 */

public class jsonDo {
    public static ArrayList<CustomUser> stringToJson(String result) throws JSONException {
        ArrayList<CustomUser> arrayList = new ArrayList<CustomUser>();
        JSONArray jsonArray = new JSONArray(result);
//            jsonObject.get("success");
        Log.v("Tag", jsonArray.toString());
        for (int i=0; i<jsonArray.length();i++) {
            CustomUser customUser = new CustomUser();
            JSONObject cjson = null;
            try {
                cjson = (JSONObject) jsonArray.get(i);
                customUser.setUsername(cjson.get("username").toString());
                customUser.setId(cjson.get("id").toString());
                customUser.setGrade(Integer.parseInt(cjson.get("grade").toString()));
                customUser.setAddress(cjson.get("address").toString());
                customUser.setSex(Integer.parseInt(cjson.get("sex").toString()));
                arrayList.add(customUser);
            } catch (JSONException e) {
                Log.v("Tag", e.toString());
            }
            Log.v("Tag", "3");
        }
        return arrayList;
    }
}
