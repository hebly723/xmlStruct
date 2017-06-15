package com.example.jenkin.xmlstruct;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import po.CustomUser;

public class ListActivity2 extends Activity implements AdapterView.OnItemClickListener {
    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private JSONArray jsonArray;
    SharedPreferences settings;
    // 图片封装为一个数组
    private int[] icon =  { R.drawable.ic_grade_search,
                               R.drawable.ic_grade_add,
                                R.drawable.ic_grade_alta,
                                R.drawable.ic_grade_delete,
                                R.drawable.ic_grade_analysis,
                                R.drawable.ic_grade_calculate,
                                R.drawable.ic_grade_setting
                                };
    private String[] iconName = { "查询", "新增", "修改", "删除", "统计", "分析", "设置"};
    private ArrayList<CustomUser> arrayList = new ArrayList<CustomUser>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        gview = (GridView) findViewById(R.id.gview);
        //ÐÂ½¨List
        data_list = new ArrayList<Map<String, Object>>();
        //»ñÈ¡Êý¾Ý
        getData();
        //ÐÂ½¨ÊÊÅäÆ÷
        String [] from ={"image","text"};
        int [] to = {R.id.image,R.id.text};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.list_item, from, to);
        //ÅäÖÃÊÊÅäÆ÷
        gview.setAdapter(sim_adapter);

        gview.setOnItemClickListener(this);
        settings = getSharedPreferences("setting", MODE_PRIVATE);
        String result = "";

        try {
            Log.v("Tag", "4");
            URL realUrl = new URL("http://"+settings.getString("service_ip", "").toString().trim()+":"+
                    settings.getString("service_port", "").toString().trim()+"/Grade/selectGrade.action");
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();

            SharedPreferences sharedPreferences2 =  getSharedPreferences("login_info", Activity.MODE_PRIVATE);

            String sessionid = sharedPreferences2.getString("sessionid", "");

            if(sessionid != null) {
                conn.setRequestProperty("cookie", sessionid);
            }
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type",
                    "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sbf = new StringBuffer();
            String strRead = null;

            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();;
//                    Log.v("Tag", result);

            is.close();
//            os.close();
        } catch (Exception e) {
            Log.v("Tag",e.toString());
        }

        SharedPreferences sharedPreferences = getSharedPreferences("ListCustomGrade", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("jsonArray", result);
        editor.commit();

    }



    public List<Map<String, Object>> getData(){
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent();

            switch (position){
                case 0:
                    intent.setClass(ListActivity2.this,MyScoreActivity.class);
                    break;
                case 1:
                    intent.setClass(ListActivity2.this,MessageInputActivity.class);
                    break;
                case 2:
                    intent.setClass(ListActivity2.this, AddActivity.class);
                    break;
                case 3:
                    intent.setClass(ListActivity2.this, deleteActivity.class);
                    break;
                case 4:
                    intent.setClass(ListActivity2.this, AnalysisActivity.class);
                    break;
                case 5:
                    intent.setClass(ListActivity2.this, AActivity.class);
                    break;
                case 6:
                    intent.setClass(ListActivity2.this, SettingActivity.class);
                    break;
                default:
                    intent.setClass(ListActivity2.this,ErrorActivity.class);

            }
            startActivity(intent);
        }
}