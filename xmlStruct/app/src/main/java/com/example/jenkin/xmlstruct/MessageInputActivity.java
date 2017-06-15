package com.example.jenkin.xmlstruct;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

import static android.R.attr.password;

/**
 * 成绩添加页面
 * Created by hebly723 on 17-5-16.
 */

public class MessageInputActivity extends AppCompatActivity {
    private Button commitButton;
    private EditText studentNumber,
                        studentName,
                                text_grade;
    private RadioGroup sex;
    private Spinner address;
    String[] arrayList = {"北京",
        "天津",
        "上海",
        "重庆",
        "黑龙江",
        "辽宁",
        "吉林",
        "河北",
        "河南",
        "山西",
        "陕西",
        "内蒙古",
        "宁夏",
        "新疆",
        "青海",
        "西藏",
        "云南",
        "贵州",
        "广西",
        "广东",
        "福建",
        "江西",
        "安徽",
        "四川",
        "江西",
        "湖北",
        "湖南",
        "浙江",
        "山东",
        "海南",
        "甘肃",
        "香港",
        "澳门",
        "台湾"
    };
    SharedPreferences settings;
    Toast toastYes,
        toastNo;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("成绩添加");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeActionContentDescription("返回");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_input);

        text_grade = (EditText)findViewById(R.id.editText6);
        sex = (RadioGroup)findViewById(R.id.editText5);
        toastYes = Toast.makeText(this, "添加成功",Toast.LENGTH_LONG);
        toastNo = Toast.makeText(this, "添加失败",Toast.LENGTH_LONG);
        studentNumber   = (EditText) findViewById(R.id.editText3);
        studentName     = (EditText) findViewById(R.id.editText4);
        commitButton    = (Button)   findViewById(R.id.inputCommit);
        settings = getSharedPreferences("setting", MODE_PRIVATE);
        address = (Spinner)findViewById(R.id.editText7);
        ((RadioButton)sex.getChildAt(0)).setChecked(true);
//        sex.check(R.id.alter_woman);
//        text_grade = (EditText)findViewById(R.id);

        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;
                if (studentNumber.getText().toString().trim() != "") {
                    if (studentName.getText().toString().trim() == "") {
                        Log.v("Tag", "studentName");
                        studentName.setFocusable(true);
                        studentName.setFocusableInTouchMode(true);
                        studentName.requestFocus();
                        studentName.requestFocusFromTouch();
                        flag = false;
                    }
                } else {
                    Log.v("Tag", "studentNumber");
                    studentNumber.setFocusable(true);
                    studentNumber.setFocusableInTouchMode(true);
                    studentNumber.requestFocus();
                    studentNumber.requestFocusFromTouch();
                    flag = false;
                }
                String result = "";
                if (flag) {
                    URL realUrl = null;

//                HttpRequest.postDownloadJson("http://192.168.1.233:8080/Grade/login.action", user.toString2());
                    try {
                        Log.v("Tag", "http://" + settings.getString("service_ip", "").trim() + ":8080/Grade/login.action");
                        realUrl = new URL("http://" + settings.getString("service_ip", "").trim() + ":" +
                                settings.getString("service_port", "").trim() + "/Grade/addGrade.action");

                        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

                        SharedPreferences sharedPreferences2 =  getSharedPreferences("login_info", Activity.MODE_PRIVATE);

                        String sessionid = sharedPreferences2.getString("sessionid", "");

                        if(sessionid != null) {
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
                        CustomUser cuser = new CustomUser();
                        Log.v("Tag", "2");
                        cuser.setId(studentNumber.getText().toString());
                        Log.v("Tag", "3");
//                        Log.v("Tag", "address position"+address.getSelectedItemPosition()+"");
                        cuser.setAddress(arrayList[address.getSelectedItemPosition()]);
                        Log.v("Tag", "4");
                        cuser.setGrade(Integer.parseInt(text_grade.getText().toString()));
                        Log.v("Tag", "5");
                        Log.v("Tag", "sex = "+sex.getCheckedRadioButtonId()+"");
                        RadioButton radio = (RadioButton)findViewById(sex.getCheckedRadioButtonId());
                        Log.v("Tag", radio.getText()+"12121");
                        if (radio.getText().toString().trim().equals("男"))
                            cuser.setSex(0);
                        else
                            cuser.setSex(1);
                        Log.v("Tag", "6");
                        cuser.setUsername(studentName.getText().toString());
Log.v("Tag", "7");
// json.put("id", editText.getText().toString());
//                    json.put("password", password.getText().toString());
                        json = cuser.toJson();
                        Log.v("Tag", "8");
                        writer.write(json.toString());
                        Log.v("Tag", json.toString());
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
                }

                if ( result.trim().equals("true") ) {
                    toastYes.show();
                    studentName.clearComposingText();
                    studentNumber.clearComposingText();
                    address.clearFocus();
                    sex.clearFocus();
                    text_grade.clearComposingText();
                }
                else
                    toastNo.show();
//                    Toast.makeText(this)
//                String result = "";
//                    final AlertDialog ad = new AlertDialog.Builder(MessageInputActivity.this).
//                            setMessage("添加成功").show();


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
                /**
                 *
                 */

                /**
                 *
                 */
            }
        });
//        String [] from ={"province"};
//        int [] to = {R.id.province};
//        getData();
//        sim_adapter = new SimpleAdapter(this, data_list, R.layout.province_item, from, to);
//        spinner=(Spinner)findViewById(R.id.editText7);
//        spinner.setAdapter(sim_adapter);
//        LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layo.activity_input, null);
//        for (int i=0; i<checkBoxText.length; i++)
//        {
//            CheckBox checkBox = (CheckBox)getLayoutInflater().inflate(R.l)
//        }
    }
//
//    private void getData(){
//        for(int i=0;i<province.length;i++){
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("province", province[i]);
//            data_list.add(map);
//        }
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
