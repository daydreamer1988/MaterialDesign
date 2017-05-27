package com.minicup.materialdesign.BottomSheet;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class BottomSheetBehaviorActivity extends AppCompatActivity implements BottomSheetFragment.OnFragmentInteractionListener {
    private Button mButton;
    private Button mFragmentbutton;
    private RelativeLayout mDesignBottomSheet;
    private TextView mBottomsheetText;
    private BottomSheetBehavior<RelativeLayout> behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_behavior);

        mButton = (Button) findViewById(R.id.button);
        mFragmentbutton = (Button) findViewById(R.id.fragmentbutton);

        mDesignBottomSheet = (RelativeLayout) findViewById(R.id.design_bottom_sheet);
        mBottomsheetText = (TextView) findViewById(R.id.bottomsheet_text);


        behavior = BottomSheetBehavior.from(mDesignBottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState==BottomSheetBehavior.STATE_HIDDEN){
                    mButton.setText("Show Bottom Sheet");
                }else{
                    mButton.setText("Hide Bottom Sheet");
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

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


        mFragmentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment bottomSheetFragment = BottomSheetFragment.newInstance(null, null);
                bottomSheetFragment.show(getSupportFragmentManager(), BottomSheetDialogFragment.class.getSimpleName());
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
