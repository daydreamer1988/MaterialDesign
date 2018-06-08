package com.minicup.materialdesign.MaterialDesign;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Austin on 2017-06-05.
 */

public class CustomView extends LinearLayout {

    private final ViewDragHelper dragHelper;
    private View normalView;
    private View backView;
    private View edgeView;
    private float backViewOriginX;
    private float backViewOriginY;
    private View button;

    public CustomView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                int boundLeft = getPaddingLeft();
                int boundRight = getWidth() - getPaddingRight() - child.getWidth();
                int newLeft = Math.min(Math.max(left, boundLeft), boundRight);
                return newLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                int boundTop = getPaddingTop();
                int boundBottom = getHeight() - getPaddingBottom() - child.getHeight();
                int newTop = Math.min(boundBottom, Math.max(boundTop, top));
                return newTop;
            }


            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (releasedChild==backView) {
                    dragHelper.settleCapturedViewAt((int)backViewOriginX, (int)backViewOriginY);
                    invalidate();
                }
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                if (capturedChild == normalView) {
                    Toast.makeText(context, "normal view captured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                if(changedView==normalView){
                    ((TextView) normalView).setText("left:" + left + " top:" + top + "\ndx:" + dx + " dy:" + dy);
                }
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                    dragHelper.captureChildView(edgeView,pointerId);

            }


            //以下两个方法在子View设置了点击事件后，手动无效的情况下必须要重写的方法
            @Override
            public int getViewHorizontalDragRange(View child) {
                //只要大于0就可以移动
                return getMeasuredWidth()-child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }
        });

        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //在onLayout之前
        Log.e("CustomView", "onFinishInflate()");
        normalView = getChildAt(0);
        backView = getChildAt(1);
        edgeView = getChildAt(2);
        button = getChildAt(3);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("CustomView", "OnLayout");
        backViewOriginX = backView.getX();
        backViewOriginY = backView.getY();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}
