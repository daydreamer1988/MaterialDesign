package com.minicup.materialdesign.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView itemView;
        public SimpleViewHolder(View inflate) {
            super(inflate);
            itemView = (TextView) inflate;
        }
    }