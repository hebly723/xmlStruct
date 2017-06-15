package com.example.jenkin.xmlstruct;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import po.User;
import tool.HttpRequest;

public class MainActivity extends AppCompatActivity  implements View.OnFocusChangeListener, View.OnKeyListener {

    private EditText editText,
                    password,
                    message;
    private Button login_btn,
            ip_config;

    private CheckBox checkBox;
    String sessionid;
    boolean answer = false;

    SharedPreferences sharedPreferences;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        getSupportActionBar().hide();


//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        EditText editText = (EditText)findViewById(R.id.textview11);
//        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
//        View layout2 = factory.inflate(R.layout.activity_main,null);
//        Button button = (Button)layout2.findViewById(R.id.sample_edit_text0);
//        LinearLayout layout = new LinearLayout(this);
//        LinearLayoutCompat layout = new LinearLayoutCompat(this);
//        setContentView(layout);
//        TextView textView = new TextView(this);
//        textView.setBackgroundColor(GREEN);
//        textView.setText("什么情况");
//        layout.addView(textView);
//        layout.addView(button);
//        button.setText("......");
//        button.setOnClickListener(this);
        /**
         * 图片自适应测试
         */
//        int maxHeight = ZUI.dp2px(mContext, 300);
//        int height = (int) ((float) view.getWidth()/drawable.getMinimumWidth() * drawable.getMinimumHeight());
//        if (height > maxHeight) height = maxHeight;
//        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height));
        /**
         *
         */

        sharedPreferences =  getSharedPreferences("login_info", Activity.MODE_PRIVATE);

        editor = sharedPreferences.edit();


        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.sample_button_0);
        editText = (EditText)findViewById(R.id.sample_text_0);
        password = (EditText)findViewById(R.id.editText2);
        message = (EditText)findViewById(R.id.editText5);
        login_btn = (Button)findViewById(R.id.sample_button_0);
        checkBox = (CheckBox)findViewById(R.id.login_info_save);
        ip_config = (Button)findViewById(R.id.ipconfig);
//        button.setOnClickListener(this);
//        editText.setText("hello");
        editText.setOnFocusChangeListener(this);
        password.setOnFocusChangeListener(this);
        editText.setOnKeyListener(this);
        password.setOnKeyListener(this);
//        SharedPreferences settings;
        editText.setText(sharedPreferences.getString("name", ""));
        password.setText(sharedPreferences.getString("password", ""));


//        SharedPreferences serviceIpPreference = getSharedPreferences("service_ip", MODE_PRIVATE);
//        SharedPreferences serviceIpPreferences= serviceIpPreference.getSharedPreferences();


//        serviceIpPreference.setSummary(serviceIpPreferences.getString("service_ip", ""));
        settings =  getSharedPreferences("setting", MODE_PRIVATE);
//        Log.v("Tag",settings.getAll().toString());
//        tvCheckout.setText(settings.getBoolean(Consts.CHECKOUT_KEY, false)+"");
//        tvEditText.setText(settings.getString(Consts.EDIT_KEY, ""));
//        tvList.setText(settings.getString(Consts.LIST_KEY, "linc"));


        ip_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editText.setText("按钮事件被触发");
                Intent intent = new Intent();
                boolean flag = true;
                ArrayList list;
                if (checkBox.isChecked()) {
                    editor.putString("name", editText.getText().toString());
                    editor.putString("password", password.getText().toString());

                }
                /**
                 * 模拟http等待
                 */
                URL realUrl = null;



                String result = "";
//                HttpRequest.postDownloadJson("http://192.168.1.233:8080/Grade/login.action", user.toString2());
                try {
//                    Log.v("Tag", "http://"+settings.getString("service_ip", "").toString().trim()+":8080/Grade/login.action");
                    realUrl = new URL("http://"+settings.getString("service_ip", "").toString().trim()+":"+
                            settings.getString("service_port", "").toString().trim()+"/Grade/login.action");
                    Log.v("Tag", "-");
                    HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    Log.v("Tag", "0");
                    conn.setRequestProperty("Charset", "UTF-8");
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type",
                            "application/json");
                    Log.v("Tag", "1");

                    Log.v("Tag", "2.1");

                    Log.v("Tag", "2.2");
//                    conn.connect();
                    OutputStream os = conn.getOutputStream();
                    OutputStreamWriter writer = new OutputStreamWriter(os);
                    Log.v("Tag", "2.3");
                    //发送参数
                    JSONObject json = new JSONObject();
                    json.put("id", editText.getText().toString());
                    Log.v("Tag", "2.4");
                    json.put("password", password.getText().toString());
                    writer.write(json.toString());
                    Log.v("Tag", "2.5");
                    Log.v("Tag", json.toString());
                    writer.flush();
                    Log.v("Tag", "2.6");
                    InputStream is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    Log.v("Tag", "2.7");
                    StringBuffer sbf = new StringBuffer();
                    String strRead = null;
                    Log.v("Tag", "3");
                    while ((strRead = reader.readLine()) != null) {
                        sbf.append(strRead);
                        sbf.append("\r\n");
                    }
                    reader.close();
                    String cookieval = conn.getHeaderField("set-cookie");
                    Log.v("Tag", "2");
                    if(cookieval != null) {
                        sessionid = cookieval.substring(0, cookieval.indexOf(";"));
                    }
                    result = sbf.toString();
                    JSONObject jsonObject = new JSONObject(result);
                    answer = Boolean.parseBoolean(jsonObject.get("success").toString());
//                    Log.v("Tag", result);

                    is.close();
                    os.close();
                } catch (Exception e) {
                    Log.v("Tag",e.toString());
                }
                /**
                 * 模拟结束
                 */
                if (answer)
                {
                    editor.putString("name", editText.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.putString("sessionid", sessionid);
                    editor.commit();
                    intent.setClass(MainActivity.this, ListActivity2.class);
                    startActivity(intent);
                }
                else
                {
                    editor.commit();
                    message.setVisibility(View.VISIBLE);
                }
//                intent.putExtra("username", editText.getText().toString());
//                intent.putExtra("password", password.getText().toString());

            }
        });

//        button.setText("hahahahahaha");
//        EditText editText = (EditText)findViewById(R.id);
//        editText.setText("????");
//        editText.setText(R.string.app_name);
//        System.console().printf(editText.getText().toString());
//        editText.setText("到底是什么东西呢");

    }

    public void onFocusChange(View v, boolean hasFocus){
//        EditText text = (EditText) findViewById(v.getId());
        if (((EditText)v).getText().length()>0)
        {
            message.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_ENTER)
        {
            if (v.getId() == R.id.sample_text_0)
            {
                password.setFocusable(true);
                password.setFocusableInTouchMode(true);
                password.requestFocus();
                password.requestFocusFromTouch();
            }
            else if (v.getId() == R.id.editText2)
            {
                login_btn.setFocusable(true);
                login_btn.setFocusableInTouchMode(true);
                login_btn.requestFocus();
                login_btn.requestFocusFromTouch();
            }
        }
        return false;
    }
}
