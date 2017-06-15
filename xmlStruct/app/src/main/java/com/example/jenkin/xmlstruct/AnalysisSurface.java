package com.example.jenkin.xmlstruct;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import po.Grade;

/**
 * Created by JenKin on 2017/6/5.
 */

public class AnalysisSurface extends SurfaceView implements
        SurfaceHolder.Callback{
    public float x, centerX;
    public float y, centerY;
    public float radius;
    public float cursorInRadius, cursorRect;
    public float myScore;
    public boolean flag;
    public boolean circle = false;
    public RectF oval;
    private ArrayList<Float> gradeList = new ArrayList<Float>();
    public int[]  colorIs = {R.color.failGrade, R.color.passGrade,
        R.color.averageGrade, R.color.creditGrade,
            R.color.excellentGrade};

    private SurfaceHolder surfaceHolder;

    public AnalysisSurface(Context context)
    {
        super(context);
        flag = true;
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        this.setFocusable(true);

    }
    public AnalysisSurface(Context context, boolean flag, Grade grade)
    {
        super(context);
        gradeList.add(grade.getFailGrade());
        gradeList.add(grade.getPassGrade());
        gradeList.add(grade.getAverageGrade());
        gradeList.add(grade.getCreditGrade());
        gradeList.add(grade.getExcellentGrade());
        surfaceHolder = this.getHolder();
        this.flag = flag;
        surfaceHolder.addCallback(this);
        this.setFocusable(true);
    }

    public AnalysisSurface(Context context, boolean flag, Grade grade, boolean circle)
    {
        super(context);
        this.circle = circle;
        if (circle)
            this.myScore = grade.getMyScore();
        gradeList.add(grade.getFailGrade());
        gradeList.add(grade.getPassGrade());
        gradeList.add(grade.getAverageGrade());
        gradeList.add(grade.getCreditGrade());
        gradeList.add(grade.getExcellentGrade());
        surfaceHolder = this.getHolder();
        this.flag = flag;
        surfaceHolder.addCallback(this);
        this.setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        /**
         *
         *
         */
         /**
         *
         */
        centerX = this.getWidth() / 2;
        centerY = this.getHeight() / 2;
        radius = 3 * centerX / 5;
        oval = new RectF(centerX-radius, centerY-radius,
                centerX+radius, centerY+radius);
        if (flag)
        {
            drawCircle();
        }
        else
        {
            drawRect();
        }

    }
    public void drawRect()
    {
        if (surfaceHolder == null)
            return;

        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        if (canvas == null)
            return;

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawLine(centerX-radius, centerY-radius,
                centerX-radius, centerY+radius, paint);
        canvas.drawLine(centerX-radius, centerY-radius,
                centerX-radius-radius/20, centerY-radius+2*radius/20, paint);
        canvas.drawLine(centerX-radius, centerY-radius,
                centerX-radius+radius/20, centerY-radius+2*radius/20, paint);
        canvas.drawLine(centerX-radius, centerY+radius,
                centerX+6*radius/5, centerY+radius, paint);
        canvas.drawLine(centerX+6*radius/5-2*radius/20, centerY+radius-radius/20,
                centerX+6*radius/5, centerY+radius, paint);
        canvas.drawLine(centerX+6*radius/5-2*radius/20, centerY+radius+radius/20,
                centerX+6*radius/5, centerY+radius, paint);
        cursorRect = 0;
        float max = 0;
        for (float f : gradeList)
        {
            if (max< f)
            {
                max = f;
            }
        }
        for (int i=0; i<colorIs.length;i++)
        {
            paint.setColor(getResources().getColor(colorIs[i]));
//            float fradius =  ((float)gradeList.get(i)/sum)*360;
            canvas.drawRect(centerX-radius+(2*i+1)*radius/5, centerY+radius-(gradeList.get(i)/max)*2*radius,
                    centerX-radius+(2*i+2)*radius/5, centerY+radius, paint);
//            cursorInRadius += gradeRadius.get(i);
        }

//        canvas.drawRect(centerX-4*radius/5, centerY-radius,
        if (circle)
        {
            int sco = ((int)myScore)/10;
            if (sco<6)
                sco = 5;
            else if (sco>9)
                sco = 9;
            sco = sco -  5;



            paint.setColor(Color.BLACK);
            canvas.drawCircle( centerX-radius+(2*sco+1)*radius/5+radius/10, centerY+radius-(gradeList.get(sco)/max)*2*radius+radius/10, 1f/10*radius, paint);
        }
//                centerX-3*radius/5, centerY+radius, paint);

        surfaceHolder.unlockCanvasAndPost(canvas);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        // TODO Auto-generated method stub

    }

    public void drawCircle()
    {
        if (surfaceHolder == null)
            return;

        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        if (canvas == null)
            return;
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
//        canvas.drawCircle(centerX, centerY, radius, paint);
        Paint p = new Paint();
        Paint mPaint = new Paint();
        Paint mTextPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mTextPaint.setColor(Color.GREEN);

        p.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(Color.RED);
        /*RectF oval = new RectF(centerX-radius, centerY-radius,
                centerX+radius, centerY+radius);
        canvas.drawArc(oval, 0, 60, true, mPaint);
        cursorInRadius -= 60;
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(oval, cursorInRadius, 60, true, mPaint);*/

        ArrayList<Float> gradeRadius = new ArrayList<Float>();
        int sum = 0;
        for (Float i : gradeList)
        {
            sum += i;
        }
        for (Float i : gradeList)
        {
            gradeRadius.add(i*360/sum);
        }
        cursorInRadius = 0;
//        canvas.drawText("这里是"+arg, 200, 200, p);
        for (int i=0; i<colorIs.length;i++)
        {
            mPaint.setColor(getResources().getColor(colorIs[i]));
//            float fradius =  ((float)gradeList.get(i)/sum)*360;
            canvas.drawArc(oval, cursorInRadius, gradeRadius.get(i),
                    true, mPaint);
            cursorInRadius += gradeRadius.get(i);
        }
        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}