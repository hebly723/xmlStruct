package com.example.jenkin.xmlstruct;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class AActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    // 图片封装为一个数组
    private int[] icon =  {
            R.drawable.ic_menu_quit,
            R.drawable.ic_action_classmember,
            R.drawable.ic_action_person
    };
    private String[] iconName = { "返回", "全体成绩", "我的成绩"};
    private ArrayList<CustomUser> jsonArray;

    private Grade grade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
//        AnalysisSurface radiusView = new AnalysisSurface(this);
        setContentView(R.layout.activity_analysis);
        ArrayList<Float> floatList = new ArrayList<Float>();
        Random random = new Random();
        for (int i=0; i<100; i++)
        {

            floatList.add(100*random.nextFloat());
        }

        SharedPreferences sharedPreferences = getSharedPreferences("ListCustomGrade", MODE_PRIVATE);
        String result = (String) sharedPreferences.getString("jsonArray", "");
        try {
            jsonArray =  tool.jsonDo.stringToJson(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences sharedPreferences2 =  getSharedPreferences("login_info", Activity.MODE_PRIVATE);
        String userid = sharedPreferences2.getString("name", "");
        grade = new Grade();
        grade.setData(jsonArray, userid);
        Log.v("Tag", grade.toString());

//        grade.setMyScore(90);
        lockText();
        Bundle bundle = new Bundle();
        bundle.putSerializable("grade", grade);

        Fragment fragment = new RectFragment();
        fragment.setArguments(bundle);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentRect2, fragment);
        transaction.commit();

        gview = (GridView)findViewById(R.id.menu_circle_grid);
        data_list = new ArrayList<Map<String, Object>>();
        getData();
        String [] from ={"image1","text1"};
        int [] to = {R.id.image1,R.id.text1};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.list_item_aa, from, to);
        gview.setAdapter(sim_adapter);
        gview.setOnItemClickListener(this);

    }

    public void lockText(){
        ((TextView)findViewById(R.id.grade_average)).setText(""+grade.getAverage());
        ((TextView)findViewById(R.id.grade_credit_percent)).setText(""+grade.getCreditPercent());
        ((TextView)findViewById(R.id.grade_fail_percent)).setText(""+grade.getFailPercent());
        ((TextView)findViewById(R.id.grade_max)).setText(""+grade.getMax());
        ((TextView)findViewById(R.id.grade_min)).setText(""+grade.getMin());
        ((TextView)findViewById(R.id.grade_sum)).setText(""+grade.getSum());
        ((TextView)findViewById(R.id.grade_my)).setText(""+grade.getMyScore());
        if (grade.getMyScore()<grade.getAverage())
        {
            ((TextView)findViewById(R.id.grade_word)).setText("低于班级平均分，需要加把劲了");
        }
        else if (grade.getMyScore()<85)
        {
            ((TextView)findViewById(R.id.grade_word)).setText("中等水平，请继续努力");
        }
        else if (grade.getMyScore()>85)
        {
            ((TextView)findViewById(R.id.grade_word)).setText("优秀，请再接再厉");
        }

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
                fragment = new RectFragment();
                fragment.setArguments(bundle);
                fragmentManager = getSupportFragmentManager();
                findViewById(R.id.miss_link).setVisibility(View.INVISIBLE);
                findViewById(R.id.miss_word).setVisibility(View.INVISIBLE);
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentRect2, fragment);
                transaction.commit();
                break;
            case 2:
                bundle.putString("door", "open");
                fragment = new RectFragment();
                fragment.setArguments(bundle);
                findViewById(R.id.miss_word).setVisibility(View.VISIBLE);
                findViewById(R.id.miss_link).setVisibility(View.VISIBLE);
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentRect2, fragment);
                transaction.commit();
                break;
            default:


        }
    }

}
