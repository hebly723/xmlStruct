package com.example.jenkin.xmlstruct;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import po.CustomUser;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnCreateContextMenuListener {
    private ListView listView;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private Menu menu;
    private ArrayList<CustomUser> jsonArray;

    private int menuItemId = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("成绩修改");
//        actionBar.set
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeActionContentDescription("返回");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        data_list = new ArrayList<Map<String, Object>>();
        setContentView(R.layout.activity_score);
        listView = (ListView)findViewById(R.id.listview);
        String[] from ={"text1","text2", "text3", "text4"};
        int[] to = {R.id.text1,R.id.text2,R.id.text3,R.id.text4};
        getData();
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);
        listView.setAdapter(sim_adapter);

        listView.setOnItemClickListener(this);
//        ArrayAdapter<String> aaData = new ArrayAdapter<String>(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
        sim_adapter.notifyDataSetChanged();
    }

    public void getData()  {

        SharedPreferences sharedPreferences = getSharedPreferences("ListCustomGrade", MODE_PRIVATE);
        String result = (String) sharedPreferences.getString("jsonArray", "");
        try {
            jsonArray =  tool.jsonDo.stringToJson(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        data_list.clear();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        registerForContextMenu(view);
//        new AlertDialog.Builder(this).setIcon(R.drawable.ic_action_warning).setTitle("是否删除该成绩") .
//                setPositiveButton("确定",new DialogInterface.OnClickListener(){
//            public void onClick(DialogInterface dialog, int whichButton){
//                new AlertDialog.Builder(AddActivity.this).setMessage("成绩已经成功删除.").create().show();
//            }
//        }
//        ).setNegativeButton("取消",new DialogInterface.OnClickListener(){
//            public void onClick(DialogInterface dialog, int whichButton){
//                new AlertDialog.Builder(AddActivity.this).setMessage(
//                        "您已经选择了取消按钮，该成绩未被删除.").create().show();}
//        }).show();
//        bundle.putString();
//        Log.v("Tag", view.);
//        Bundle bundle = new Bundle();
//        bundle.putString("position", position+"");
        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.setClass(AddActivity.this, AlterScoreActivity.class);
        startActivity(intent);
        Log.v("Tag", "llllll");

    }

}
