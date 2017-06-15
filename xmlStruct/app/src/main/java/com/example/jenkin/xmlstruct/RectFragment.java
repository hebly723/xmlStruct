package com.example.jenkin.xmlstruct;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import po.Grade;

public class RectFragment extends Fragment {

    public RectFragment() {
        // Required empty public constructor
    }
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    Bundle bundle = getArguments();
    Grade grade = (Grade)bundle.get("grade");
    View view;
    if (bundle.get("door") == null)
        view = new AnalysisSurface(this.getActivity(), false, grade);
    else
        view = new AnalysisSurface(this.getActivity(), false, grade, true);
        return view;
    }

}
