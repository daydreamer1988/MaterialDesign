package com.minicup.materialdesign.MaterialDesign;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewOutlineProvider;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.minicup.materialdesign.R;

import static com.minicup.materialdesign.R.id.view1;
import static com.minicup.materialdesign.R.id.view2;

public class CircularRevealActivity extends AppCompatActivity {
    private RelativeLayout mActivityCircularReveal;
    private ImageView mView1;
    private ImageView mView2;
    private Animator view1HideAnimator;
    private Animator view2ShowAnimator;
    private Animator view2HideAnimator;
    private Animator view1ShowAnimator;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);


        mActivityCircularReveal = (RelativeLayout) findViewById(R.id.activity_circular_reveal);
        mView1 = (ImageView) findViewById(view1);
        mView2 = (ImageView) findViewById(view2);

        ViewOutlineProvider provider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        };

        mView1.setOutlineProvider(provider);
        mView1.setClipToOutline(true);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void view1Clicked(final View view) {
        if(view2HideAnimator!=null){
            view2HideAnimator.end();
        }
        if(view2ShowAnimator!=null){
            view2ShowAnimator.end();
        }
        int centerX = view.getWidth() / 2;
        int centerY = view.getHeight() / 2;
        view1HideAnimator = ViewAnimationUtils.createCircularReveal(
                view,
                centerX,
                centerY,
                Math.max(centerX, centerY),
                0
        );

        view1HideAnimator.setInterpolator(new AccelerateInterpolator());
        view1HideAnimator.setDuration(500);

        /////////////////////////////////////////////////////////////////////////////////////////////////
        view1HideAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        view1HideAnimator.start();


        int width = view.getWidth();
        int height = view.getHeight();
        view2ShowAnimator = ViewAnimationUtils.createCircularReveal(
                mView2,
                0,
                0,
                0,
                (float) Math.hypot(width, height)
        );

        view2ShowAnimator.setInterpolator(new AccelerateInterpolator());
        view2ShowAnimator.setDuration(500);
        mView2.setVisibility(View.VISIBLE);
        view2ShowAnimator.start();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void view2Clicked(final View view) {

        if(view1HideAnimator!=null){
            view1HideAnimator.end();
        }
        if(view1ShowAnimator!=null){
            view1ShowAnimator.end();
        }

        int width = view.getWidth();
        int height = view.getHeight();
        view2HideAnimator = ViewAnimationUtils.createCircularReveal(
                view,
                0,
                0,
                (float) Math.hypot(width, height),
                0
        );

        view2HideAnimator.setInterpolator(new AccelerateInterpolator());
        view2HideAnimator.setDuration(500);



        /////////////////////////////////////////////////////////////////////////////////////////////////
        view2HideAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);

            }
        });

        mView1.setVisibility(View.VISIBLE);
        view2HideAnimator.start();



        int centerX = mView1.getWidth() / 2;
        int centerY = mView1.getHeight() / 2;
        view1ShowAnimator = ViewAnimationUtils.createCircularReveal(
                mView1,
                centerX,
                centerY,
                0,
                Math.max(centerX, centerY)
        );

        view1ShowAnimator.setInterpolator(new AccelerateInterpolator());
        view1ShowAnimator.setDuration(500);
        view1ShowAnimator.start();

    }
}
