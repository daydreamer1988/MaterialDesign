package com.minicup.materialdesign.MaterialDesign;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.minicup.materialdesign.R;

public class ShadowAndOutlineActivity extends AppCompatActivity {
    private TextView mSecondTv;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_and_outline);
        mSecondTv = (TextView) findViewById(R.id.secondTv);

        ViewOutlineProvider provider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //该方法会被多次调用
                Toast.makeText(ShadowAndOutlineActivity.this, ""+view.getWidth()+":"+view.getHeight(), Toast.LENGTH_SHORT).show();

                //1
                //int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
                //outline.setOval(0, 0, size, size);
                //2
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        };


        mSecondTv.setOutlineProvider(provider);
        mSecondTv.setClipToOutline(true);
        mSecondTv.setElevation(10);


    }
}
