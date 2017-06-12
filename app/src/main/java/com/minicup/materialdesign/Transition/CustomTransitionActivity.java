package com.minicup.materialdesign.Transition;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class CustomTransitionActivity extends AppCompatActivity {
    private RelativeLayout mContainer;
    private TextView mTextView;
    private boolean isScene1 = true;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_transition);
        mContainer = (RelativeLayout) findViewById(R.id.container);
        mTextView = (TextView) findViewById(R.id.textView);


        final ChangeColor changeColor = new ChangeColor();
        final Scene scene1 = new Scene(mContainer, findViewById(R.id.textView));
        final Scene scene2 = Scene.getSceneForLayout(mContainer, R.layout.activity_custom_transition2, this);

        mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isScene1) {
                    TransitionManager.go(scene2, changeColor);

                }else{
                    TransitionManager.go(scene1, changeColor);
                }

                isScene1 = !isScene1;
            }
        });

    }
}
