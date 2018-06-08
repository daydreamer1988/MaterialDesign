package com.minicup.materialdesign.MaterialDesign;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class ZActivity extends AppCompatActivity {
    private TextView mSquare;
    private TextView mSquare2;
    private TextView mRound;
    private TextView mRound2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z);
        mSquare = (TextView) findViewById(R.id.square);
        mSquare2 = (TextView) findViewById(R.id.square2);
        mRound = (TextView) findViewById(R.id.round);
        mRound2 = (TextView) findViewById(R.id.round2);

        //square 10px
        //round 5px

        //Z = elevation + translationZ;
        mRound.setOnTouchListener(new View.OnTouchListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mRound.setTranslationZ(5);
                        break;
                    case MotionEvent.ACTION_UP:
                        //这里设置为0, 1, 2, 3, 4 都 可以 +round的elevation 5都会小于square的elevation10
                        //松开后置于square的下方
                        mRound.setTranslationZ(4);
                        break;
                }
                return true;
            }
        });


        mRound2.setOnTouchListener(new View.OnTouchListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mRound2.animate().z(11);
                        mRound.animate().translationZ(6);
                        break;
                    case MotionEvent.ACTION_UP:
                        mRound2.animate().z(9);
                        mRound.animate().translationZ(4);
                        break;
                }
                return true;
            }
        });
    }
}
