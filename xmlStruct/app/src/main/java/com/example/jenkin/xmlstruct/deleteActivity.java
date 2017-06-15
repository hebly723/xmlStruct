package com.example.jenkin.xmlstruct;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import po.CustomUser;

public class deleteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private ArrayList<CustomUser> jsonArray;
    SharedPreferences settings;
    Toast toastYes,
            toastNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("成绩删除");
//        actionBar.set
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeActionContentDescription("返回");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        data_list = new ArrayList<Map<String, Object>>();
        setContentView(R.layout.activity_score);
        listView = (ListView)findViewById(R.id.listview);
        toastYes = Toast.makeText(this, "删除成功",Toast.LENGTH_LONG);
        toastNo = Toast.makeText(this, "删除失败",Toast.LENGTH_LONG);

        settings = getSharedPreferences("setting", MODE_PRIVATE);
        String[] from ={"text1","text2", "text3", "text4"};
        int[] to = {R.id.text1,R.id.text2,R.id.text3,R.id.text4};
        getData();
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);
        listView.setAdapter(sim_adapter);
        listView.setOnItemClickListener(this);
//        ArrayAdapter<String> aaData = new ArrayAdapter<String>(this);

    }
    public void getData(){
        data_list.clear();
        SharedPreferences sharedPreferences = getSharedPreferences("ListCustomGrade", MODE_PRIVATE);
        String result = (String) sharedPreferences.getString("jsonArray", "");
        try {
            jsonArray =  tool.jsonDo.stringToJson(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i=0; i<jsonArray.size();i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            CustomUser cjson = jsonArray.get(i);
            map.put("text1", cjson.getUsername());
            map.put("text2", cjson.getId());
            map.put("text3", cjson.getGrade());
            map.put("text4", cjson.getAddress());
            data_list.add(map);
        }
        Log.v("Tag", "4");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        new AlertDialog.Builder(this).setIcon(R.drawable.ic_action_warning).setTitle("是否删除该成绩") .
                setPositiveButton("确定",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                String result = "";
                CustomUser cuser = new CustomUser();
                Log.v("Tag", "2");
                String idC =  jsonArray.get(position).getId();
                SharedPreferences sharedPreferences2 =  getSharedPreferences("login_info", Activity.MODE_PRIVATE);
                String userid = sharedPreferences2.getString("name", "");
                String sessionid = sharedPreferences2.getString("sessionid", "");
                if (!userid.trim().equals(idC)) {
                    try {
                        URL realUrl = new URL("http://" + settings.getString("service_ip", "").trim() + ":" +
                                settings.getString("service_port", "").trim() + "/Grade/deleteGrade.action");

                        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

                        if (sessionid != null) {
                            conn.setRequestProperty("cookie", sessionid);
                        }

                        conn.setRequestProperty("Charset", "UTF-8");
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type",
                                "application/json");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        conn.connect();

                        OutputStream os = conn.getOutputStream();
                        OutputStreamWriter writer = new OutputStreamWriter(os);
                        //发送参数
                        JSONObject json = new JSONObject();
                        Log.v("Tag", "1");
                        cuser.setId(jsonArray.get(position).getId());
                        json = cuser.toJson();
//                    Log.v("Tag", );

                        writer.write(json.toString());
                        writer.flush();
                        InputStream is = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        StringBuffer sbf = new StringBuffer();
                        String strRead = null;

                        while ((strRead = reader.readLine()) != null) {
                            sbf.append(strRead);
                            sbf.append("\r\n");
                        }
                        reader.close();
                        result = sbf.toString();
                        Log.v("Tag", result);
//                        JSONObject jsonObject = new JSONObject(result);
//                    answer = Boolean.parseBoolean(jsonObject.get("success").toString());
//                    Log.v("Tag", result);

                        is.close();
                        os.close();
                    } catch (Exception e) {
                        Log.v("Tag", e.toString());
                    }
                    Log.v("Tag", "000000q"+result);
                    if (result.trim().equals("1")) {
                        Log.v("Tag", "1");
                        toastYes.show();
//                    new AlertDialog.Builder(deleteActivity.this).setMessage("成绩已经成功删除." + whichButton).create().show();
                        try {
                            Log.v("Tag", "4");
                            URL realUrl = new URL("http://" + settings.getString("service_ip", "").toString().trim() + ":" +
                                    settings.getString("service_port", "").toString().trim() + "/Grade/selectGrade.action");
                            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

                            sharedPreferences2 = getSharedPreferences("login_info", Activity.MODE_PRIVATE);


                            if (sessionid != null) {
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
                            Log.v("Tag", "6");
                            while ((strRead = reader.readLine()) != null) {
                                sbf.append(strRead);
                                sbf.append("\r\n");
                            }
                            Log.v("Tag", "66");
                            reader.close();
                            result = sbf.toString();
                            ;
//                    Log.v("Tag", result);

                            is.close();
//            os.close();
                        } catch (Exception e) {
                            Log.v("Tag", e.toString());
                        }
                        Log.v("Tag", "666");
                        SharedPreferences sharedPreferences = getSharedPreferences("ListCustomGrade", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("jsonArray", result);
                        editor.commit();
                        Log.v("Tag", "6666");
                        getData();
                        sim_adapter.notifyDataSetChanged();
//                        listView.removeViewAt(position);
                    }
                    else
                        toastNo.show();
                }
                else
                    toastNo.show();
//                    new AlertDialog.Builder(deleteActivity.this).setMessage("成绩已经删除失败.").create().show();
            }
        }).setNegativeButton("取消",
                new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                new AlertDialog.Builder(deleteActivity.this).setMessage(
                        "您已经选择了取消按钮，该成绩未被删除.").create().show();}
        }).show();
    }
}
