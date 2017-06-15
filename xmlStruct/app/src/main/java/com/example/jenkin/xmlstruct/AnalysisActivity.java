package com.example.jenkin.xmlstruct;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import po.CustomUser;
import po.Grade;

public class AnalysisActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener,
        AdapterView.OnItemClickListener{

    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private Grade grade;
    // 图片封装为一个数组
    private int[] icon =  {
            R.drawable.ic_menu_quit,
            R.drawable.ic_menu_circle,
            R.drawable.ic_menu_rect
    };
    private ArrayList<CustomUser> jsonArray;
    private String[] iconName = { "返回", "扇形图", "直方图"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("ListCustomGrade", MODE_PRIVATE);
        String result = (String) sharedPreferences.getString("jsonArray", "");
        try {
            jsonArray =  tool.jsonDo.stringToJson(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//        try {
//            URL url = new URL("http://www.ly723.site:8080/ ");
//            HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();
//            httpsURLConnection.setRequestMethod("POST");
//            httpsURLConnection.setDoInput(true);
//            httpsURLConnection.setDoOutput(true);
//            httpsURLConnection.setRequestProperty("Charset", "UTF-8");
//
//            InputStream is = httpsURLConnection.getInputStream();
//            OutputStream os = httpsURLConnection.getOutputStream();
//
//            is.close();
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();
        SharedPreferences sharedPreferences2 =  getSharedPreferences("login_info", Activity.MODE_PRIVATE);
        String userid = sharedPreferences2.getString("name", "");
//        AnalysisSurface radiusView = new AnalysisSurface(this);

        setContentView(R.layout.fragment_circle);

//        ArrayList<Float> floatList = new ArrayList<Float>();
//        for (int i=0; i<100; i++)
//        {
//            Random random = new Random();
//            floatList.add(100*random.nextFloat());
//        }


        grade = new Grade();
        grade.setData(jsonArray, userid);
        Log.v("Tag", grade.toString());

        lockText();
        Bundle bundle = new Bundle();
        bundle.putSerializable("grade", grade);

        Fragment fragment = new CircleFragment();
        fragment.setArguments(bundle);
//        Fragment fragment = CircleFragment.newInstance( ""+1, ""+2);

//        setContentView(R.layout.fragment_circle);
//        setContentView(R.layout.analysis_surface);

//
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentCircle2, fragment);
            transaction.commit();
        ActionBar actionBar = getSupportActionBar();
        gview = (GridView)findViewById(R.id.menu_circle_grid);
        data_list = new ArrayList<Map<String, Object>>();
        getData();
        String [] from ={"image1","text1"};
        int [] to = {R.id.image1,R.id.text1};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.list_item_analysis, from, to);
        gview.setAdapter(sim_adapter);
        gview.setOnItemClickListener(this);

    }

    public void lockText(){
        TextView text;
        float sum = grade.countSum();
        ((TextView)findViewById(R.id.grade_number_average)).setText(""+grade.getAverageGrade());
        ((TextView)findViewById(R.id.grade_number_credit)).setText(""+grade.getCreditGrade());
        ((TextView)findViewById(R.id.grade_number_pass)).setText(""+grade.getPassGrade());
        ((TextView)findViewById(R.id.grade_number_fail)).setText(""+grade.getFailGrade());
        ((TextView)findViewById(R.id.grade_number_excellent)).setText(""+grade.getExcellentGrade());
        ((TextView)findViewById(R.id.grade_percent_fail)).setText(""+grade.getFailGrade()/sum);
        ((TextView)findViewById(R.id.grade_percent_pass)).setText(""+grade.getPassGrade()/sum);
        ((TextView)findViewById(R.id.grade_percent_average)).setText(""+grade.getAverageGrade()/sum);
        ((TextView)findViewById(R.id.grade_percent_credit)).setText(""+grade.getCreditGrade()/sum);
        ((TextView)findViewById(R.id.grade_percent_excellent)).setText(""+grade.getExcellentGrade()/sum);
    }

    public List<Map<String, Object>> getData(){
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image1", icon[i]);
            map.put("text1", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment;
        android.support.v4.app.FragmentManager fragmentManager;
        android.support.v4.app.FragmentTransaction transaction;
        Bundle bundle = new Bundle();
        bundle.putSerializable("grade", grade);
        switch (position)
        {
            case 0:
                this.finish();
                break;
            case 1:
                fragment = new CircleFragment();
//                bundle.putL
                fragment.setArguments(bundle);
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentCircle2, fragment);
                transaction.commit();
                break;
            case 2:
                fragment = new RectFragment();
                fragment.setArguments(bundle);
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentCircle2, fragment);
                transaction.commit();
                break;
            default:
                this.finish();
        }
    }
}
