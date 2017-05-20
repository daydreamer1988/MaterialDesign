package com.minicup.materialdesign.FAB;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by Austin on 2017-05-10.
 */

public class ScrollBehaviorCustom extends FloatingActionButton.Behavior {

    private boolean isAnimation = false;

    public ScrollBehaviorCustom() {
    }

    public ScrollBehaviorCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL||super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && isAnimation == false) {
            animateBottomOut(child);
        } else if (dyConsumed < 0 && isAnimation == false) {
            animateBottomIn(child);
        }
    }

    private void animateBottomIn(FloatingActionButton child) {
        ViewCompat.animate(child).translationY(0).alpha(1).setDuration(400)
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        isAnimation = true;
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        isAnimation = false;
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                })
                .setInterpolator(new OvershootInterpolator())
                .withLayer()
                .start();
    }

    private void animateBottomOut(FloatingActionButton child) {
        ViewCompat.animate(child).translationY(200).alpha(0).setDuration(400)
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        isAnimation = true;

                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        isAnimation = false;

                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                })
                .setInterpolator(new AnticipateInterpolator())
                .withLayer()
                .start();

    }
}
