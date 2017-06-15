package com.example.jenkin.xmlstruct;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import po.Grade;


public class CircleFragment extends Fragment {

    public CircleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

            Bundle bundle = getArguments();
        View view;
        Grade grade = (Grade) bundle.get("grade");
        view = new AnalysisSurface(this.getActivity(), true, grade);
        return view;
    }
}
