package com.minicup.materialdesign.Transition;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minicup.materialdesign.R;


public class SimpleTransition2Activity extends AppCompatActivity {
    private LinearLayout mContainer;
    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    private Transition transition;
    private TransitionManager mCustomManager;
    private Transition mCustomTransition;
    private boolean circle4Expanded = true;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_simple_transition2);
        mContainer = (LinearLayout) findViewById(R.id.container);
        scene1 = Scene.getSceneForLayout(mContainer, R.layout.scene1, this);
        scene2 = Scene.getSceneForLayout(mContainer, R.layout.scene2, this);
        scene3 = Scene.getSceneForLayout(mContainer, R.layout.scene3, this);

        //TransitionManager.go(Scene)会默认使用AutoTransition, 查看源码得知会顺序播放Fadeout, changebounds, 和fadein动画
        transition = new ChangeBounds();
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

        mCustomManager = TransitionInflater.from(this).inflateTransitionManager(R.transition.transition_manager, mContainer);

        mCustomTransition = TransitionInflater.from(this).inflateTransition(R.transition.change_bounds);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void changeScene(View view) {

        switch (view.getId()) {
            case R.id.scene1:
                TransitionManager.go(scene1, transition);
                break;
            case R.id.scene2:
                TransitionManager.go(scene2, transition);
                Snackbar.make(view, "普通的TransitionManager.go()", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.scene3:
                TransitionManager.go(scene3, mCustomTransition);
                TextView info = (TextView) findViewById(R.id.info);

                info.setText("scene3 = Scene.getSceneForLayout(mContainer, R.layout.scene3, this);\n\n" +
                        "mCustomTransition = TransitionInflater.from(this).inflateTransition(R.transition.change_bounds);\n\n" +
                        "TransitionManager.go(scene3, mCustomTransition)");
                break;
            case R.id.scene4:
                //mCustomManager.go(scene3);
                mCustomManager.transitionTo(scene3);
                TextView info1 = (TextView) findViewById(R.id.info);

                info1.setText("mCustomManager = TransitionInflater.from(this).inflateTransitionManager(R.transition.transition_manager, mContainer);\n\n" +
                        "mCustomManager.go(scene3);\n\n" +
                        "貌似没有效果，查看源码也只是new了一个TransitionManager, 所以感觉xml中设置的transition没起什么作用\n\n" +
                        "<transitionManager xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                        "    <transition android:toScene=\"@layout/scene3\"\n" +
                        "        android:transition=\"@transition/change_bounds\">\n" +
                        "    </transition>\n" +
                        "</transitionManager>\n\n" +
                        "" +
                        "但是如果写成mCustomManager.transitionTo(scene3);便正常");
                break;
            case R.id.scene5:
                TransitionManager manager = new TransitionManager();
                manager.setTransition(scene3, mCustomTransition);
                manager.transitionTo(scene3);
                TextView info2 = (TextView) findViewById(R.id.info);
                info2.setText("scene3 = Scene.getSceneForLayout(mContainer, R.layout.scene3, this);\n\n" +
                        "mCustomTransition = TransitionInflater.from(this).inflateTransition(R.transition.change_bounds);\n\n" +
                        "TransitionManager manager = new TransitionManager();\n\n" +
                        "manager.setTransition(scene3, mCustomTransition);\n\n" +
                        "manager.transitionTo(scene3);");
                break;

            case R.id.scene6:
                //在下一帧进行动画，可以继续设置属性等
                TransitionManager.beginDelayedTransition(mContainer);
                //可以继续设置View的属性，不会影响动画
                TextView circle4 = (TextView) mContainer.findViewById(R.id.share4);
                ViewGroup.LayoutParams params = circle4.getLayoutParams();
                if (circle4Expanded) {
                    circle4Expanded = false;
                    params.width = dip2px(this, 51);
                    params.height = dip2px(this, 50);
                } else {
                    circle4Expanded = true;
                    params.width = dip2px(this, 100);
                    params.height = dip2px(this, 100);
                }
                circle4.setLayoutParams(params);


                TextView info3 = (TextView) findViewById(R.id.info);
                if(info3!=null)
                info3.setText("" +
                        "//在下一帧进行动画，可以继续设置属性等\n" +
                        "TransitionManager.beginDelayedTransition(mContainer);\n\n" +
                        "//可以继续设置View的属性，不会影响动画\n" +
                        "TextView circle4 = (TextView) mContainer.findViewById(R.id.share4);\n" +
                        "ViewGroup.LayoutParams params = circle4.getLayoutParams();\n" +
                        "if (params.width > dip2px(this, 51)) {\n" +
                        "    params.width = dip2px(this, 51);\n" +
                        "    params.height = dip2px(this, 50);\n" +
                        "} else {\n" +
                        "    params.width = dip2px(this, 100);\n" +
                        "    params.height = dip2px(this, 100);\n" +
                        "}\n" +
                        "circle4.setLayoutParams(params);");
                break;
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
