package com.minicup.materialdesign.FAB;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.minicup.materialdesign.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FABActivity5 extends AppCompatActivity {
    private static final int HIDE = 1;
    private static final int SHOWING = 2;

    private ListView mListView;
    private FloatingActionButton mFab;
    private Animation scaleUpAnimation;
    private Animation scaleDownAnimation;
    private int previousVisibleItem;
    private Animation hideToBottomAnimation;
    private Animation showFromBottomAnimation;
    private boolean isAnimation = false;
    private int mFabStatus = HIDE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab5);

        mListView = (ListView) findViewById(R.id.listView);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        Locale[] locale = Locale.getAvailableLocales();
        List<String> language = new ArrayList<>();

        for (Locale locale1 : locale) {
            language.add(locale1.getDisplayName());

        }

        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, language));


        scaleUpAnimation = AnimationUtils.loadAnimation(FABActivity5.this, R.anim.fab_scale_up);
        scaleDownAnimation = AnimationUtils.loadAnimation(FABActivity5.this, R.anim.fab_scale_down);
        hideToBottomAnimation = AnimationUtils.loadAnimation(FABActivity5.this, R.anim.hide_to_bottom);
        hideToBottomAnimation.setFillAfter(true);
        hideToBottomAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimation = false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        showFromBottomAnimation = AnimationUtils.loadAnimation(FABActivity5.this, R.anim.show_from_bottom);
        showFromBottomAnimation.setFillAfter(true);
        showFromBottomAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimation = true;

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimation = false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mFab.hide();
        mFabStatus = HIDE;
        mListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFab.show();
                mFabStatus = SHOWING;
                mFab.startAnimation(scaleUpAnimation);
            }
        }, 500);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                Log.e("TAG", "firstVisibleItem:" + firstVisibleItem + "states:" + mFabStatus);

                if (firstVisibleItem > previousVisibleItem && !isAnimation && mFabStatus == SHOWING) {
                    mFabStatus = HIDE;

                    mFab.startAnimation(hideToBottomAnimation);
//                    Log.e("TAG", "hide");

                } else if (firstVisibleItem < previousVisibleItem && !isAnimation && mFabStatus == HIDE) {

                    mFabStatus = SHOWING;

                    mFab.startAnimation(showFromBottomAnimation);
//                    Log.e("TAG", "show");

                }
                previousVisibleItem = firstVisibleItem;
            }
        });

    }
}
