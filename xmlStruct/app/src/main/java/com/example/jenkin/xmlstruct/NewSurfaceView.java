package com.example.jenkin.xmlstruct;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by JenKin on 2017/5/31.
 */

public class NewSurfaceView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    public float x, y;
    public SurfaceHolder surfaceHolder;

    public NewSurfaceView(Context context){
        super(context);
        setOnTouchListener(this);
        surfaceHolder = this.getHolder();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }

}