package com.minicup.materialdesign.MaterialDesign;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class RevealActivity extends AppCompatActivity {
    private TextView mView1;
    private TextView mView2;
    private TextView mView3;
    private TextView mView4;
    private View mCurrentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        mView1 = (TextView) findViewById(R.id.view1);
        mView1.setSelected(true);
        mCurrentView = mView1;
        mView2 = (TextView) findViewById(R.id.view2);
        mView3 = (TextView) findViewById(R.id.view3);
        mView4 = (TextView) findViewById(R.id.view4);

        mView4.setOnTouchListener(new View.OnTouchListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setHotspot(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_UP:


                        if(mCurrentView!=null){
                            if (mCurrentView == v) {
                                return true;
                            }
                            mCurrentView.setSelected(false);
                        }
                        mCurrentView = v;
                        v.setSelected(true);

                        Animator circularReveal = ViewAnimationUtils.createCircularReveal(v, (int) event.getX(), (int) event.getY(), 0, v.getHeight() + 10);
                        circularReveal.setDuration(300);
                        circularReveal.setInterpolator(new AccelerateInterpolator());
                        circularReveal.start();
                        break;
                }

                return true;
            }
        });

    }

    public void selectButton(View view) {

        if(mCurrentView!=null){
            mCurrentView.setSelected(false);
        }

        view.setSelected(true);
        mCurrentView = view;

    }


}
