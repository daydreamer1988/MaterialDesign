package com.minicup.materialdesign.RecyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Austin on 2017-05-19.
 */

public class LinearCustomItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private final Context mContext;
    private final int mOritation;
    private final Drawable mDivider;

    public LinearCustomItemDecoration(Context context, int oritation) {
        this.mContext = context;
        this.mOritation = oritation;
        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        if(mOritation==LinearLayoutManager.HORIZONTAL){
            drawHorizontal(c, parent, state);
        }else if(mOritation==LinearLayoutManager.VERTICAL){
            drawVertical(c, parent, state);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + lp.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        //是否显示最后一条数据底部的Divider
        if(parent.getChildAdapterPosition(view)== parent.getAdapter().getItemCount()-1){
            return;
        }
        if(mOritation== LinearLayoutManager.HORIZONTAL){
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }else if(mOritation==LinearLayoutManager.VERTICAL){
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());

        }

    }
}
