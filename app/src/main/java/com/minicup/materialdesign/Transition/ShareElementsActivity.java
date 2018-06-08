package com.minicup.materialdesign.Transition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class ShareElementsActivity extends AppCompatActivity {
    private ImageView mViewTop;
    private TextView mTvTransitionType;
    private TextView mTvTransitionDescribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_elements);

        mViewTop = (ImageView) findViewById(R.id.view_top);
        mTvTransitionType = (TextView) findViewById(R.id.tv_transition_type);
        mTvTransitionDescribe = (TextView) findViewById(R.id.tv_transition_describe);

        mTvTransitionDescribe.setText("" +
                "1A 获得Transition, TransitionInflater.from(this).inflateTransition(R.transition.fade)\n" +
                "2A 设置退出Transition， getWindow().setExitTransition(fade)\n" +
                "3A 跳转startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context, Pair.create(transitionView, transitionName)))\n" +
                "   跳转(兼容)startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(context, android.support.v4.util.Pair.create(transitionView, transitionName)))\n" +
                "4B AndroidMenifest中设置style\n" +
                "style:\n\n" +
                "<style name=\"TransitionExplode\" parent=\"AppTheme\">\n" +
                "    <!-- 允许使用transitions -->\n" +
                "    <item name=\"android:windowContentTransitions\">true</item>\n" +
                "    <!-- 指定进入和退出transitions -->\n" +
                "    <item name=\"android:windowEnterTransition\">@transition/explode</item>\n" +
                "    <item name=\"android:windowExitTransition\">@transition/explode</item>\n" +
                "    <!--是否覆盖执行，其实可以理解成是否同步执行还是顺序执行-->\n" +
                "    <item name=\"android:windowAllowEnterTransitionOverlap\">true</item>\n" +
                "    <item name=\"android:windowAllowReturnTransitionOverlap\">true</item>\n" +
                "    <!-- 指定shared element transitions -->\n" +
                "    <item name=\"android:windowSharedElementEnterTransition\">\n" +
                "        @transition/change_image_transform</item>\n" +
                "    <item name=\"android:windowSharedElementExitTransition\">\n" +
                "        @transition/change_image_transform</item>\n" +
                "</style>");

    }
}
