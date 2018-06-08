package com.minicup.materialdesign.Transition;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class TransitionDescActivity extends AppCompatActivity {
    private RelativeLayout mActivityTransitionDesc;
    private TextView mDesc;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_desc);

        mActivityTransitionDesc = (RelativeLayout) findViewById(R.id.activity_transition_desc);
        mDesc = (TextView) findViewById(R.id.desc);

        mDesc.setText("" +
                "Transition\n" +
                "       |                            |               |              |               |                   |\n" +
                "       |                            |               |              |               |                   |\n" +
                "   Visibility                TransitionSet    ChangeBounds  ChangeClipBounds  ChangeTransform  ChangeImageTransform\n" +
                "       |\n"+
                "       |\n" +
                "   Explode     Slide   Fade\n" +
                "\n" +
                "\n" +
                "1. 设置允许转换\n" +
                "       代码: getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);\n" +
                "       布局：继承Material主题后，<item name=\"android:windowContentTransitions\">true</item>\n" +
                "2. 获得Transition：\n" +
                "       布局：res/transition-v21下:\n" +
                "               <?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "               <slide xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "                   android:slideEdge=\"bottom\"\n" +
                "                   android:duration=\"500\" />\n" +
                "            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.slide)\n" +
                "       代码：Slide slide = new Slide();\n" +
                "           slide.setDuration(500);\n" +
                "           slide.setSlideEdge(Gravity.TOP);\n" +
                "           slide.setInterpolator(new LinearInterpolator());\n" +
                "           slide.setMode(Visibility.MODE_OUT);\n" +
                "           slide.setEpicenterCallback(new Transition.EpicenterCallback() {\n" +
                "               @Override\n" +
                "               public Rect onGetEpicenter(Transition transition) {\n" +
                "                   return null;\n" +
                "               }\n" +
                "           });\n" +
                "           slide.setMatchOrder();\n" +
                "           slide.setStartDelay(500);\n" +
                "           slide.setPathMotion(new PathMotion() {\n" +
                "               @Override\n" +
                "               public Path getPath(float startX, float startY, float endX, float endY) {\n" +
                "                   return null;\n" +
                "               }\n" +
                "           });\n" +
                "           slide.setPropagation(new TransitionPropagation() {\n" +
                "               @Override\n" +
                "               public long getStartDelay(ViewGroup sceneRoot, Transition transition, TransitionValues startValues, TransitionValues endValues) {\n" +
                "                   return 0;\n" +
                "               }\n" +
                "               @Override\n" +
                "               public void captureValues(TransitionValues transitionValues) {\n" +
                "               }\n" +
                "               @Override\n" +
                "               public String[] getPropagationProperties() {\n" +
                "                   return new String[0];\n" +
                "               }\n" +
                "           });\n" +
                "3. 代码设置：getWindow().setEnterTransition();\n" +
                "           getWindow().setExitTransition();\n" +
                "           getWindow().setReturnTransition();\n" +
                "           getWindow().setReenterTransition();\n" +
                "           getWindow().setAllowEnterTransitionOverlap();\n" +
                "           getWindow().setAllowReturnTransitionOverlap();\n" +
                "           getWindow().setSharedElementEnterTransition();\n" +
                "           getWindow().setSharedElementExitTransition();\n" +
                "           getWindow().setSharedElementReturnTransition();\n" +
                "           getWindow().setSharedElementReenterTransition();\n" +
                "   style设置：<style name=\"TransitionFade\" parent=\"AppTheme\">\n" +
                "                 <item name=\"android:windowContentTransitions\">true</item>\n" +
                "                 <item name=\"android:windowEnterTransition\">@transition/fade</item>\n" +
                "                 <item name=\"android:windowExitTransition\">@transition/fade</item>\n" +
                "                 <item name=\"android:windowReturnTransition\">@transition/fade</item>\n" +
                "                 <item name=\"android:windowReenterTransition\">@transition/fade</item>\n" +
                "                 <item name=\"android:windowAllowEnterTransitionOverlap\">false</item>\n" +
                "                 <item name=\"android:windowAllowReturnTransitionOverlap\">false</item>\n" +
                "                 <item name=\"android:windowSharedElementEnterTransition\">\n" +
                "                     @transition/change_image_transform</item>\n" +
                "                 <item name=\"android:windowSharedElementExitTransition\">\n" +
                "                     @transition/change_image_transform</item>\n" +
                "             </style>\n" +
                "4. startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());\n" +
                "" +
                "");


    }
}
