package com.minicup.materialdesign.Behavior;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class BottomSheetApplay1Activity extends AppCompatActivity {
    private Button mButton;
    private Button mFragmentbutton;
    private RelativeLayout mDesignBottomSheet;
    private TextView mBottomsheetText;
    private BottomSheetBehavior<RelativeLayout> behavior;
    private TextView mTopView;
    private float originY;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_apply1);

        mButton = (Button) findViewById(R.id.button);
        mFragmentbutton = (Button) findViewById(R.id.fragmentbutton);
        mTopView = (TextView) findViewById(R.id.topView);

        mDesignBottomSheet = (RelativeLayout) findViewById(R.id.design_bottom_sheet);
        mBottomsheetText = (TextView) findViewById(R.id.bottomsheet_text);


        behavior = BottomSheetBehavior.from(mDesignBottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState==BottomSheetBehavior.STATE_HIDDEN){
                    mButton.setText("Show Bottom Sheet");
                }else if(newState == BottomSheetBehavior.STATE_SETTLING){
                    if(mTopView.getBottom()>0){
//                        ViewCompat.animate(mTopView).translationY(0).start();
                    }
                }else{
                    mButton.setText("Hide Bottom Sheet");
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.e("TAG", slideOffset + "");
                float v = (slideOffset-0.75f)*400;
                if (slideOffset > 0.75f) {
                    mTopView.setY(originY + v);
                }
            }
        });


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(behavior.getState()==BottomSheetBehavior.STATE_HIDDEN){
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else{
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics d = new DisplayMetrics();
        defaultDisplay.getMetrics(d);
        screenHeight = d.heightPixels;
        originY = mTopView.getY();
    }
}
