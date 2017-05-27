package com.minicup.materialdesign.RecyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by Austin on 2017-05-19.
 */

public class GridCustomItemDecoration extends RecyclerView.ItemDecoration {

    private final Context mContext;
    private final int mOrientation;
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private final Drawable mDivider;

    public GridCustomItemDecoration(Context context, int orientation) {
        this.mContext = context;
        this.mOrientation = orientation;
        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c, parent, state);
        drawVertical(c, parent, state);
    }

    /**
     * 画横线
     *
     * @param c
     * @param parent
     * @param state
     */
    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft();
            int right = 0;
            if (layoutManager instanceof GridLayoutManager) {
                if (((GridLayoutManager) layoutManager).getOrientation() == GridLayoutManager.VERTICAL) {
                    right = child.getRight() + mDivider.getIntrinsicWidth();
                } else if (((GridLayoutManager) layoutManager).getOrientation() == GridLayoutManager.HORIZONTAL) {
                    right = child.getRight();
                }
            }else if(layoutManager instanceof StaggeredGridLayoutManager){
                if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == GridLayoutManager.VERTICAL) {
                    right = child.getRight() + mDivider.getIntrinsicWidth();
                } else if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == GridLayoutManager.HORIZONTAL) {
                    right = child.getRight();
                }
            }
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);

        }

    }

    /**
     * 画竖线
     *
     * @param c
     * @param parent
     * @param state
     */
    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();
            int top = child.getTop() + layoutParams.topMargin;
            int bottom = 0;
            if (layoutManager instanceof GridLayoutManager) {
                if (((GridLayoutManager) layoutManager).getOrientation() == GridLayoutManager.VERTICAL) {
                    bottom = top + child.getHeight();
                } else if (((GridLayoutManager) layoutManager).getOrientation() == GridLayoutManager.HORIZONTAL) {
                    bottom = top + child.getHeight() + mDivider.getIntrinsicHeight();
                }
            }else if(layoutManager instanceof StaggeredGridLayoutManager){
                if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == GridLayoutManager.VERTICAL) {
                    bottom = top + child.getHeight();
                } else if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == GridLayoutManager.HORIZONTAL) {
                    bottom = top + child.getHeight() + mDivider.getIntrinsicHeight();
                }
            }
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int spanCount = getSpanCount(parent);
        int itemCount = parent.getAdapter().getItemCount();
        boolean lastRow = isLastRow(parent, parent.getChildAdapterPosition(view), spanCount, itemCount);
        boolean lastColume = isLastColume(parent, parent.getChildAdapterPosition(view), spanCount, itemCount);

        if (lastColume && lastRow) {
            outRect.set(0, 0, 0, 0);
            return;
        }
        if(lastRow){
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            return;
        }
        if (lastColume) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            return;
        }
        outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
    }

    private boolean isLastColume(RecyclerView parent, int childAdapterPosition, int spanCount, int itemCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if(((GridLayoutManager) layoutManager).getOrientation()==GridLayoutManager.VERTICAL) {
                return childAdapterPosition % spanCount == spanCount - 1;
            }else if(((GridLayoutManager) layoutManager).getOrientation()==GridLayoutManager.HORIZONTAL){
                if(itemCount%spanCount==0){
                    return childAdapterPosition >= itemCount - spanCount;
                }else {
                    return childAdapterPosition >= itemCount - (itemCount % spanCount);
                }
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            if(((StaggeredGridLayoutManager) layoutManager).getOrientation()==StaggeredGridLayoutManager.VERTICAL){
                return (childAdapterPosition % spanCount == spanCount - 1);
            } else if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == StaggeredGridLayoutManager.HORIZONTAL) {
                if(itemCount%spanCount==0){
                    return childAdapterPosition >= itemCount - spanCount;
                }else {
                    return childAdapterPosition >= itemCount - (itemCount % spanCount);
                }
            }
        }
        return false;
    }

    private boolean isLastRow(RecyclerView parent, int childAdapterPosition, int spanCount, int itemCount) {

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {

            if(((GridLayoutManager) layoutManager).getOrientation()==GridLayoutManager.VERTICAL) {
                if (itemCount % spanCount == 0) {//最后一行满了
                    return childAdapterPosition > itemCount - spanCount - 1;
                } else {
                    return childAdapterPosition > itemCount - itemCount % spanCount - 1;
                }
            }else if(((GridLayoutManager) layoutManager).getOrientation()==GridLayoutManager.HORIZONTAL){
                return childAdapterPosition % spanCount == spanCount - 1;
            }
        }else if(layoutManager instanceof StaggeredGridLayoutManager){
            if(((StaggeredGridLayoutManager) layoutManager).getOrientation()==StaggeredGridLayoutManager.VERTICAL){
                if(itemCount%spanCount==0){
                    return childAdapterPosition >= itemCount - spanCount;
                }else{
                    return childAdapterPosition >= itemCount - itemCount % spanCount;
                }
            } else if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == StaggeredGridLayoutManager.HORIZONTAL) {
                return childAdapterPosition % spanCount == spanCount - 1;
            }
        }

        return false;
    }

    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }

        if(layoutManager instanceof StaggeredGridLayoutManager){
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }
}
