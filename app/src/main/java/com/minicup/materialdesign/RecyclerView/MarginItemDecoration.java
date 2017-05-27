package com.minicup.materialdesign.RecyclerView;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Austin on 2017-05-20.
 */

public class MarginItemDecoration extends RecyclerView.ItemDecoration {

    private int margin;

    public MarginItemDecoration(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(margin, margin, margin, margin);
    }
}
