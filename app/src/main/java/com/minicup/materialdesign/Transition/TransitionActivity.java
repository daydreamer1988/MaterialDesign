package com.minicup.materialdesign.Transition;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class TransitionActivity extends AppCompatActivity {
    private String transition;
    private ImageView imageView;
    private TextView textView;
    private TextView textDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        transition = getIntent().getStringExtra("transition");
        initAnimation();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initAnimation() {
        imageView = (ImageView) findViewById(R.id.img_transition);
        textView = (TextView) findViewById(R.id.tv_transition_type);
        textView.setText(transition);
        textDesc = (TextView) findViewById(R.id.tv_transition_describe);

        switch (transition) {

            case "perfect":
                imageView.setBackgroundResource(R.drawable.circle_red);
                Slide slide1 = new Slide();
                slide1.setSlideEdge(Gravity.TOP);
                Explode explode1 = (Explode) TransitionInflater.from(TransitionActivity.this).inflateTransition(R.transition.explode);
                getWindow().setEnterTransition(slide1);
                getWindow().setReturnTransition(explode1);
                textDesc.setText("" +
                        "步骤：\n" +
                        "1A 获得Transition, TransitionInflater.from(this).inflateTransition(R.transition.slide)\n" +
                        "2A 设置退出Transition， getWindow().setExitTransition(slide)\n" +
                        "3A 跳转startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());\n" +
                        "4B 设置进入Transition, getWindow().setEnterTransition(explode);\n" +
                        "");
                break;
            case "explode":
                imageView.setBackgroundResource(R.drawable.circle_red);
                Explode explode = (Explode) TransitionInflater.from(this).inflateTransition(R.transition.explode);
                getWindow().setEnterTransition(explode);
                textDesc.setText("" +
                        "步骤：\n" +
                        "1A 获得Transition, TransitionInflater.from(this).inflateTransition(R.transition.slide)\n" +
                        "2A 设置退出Transition， getWindow().setExitTransition(slide)\n" +
                        "3A 跳转startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());\n" +
                        "4B 设置进入Transition, getWindow().setEnterTransition(explode);\n" +
                        "");
                break;
            case "slide":
                imageView.setBackgroundResource(R.drawable.circle_purple);
                Slide slide = new Slide(Gravity.TOP);
                slide.setDuration(500);
                slide.excludeTarget(android.R.id.navigationBarBackground,true);
                getWindow().setEnterTransition(slide);
                textDesc.setText("" +
                        "步骤：\n" +
                        "1A 获得Transition, TransitionInflater.from(this).inflateTransition(R.transition.slide)\n" +
                        "2A 设置退出Transition， getWindow().setExitTransition(slide)\n" +
                        "3A 跳转startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());\n" +
                        "4B 代码获得Transition， Slide slide = new Slide(Gravity.TOP);slide.setDuration(500);\n"+
                        "5B 设置进入Transition, getWindow().setEnterTransition(slide);\n" +
                        "");
                break;
            case "fade":
                imageView.setBackgroundResource(R.drawable.circle_blue);
                Fade fade = new Fade();
                fade.setDuration(500);
                getWindow().setEnterTransition(fade);

                textDesc.setText("" +
                        "步骤：\n" +
                        "1A 获得Transition, TransitionInflater.from(this).inflateTransition(R.transition.fade)\n" +
                        "2A 设置退出Transition， getWindow().setExitTransition(fade)\n" +
                        "3A 跳转startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());\n" +
                        "4B 代码获得Transition， Fade fade = new Fade();fade.setDuration(500);\n"+
                        "5B 设置进入Transition, getWindow().setEnterTransition(fade);\n" +
                        "");

                break;
        }
    }
}
